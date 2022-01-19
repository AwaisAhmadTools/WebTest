package stepDefinitions;

import Utilities.DriverSetup;
import Utilities.WebPageValidation;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;

public class StepDefs {

    private final DriverSetup driverSetup = new DriverSetup();
    private WebDriver driver;

    WebPageValidation webPageValidation;


    @Given("the user opens the browser")
    public void the_user_opens_the_browser() throws MalformedURLException {
        driver = driverSetup.initialiseDriver();
        webPageValidation = new WebPageValidation(driver);
    }

    @When("the user navigates to the web page {string}")
    public void the_user_navigates_to_the_web_page(String url) {
        driver.get(url);
    }

    @Then("there are no console errors on the page")
    public void there_are_no_console_errors_on_the_page() {
        webPageValidation.validateConsoleErrors(driverSetup.getBrowser());
    }

    @Then("the status code {int} is returned")
    public void the_status_code_is_returned(int statusCode) throws IOException {
        webPageValidation.validateStatusCode(statusCode);
    }

    @Then("all the links on the page go to another live page")
    public void all_the_links_on_the_page_go_to_another_live_page() throws IOException {
        webPageValidation.validateLinks();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
