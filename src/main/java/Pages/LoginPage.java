package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    public LoginPage(Page page){
        super(page);
    }


    Locator emailInput = page.locator(("//input[@type='email']"));
    Locator passwordInput = page.locator(("//input[@type='password']"));
    Locator loginButton = page.locator("//button[@name='button']");




    public LoginPage enterEmail(){
      emailInput.type("victor.panchenko@afsi.com");
      return this;
    }

    public LoginPage enterPassword(){
        passwordInput.fill("Evgeny@123");
        return this;
    }

    public LoginPage clickOnLoginButton(){
        loginButton.click();
        return this;
    }

}
