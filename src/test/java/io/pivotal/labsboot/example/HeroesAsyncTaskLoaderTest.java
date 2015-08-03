package io.pivotal.labsboot.example;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import io.pivotal.labsboot.BuildConfig;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class HeroesAsyncTaskLoaderTest {

    @Test
    public void testLoadInBackground() throws IOException, InterruptedException {
        MockWebServer server = new MockWebServer();
        MockResponse resp = new MockResponse();
        String json = "{" +
            "   \"heroes\" : [" +
            "      {" +
            "         \"name\" : \"Air-Walker (Gabriel Lan)\"," +
            "         \"id\" : 1011136," +
            "         \"detailUrl\" : \"http://marvelousapi.cfapps.io/heroes/1011136\"" +
            "      }," +
            "      {" +
            "         \"id\" : 1011176," +
            "         \"name\" : \"Ajak\"," +
            "         \"detailUrl\" : \"http://marvelousapi.cfapps.io/heroes/1011176\"" +
            "      }," +
            "      {" +
            "         \"detailUrl\" : \"http://marvelousapi.cfapps.io/heroes/1010870\"," +
            "         \"name\" : \"Ajaxis\"," +
            "         \"id\" : 1010870" +
            "      }" +
            "   ]," +
            "   \"links\" : {" +
            "      \"nextPage\" : \"http://marvelousapi.cfapps.io/heroes?page=2\"," +
            "      \"self\" : \"http://marvelousapi.cfapps.io/heroes?page=1\"" +
            "   }" +
            "}";
        resp.addHeader("Content-Type", "application/json;charset=UTF-8")
            .setBody(json);
        server.enqueue(resp);

        server.start();

        URL url = server.getUrl("");

        HeroesAsyncTaskLoader loader = new HeroesAsyncTaskLoader(RuntimeEnvironment.application);
        loader.setUrl(url);
        List<Hero> heroes = loader.loadInBackground();

        RecordedRequest recordedRequest = server.takeRequest();
        Assert.assertEquals("application/json; charset=utf-8", recordedRequest.getHeader("Accept"));
        Assert.assertEquals("/heroes", recordedRequest.getPath());

        Assert.assertEquals("Air-Walker (Gabriel Lan)", heroes.get(0).getName());
        Assert.assertEquals("Ajak", heroes.get(1).getName());
        Assert.assertEquals("Ajaxis", heroes.get(2).getName());

        Assert.assertEquals("http://marvelousapi.cfapps.io/heroes/1011136", heroes.get(0).getDetailUrl());
        Assert.assertEquals("http://marvelousapi.cfapps.io/heroes/1011176", heroes.get(1).getDetailUrl());
        Assert.assertEquals("http://marvelousapi.cfapps.io/heroes/1010870", heroes.get(2).getDetailUrl());
        server.shutdown();
    }

}