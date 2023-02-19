package helpers;

import com.codeborne.selenide.Configuration;
import config.Project;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverSettings {

    public static void configure() {
        Configuration.browser = Project.config.browser();
        Configuration.browserVersion = Project.config.browserVersion();
        Configuration.browserSize = Project.config.browserSize();
        Configuration.timeout = 20000;
        Configuration.pageLoadTimeout=60000;
        Configuration.baseUrl = Project.config.webUrl();
        RestAssured.baseURI = Project.config.webUrl();


        if (Project.config.getRemoteURL().contains("true")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        //  Configuration.remote = "http://172.30.12.7/wd/hub";
       //     Configuration.remote = "http://62.113.110.235:4444/wd/hub";
            Configuration.remote = Project.config.getRemote();
        } else {
            Configuration.remote = null;;
        }




    }
}
