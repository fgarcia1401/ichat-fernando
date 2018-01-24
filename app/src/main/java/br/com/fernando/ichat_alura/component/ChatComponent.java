package br.com.fernando.ichat_alura.component;

import br.com.fernando.ichat_alura.activity.MainActivity;
import br.com.fernando.ichat_alura.module.ChatModule;
import dagger.Component;

/**
 * Created by tqiuser on 24/01/18.
 */

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity mainActivity);

}
