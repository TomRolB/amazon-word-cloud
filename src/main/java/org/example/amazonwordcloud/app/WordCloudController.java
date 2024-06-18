package org.example.amazonwordcloud.app;

import org.example.amazonwordcloud.caching.CachingService;
import org.example.amazonwordcloud.crawling.Crawler;
import org.example.amazonwordcloud.words.WordRanker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class WordCloudController {

    private final Crawler crawler;
    private final WordRanker ranker;
    private final CachingService caching;

    @Autowired
    WordCloudController(Crawler crawler, WordRanker wordRanker, CachingService caching) {
        this.crawler = crawler;
        this.ranker = wordRanker;
        this.caching = caching;
    }

    @PostMapping("/")
    public ResponseEntity<?> generateCloud(@RequestParam("productUrl") String productUrl) {
        Optional<ResponseEntity<?>> cached = caching.getCachedCloud(productUrl);
        return cached.orElse(fetchDescriptionAndGenerateCloud(productUrl));

        //TODO: URL sanitization
    }

    private ResponseEntity<?> fetchDescriptionAndGenerateCloud(String productUrl) {
        String description = crawler.getProductDescription(productUrl);
        //TODO: may put into separate 'CachingService' and cache it

        if (description.isEmpty()) return saveAndReturn(
                productUrl,
                ResponseEntity.ok()
                        .body(Map.of("error", "Cannot generate cloud for this url"))
        );
        else return saveAndReturn(
                productUrl,
                ResponseEntity.ok()
                        .body(Map.of("ranks", ranker.getRankedWords(description)))
        );
    }

    private ResponseEntity<?> saveAndReturn(String productUrl, ResponseEntity<?> response) {
        caching.saveCloud(productUrl, response);
        return response;
    }
}
