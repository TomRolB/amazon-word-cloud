package org.example.amazonwordcloud.app;

import org.example.amazonwordcloud.crawling.Crawler;
import org.example.amazonwordcloud.crawling.JsoupCrawler;
import org.example.amazonwordcloud.crawling.SeleniumCrawler;
import org.example.amazonwordcloud.words.FrequencyRanker;
import org.example.amazonwordcloud.words.WordRanker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Crawler crawler() {
        return new SeleniumCrawler();
//        return new JsoupCrawler();
    }

    @Bean
    public WordRanker ranker() {
        return new FrequencyRanker(word -> false);
    }
}
