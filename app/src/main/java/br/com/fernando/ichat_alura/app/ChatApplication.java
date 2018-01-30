package br.com.fernando.ichat_alura.app;

import android.app.Application;

import br.com.fernando.ichat_alura.component.ChatComponent;
import br.com.fernando.ichat_alura.component.DaggerChatComponent;
import br.com.fernando.ichat_alura.module.ChatModule;

/**
 * Created by fernando on 25/01/18.
 */

public class ChatApplication extends Application {

    private ChatComponent chatComponent;

    public ChatComponent getComponent() {
        return chatComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        chatComponent = DaggerChatComponent.builder()
                .chatModule(new ChatModule(this))
                .build();
    }
}
