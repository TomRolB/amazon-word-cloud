package org.example.amazonwordcloud.caching;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class GuavaCaching implements CachingService {
    private final Cache<String, ResponseEntity<?>> cache;

    public GuavaCaching(long maximumSize, Duration expireAfterAccess, Duration expireAfterWrite) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(expireAfterAccess)
                .expireAfterWrite(expireAfterWrite)
                .build();
    }

    @Override
    public Optional<ResponseEntity<?>> getCachedCloud(String url) {
        return Optional.ofNullable(cache.getIfPresent(url));
    }

    @Override
    public void saveCloud(String url, ResponseEntity<?> response) {
        cache.put(url, response);
    }
}
