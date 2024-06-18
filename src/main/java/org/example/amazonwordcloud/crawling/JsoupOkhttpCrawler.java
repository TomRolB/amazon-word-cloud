package org.example.amazonwordcloud.crawling;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JsoupOkhttpCrawler implements Crawler {

    @Override
    public String getProductDescription(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();


        Document doc;

        try {
            doc = Jsoup.parse(client.newCall(request).execute().body().string());
        } catch (IOException e) {
            System.out.println("Could not connect to " + url);
            System.out.println(e.getMessage());
            return "";
        }

        System.out.println("Document:");
        System.out.println(doc);

        Element description = doc.selectFirst("#productDescription");
        if (description == null) return "";

        return description.text();
    }
}
