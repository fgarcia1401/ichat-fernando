package br.com.fernando.ichat_alura.callback;

import android.util.Log;

import br.com.fernando.ichat_alura.activity.MainActivity;
import br.com.fernando.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tqiuser on 23/01/18.
 */

public class OuvirMensagensCallback implements Callback<Mensagem> {

    private MainActivity activity;

    public OuvirMensagensCallback(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if(response.isSuccessful() && response.body() != null) {
            Mensagem mensagem = response.body();
            activity.colocaNaLista(mensagem);
        }

    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable throwable) {
        activity.ouvirMensagem();
    }
}
