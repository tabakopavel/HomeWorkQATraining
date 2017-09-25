package com.epam.pages.ui.main.page.pagecontent.div.table;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;


@FindBy(how = How.ID, using = "tableOptionsForm")
public class TableOperations extends HtmlElement {


    @FindBy(how = How.NAME, using = "new_tbl_storage_engine")
    Select storageEngine;

    @FindBy(how = How.NAME, using = "tbl_collation")
    Select tblCollation;

    @FindBy(how = How.ID, using = "tbl_collation")
    WebElement AIValue;


    public boolean checkStorageEngine(String storageEngine) {
        if (this.storageEngine.getFirstSelectedOption().getText().equals(storageEngine)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkCollation(String collation) {
        if (this.tblCollation.getFirstSelectedOption().getText().equals(collation)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkAI(String AIvalue) {

        if (this.AIValue.getAttribute("value").equals(AIvalue)) {
            return true;
        } else {
            return false;
        }

    }

}
