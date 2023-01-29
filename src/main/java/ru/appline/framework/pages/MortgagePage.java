package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class MortgagePage extends BasePage {

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement cookiesBtnClose;

    @FindBy(xpath = "//h1[contains(text(), \"Ипотека на вторичное жильё от 10,9%\")]")
    private List<WebElement> title;

    @FindBy(xpath = "//label[contains(text(), \"Стоимость недвижимости\")]//../input")
    private WebElement estateValue;

    @FindBy(xpath = "//label[contains(text(), \"Первоначальный взнос\")]//../input")
    private WebElement initialFee;

    @FindBy(xpath = "//label[contains(text(), \"Срок кредита\")]//../input")
    private WebElement creditTerm;

    @FindBy(xpath = ".//div[contains(@class,'gRWVZjEp676wfaZSnbjVR')]")
    List<WebElement> listOfCheckbox;

    @FindBy(xpath = ".//div[@data-test-id='main-results-block']/../..//span")
    List<WebElement> listOfResultMenu;

    @Step("Проверяем что открылась страница 'Заполнение формы'")
    public MortgagePage checkOpenMortgagePage() {
        waitUtilElementToBeVisible(title.get(1));
        wait.until(ExpectedConditions.attributeContains(title.get(1), "class", "kit-heading kit-heading_l product-teaser-full-width__header"));
        return this;
    }

    @Step("Заполняем поле '{nameField}' значением '{value}'")
    public MortgagePage fillField(String nameField, char[] value) {
        WebElement element = null;
        switch (nameField) {
            case "Стоимость недвижимости":
                fillInputField(estateValue, value);
                element = estateValue;
                break;
            case "Первоначальный взнос":
                fillInputField(initialFee, value);
                element = initialFee;
                break;
            case "Срок кредита":
                fillInputField(creditTerm, value);
                element = creditTerm;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Оформления ипотеки'");

        }
        Actions actions = new Actions(driverManager.getDriver());
        actions.moveToElement(element);
        actions.perform();
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                String.valueOf(value), element.getAttribute("value").replace(" ", ""));
        return this;
    }

    @Step("Проверяем поле '{nameField}' со значением '{value}'")
    public MortgagePage checkParameters(String nameOfParameter, String meaning) {
        for (WebElement parameter : listOfResultMenu) {
            if (parameter.getText().contains(nameOfParameter)) {
                waitStabilityPage(5000, 250);
                String parameterInString = parameter
                        .findElement(By.xpath("./..//span/span"))
                        .getText()
                        .replaceAll("[^0-9,]", "");
                Assert.assertEquals("Параметр " + nameOfParameter + " " + parameterInString +" не верный", meaning, parameterInString);
                return this;
            }
        }
        Assert.fail("Параметр не найден");
        return this;
    }
    @Step("Переходим на другой фрейм")
    public MortgagePage goOtherFrame(){
        driverManager.getDriver().switchTo().frame(waitElement("//*[@sandbox=\"allow-forms allow-scripts allow-same-origin allow-popups allow-top-navigation\"]", driverManager.getDriver()));
        return this;
    }

    private WebElement waitElement(String elem, WebDriver driver) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elem)));
    }
    @Step("Клик по чекбоксу {nameOfCheckbox}")
    public MortgagePage clickCheckbox(String nameOfCheckbox) {
        for (WebElement form : listOfCheckbox) {
            if (form.findElement(By.xpath("./div/span")).getText().contains(nameOfCheckbox)) {
                clickToElementJs(form.findElement(By.xpath(".//input")));
                return this;
            }
        }
        Assert.fail("Checkbox не найден");
        return this;
    }
    @Step("Закрытия сообщения cookies")
    public MortgagePage closeCookiesDialog() {
        waitUtilElementToBeClickable(cookiesBtnClose).click();
        return this;
    }
}
