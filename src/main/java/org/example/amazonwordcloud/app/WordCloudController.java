package org.example.amazonwordcloud.app;

import org.example.amazonwordcloud.crawling.Crawler;
import org.example.amazonwordcloud.words.WordRanker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class WordCloudController {

    private final Crawler crawler;
    private final WordRanker ranker;

    @Autowired
    WordCloudController(Crawler crawler, WordRanker wordRanker) {
        this.crawler = crawler;
        this.ranker = wordRanker;
    }

    @PostMapping("/")
    public Map<String, Map<String, Integer>> generateCloud(@RequestParam("productUrl") String productUrl) {
        //TODO: URL sanitization
        String description = crawler.getProductDescription(productUrl);

        if (description.isEmpty()) return Map.of("ranks", Collections.emptyMap());
        else return Map.of("ranks", ranker.getRankedWords(description));
    }
}
