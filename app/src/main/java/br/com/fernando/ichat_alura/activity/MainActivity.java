package br.com.fernando.ichat_alura.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import br.com.fernando.ichat_alura.BuildConfig;
import br.com.fernando.ichat_alura.R;
import br.com.fernando.ichat_alura.adapter.MensagemAdapter;
import br.com.fernando.ichat_alura.app.ChatApplication;
import br.com.fernando.ichat_alura.callbacks.EnviarMensagemCallback;
import br.com.fernando.ichat_alura.callbacks.OuvirMensagensCallback;
import br.com.fernando.ichat_alura.component.ChatComponent;
import br.com.fernando.ichat_alura.modelo.Mensagem;
import br.com.fernando.ichat_alura.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Integer idDoCliente = 1;

    private List<Mensagem> mensagens;

    @BindView(R.id.et_texto)
    EditText etMensagem;
    @BindView(R.id.btn_enviar)
    Button btnEnviar;
    @BindView(R.id.lv_mensagens)
    ListView listaDeMensagens;
    @BindView(R.id.iv_avatar_usuario)
    ImageView ivAvatarUsuario;

    @Inject
    ChatService chatService;

    @Inject
    Picasso picasso;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        picasso.with(this).load("http://api.adorable.io/avatars/285/" + idDoCliente + "png").into(ivAvatarUsuario);

        component = ((ChatApplication) getApplication()).getComponent();
        component.inject(this);

        mensagens = new ArrayList<>();

        MensagemAdapter mensagemAdapter = new MensagemAdapter(mensagens, this, 1);
        listaDeMensagens.setAdapter(mensagemAdapter);

        ouvirMensagem();
    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem(){
        chatService.enviar(new Mensagem(idDoCliente, etMensagem.getText().toString())).enqueue(new EnviarMensagemCallback());
    }

    public void colocaNaLista(Mensagem mensagem) {

        if(mensagem != null && !TextUtils.isEmpty(mensagem.getTexto())) {
            mensagens.add(mensagem);

            MensagemAdapter adapter = new MensagemAdapter(mensagens,this, idDoCliente);
            listaDeMensagens.setAdapter(adapter);

        }

        ouvirMensagem();

    }

    public void ouvirMensagem() {
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallback(this));
    }

}


