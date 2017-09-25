package com.epam.pages.ui.main.page.pagecontent.div.data;


import com.epam.Driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.ArrayList;
import java.util.List;

@FindBy(how = How.ID, using = "insertForm")
public class InsertDataForm extends HtmlElement {

    @FindBy(how = How.XPATH, using = "table[@class=\"insertRowTable topmargin\"]")
    List<WebElement> insertRowTable;

    By goInRowTable = By.xpath("tfoot/tr/th/input[@type=\"submit\"]");

    @FindBy(how = How.XPATH, using = "//select[@id=\"insert_rows\"]")
    Select numberOfRows;

    @FindBy(how = How.ID, using = "actions_panel")
    WebElement actionPanel;
    By go = By.id("buttonYes");

    By inputFields = By.xpath("tbody/tr/td[5]");

    public void insertRow(int row, String[] data) {
        List<WebElement> listOfFields = insertRowTable.get(row).findElements(inputFields);
        int count = 0;
        for (int i = 0; i < listOfFields.size(); i++) {
            listOfFields.get(i).findElement(getInputLocator(count + 1)).sendKeys(data[i]);
            count++;
        }
    }

    /**
     * Insert Data in fields.
     */
    public void insertData(List<String[]> dataList) {
        int count = 1;
        for (int i = 0; i < dataList.size(); i++) {
            List<WebElement> listOfFields = insertRowTable.get(i).findElements(inputFields);
            for (int i1 = 0; i1 < listOfFields.size(); i1++) {
                listOfFields.get(i1).findElement(getInputLocator(count)).sendKeys(dataList.get(i)[i1]);
                count++;
            }
        }
    }

    /**
     * Returns data from all fields which are selected to change.
     */
    public List<String[]> getRows() {
        int count = 1;
        List<String[]> listOfData = new ArrayList<String[]>();
        for (int i = 0; i < insertRowTable.size(); i++) {
            List<WebElement> listOfFields = insertRowTable.get(i).findElements(inputFields);
            String[] data = new String[listOfFields.size()];
            for (int i1 = 0; i1 < listOfFields.size(); i1++) {
                data[i1] = getAttributeifNoText(listOfFields.get(i1).findElement(getInputLocator(count)));
                count++;
            }
            listOfData.add(data);
        }
        return listOfData;
    }

    public String[] getRow(int row) {
        int count = 1;
        List<WebElement> listOfFields = insertRowTable.get(row).findElements(inputFields);
        String[] data = new String[listOfFields.size()];
        for (int i = 0; i < listOfFields.size(); i++) {
            data[i] = getAttributeifNoText(listOfFields.get(i).findElement(getInputLocator(i + 1)));
        }
        return data;
    }

    public void clickPanelGo() {
        actionPanel.findElement(go).submit();
        Driver.waitForJSandJQueryToLoad();
    }

    public void selectNumberOfRows(int numberOfRows) {
        if (Integer.parseInt(this.numberOfRows.getFirstSelectedOption().getText()) < numberOfRows) {
            for (WebElement element : this.numberOfRows.getOptions()) {
                if (Integer.parseInt(element.getText()) >= numberOfRows) {
                    this.numberOfRows.selectByVisibleText(element.getText());
                    Driver.waitForJSandJQueryToLoad();
                    break;
                }
            }
        }

    }

    /**
     * Fields where there is no Text,returns Attribute "value".
     */
    private String getAttributeifNoText(WebElement webElement) {
        if (webElement.getText().equals("")) {
            return webElement.getAttribute("value");
        } else {
            return webElement.getText();
        }
    }

    /**
     * Number Of Tab starts from "1"
     */
    private By getInputLocator(int numberOfTab) {
        return By.xpath("(input[@tabindex=\"" + numberOfTab + "\"] | textarea[@tabindex=\"" + numberOfTab + "\"])");
    }
}
