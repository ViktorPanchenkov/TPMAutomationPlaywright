package DataProviders;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public interface claimAdminDataProviders {
    String randomCode = "Code" + RandomStringUtils.randomAlphabetic(5);
    String randomName = "Name" + RandomStringUtils.randomAlphabetic(5);


    @DataProvider(name = "RandomCodeAndNameDataProvider")
    public static Object[][] GetNameAndCode() {
        Object[][] NameAndCode = {{randomCode, randomName}};
        return NameAndCode;
    }
}

