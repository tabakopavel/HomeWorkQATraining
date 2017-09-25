package com.epam.pages;

import com.epam.Driver.Driver;
import com.epam.pages.ui.main.page.navigation.navigationbar.TopMenu;
import com.epam.pages.ui.main.page.navigation.sidebar.SideBarNavigation;
import com.epam.pages.ui.main.page.pagecontent.PageContent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class HomePage {

    private TopMenu topMenu;
    private SideBarNavigation sideBarNavigation;
    private PageContent pageContent;

    public HomePage() {
        HtmlElementLoader.populatePageObject(this, Driver.getDriver());
    }

    public static HomePage open() {
        Driver.getDriver().get(System.getProperty("test.home_url"));
        return new HomePage();
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }

    public SideBarNavigation getSideBarNavigation() {
        return sideBarNavigation;
    }

    public PageContent getPageContent() {
        return pageContent;
    }

}
