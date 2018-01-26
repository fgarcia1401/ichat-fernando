package br.com.fernando.ichat_alura.callbacks;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by tqiuser on 23/01/18.
 */

public class EnviarMensagemCallback implements retrofit2.Callback<Void> {
    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        Log.i(EnviarMensagemCallback.class.getName(), "Sucesso ao enviar mensagem");

    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

        Log.e(EnviarMensagemCallback.class.getName(), "Erro ao enviar mensagem");

    }
}
