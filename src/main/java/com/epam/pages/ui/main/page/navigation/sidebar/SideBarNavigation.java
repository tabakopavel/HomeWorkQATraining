package com.epam.pages.ui.main.page.navigation.sidebar;

import com.epam.Driver.Driver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(how = How.ID, using = "pma_navigation_content")
public class SideBarNavigation extends HtmlElement {

    private NavigationHeaderContent navigationHeaderContent;
    private NavigationTreeContent navigationTreeContent;

    public void clickRefresh() {
        this.navigationHeaderContent.reload.click();
        Driver.waitForJSandJQueryToLoad();
    }

    public void clickHome() {
        this.navigationHeaderContent.home.click();
        Driver.waitForJSandJQueryToLoad();
    }

    public void clickLogOut() {
        this.navigationHeaderContent.logOut.click();
        Driver.waitForJSandJQueryToLoad();
    }

    public void clickDocumentation() {
        this.navigationHeaderContent.documentation.click();
        Driver.waitForJSandJQueryToLoad();
    }

    public void clickDocumentationMySql() {
        this.navigationHeaderContent.documentationMySql.click();
        Driver.waitForJSandJQueryToLoad();
    }

    public void clickNavPanSettings() {
        this.navigationHeaderContent.navPanSettings.click();
        Driver.waitForJSandJQueryToLoad();
    }

    /**
     * Click specified DataBase.
     */
    public void clickDataBaseByName(String nameOfDataBase) {
        try {
            this.navigationTreeContent.getLinkForDBByName(nameOfDataBase).click();
            Driver.waitForJSandJQueryToLoad();
        } catch (NullPointerException e) {
            e.printStackTrace();
            //To Add Logging
        }
    }

    /**
     * Click Table in specified DataBase.
     */
    public void clickTableInSpecifiedDB(String nameOfTable, String nameOfDataBase) {
        try {
            this.navigationTreeContent.getLinkForDBByName(nameOfDataBase).click();
            Driver.waitForJSandJQueryToLoad();
            this.navigationTreeContent.getLinkForTableByName(nameOfTable).click();
            Driver.waitForJSandJQueryToLoad();
        } catch (NullPointerException e) {
            e.printStackTrace();
            //To Add Logging
        }


    }

}
