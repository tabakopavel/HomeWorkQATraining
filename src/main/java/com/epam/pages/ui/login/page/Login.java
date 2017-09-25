package com.epam.pages.ui.login.page;

import com.epam.Driver.Driver;
import com.epam.pages.HomePage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(how = How.XPATH, using = "div[@class=\"container\"][1]")
public class Login extends HtmlElement {

    @FindBy(how = How.ID, using = "sel-lang")
    private Select langSelection;

    @FindBy(how = How.ID, using = "input_username")
    private TextInput login;

    @FindBy(how = How.ID, using = "input_password")
    private TextInput password;

    @FindBy(how = How.ID, using = "input_go")
    private Button go;

    public void selectLanguage(String language) {
        this.langSelection.selectByVisibleText(language);
        Driver.waitForPageToLoad();
        Driver.waitForJSandJQueryToLoad();
    }

    public void insertLogAndPass(String login, String password) {
        this.login.clear();
        this.login.sendKeys(login);
        this.password.clear();
        this.password.sendKeys(password);
    }

    public HomePage clicklogin() {
        this.go.click();
        Driver.waitForJSandJQueryToLoad();
        return new HomePage();
    }

}