package org.example.amazonwordcloud.crawling;

//TODO: may make more generic, e.g. being able to
// get something apart form the description
public interface Crawler {
    String getProductDescription(String url);
}
