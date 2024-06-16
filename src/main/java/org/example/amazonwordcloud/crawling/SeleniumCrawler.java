package org.example.amazonwordcloud.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

//TODO: use Cacheable?
@Service
public class SeleniumCrawler implements Crawler {
    WebDriver driver;

    public SeleniumCrawler() {

    }

    @Override
    @Cacheable("descriptions")
    public String getProductDescription(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-javascript");
        options.addArguments("--blink-settings=imagesEnabled=false");
        driver = new ChromeDriver(options);

        driver.get(url);
        return driver.findElement(By.id("productDescription")).getText();
    }
}
