package org.example.amazonwordcloud.app;

import org.example.amazonwordcloud.caching.CachingService;
import org.example.amazonwordcloud.caching.GuavaCaching;
import org.example.amazonwordcloud.crawling.Crawler;
import org.example.amazonwordcloud.crawling.SeleniumCrawler;
import org.example.amazonwordcloud.words.And;
import org.example.amazonwordcloud.words.FrequencyRanker;
import org.example.amazonwordcloud.words.NotAnAcronym;
import org.example.amazonwordcloud.words.ShortWord;
import org.example.amazonwordcloud.words.WordRanker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;

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
                        new ShortWord(),
                        new NotAnAcronym()
                ),
                new HashSet<>(List.of(' ', ':', '.', ',', '.', '(', ')', '\n'))
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
