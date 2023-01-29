package ru.appline.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.DriverManager;
import ru.appline.framework.managers.PageManager;

public class BasePage {

    protected final DriverManager driverManager = DriverManager.getDriverManager();

    protected PageManager pageManager = PageManager.getPageManager();

    protected Actions action = new Actions(driverManager.getDriver());

    protected JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 10, 1000);


    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
    protected WebElement scrollToElementAndClickJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
        return element;
    }

    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void fillInputField(WebElement field, char[] value) {
        //field.getLocation();
        waitUtilElementToBeClickable(field).click();
        field.sendKeys(Keys.CONTROL + "A");
        field.sendKeys(Keys.BACK_SPACE);
        Actions actions = new Actions(driverManager.getDriver());
        for (char c : value) {
            actions.moveToElement(field).pause(100).click().sendKeys(String.valueOf(c)).pause(100).build().perform();
        }
        waitStabilityPage(5000, 250);
    }
    protected void waitStabilityPage(int maxWaitMillis, int pollDelimiter){
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis()<startTime + maxWaitMillis){
            String prevState = driverManager.getDriver().getPageSource();
            wait(pollDelimiter);
            if(prevState.equals(driverManager.getDriver().getPageSource())){
                return;
            }
        }
    }
    protected void wait(int mlSec){
        try {
            Thread.sleep(mlSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
