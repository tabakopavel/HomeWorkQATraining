package com.epam.pages;

import com.epam.Driver.Driver;
import com.epam.pages.ui.main.page.pagecontent.PageContent;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class LoginPage {

    private PageContent pageContent;

    public LoginPage() {
        HtmlElementLoader.populatePageObject(this, Driver.getDriver());
    }

    public static LoginPage open() {
        Driver.getDriver().get(System.getProperty("test.login_url"));
        return new LoginPage();
    }

    public PageContent getPageContent() {
        return pageContent;
    }
}
