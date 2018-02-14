package br.com.fernando.ichat_alura.callbacks;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import br.com.fernando.ichat_alura.event.FailureEvent;
import br.com.fernando.ichat_alura.event.MensagemEvent;
import br.com.fernando.ichat_alura.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tqiuser on 23/01/18.
 */

public class OuvirMensagensCallback implements Callback<Mensagem> {

    private Context context;

    private EventBus eventBus;

    public OuvirMensagensCallback(EventBus eventBus, Context context) {
        this.eventBus = eventBus;
        this.context = context;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if(response.isSuccessful() && response.body() != null) {
            Mensagem mensagem = response.body();

            eventBus.post(new MensagemEvent(mensagem));

        }

    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable throwable) {
        eventBus.post(new FailureEvent());
    }
}
