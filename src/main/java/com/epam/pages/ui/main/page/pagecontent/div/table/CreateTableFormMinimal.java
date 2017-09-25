package com.epam.pages.ui.main.page.pagecontent.div.table;


import com.epam.Driver.Driver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(how = How.ID, using = "create_table_form_minimal")
public class CreateTableFormMinimal extends HtmlElement {

    @FindBy(how = How.NAME, using = "table")
    TextInput nameOfTable;

    @FindBy(how = How.NAME, using = "num_fields")
    TextInput numberOfColumns;

    @FindBy(how = How.XPATH, using = "fieldset/input[@value=\"Go\"]")
    Button go;

    private void setNameOfTable(String nameField) {
        this.nameOfTable.sendKeys(nameField);
    }

    private void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns.clear();
        this.numberOfColumns.sendKeys(String.valueOf(numberOfColumns));
    }

    public void clickGo() {
        this.go.click();
        Driver.waitForJSandJQueryToLoad();
    }

    public void createTable(String nameOfTable, String numberOfColumns) {
        this.setNameOfTable(nameOfTable);
        this.setNumberOfColumns(Integer.parseInt(numberOfColumns));
    }
}
