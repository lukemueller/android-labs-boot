package io.pivotal.labsboot.example;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class HeroesAsyncTaskLoader extends AsyncTaskLoader<List<Hero>> {
    private OkHttpClient client;
    private URL url;

    public HeroesAsyncTaskLoader(Context context) {
        super(context);

        client = new OkHttpClient();
    }

    @Override
    public List<Hero> loadInBackground() {
        Request request = new Request.Builder()
            .addHeader("Accept", "application/json; charset=utf-8")
            .url(url + "/heroes")
            .get()
            .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String jsonString = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(jsonString, HeroesApiResponse.class).getHeroes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    private static class HeroesApiResponse {
        private List<Hero> heroes;

        public List<Hero> getHeroes() {
            return heroes;
        }

        public void setHeroes(List<Hero> heroes) {
            this.heroes = heroes;
        }
    }
}
