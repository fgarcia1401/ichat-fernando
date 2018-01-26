package br.com.fernando.ichat_alura.component;

import br.com.fernando.ichat_alura.module.ChatModule;
import br.com.fernando.ichat_alura.activity.MainActivity;
import dagger.Component;

/**
 * Created by fernando on 25/01/18.
 */

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity mainActivity);

}
