package Tests;

import DataProviders.loginDataProviders;
import Pages.LoginPage2;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest2 implements loginDataProviders {

   LoginPage2 loginPage2;
    @BeforeMethod
    public void SetUP(){
        Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setSlowMo(500).setHeadless(false));



        Page page = browser.newPage();
        page.navigate("https://vnextui1.azurewebsites.net/");

         loginPage2 = new LoginPage2(page);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "validCredsDataProvider")
    public void login(String email, String password){
    loginPage2.enterEmail(email);
    loginPage2.enterPassword(password);
    loginPage2.clickOnLoginButton();
    Assert.assertTrue(loginPage2.IsUserLoggedIN());

}

    @Test(dataProvider = "invalidCredsDataProvider")
    public void loginWithInvalidCreds(String invalidEmail, String invalidPassword){
        loginPage2.enterEmail(invalidEmail);
        loginPage2.enterPassword(invalidPassword);
        loginPage2.clickOnLoginButton();
        Assert.assertTrue(loginPage2.IsInvalidCredsErrorDisplayed());
}
   @Test
    public void loginWithEmptyNameAndPassword(){

       loginPage2.clickOnLoginButton();
       loginPage2.IsEmptyUserNameAndPasseordTextVisible();
   }



}
