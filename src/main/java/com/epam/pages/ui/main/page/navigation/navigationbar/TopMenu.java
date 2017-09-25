package com.epam.pages.ui.main.page.navigation.navigationbar;

import com.epam.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@FindBy(how = How.ID, using = "floating_menubar")
public class TopMenu extends HtmlElement {

    @FindBy(how = How.ID, using = "topmenucontainer")
    private WebElement topMenuContainer;

    private By linksTopMenu = By.xpath("ul[@id=\"topmenu\"]/li/a");

    /**
     * Returns link by the specified name.
     */
    private WebElement getLinkByName(String nameOfLink) {
        List<WebElement> listOfTopMenuLinks = topMenuContainer.findElements(linksTopMenu);
        for (WebElement menuLink : listOfTopMenuLinks) {
            if (menuLink.getText().trim().equals(nameOfLink)) {
                return menuLink;
            }
        }
        return null;
    }

    /**
     * Click link by specified name.
     */
    public void clickLinkByName(String nameOfLink) {
        try {
            this.getLinkByName(nameOfLink).click();
            Driver.waitForJSandJQueryToLoad();
        } catch (NullPointerException e) {
            e.printStackTrace();
            //To Add Logging
        }
    }
}
