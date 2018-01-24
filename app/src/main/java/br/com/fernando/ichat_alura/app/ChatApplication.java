package br.com.fernando.ichat_alura.app;

import android.app.Application;

import br.com.fernando.ichat_alura.component.ChatComponent;
import br.com.fernando.ichat_alura.component.DaggerChatComponent;

/**
 * Created by tqiuser on 24/01/18.
 */

public class ChatApplication extends Application {

    private ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder().build();
    }

    public ChatComponent getComponent() {
        return component;
    }
}
