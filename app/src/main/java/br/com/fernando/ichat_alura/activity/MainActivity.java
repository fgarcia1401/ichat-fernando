package br.com.fernando.ichat_alura.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fernando.ichat_alura.R;
import br.com.fernando.ichat_alura.adapter.MensagemAdapter;
import br.com.fernando.ichat_alura.modelo.Mensagem;
import br.com.fernando.ichat_alura.service.ChatService;

public class MainActivity extends AppCompatActivity {

    private Integer idDoCliente = 1;
    private EditText etMensagem;
    private Button btnEnviar;

    private ListView listaDeMensagens;
    private List<Mensagem> mensagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaDeMensagens = findViewById(R.id.lv_mensagens);
        etMensagem = findViewById(R.id.et_texto);
        btnEnviar = findViewById(R.id.btn_enviar);

        mensagens = new ArrayList<>();

        MensagemAdapter mensagemAdapter = new MensagemAdapter(mensagens, this, 1);
        listaDeMensagens.setAdapter(mensagemAdapter);

        final ChatService chatService = new ChatService(this);
        chatService.ouvirMensagens();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChatService(MainActivity.this).enviar(new Mensagem(idDoCliente, etMensagem.getText().toString()));
            }
        });


    }

    public void colocaNaLista(Mensagem mensagem) {
        mensagens.add(mensagem);

        MensagemAdapter adapter = new MensagemAdapter(mensagens,this, idDoCliente);
        listaDeMensagens.setAdapter(adapter);
    }
}
