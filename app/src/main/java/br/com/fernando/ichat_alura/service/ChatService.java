
package br.com.fernando.ichat_alura.service;


import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.com.fernando.ichat_alura.activity.MainActivity;
import br.com.fernando.ichat_alura.modelo.Mensagem;

/**
 * Created by fernando on 04/01/18.
 */

public class ChatService {

    private MainActivity activity;

    public ChatService(MainActivity activity) {
        this.activity = activity;
    }

    public void enviar(final Mensagem mensagem){

        new Thread(new Runnable() {
            @Override
            public void run() {

                String texto = mensagem.getTexto();

                try {
                    URL url = new URL("http://192.168.2.48:8080/polling");

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-type", "application/json");

                    JSONStringer json = new JSONStringer().object()
                            .key("text").value(texto)
                            .key("id").value(mensagem.getId()).endObject();

                    OutputStream saida = httpURLConnection.getOutputStream();

                    PrintStream ps = new PrintStream(saida);

                    ps.println(json.toString());

                    httpURLConnection.connect();
                    httpURLConnection.getInputStream();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    public void ouvirMensagens() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://192.168.2.48:8080/polling");

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Accept", "application/json");

                    httpURLConnection.connect();
                    Scanner scanner = new Scanner(httpURLConnection.getInputStream());

                    StringBuilder builder = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        builder.append(scanner.nextLine());
                    }

                    String json = builder.toString();
                    JSONObject jsonObject = new JSONObject(json);

                    final Mensagem mensagem = new Mensagem(jsonObject.getInt("id"), jsonObject.getString("test"));

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.colocaNaLista(mensagem);
                        }
                    });


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


}
