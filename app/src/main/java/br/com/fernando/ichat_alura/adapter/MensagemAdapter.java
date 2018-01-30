package br.com.fernando.ichat_alura.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.fernando.ichat_alura.R;
import br.com.fernando.ichat_alura.modelo.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fernando on 04/01/18.
 */

public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Activity activity;
    private Integer idDoCliente;

    @BindView(R.id.tv_texto)
    TextView texto;
    @BindView(R.id.iv_avatar_mensagem)
    ImageView avatar;

    @Inject
    Picasso picasso;

    public MensagemAdapter(List<Mensagem> mensagens, Activity activity,Integer idDoCliente) {
        this.mensagens = mensagens;
        this.activity = activity;
        this.idDoCliente = idDoCliente;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int i) {
        return mensagens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem, viewGroup, false);

        ButterKnife.bind(this, linha);

        Mensagem mensagem = getItem(i);
        texto.setText(mensagem.getTexto());

        picasso.with(activity).load("http://api.adorable.io/avatars/285/" + mensagem.getId() + "png").into(avatar);

        if(!idDoCliente.equals(mensagem.getId())) {
            linha.setBackgroundColor(Color.CYAN);
        }

        return linha;
    }
}
