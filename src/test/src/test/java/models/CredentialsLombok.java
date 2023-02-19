package models;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tests.TestBase;

@EqualsAndHashCode(callSuper = true)
public class CredentialsLombok extends TestBase {
    private String categoriesId;
    private String title;
    private String date;
    private Integer country;
    private Integer region;
    private Integer city;
    private String supplierRequirements;
    private String responseEndDate;
    private String responsibleUser;
    private String number;
    private String accessCompanyList;
    private String accessCompanyList1;
    private String isShowForAllCompanies;
    private String productsList;
    private Object attachments;

    private Object links;
    private String idLinks;
    private String nameLinks;
    private String urlLinks;
    private String typeLinks;

    private String partResponse;
    private String products;
    private String nameProducts;
    private String priceProducts;
    private String countProducts;
    private String unitProducts;

    private String sum;
    private String currency;
    private String companyId;
    private String body;

    public String getBody() {
        System.out.println("responsibleUser!!! "+responsibleUser);
        System.out.println("companyId!!! "+companyId);
        System.out.println("accessCompanyList!!! "+accessCompanyList);
        String body = "{" +
                "\"data\": {" +
                "\"type\": \"Regular\"," +
                "\"routeId\": null," +
                "\"authorId\": "+responsibleUser+"," +
                "\"executors\": []," +
                "\"categoriesId\": [49,55,63,68,64,79,25,31]," +
                "\"country\": \""+country+"\"," +
                "\"region\": \""+region+"\"," +
                "\"city\": \""+city+"\"," +
                "\"productsList\": \"<p>"+productsList+"</p>\"," +
                "\"products\": [{" +
                "\"name\": \""+nameProducts+"\"," +
                "\"price\":    \" \"     ,"+
                "\"count\": \""+countProducts+"\"," +
                "\"unit\": \"instances\"" +
                "        }]," +
                " \"partResponse\": "+partResponse+"," +
                "\"supplierRequirements\": \""+supplierRequirements+"\"," +
                "\"attachments\": []," +
                "\"companyId\": "+companyId+"," +
                "\"responsibleUser\": "+responsibleUser+"," +
                "\"sum\": "+sum+"," +
                "\"responseEndDate\": \"2024-12-29T00:00:00.000Z\"," +
                "\"currency\": \"RUB\"," +
                "\"links\": []," +
                " \"title\": \""+title+"\"," +
                "\"number\": \""+number+"\"," +
                "\"accessCompanyList\": ["+accessCompanyList+"]," +
                "\"suppliers\": []" +
                "    }" +
                "}";
        return body;
    }

    public String getBodyWith2Products() {
        String body = "{" +
                "\"data\": {" +
                "\"type\": \"Regular\"," +
                "\"routeId\": null," +
                "\"authorId\": "+responsibleUser+"," +
                "\"executors\": []," +
                "\"categoriesId\": [49,55,63,68,64,79,25,31]," +
                "\"country\": \""+country+"\"," +
                "\"region\": \""+region+"\"," +
                "\"city\": \""+city+"\"," +
                "\"productsList\": \"<p>"+productsList+"</p>\"," +
                "\"products\": [{" +
                "\"name\": \""+nameProducts+"\"," +
                "\"price\": \""+priceProducts+"\"," +
                "\"count\": \""+countProducts+"\"," +
                "\"unit\": \"instances\"" +
                "        }," +
                "{\"name\" : \""+nameProducts+"\","+
                "\"price\": \""+priceProducts+"\","+
                "\"count\": \""+countProducts+"\","+
                "\"unit\":\"instances\""+
                "}" +
                "]," +
                " \"partResponse\": "+partResponse+"," +
                "\"supplierRequirements\": \""+supplierRequirements+"\"," +
                "\"attachments\": []," +
                "\"companyId\": "+companyId+"," +
                "\"responsibleUser\": "+responsibleUser+"," +
                "\"sum\": "+sum+"," +
                "\"responseEndDate\": \"2024-12-29T00:00:00.000Z\"," +
                "\"currency\": \"RUB\"," +
                "\"links\": []," +
                " \"title\": \""+title+"\"," +
                "\"number\": \""+number+"\"," +
                "\"accessCompanyList\": ["+accessCompanyList+"]," +
                "\"suppliers\": []" +
                "    }" +
                "}";
        return body;
    }

    public String getBodyWithTwoAccessCompanyList() {

        System.out.println("00000"+ accessCompanyList);
        System.out.println("000001"+ accessCompanyList1);
        String body = "{" +
                "\"data\": {" +
                "\"type\": \"Regular\"," +
                "\"routeId\": null," +
                "\"authorId\": "+responsibleUser+"," +
                "\"executors\": []," +
                "\"categoriesId\": [49,55,63,68,64,79,25,31]," +
                "\"country\": \""+country+"\"," +
                "\"region\": \""+region+"\"," +
                "\"city\": \""+city+"\"," +
                "\"productsList\": \"<p>"+productsList+"</p>\"," +
                "\"products\": [{" +
                "\"name\": \""+nameProducts+"\"," +
                "\"price\": \""+priceProducts+"\"," +
                "\"count\": \""+countProducts+"\"," +
                "\"unit\": \"instances\"" +
                "        }]," +
                " \"partResponse\": "+partResponse+"," +
                "\"supplierRequirements\": \""+supplierRequirements+"\"," +
                "\"attachments\": []," +
                "\"companyId\": "+companyId+"," +
                "\"responsibleUser\": "+responsibleUser+"," +
                "\"sum\": "+sum+"," +
                "\"responseEndDate\": \"2024-12-29T00:00:00.000Z\"," +
                "\"currency\": \"RUB\"," +
                "\"links\": []," +
                " \"title\": \""+title+"\"," +
                "\"number\": \""+number+"\"," +
                "\"accessCompanyList\": ["+accessCompanyList+","+accessCompanyList1+"]," +
                "\"suppliers\": []" +
                "    }" +
                "}";
        return body;
    }
//"\"accessCompanyList\": ["+accessCompanyList+","+accessCompanyList1+"]," +

}
