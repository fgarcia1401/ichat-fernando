package br.com.fernando.ichat_alura.event;

import br.com.fernando.ichat_alura.modelo.Mensagem;

/**
 * Created by tqiuser on 01/02/18.
 */

public class MensagemEvent {

    private  Mensagem mensagem;

    public MensagemEvent() {
    }

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }
}
