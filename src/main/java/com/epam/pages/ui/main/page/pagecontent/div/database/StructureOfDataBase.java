package com.epam.pages.ui.main.page.pagecontent.div.database;

import com.epam.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.Table;

import java.util.List;

@FindBy(how = How.ID, using = "tableslistcontainer")
public class StructureOfDataBase extends HtmlElement {

    @FindBy(how = How.ID, using = "structureTable")
    Table dataBaseTable;

    @FindBy(how = How.NAME, using = "tablesForm_checkall")
    CheckBox checkAll;

    @FindBy(how = How.NAME, using = "submit_mult")
    Select action;

    By nameOfTablesLocator = By.xpath("tbody[1]/tr/th[1]/a[1]");

    /**
     * Click specified table in tables list
     */
    public void clickTableByName(String nameOfTable) {
        List<WebElement> listOfTables = dataBaseTable.findElements(nameOfTablesLocator);
        for (WebElement webElement : listOfTables) {
            if (webElement.getText().equalsIgnoreCase(nameOfTable)) {
                webElement.click();
                Driver.waitForJSandJQueryToLoad();
                break;
            }
        }
    }

    public void checkAll() {
        if (!this.checkAll.isSelected()) {
            this.checkAll.select();
        }
    }

    public void uncheckAll() {
        if (this.checkAll.isSelected()) {
            this.checkAll.select();
        }
    }

    public void chooseAction(String actionForTables) {
        action.selectByVisibleText(actionForTables);
    }

}
