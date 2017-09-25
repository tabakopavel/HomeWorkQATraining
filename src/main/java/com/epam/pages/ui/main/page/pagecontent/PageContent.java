package com.epam.pages.ui.main.page.pagecontent;

import com.epam.pages.ui.login.page.Login;
import com.epam.pages.ui.main.page.pagecontent.div.data.InsertDataForm;
import com.epam.pages.ui.main.page.pagecontent.div.database.CreateDataBaseMin;
import com.epam.pages.ui.main.page.pagecontent.div.table.*;
import com.epam.pages.ui.main.page.pagecontent.div.data.ResultDataForm;
import com.epam.pages.ui.main.page.pagecontent.div.database.StructureOfDataBase;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(how = How.ID, using = "page_content")
public class PageContent extends HtmlElement {

    private CreateDataBaseMin createDataBaseMin;
    private CreateTableFormMinimal createTableFormMinimal;
    private CreateTableForm createTableForm;
    private EditTableForm editTableForm;
    private StructureOfTable structureOfTable;
    private StructureOfDataBase structureOfDataBase;
    private InsertDataForm insertDataForm;
    private Login login;
    private ResultDataForm resultDataForm;
    private TableOperations tableOperations;

    public TableOperations getTableOperations() {
        return tableOperations;
    }

    public ResultDataForm getResultDataForm() {
        return resultDataForm;
    }

    public CreateDataBaseMin getCreateDataBaseMin() {
        return createDataBaseMin;
    }

    public CreateTableFormMinimal getCreateTableFormMinimal() {
        return createTableFormMinimal;
    }

    public CreateTableForm getCreateTableForm() {
        return createTableForm;
    }

    public InsertDataForm getInsertDataForm() {
        return insertDataForm;
    }

    public StructureOfDataBase getStructureOfDataBase() {
        return structureOfDataBase;
    }

    public StructureOfTable getStructureOfTable() {
        return structureOfTable;
    }

    public Login getLogin() {
        return login;
    }

    public EditTableForm getEditTableForm() {
        return editTableForm;
    }
}
