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

    @FindBy(xpath = "//span[text()=\"Страхование жизни и здоровья\"]/../span[@class]/label/div/input")
    private WebElement insurance;

    @FindBy(xpath = "//span[text()=\"Сумма кредита\"]/../div/span/span")
    private WebElement amountCredit;

    @FindBy(xpath = "//span[text()=\"Ежемесячный платеж\"]/../div/span/span")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//span[text()=\"Необходимый доход\"]/../div/span/span")
    private WebElement requiredIncome;

    @FindBy(xpath = "//div[@class=\"_3gNM-Vy-F04mXdV3m7eDa5\"]/span[@class=\"_19zitcoxuphOm2IGRCrUgj _3tYtxkewCDzsmb7j3HW491 _1rMtvwq1_0KwWEWAsjJ98j\"]/span")
    private WebElement interestRate;

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
    public MortgagePage checkFields(String nameField, String value) {
        switch (nameField) {
            case ("Сумма кредита"):
                Assert.assertEquals("Сумма кредита не совпадает", value, amountCredit.getAttribute("innerHTML").replaceAll("[^0-9]", ""));
                break;
            case ("Ежемесячный платеж"):
                Assert.assertEquals("Ежемесячный платеж не совпадает", value, monthlyPayment.getAttribute("innerHTML").replaceAll("[^0-9]", ""));
                break;
            case ("Необходимый доход"):
                Assert.assertEquals("Необходимый доход не совпадает", value, requiredIncome.getAttribute("innerHTML").replaceAll("[^0-9]", ""));
                break;
            case ("Процентная ставка"):
                Assert.assertEquals("Процентная ставка не совпадает", value, interestRate.getAttribute("innerHTML").replaceAll("[^0-9,]", ""));
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Оформления ипотеки'");
        }
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
    @Step("Убираем галочку со страхования")
    public MortgagePage clickInsurance(){
        scrollToElementAndClickJs(insurance);

        return this;
    }
    @Step("Закрытия сообщения cookies")
    public MortgagePage closeCookiesDialog() {
        waitUtilElementToBeClickable(cookiesBtnClose).click();
        return this;
    }
}
