package br.com.fernando.ichat_alura.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.fernando.ichat_alura.R;
import br.com.fernando.ichat_alura.adapter.MensagemAdapter;
import br.com.fernando.ichat_alura.callback.EnviarMensagemCallback;
import br.com.fernando.ichat_alura.callback.OuvirMensagensCallback;
import br.com.fernando.ichat_alura.component.ChatComponent;
import br.com.fernando.ichat_alura.modelo.Mensagem;
import br.com.fernando.ichat_alura.app.ChatApplication;
import br.com.fernando.ichat_alura.service.ChatService;
import retrofit2.Call;


public class MainActivity extends AppCompatActivity {

    private Integer idDoCliente = 1;
    private EditText etMensagem;
    private Button btnEnviar;

    private ListView listaDeMensagens;
    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component = ((ChatApplication) getApplication()).getComponent();
        component.inject(this);

        listaDeMensagens = findViewById(R.id.lv_mensagens);
        etMensagem = findViewById(R.id.et_texto);
        btnEnviar = findViewById(R.id.btn_enviar);

        mensagens = new ArrayList<>();

        MensagemAdapter mensagemAdapter = new MensagemAdapter(mensagens, this, 1);
        listaDeMensagens.setAdapter(mensagemAdapter);

        ouvirMensagem();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatService.enviar(new Mensagem(idDoCliente, etMensagem.getText().toString())).enqueue(new EnviarMensagemCallback());
            }
        });

    }

    public void colocaNaLista(Mensagem mensagem) {

        if(mensagem != null) {
            mensagens.add(mensagem);

            MensagemAdapter adapter = new MensagemAdapter(mensagens,this, idDoCliente);
            listaDeMensagens.setAdapter(adapter);

            ouvirMensagem();
        }


    }

    public void ouvirMensagem() {
        Call<Mensagem> call = chatService.ouvirMensagens();
        call.enqueue(new OuvirMensagensCallback(this));
    }

}


