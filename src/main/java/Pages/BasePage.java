package Pages;

import com.microsoft.playwright.*;

public class BasePage {


   public static Page page;

   Locator AdminDropdown;

   public BasePage(Page page){
       this.page = page;

       AdminDropdown = page.getByText(" Admin ");

   }







}
