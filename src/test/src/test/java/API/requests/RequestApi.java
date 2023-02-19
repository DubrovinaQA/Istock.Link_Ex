package API.requests;

import api.auth.GetCookie;
import components.RandomUtils;
import config.CompanyReguisite;
import config.Project;
import models.lombok.CredentialsLombok;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class RequestApi extends TestBase {
    CredentialsLombok credentials = new CredentialsLombok();

    GetCookie getCookie = new GetCookie();

    static String login,
            password,id;
    public String purchaseRequestName = RandomUtils.getRandomString(10),
            purchaseRequestName1 = RandomUtils.getRandomString(10),
            supplierRequirements = RandomUtils.getRandomString(10),
            productName = RandomUtils.getRandomString(10),
            requestContents = RandomUtils.getRandomString(10),
            requestNumber = RandomUtils.getRandomOnlyInteger(4);
    Integer count = RandomUtils.rnd(100),
            priceForOne = RandomUtils.rnd(100),
            purchasePrice = count * priceForOne;


    public void request(Integer country, Integer region, Integer city, String responsibleUser, String accessCompanyList, String companyId) {
        credentials.setTitle(purchaseRequestName);    //Название заявки
        credentials.setCountry(country);       // id Страны
        credentials.setRegion(region);        // id региона
        credentials.setCity(city);          // id города
        credentials.setSupplierRequirements(supplierRequirements);    // Требования к поставщику
        credentials.setResponsibleUser(responsibleUser);         //Ответственный сотрудник 4239 9977
        credentials.setNumber(requestNumber);              //Номер заявки
        credentials.setAccessCompanyList(accessCompanyList);       //Для какой компании 4194 9699
        credentials.setIsShowForAllCompanies("false");  //Видно ли всем компаниям
        credentials.setProductsList(requestContents);          //Содержание заявки
        credentials.setPartResponse("true");            //Возможность части заявки
        credentials.setNameProducts(productName);          //Название товара
        credentials.setPriceProducts(priceForOne.toString());              //Цена
        credentials.setCountProducts(count.toString());              //Количество
        credentials.setUnitProducts("instances");       //Тип товара
        credentials.setSum(purchasePrice.toString());
        credentials.setCurrency("dd");
        credentials.setCompanyId(companyId);               //Какая компания опубликовала 4195 9692

        login = CompanyReguisite.config.userEmailPartner();
        password = CompanyReguisite.config.userPasswordPartner();

        String cookies = getCookie.getCookie(login, password);
        open("/img/chat-widget/logo.svg");
        given()
                .cookie(Project.config.cookie(), cookies)
                .contentType("application/json")
                .body(credentials.getBody())
                .when()
                .post("/api/requests/undefined/publish")
                .then()
                .statusCode(200);
        this.purchaseRequestName=purchaseRequestName;
        this.productName=productName;
        this.priceForOne=priceForOne;
        this.count=count;
        this.purchasePrice=purchasePrice;
    }
    public String purchaseRequestName() {
        return purchaseRequestName;
    }
    public String productName() {return productName;}
    public String priceForOne() {return priceForOne.toString();}
    public String count() {return count.toString();}
    public String purchasePrice() {return purchasePrice.toString();}



    public void request1(String cookies, Integer country, Integer region, Integer city, String responsibleUser,
                         String accessCompanyList, String companyId, String priceForOne1, String count1) {
        credentials.setTitle(purchaseRequestName1);    //Название заявки
        credentials.setCountry(country);       // id Страны
        credentials.setRegion(region);        // id региона
        credentials.setCity(city);          // id города
        credentials.setSupplierRequirements(supplierRequirements);    // Требования к поставщику
        credentials.setResponsibleUser(responsibleUser);         //Ответственный сотрудник 4239 9977
        credentials.setNumber(requestNumber);              //Номер заявки
        credentials.setAccessCompanyList(accessCompanyList);       //Для какой компании 4194 9699
        credentials.setIsShowForAllCompanies("false");  //Видно ли всем компаниям
        credentials.setProductsList(requestContents);          //Содержание заявки
        credentials.setPartResponse("true");            //Возможность части заявки
        credentials.setNameProducts(productName);          //Название товара
        credentials.setPriceProducts(priceForOne1);              //Цена
        credentials.setCountProducts(count1);              //Количество
        credentials.setUnitProducts("instances");       //Тип товара
        credentials.setSum(purchasePrice.toString());
        credentials.setCurrency("dd");
        credentials.setCompanyId(companyId);               //Какая компания опубликовала 4195 9692



        given()
                .cookie(Project.config.cookie(), cookies)
                .contentType("application/json")
                .body(credentials.getBody())
                .when()
                .post("/api/requests/undefined/publish")
                .then()
                .statusCode(200);
        this.purchaseRequestName1=purchaseRequestName1;
        this.purchasePrice=purchasePrice;
    }

    public String companyRequestName1() {
        return purchaseRequestName1;
    }
    public String getPurchasePrice() {
        return purchasePrice.toString();
    }


    public String getIdRequest(String cookies){
        String id = given()
                .cookie(Project.config.cookie(), cookies)
                .contentType("application/json")
                .body("{\"filters\":[]}")
                .when()
                .post("/api/requests/all?limit=1&offset=0&initialRequest=true")
                .then()
                .statusCode(200)
                .extract().path("requests[0].number").toString();
        return id;
    }

    public void requestWith2Products(Integer country, Integer region, Integer city, String responsibleUser, String accessCompanyList, String companyId) {
        credentials.setTitle(purchaseRequestName);    //Название заявки
        credentials.setCountry(country);       // id Страны
        credentials.setRegion(region);        // id региона
        credentials.setCity(city);          // id города
        credentials.setSupplierRequirements(supplierRequirements);    // Требования к поставщику
        credentials.setResponsibleUser(responsibleUser);         //Ответственный сотрудник 4239 9977
        credentials.setNumber(requestNumber);              //Номер заявки
        credentials.setAccessCompanyList(accessCompanyList);       //Для какой компании 4194 9699
        credentials.setIsShowForAllCompanies("false");  //Видно ли всем компаниям
        credentials.setProductsList(requestContents);          //Содержание заявки
        credentials.setPartResponse("true");            //Возможность части заявки
        credentials.setNameProducts(productName);          //Название товара
        credentials.setPriceProducts(priceForOne.toString());              //Цена
        credentials.setCountProducts(count.toString());              //Количество
        credentials.setUnitProducts("instances");       //Тип товара
        credentials.setSum(purchasePrice.toString());
        credentials.setCurrency("dd");
        credentials.setCompanyId(companyId);               //Какая компания опубликовала 4195 9692

        login = CompanyReguisite.config.userEmailPartner();
        password = CompanyReguisite.config.userPasswordPartner();

        String cookies = getCookie.getCookie(login, password);
        open("/img/chat-widget/logo.svg");
        given()
                .cookie(Project.config.cookie(), cookies)
                .contentType("application/json")
                .body(credentials.getBodyWith2Products())
                .when()
                .post("/api/requests/undefined/publish")
                .then()
                .statusCode(200);
        this.purchaseRequestName=purchaseRequestName;
        this.productName=productName;
        this.priceForOne=priceForOne;
        this.count=count;
        this.purchasePrice=purchasePrice;
    }


    public void requestWithTwoAccessCompanyList(Integer country, Integer region, Integer city, String responsibleUser, String accessCompanyList, String accessCompanyList1, String companyId) {
        credentials.setTitle(purchaseRequestName);    //Название заявки
        credentials.setCountry(country);       // id Страны
        credentials.setRegion(region);        // id региона
        credentials.setCity(city);          // id города
        credentials.setSupplierRequirements(supplierRequirements);    // Требования к поставщику
        credentials.setResponsibleUser(responsibleUser);         //Ответственный сотрудник 4239 9977
        credentials.setNumber(requestNumber);              //Номер заявки
        credentials.setAccessCompanyList(accessCompanyList);
        credentials.setAccessCompanyList1(accessCompanyList1); //Для какой компании 4194 9699
        credentials.setIsShowForAllCompanies("false");  //Видно ли всем компаниям
        credentials.setProductsList(requestContents);          //Содержание заявки
        credentials.setPartResponse("true");            //Возможность части заявки
        credentials.setNameProducts(productName);          //Название товара
        credentials.setPriceProducts(priceForOne.toString());              //Цена
        credentials.setCountProducts(count.toString());              //Количество
        credentials.setUnitProducts("instances");       //Тип товара
        credentials.setSum(purchasePrice.toString());
        credentials.setCurrency("dd");
        credentials.setCompanyId(companyId);               //Какая компания опубликовала 4195 9692

        login = CompanyReguisite.config.userEmailPartner();
        password = CompanyReguisite.config.userPasswordPartner();

        String cookies = getCookie.getCookie(login, password);
        open("/img/chat-widget/logo.svg");
        given()
                .cookie(Project.config.cookie(), cookies)
                .contentType("application/json")
                .body(credentials.getBodyWithTwoAccessCompanyList())
                .when()
                .post("/api/requests/undefined/publish")
                .then()
                .statusCode(200);
        this.purchaseRequestName=purchaseRequestName;
        this.productName=productName;
        this.priceForOne=priceForOne;
        this.count=count;
        this.purchasePrice=purchasePrice;
    }

    public String getPurchaseRequestName() {
        return purchaseRequestName;
    }

}

