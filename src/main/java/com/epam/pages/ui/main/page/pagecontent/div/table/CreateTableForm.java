package com.epam.pages.ui.main.page.pagecontent.div.table;

import com.epam.Driver.Driver;
import com.epam.pages.ui.widgets.UiDialogButtonset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.Table;

import java.util.ArrayList;
import java.util.List;

@FindBy(how = How.XPATH, using = "//form[@action=\"tbl_create.php\"]")
public class CreateTableForm extends HtmlElement {

    private UiDialogButtonset uiDialogButtonset;

    @FindBy(how = How.NAME, using = "tbl_storage_engine")
    private Select storageEngine;

    @FindBy(how = How.NAME, using = "tbl_collation")
    private Select tableCollation;

    @FindBy(how = How.NAME, using = "do_save_data")
    private Button save;

    @FindBy(how = How.ID, using = "table_columns")
    private Table table;

    private By name = By.xpath("input[@class=\"textfield\"]");
    private By type = By.xpath("select[@class=\"column_type\"]");
    private By length = By.xpath("input[@class=\"textfield\"]");
    private By defaultt = By.xpath("select[@class=\"default_type\"]");
    private By collation = By.xpath("select[1]");
    private By attributes = By.xpath("select[1]");
    private By allowNull = By.xpath("input[@class=\"allow_null\"]");
    private By index = By.xpath("select[1]");
    private By aI = By.xpath("input[1]");
    private By comments = By.xpath("input[@class=\"textfield\"]");
    private By virtuality = By.xpath("select[1]");


    private void setRow(int row, String[] data) {
        List<WebElement> workRow = table.getRows().get(row);
        workRow.get(0).findElement(this.name).sendKeys(data[0]);
        new org.openqa.selenium.support.ui.Select(workRow.get(1).findElement(this.type)).selectByVisibleText(data[1]);
        workRow.get(2).findElement(this.length).sendKeys(data[2]);
        new org.openqa.selenium.support.ui.Select(workRow.get(3).findElement(this.defaultt)).selectByVisibleText(data[3]);
        new org.openqa.selenium.support.ui.Select(workRow.get(4).findElement(this.collation)).selectByVisibleText(data[4]);
        new org.openqa.selenium.support.ui.Select(workRow.get(5).findElement(this.attributes)).selectByVisibleText(data[5]);
        if (workRow.get(6).findElement(this.allowNull).isSelected()) {
            if (!data[6].equals("true")) {
                workRow.get(6).findElement(this.allowNull).click();
            }
        } else {
            if (data[6].equals("true")) {
                workRow.get(6).findElement(this.allowNull).click();
            }
        }
        new org.openqa.selenium.support.ui.Select(workRow.get(7).findElement(this.index)).selectByVisibleText(data[7]);
        if (data[7].equals("PRIMARY") || data[7].equals("UNIQUE") || data[7].equals("INDEX") || data[7].equals("FULLTEXT") || data[7].equals("SPATIAL")) {
            this.uiDialogButtonset.clickGo();
        }
        if (workRow.get(8).findElement(this.aI).isSelected()) {
            if (!data[8].equals("true")) {
                workRow.get(8).findElement(this.aI).click();
            }
        } else {
            if (data[8].equals("true")) {
                workRow.get(8).findElement(this.aI).click();
            }
        }
        workRow.get(9).findElement(this.comments).sendKeys(data[9]);
        new org.openqa.selenium.support.ui.Select(workRow.get(10).findElement(this.virtuality)).selectByVisibleText(data[10]);
    }

    public void clickSave() {
        this.save.submit();
        Driver.waitForJSandJQueryToLoad();
    }

    private void setTableCollation(String collation) {
        this.tableCollation.selectByVisibleText(collation);
    }

    private void setStorageEngine(String storageEngine) {
        this.storageEngine.selectByVisibleText(storageEngine);
    }

    public void createTable(List<String[]> dataForFields, String collation, String storageEngine) {
        for (int i = 0; i < dataForFields.size(); i++) {
            this.setRow(i, dataForFields.get(i));
        }
        this.setTableCollation(collation);
        this.setStorageEngine(storageEngine);
    }

}
