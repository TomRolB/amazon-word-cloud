package org.example.amazonwordcloud.caching;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CachingService {
    Optional<ResponseEntity<?>> getCachedCloud(String url);
    void saveCloud(String url, ResponseEntity<?> response);
}
