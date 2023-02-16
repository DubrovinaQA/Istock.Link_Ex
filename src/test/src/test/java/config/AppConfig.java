package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:resources/config/app.properties"
})
public interface AppConfig extends Config {

    String webUrl();

    String userLogin();
    String userPassword();
    String companyNameUser();
    String  companyINNUser();
    String companyEmailUser();
    String companyOGRNUser();
    String companyKPPUser();
    String address();
    String nameEnployee();
    String userEmployee();
    String passwordEmployee();
    String userDopEmployee();
    String passwordDopEmployee();
    String nameDopEmployee();
    String accessCompanyList();
    String responsibleUser();
    String companyId();

    String emailPartner();
    String passwordPartner();
    String accessCompanyList1();
    String namePartner();


    String emailNotPartner();
    String passwordNotPartner();


}
