package com.epam.pages.ui.main.page.navigation.sidebar;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@FindBy(how = How.ID, using = "pma_navigation_tree")
public class NavigationTreeContent extends HtmlElement {

    @FindBy(how = How.ID, using = "pma_navigation_tree_content")
    private WebElement navigationTreeContent;

    private By linksDB = By.xpath("ul[1]/li/a[@class=\"hover_show_full\"]");
    private By linksTable = By.xpath("ul[1]/li/div[@class=\"list_container\"]/ul/li/a[@class=\"hover_show_full\"]");

    /**
     * Returns Link by specified DB
     */
    public WebElement getLinkForDBByName(String nameOfDataBase) {
        List<WebElement> listOfLinks = navigationTreeContent.findElements(linksDB);
        for (WebElement link : listOfLinks) {
            if (link.getText().trim().equals(nameOfDataBase)) {
                return link;
            }
        }
        return null;
    }

    /**
     * Returns Link by specified Table
     */
    public WebElement getLinkForTableByName(String nameOfTable) {
        List<WebElement> listOfLinks = navigationTreeContent.findElements(linksTable);
        for (WebElement link : listOfLinks) {
            if (link.getText().trim().equals(nameOfTable)) {
                return link;
            }
        }
        return null;
    }


}
