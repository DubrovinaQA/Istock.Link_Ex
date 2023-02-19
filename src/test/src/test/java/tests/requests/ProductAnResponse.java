package tests.requests;

import API.auth.AuthWithLogin;
import api.auth.AuthWithLogin;
import api.request.RequestApi;
import components.RandomUtils;
import config.App;
import config.CompanyReguisite;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.DopPage;
import pages.TheSearchPage;
import pages.request.filter.TheSearchPage;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Feature("Заявки на закупку")
@Story("Добавление аналога продукта в отклик по заявке на закупку")
@Owner("Olga")
public class ProductAnResponse extends TestBase {
    AuthWithLogin authWithLogin = new AuthWithLogin();
    TheSearchPage theSearchPage = new TheSearchPage();
    RequestApi requestApi = new RequestApi();
    RandomUtils randomUtils = new RandomUtils();
    DopPage dopPage = new DopPage();
    String responsibleUser = CompanyReguisite.config.responsibleUser();
    String accessCompanyList = App.config.accessCompanyList();
    String companyId = CompanyReguisite.config.companyId();
    String productName = requestApi.productName(); //Название продукта из заявки
    String purchaseRequestName = requestApi.purchaseRequestName(); //Название заявки
    String randomProductNameMax = randomUtils.getRandomStringAllValidSymbols(500);
    String randomProductNameMin = randomUtils.getRandomStringAllValidSymbols(1);
    String randomProductNameMid = randomUtils.getRandomStringAllValidSymbols(15);
    String[] inputData = {randomProductNameMin, "       ", "~!?@#$%^&*_-+( )[ ]{ }></|'.,:;.", "     Проверка пробелов", "Проверка      пробелов"};
    String[] outputData = {randomProductNameMin, productName, productName, "Проверка пробелов", "Проверка пробелов"};
    Integer priceForOne = RandomUtils.rnd(100);
    String userTwo = App.config.userLogin();
    String userOne = CompanyReguisite.config.userEmailPartner();
    Integer country = 3159;
    Integer region = 4925;

    @Test
    @DisplayName("Валидация поля аналога продукта")
    void analogueFieldValidity() {

        step("Создание приватной заявки П1 для П2 API", () -> {
            requestApi.request(country, region, null, responsibleUser, accessCompanyList, companyId);
        });

        step("Авторизация П2", () -> {
            authWithLogin.authWithLogin("/requests/all", userTwo, userTwo);
        });

        step("Нахождение и отклик на заявку", () -> {
            theSearchPage.chooseDataWithEnter(purchaseRequestName);
            $(".cr-content__footer-btn").click();
        });


        step("Проверка сброса значения аналога", () -> {
            $("[data-testid=confirmation-input-textarea]").setValue(randomProductNameMid);
            $("[data-testid=button-icon", 1).click();
            $("[data-testid=confirmation-input-textarea]").shouldHave(text(productName));
        });

        step("Валидация поля,(минимальное значение,пустое поле,спецсимволы,пробелы вначале строки,множественные пробелы в середине строки) ", () -> {
            for (int i = 0; i < inputData.length; i++) {

                $("[data-testid=confirmation-input-textarea]").setValue(inputData[i]);
                $(".MuiTooltip-popper").$("[data-testid=button-icon", 0).click();
                $("[data-testid=confirmation-input-textarea]").shouldHave(text(outputData[i]));
            }
        });

        step("Валидация поля, максимальное количество символов", () -> {
            $("[data-testid=confirmation-input-textarea]").sendKeys(randomProductNameMax);
            $(".MuiTooltip-popper").$("[data-testid=button-icon", 0).click();
        });

        step("Проверка звездочки", () -> {
            $(".analogAdornment___3RcSv").shouldHave(visible);
            $(".analogAdornment___3RcSv").hover();
            $(".MuiTooltip-tooltip").shouldHave(text(productName));
        });

    }

    @Test
    @DisplayName("Проверка функциональности аналога продукта для Продавца")
    void successfulReplacementOfAnalogueSeller() {

        step("Создание приватной заявки П1 для П2 API", () -> {
            requestApi.request(country, region, null, responsibleUser, accessCompanyList, companyId);
        });

        step("Авторизация П2", () -> {
            authWithLogin.authWithLogin("/requests/all", userTwo, userTwo);
        });

        step("Найти и откликнуться на заявку", () -> {
            theSearchPage.chooseDataWithEnter(purchaseRequestName);
            $(".cr-content__footer-btn").click();
        });

        step("Ввод аналога с проверкой", () -> {
            $("[data-testid=confirmation-input-textarea]").setValue(randomProductNameMid);
            $(".MuiTooltip-popper").$("[data-testid=button-icon", 0).click();
            $("[data-testid=confirmation-input-textarea]").shouldHave(text(randomProductNameMid));
        });

        step("Введение суммы и отправка отклика", () -> {
            $(".product-table-number", 1).$("[type=\"number\"]").setValue(priceForOne.toString());
            $(".form-respond-bottom-buttons").$("[data-testid=\"button\"]", 2).click();
            $("#saveButton").shouldBe(visible).click();
            $(".respond-title-status__status-name").shouldHave(text("Отправлен"));
            $(".breadcrumbs__item").click();
        });

        step("Проверка в разделе отклики корректной замены аналога для Продавца", () -> {
            $(".nav-tabs").$(byText("Мои отклики")).click();
            theSearchPage.chooseDataWithEnter(purchaseRequestName);
            $(".cws-tooltip", 3).click();
            $("[data-testid=product-table-space]").shouldHave(text(randomProductNameMid));
            $(".adornment___1OKXa").shouldHave(visible);
            $(".adornment___1OKXa").hover();
            $(".MuiTooltip-tooltip").shouldHave(text(productName));
        });

        step("Проверка во внутренностях заявки корректной замены аналога для Продавца", () -> {
            $(".cws-tooltip__children").click();
            $(".request-card-title").click();
            $("[data-testid=product-table-space]", 1).shouldHave(text(randomProductNameMid));
            $(".adornment___1OKXa").shouldHave(visible);
        });
    }


    @Test
    @DisplayName("Проверка функциональности аналога продукта для Покупателя")
    void successfulReplacementOfAnalogueBuyer() {

        step("Создание приватной заявки П1 для П2 API", () -> {
            requestApi.request(country, region, null, responsibleUser, accessCompanyList, companyId);
        });

        step("Авторизация П2", () -> {
            authWithLogin.authWithLogin("/requests/all", userTwo, userTwo);
        });

        step("Найти и откликнуться на заявку", () -> {
            theSearchPage.chooseDataWithEnter(purchaseRequestName);
            $(".cr-content__footer-btn").click();
        });

        step("Ввод аналога с проверкой", () -> {
            $("[data-testid=confirmation-input-textarea]").setValue(randomProductNameMid);
            $(".MuiTooltip-popper").$("[data-testid=button-icon", 0).click();
            $("[data-testid=confirmation-input-textarea]").shouldHave(text(randomProductNameMid));
        });

        step("Введение суммы и отправка отклика", () -> {
            $(".product-table-number", 1).$("[type=\"number\"]").setValue(priceForOne.toString());
            $(".form-respond-bottom-buttons").$("[data-testid=\"button\"]", 2).click();
            $("#saveButton").shouldBe(visible).click();
            $(".respond-title-status__status-name").shouldHave(text("Отправлен"));
            $(".breadcrumbs__item").click();
        });


        step("Разлогин и вход под акком П1", () -> {
            dopPage.exitWithIstock();
            authWithLogin.authWithLogin("/requests/my", userOne, userOne);
        });

        step("Проверка в разделе Мои заявки корректной замены аналога для Покупателя", () -> {
            theSearchPage.chooseDataWithEnter(purchaseRequestName);
            $(".request-card-arrow").click();
            $(".request-card-arrow", 1).click();
            $("[data-testid=product-table-space]").shouldHave(text(randomProductNameMid));
            $(".adornment___1OKXa").shouldHave(visible);
            $(".adornment___1OKXa").hover();
            $(".MuiTooltip-tooltip").shouldHave(text(productName));
        });

        step("Проверка во внутренностях заявки корректной замены аналога для Покупателя", () -> {
            $(".request-card-title").click();
            $(".request-card-arrow").click();
            $("[data-testid=product-table-space]", 1).shouldHave(text(randomProductNameMid));
            $(".adornment___1OKXa").shouldHave(visible);
        });

    }
}
