package com.epam.pages.ui.main.page.navigation.sidebar;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

@FindBy(how = How.ID, using = "pma_navigation_header")
public class NavigationHeaderContent extends HtmlElement {

    @FindBy(how = How.XPATH, using = "div[@id=\"navipanellinks\"]/a[@title=\"Reload navigation panel\"]")
    Link reload;

    @FindBy(how = How.XPATH, using = "div[@id=\"navipanellinks\"]/a[@title=\"Home\"]")
    Link home;

    @FindBy(how = How.XPATH, using = "div[@id=\"navipanellinks\"]/a[@title=\"Log out\"]")
    Link logOut;

    @FindBy(how = How.XPATH, using = "div[@id=\"navipanellinks\"]/a[@title=\"phpMyAdmin documentation\"]")
    Link documentation;

    @FindBy(how = How.XPATH, using = "div[@id=\"navipanellinks\"]/a[@title=\"Documentation\"]")
    Link documentationMySql;

    @FindBy(how = How.XPATH, using = "div[@id=\"navipanellinks\"]/a[@title=\"SideBarNavigation panel settings\"]")
    Link navPanSettings;

}
