package com.epam.pages.ui.main.page.pagecontent.div.data;

import com.epam.Driver.Driver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@FindBy(how = How.XPATH, using = "//form[@action=\"tbl_row_action.php\"]")
public class ResultDataForm extends HtmlElement {

    @FindBy(how = How.XPATH, using = "div[@class=\"print_ignore\"]/input[@type=\"checkbox\"]")
    CheckBox checkAll;

    @FindBy(how = How.XPATH, using = "div[@class=\"print_ignore\"]/button[@value=\"edit\"]")
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
