package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties"

//        "classpath:config/local.properties.properties",
//        "classpath:config/remote.properties"
})
public interface ProjectConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
        // @DefaultValue("firefox")
    String browser();

    @Key("browserVersion")
    @DefaultValue("108.0")
        // @DefaultValue("108.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1900x1080")
    String browserSize();

    String videoStorage();

    @Key("webUrl")
    @DefaultValue("https://zakupki.test.istock.link")
        //@DefaultValue("https://zakupki.istock.link")
    String webUrl();

    @Key("cookie")
    @DefaultValue("test_goodwix_sid")
        // @DefaultValue("goodwix_sid")
    String cookie();

    @Key("remote")
    @DefaultValue("false")
    String getRemoteURL();

    @Key("remoteUrl")
    String getRemote();
}