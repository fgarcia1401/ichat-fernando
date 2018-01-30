package br.com.fernando.ichat_alura.callbacks;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import br.com.fernando.ichat_alura.activity.MainActivity;
import br.com.fernando.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tqiuser on 23/01/18.
 */

public class OuvirMensagensCallback implements Callback<Mensagem> {

    private Context context;

    public OuvirMensagensCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if(response.isSuccessful() && response.body() != null) {
            Mensagem mensagem = response.body();

            Intent intent =  new Intent("nova_mensagem");
            intent.putExtra("mensagem", mensagem);

            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
            localBroadcastManager.sendBroadcast(intent);
        }

    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable throwable) {

    }
}
