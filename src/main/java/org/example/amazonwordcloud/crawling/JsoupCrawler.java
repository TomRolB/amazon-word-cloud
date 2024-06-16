package org.example.amazonwordcloud.crawling;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JsoupCrawler implements Crawler {

    @Override
    public String getProductDescription(String url) {
        Connection.Response response;

        try {
            response = Jsoup.connect(url)
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .header("Connection", "keep-alive")
                    .method(Connection.Method.GET)
                    .execute();
        } catch (IOException e) {
            System.out.println("Could not get cookies of " + url);
            System.out.println(e.getMessage());
            return "";
        }

        Document doc;

        try {
            doc = Jsoup
                    .connect(url)
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "en-US,en;q=0.9")
                    .header("Connection", "keep-alive")
                    .cookies(response.cookies())
//                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36")
//                    .referrer("https://www.google.com")
                    .get();
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
