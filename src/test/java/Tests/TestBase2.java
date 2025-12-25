package Tests;

import Pages.LoginPage2;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeMethod;

public class TestBase2 {


    LoginPage2 loginPage2;
    @BeforeMethod
    public void SetUP(){
        Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setSlowMo(500));
        Page page = browser.newPage();
        page.navigate("https://vnextui1.azurewebsites.net/");

        loginPage2 = new LoginPage2(page);
    }
}
