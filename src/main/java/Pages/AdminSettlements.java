package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import com.microsoft.playwright.options.SelectOption;
import org.opentest4j.AssertionFailedError;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminSettlements extends BasePage{


    Locator addAttributeButton;
    Locator codeInput;
    Locator nameInput;

    Locator controlTypeSelect;

    Locator addSaveButton;
    public AdminSettlements(Page page) {
        super(page);

        addAttributeButton = page.getByText(" Add attribute ");
        codeInput = page.getByPlaceholder("Code Must be 50 characters or fewer ");
        controlTypeSelect = page.locator("//mat-select[@data-e2e='select-search-mat-select']");
        nameInput = page.getByPlaceholder("Name Must be 200 characters or fewer ");
        addSaveButton = page.locator("//button[@data-e2e='dialog-positive-button']");
    }


    public AdminSettlements openAdminSettlementsScreen(){
        AdminDropdown.click();
        page.getByText(" Settings ").click();
        page.reload();
        page.locator("//a[text()='Settlement']").click();
        return this;
    }

    public AdminSettlements clickOnAddAttributeButton(){
        addAttributeButton.click();
        return this;
    }

    public AdminSettlements selectInputAttributeType(){
        List<Locator> listOfcomboboxes = controlTypeSelect.all();
        listOfcomboboxes.get(3).click();
        page.getByText(" Input ").click();

        return this;
    }
    public AdminSettlements enterCode(String code){
        codeInput.fill(code);
        return this;
    }

    public AdminSettlements enterName(String name){
        nameInput.type(name);
        return this;
    }

    public AdminSettlements clickOnAddSaveButton(){
        addSaveButton.click();
        return this;
    }




    public boolean isAddedItemDisplayedOnGrid(String name){

            List<Locator> listOfNames = page.locator("//td").all();
            for(int i = 0; i<listOfNames.size(); i++ ){
                System.out.println(listOfNames.get(i).innerText());
        }
        boolean result = listOfNames.stream().anyMatch(n -> n.innerText().equals(name));
        System.out.println(result);
        System.out.println(name);
        if(result== true){
            return true;
        }else {
            Assert.fail("Added attribute note visible");
            return false;
        }







    }






}
