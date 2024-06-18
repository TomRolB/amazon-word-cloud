package org.example.amazonwordcloud.app;

import org.example.amazonwordcloud.caching.CachingService;
import org.example.amazonwordcloud.caching.GuavaCaching;
import org.example.amazonwordcloud.crawling.Crawler;
import org.example.amazonwordcloud.crawling.JsoupCrawler;
import org.example.amazonwordcloud.crawling.JsoupOkhttpCrawler;
import org.example.amazonwordcloud.crawling.SeleniumCrawler;
import org.example.amazonwordcloud.words.And;
import org.example.amazonwordcloud.words.FrequencyRanker;
import org.example.amazonwordcloud.words.NonAcronymFilter;
import org.example.amazonwordcloud.words.ShortWordFilter;
import org.example.amazonwordcloud.words.WordRanker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AppConfig {
    @Bean
    public Crawler crawler() {
        return new SeleniumCrawler();
//        return new JsoupCrawler();
//        return new JsoupOkhttpCrawler();
    }

    @Bean
    public WordRanker ranker() {
        return new FrequencyRanker(
                new And(
                        new NonAcronymFilter(),
                        new ShortWordFilter()
                )
        );
    }

    @Bean
    public CachingService caching() {
        return new GuavaCaching(
                100,
                Duration.ofMinutes(3),
                Duration.ofMinutes(3)
        );
    }
}
