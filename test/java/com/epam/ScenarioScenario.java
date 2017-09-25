package com.epam;

import com.epam.pages.HomePage;
import com.epam.pages.LoginPage;
import excel.read.ExcelRead;
import jxl.read.biff.BiffException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class ScenarioScenario extends BaseScenario {


    @DataProvider(name = "tableStructure")
    public Object[][] getTableStructure() {
        List<String[]> list = null;
        try {
            list = ExcelRead.readFromExcel(".//test//testData.xls", "testTable", 11);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return new Object[][]{
                {list},
        };
    }

    @DataProvider(name = "tableData")
    public Object[][] getDataForTable() {
        List<String[]> list = null;
        try {
            list = ExcelRead.readFromExcel(".//test//testData.xls", "testRows", 6);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return new Object[][]{
                {list},
        };
    }


    @Test
    @Parameters({"login", "password", "language"})
    public void login(String login, String password, String language) {
        LoginPage loginPage = LoginPage.open();
        loginPage.getPageContent().getLogin().selectLanguage(language);
        loginPage.getPageContent().getLogin().insertLogAndPass(login, password);
        HomePage homePage = loginPage.getPageContent().getLogin().clicklogin();
        Assert.assertTrue(homePage.getPageContent().isDisplayed());
    }


    @Test
    @Parameters({"nameOfDB"})
    public void createDB(String nameOfDB) {
        HomePage homePage = new HomePage();
        homePage.getSideBarNavigation().clickHome();
        homePage.getTopMenu().clickLinkByName("Databases");
        homePage.getSideBarNavigation().clickDataBaseByName("New");
        if (homePage.getPageContent().getCreateDataBaseMin().checkDataBaseForExistence(nameOfDB)) {
            homePage.getPageContent().getCreateDataBaseMin().DropDataBaseByName(nameOfDB);
        }
        homePage.getPageContent().getCreateDataBaseMin().createDataBase("utf8_general_ci", nameOfDB);
        homePage.getPageContent().getCreateDataBaseMin().clickCreate();
    }

    @Test(dataProvider = "tableStructure")
    public void createTable(List<String[]> list) {
        HomePage homePage = new HomePage();
        homePage.getSideBarNavigation().clickHome();
        homePage.getSideBarNavigation().clickDataBaseByName("auth");
        homePage.getPageContent().getCreateTableFormMinimal().createTable("users", "6");
        homePage.getPageContent().getCreateTableFormMinimal().clickGo();
        homePage.getPageContent().getCreateTableForm().createTable(list, "utf8_general_ci", "InnoDB");
        homePage.getPageContent().getCreateTableForm().clickSave();
    }


    @Test(dataProvider = "tableStructure")
    public void checkTable(List<String[]> list) {
        HomePage homePage = new HomePage();
        homePage.getSideBarNavigation().clickHome();
        homePage.getSideBarNavigation().clickDataBaseByName("auth");
        homePage.getSideBarNavigation().clickTableInSpecifiedDB("users", "auth");
        homePage.getTopMenu().clickLinkByName("Structure");
        homePage.getPageContent().getStructureOfTable().checkAll();
        homePage.getPageContent().getStructureOfTable().clickChange();
        List<String[]> listFromTable = homePage.getPageContent().getEditTableForm().getAllRows();
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(listFromTable.get(i), list.get(i));
        }

    }

    @Test
    @Parameters({"storageEngine", "collation"})
    public void checkStorAndCollat(String storageEngine, String collation) {
        HomePage homePage = new HomePage();
        homePage.getSideBarNavigation().clickTableInSpecifiedDB("users", "auth");
        homePage.getTopMenu().clickLinkByName("Operations");
        Assert.assertTrue(homePage.getPageContent().getTableOperations().checkCollation(collation));
        Assert.assertTrue(homePage.getPageContent().getTableOperations().checkStorageEngine(storageEngine));
    }


    @Test(dataProvider = "tableData")
    public void createRows(List<String[]> list) {
        HomePage homePage = new HomePage();
        homePage.getSideBarNavigation().clickHome();
        homePage.getSideBarNavigation().clickTableInSpecifiedDB("users", "auth");
        homePage.getTopMenu().clickLinkByName("Insert");
        homePage.getPageContent().getInsertDataForm().insertData(list);
        homePage.getPageContent().getInsertDataForm().clickPanelGo();
    }

    @Test(dataProvider = "tableData")
    public void checkRows(List<String[]> list) {
        HomePage homePage = new HomePage();
        homePage.getTopMenu().clickLinkByName("Browse");
        homePage.getPageContent().getResultDataForm().checkAll();
        homePage.getPageContent().getResultDataForm().clickChange();
        List<String[]> listFromTable = homePage.getPageContent().getInsertDataForm().getRows();
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(listFromTable.get(i), list.get(i));
        }
    }

    @Test
    @Parameters({"nameOfDB"})
    public void dropDB(String nameOfDB) {
        HomePage homePage = new HomePage();
        homePage.getTopMenu().clickLinkByName("Databases");
        homePage.getPageContent().getCreateDataBaseMin().DropDataBaseByName(nameOfDB);


    }
}
