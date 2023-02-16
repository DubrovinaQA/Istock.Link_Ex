package API.auth;

import config.App;
import config.Project;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;


public class AuthAPI {

    String login = App.config.userLogin(),
            password = App.config.userPassword();
    ;

    @Step("Авторизация в мессенджере")
    public void exampleOfLogin(String url) {

        login = App.config.userLogin();
        password = App.config.userPassword();

        String cookie = given()
                .contentType("application/json")
                .body("{\"email\":\"" + login + "\",\"password\":\"" + password + "\"}")
                .when()
                .post(Project.config.webUrl() + "/auth/local")
                .then()
                .statusCode(200)
                .extract().cookie(Project.config.cookie());

        open("/img/chat-widget/logo.svg");

        getWebDriver().manage().addCookie(
                new Cookie(Project.config.cookie(), cookie));
        open(url);
    }

}
