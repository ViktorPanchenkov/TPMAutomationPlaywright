package Tests;

import DataProviders.claimAdminDataProviders;
import Pages.AdminSettlements;
import Pages.LoginPage2;
import com.microsoft.playwright.*;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class AdminSettlementsTest implements claimAdminDataProviders {

    AdminSettlements adminSettlements;
    LoginPage2 loginPage2;

    BrowserContext browserContext;
    @BeforeMethod
    public void SetUP(){
        Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setSlowMo(500).setHeadless(false));
        Page page = browser.newPage();
        BrowserContext browserContext = browser.newContext();
        page.navigate("https://vnextui1.azurewebsites.net/");
        loginPage2 = new LoginPage2(page);
        adminSettlements = new AdminSettlements(page);
        loginPage2.enterEmail("victor.panchenko@afsi.com");
        loginPage2.enterPassword("Evgeny@123");
        loginPage2.clickOnLoginButton();
        browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("autentefication.json")));

    }

    @Test(dataProvider = "RandomCodeAndNameDataProvider")
    public void addClaimAttribute(String code, String name){
        adminSettlements.openAdminSettlementsScreen()
                .clickOnAddAttributeButton()
                .selectInputAttributeType()
                .enterCode(code)
                .enterName(name)
                .clickOnAddSaveButton();
        Assert.assertTrue(adminSettlements.isAddedItemDisplayedOnGrid(name));
    }

    @Test
    public void skipLogin(){
        Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setSlowMo(500).setHeadless(false));
        BrowserContext browserContext1 = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("autentefication.json")));
        adminSettlements.openAdminSettlementsScreen()
                .clickOnAddAttributeButton();
    }


}
