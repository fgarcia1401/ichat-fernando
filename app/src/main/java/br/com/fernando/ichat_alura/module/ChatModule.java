package br.com.fernando.ichat_alura.module;

import java.util.concurrent.TimeUnit;

import br.com.fernando.ichat_alura.BuildConfig;
import br.com.fernando.ichat_alura.service.ChatService;
import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tqiuser on 24/01/18.
 */

@Module
public class ChatModule {

    @Provides
    public ChatService getChatService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_CHAT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);

        return chatService;
    }

    private okhttp3.OkHttpClient getOkHttpClient() {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return builder
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }


}
