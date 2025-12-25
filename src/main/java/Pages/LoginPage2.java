package Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.opentest4j.AssertionFailedError;
import org.testng.Assert;

import java.nio.file.Path;
import java.nio.file.Paths;

import static Pages.BasePage.page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage2 extends BasePage {

    Locator emailInput;
    Locator passwordInput;
    Locator loginButton;

    Locator financeDropdown;




    public LoginPage2(Page page){
        super(page);

        emailInput = page.locator("//input[@type='email']");
        passwordInput = page.locator("//input[@type='password']");
        loginButton = page.locator("//button[@name='button']");
        financeDropdown = page.locator("//span[(text()=' Finance ')]");

       // page.waitForSelector("//div[contains(text(),'Invalid username and password.')]"), new Page.WaitForSelectorOptions()
        //        .setState(WaitForSelectorState.VISIBLE).setTimeout(7000);

    }

    public void enterEmail(String email){
        emailInput.type(email);
    }

    public void enterPassword(String password){
        passwordInput.fill(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

    public boolean IsUserLoggedIN(){
        try{

            assertThat(financeDropdown).isVisible();
           // page.waitForURL("/app/landing-page", new Page.WaitForURLOptions().setTimeout(5000));
            return true;
        } catch (AssertionFailedError assertionFailedError){
            Assert.fail(assertionFailedError.getMessage());
            return false;
        }

    }
    // Явное ожидание которое можно конфигурировать
    public boolean IsInvalidCredsErrorDisplayed(){
        try{
            page.waitForSelector("//div[contains(text(),'InvalidUsernameOrPassword')]", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(6000));
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screen1")));
            return true;
        } catch (TimeoutError timeoutError){
            Assert.fail(timeoutError.getMessage());
            return false;
        }

    }
    public boolean IsEmptyUserNameAndPasseordTextVisible(){
         try {
           assertThat(page.getByText("The Username field is required.")).isVisible();
             return true;
         } catch (AssertionFailedError assertionFailedError){
             Assert.fail(assertionFailedError.getMessage());
             return false;
         }


    }





}
