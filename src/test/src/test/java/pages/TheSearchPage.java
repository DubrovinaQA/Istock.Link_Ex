package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


public class TheSearchPage {
    @Step("Вставить данные в поле поиска enter - {data}")
    public TheSearchPage chooseDataWithEnter(String data) {
        $(".search-block__input").setValue(data);
        sleep(500);
        $(".search-block__input").pressEnter();
        return this;
    }

    ;

    @Step("Вставить данные в поле поиска лупа - {dataLoop}")
    public TheSearchPage chooseDataWithLoop(String dataLoop) {
        $(".search-block__input").setValue(dataLoop);
        $(".search-block__btn_submit").click();
        return this;
    }

    ;

    @Step("Удаление данных")
    public TheSearchPage delData() {
        $(".search-block__btn_reset").click();
        $(".search-block__input").shouldHave(value(""));
        return this;
    }

    ;

    @Step("Проверка данных - {dataCheck}")
    public TheSearchPage checkData(String dataCheck) {
        $(".request-card-number").shouldHave(text(dataCheck));
        return this;
    }

    ;

    @Step("Проверка данных Компания - {dataCheckCompany}")
    public TheSearchPage checkDataCompany(String dataCheckCompany) {
        $(".cr-content__footer").shouldHave(text(dataCheckCompany));
        return this;
    }

    ;

    @Step("Проверка данных во всех заявках - {dataCheckZZ}")
    public TheSearchPage checkDataZZ(String dataCheckZZ) {
        $(".cr-content__request-number").shouldHave(text(dataCheckZZ));
        return this;
    }

    ;

    @Step("Проверка некорректных данных")
    public TheSearchPage dataCheckNegative() {
        $(".containers_type_default").shouldHave(text("Не найдено результатов."));
        return this;
    };

    @Step("Введение рандомного значения в поле")
    public TheSearchPage inputRandom() {
        $(".containers_type_default").shouldHave(text("Не найдено результатов."));
        return this;
    };

}
