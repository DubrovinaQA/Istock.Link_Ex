package API.auth;


import config.Project;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.openqa.selenium.Cookie;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class AuthWithLogin extends TestBase {
String cartAsHtmlString, cartAsHtmlString1;
@Step("Авторизация для логина - {login} и пароля {password}")
    public String authWithLogin(String url, String login, String password) {

        RestAssured.baseURI= Project.config.webUrl();
        String cartAsHtmlString = given()
                .contentType("application/json")
                .body("{\"email\":\""+ login + "\",\"password\":\""+ password + "\"}")
                .when()
                .post("/auth/local")
                .then()
                .statusCode(200)
                .extract().cookie(Project.config.cookie());
        System.out.println(cartAsHtmlString);
        open("/img/chat-widget/logo.svg");
        System.out.println(login);
        System.out.println(password);
        System.out.println(cartAsHtmlString);
sleep(500);
        getWebDriver().manage().addCookie(
                new Cookie(Project.config.cookie(), cartAsHtmlString));
        open(url);
        this.cartAsHtmlString=cartAsHtmlString;
        return cartAsHtmlString;
            }

    public String getCartAsHtmlString() {
        return cartAsHtmlString;
    }

    public String authWithLogin2(String url, String login, String password) {
        sleep(500);
        open();
        RestAssured.baseURI= Project.config.webUrl();
        String cartAsHtmlString1 = given()
                .contentType("application/json")
                .body("{\"email\":\""+ login + "\",\"password\":\""+ password + "\"}")
                .when()
                .post("/auth/local")
                .then()
                .statusCode(200)
                .extract().cookie(Project.config.cookie());
        System.out.println(cartAsHtmlString1);

        this.cartAsHtmlString1=cartAsHtmlString1;
        return cartAsHtmlString1;
    }

    public String getCartAsHtmlString1() {
        return cartAsHtmlString1;
    }
}

