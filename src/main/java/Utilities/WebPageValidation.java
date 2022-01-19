package Utilities;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WebPageValidation {

    private final WebDriver driver;
    public SoftAssertions softAssertions = new SoftAssertions();


    public WebPageValidation(WebDriver driver) {
        this.driver = driver;
    }

    public void validateConsoleErrors(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            LogEntries logEntries = driver.manage().logs().get("browser");
            String errorMsg = "";

            for (LogEntry entry : logEntries) {

                errorMsg += "Console Log: " + entry.getTimestamp() + " " + entry.getLevel() + " " + entry.getMessage() + System.lineSeparator();
            }
            Assert.assertEquals("", errorMsg);
        }
    }

    public void validateStatusCode(int statusCode) throws IOException {
        String url = driver.getCurrentUrl();
        HttpURLConnection httpURLConnection = setNewHttpURLConnection(url);
        Assert.assertEquals("Url is " + url + " .Response code expected is " + statusCode +
                " actual response code is " + httpURLConnection.getResponseCode() + " :",statusCode,httpURLConnection.getResponseCode());
    }

    public void validateLinks() throws IOException {

        List<WebElement> links = driver.findElements(By.xpath("//a[@href]"));
        for (WebElement link : links) {

            if (!(link.getAttribute("href").startsWith("http"))) {
                continue;
            }

            String url = link.getAttribute("href");
            HttpURLConnection httpURLConnection = setNewHttpURLConnection(url);
            int responseCode = httpURLConnection.getResponseCode();

            softAssertions.assertThat(responseCode < 400).as("the link with text " + link.getText()
                    + " " + ".The url of the link is " + url + " is broken. The response code is " + responseCode).isTrue();
        }
        softAssertions.assertAll();
    }

    private HttpURLConnection setNewHttpURLConnection(String url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setRequestMethod("HEAD");
        httpURLConnection.connect();
        return httpURLConnection;
    }

}
