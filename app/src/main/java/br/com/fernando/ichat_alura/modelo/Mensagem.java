package br.com.fernando.ichat_alura.modelo;

/**
 * Created by fernando on 04/01/18.
 */

public class Mensagem {

    private String texto;

    private Integer id;

    public Mensagem(Integer id,String texto) {
        this.texto = texto;
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public Integer getId() {
        return id;
    }
}
