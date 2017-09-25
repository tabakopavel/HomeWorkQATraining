package com.epam.pages.ui.main.page.pagecontent.div.table;

import com.epam.Driver.Driver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(how = How.ID, using = "structure_content")
public class StructureOfTable extends HtmlElement {

    @FindBy(how = How.ID, using = "fieldsForm_checkall")
    CheckBox checkAll;

    @FindBy(how = How.XPATH, using = "(//button[@name=\"submit_mult\" and @value=\"change\"])")
    Button change;

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

    public void clickChange() {
        this.change.click();
        Driver.waitForJSandJQueryToLoad();
    }

}
