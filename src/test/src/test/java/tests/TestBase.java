package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Project;
import helpers.AllureAttachments;
import helpers.DriverSettings;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({AllureJunit5.class})
public class TestBase {
    @BeforeAll
    static void beforeAll() {

        DriverSettings.configure();
       SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        RestAssured.baseURI = Project.config.webUrl();




    }

    @BeforeEach
    public void beforeEach() {
       SelenideLogger.addListener("AllureSelenide", new AllureSelenide());


//        Configuration.baseUrl = Project.config.webUrl();
//        RestAssured.baseURI= Project.config.webUrl();
    }

    @AfterEach
    public void afterEach() {
   //    String sessionId = DriverUtils.getSessionId();

       AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
 //       AllureAttachments.addVideo();

        AllureAttachments.addBrowserConsoleLogs();
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
        Selenide.closeWebDriver();


    }
}
