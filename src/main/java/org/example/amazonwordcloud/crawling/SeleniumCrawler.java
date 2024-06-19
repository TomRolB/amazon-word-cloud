package org.example.amazonwordcloud.crawling;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class SeleniumCrawler implements Crawler {
    @Override
    public String getProductDescription(String url) {
        WebDriver driver = initializeDriver();
        driver.get(url);

        try {
            return driver.findElement(By.id("productDescription")).getText();
        } catch (NoSuchElementException e) {
            System.out.println("Page does not contain the section 'Product Description'");
            return "";
        }
    }

    private static WebDriver initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-javascript");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return new ChromeDriver(options);
    }
}
