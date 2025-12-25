package Tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTest {


    Playwright playwright;

    Browser browser;

   Page page;


   @BeforeMethod
    public void SetUP(){
    playwright =Playwright.create();
    browser = playwright.chromium().launch();
    page = browser.newPage();
   }


   @Test
    public void Open(){
       page.navigate("https://playwright.dev/");

       String title = page.title();
       System.out.println(title);

       Assert.assertEquals(title,"Playwright");
   }

   @AfterMethod
    public void finish(){
       browser.close();
       playwright.close();
   }
}
