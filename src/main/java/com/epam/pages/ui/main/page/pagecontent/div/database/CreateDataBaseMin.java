package com.epam.pages.ui.main.page.pagecontent.div.database;

import com.epam.Driver.Driver;
import com.epam.pages.ui.widgets.UiDialogButtonset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

@FindBy(how = How.ID, using = "li_create_database")
public class CreateDataBaseMin extends HtmlElement {
    private UiDialogButtonset uiDialogButtonset;

    @FindBy(how = How.ID, using = "create_database_form")
    private WebElement creationForm;

    @FindBy(how = How.ID, using = "text_create_db")
    private TextInput nameOfDBField;

    @FindBy(how = How.NAME, using = "db_collation")
    private Select collation;


    @FindBy(how = How.ID, using = "buttonGo")
    private WebElement create;

    @FindBy(how = How.XPATH, using = "//form[@id=\"dbStatsForm\"]/button[@value=\"Drop\"]")
    private Button drop;

    @FindBy(how = How.XPATH, using = "//form[@id=\"dbStatsForm\"]/table[@id=\"tabledatabases\"]")
    private WebElement tableDataBases;
    private By dataBasesName = By.xpath("tbody/tr/td[@class=\"name\"]/a");
    private By dataBasesCheckBox = By.xpath("tbody/tr/td/input[@type=\"checkbox\"]");


    private void selectCollation(String collation) {
        this.collation.selectByVisibleText(collation);
    }

    private void setNameField(String nameOfDataBase) {
        this.nameOfDBField.sendKeys(nameOfDataBase);
    }

    public void clickCreate() {
        this.create.click();
        Driver.waitForJSandJQueryToLoad();
    }

    /**
     * Create DataBase, without check of existence
     * of DataBase with the same name.
     */
    public void createDataBase(String collation, String name) {
        this.selectCollation(collation);
        this.setNameField(name);
    }

    /**
     * Drop DataBase, without check of existence.
     */
    public void DropDataBaseByName(String nameOfDataBase) {
        List<WebElement> listOfDataBases = tableDataBases.findElements(dataBasesName);
        List<WebElement> listOfDataBasesCheckBox = tableDataBases.findElements(dataBasesCheckBox);
        for (int i = 0; i < listOfDataBases.size(); i++) {
            if (listOfDataBases.get(i).getText().trim().equals(nameOfDataBase)) {
                listOfDataBasesCheckBox.get(i).click();
                drop.click();
                uiDialogButtonset.checkForConfirmAndConfirm();
                break;
            }
        }
    }

    /**
     * Check DataBase for existence.
     */
    public boolean checkDataBaseForExistence(String nameOfDataBase) {
        List<WebElement> listOfDataBases = tableDataBases.findElements(dataBasesName);
        for (int i = 0; i < listOfDataBases.size(); i++) {
            if (listOfDataBases.get(i).getText().trim().equals(nameOfDataBase)) {
                return true;
            }
        }
        return false;
    }

}
