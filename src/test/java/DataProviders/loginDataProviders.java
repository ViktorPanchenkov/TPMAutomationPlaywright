package DataProviders;

import org.testng.annotations.DataProvider;
import org.apache.commons.lang3.RandomStringUtils;

public interface loginDataProviders {

     String randomEmail = RandomStringUtils.randomAlphabetic(5)+ "@gmail.com";
     String randomPassword = RandomStringUtils.randomAlphabetic(6);


    @DataProvider(name = "validCredsDataProvider")
    public static Object[][] GetValidCreds() {
        Object[][] ValidLoginAndPass = {{"victor.panchenko@afsi.com", "Evgeny@123"}};
        return ValidLoginAndPass;
    }

    @DataProvider(name = "invalidCredsDataProvider")
    public static Object[][] GetInvalidCreds() {
        Object[][] ValidLoginAndPass = {{randomEmail, randomPassword}};
        return ValidLoginAndPass;
    }

}
