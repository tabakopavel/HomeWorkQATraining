package com.epam.pages.ui.widgets;

import com.epam.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(how = How.XPATH, using = "//div[@class=\"ui-dialog-buttonset\"]")
public class UiDialogButtonset extends HtmlElement {
    By formLoc = By.xpath("//div[@class=\"ui-dialog-buttonset\"]");

    @FindBy(how = How.XPATH, using = "button[1]")
    private Button go;

    @FindBy(how = How.XPATH, using = "button[1]")
    private Button cancel;

    public void checkForConfirmAndConfirm() {
        if (exist()) {
            clickGo();
            Driver.waitForJSandJQueryToLoad();
        }
    }

    public void clickGo() {
        this.go.click();
        Driver.waitForJSandJQueryToLoad();
    }

    public void clickCancel() {
        this.cancel.click();
    }

    private boolean exist() {
        try {
            Driver.getDriver().findElement(formLoc).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
