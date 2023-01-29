package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement cookiesBtnClose;

    @FindBy(xpath = "//li[contains(@class,'kitt-top-menu__item_first')]/a[@role or @aria-expanded]")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[@data-cga_click_top_menu]")
    private List<WebElement> listSubMenu;


    @Step("Закрытия сообщения cookies")
    public HomePage closeCookiesDialog() {
        waitUtilElementToBeClickable(cookiesBtnClose).click();
        return this;
    }

    @Step("Выбираем '{nameBaseMenu}' в главном меню")
    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assert.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    @Step("Выбираем '{nameSubMenu}' в подменю главного меню")
    public MortgagePage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getMortgagePage().checkOpenMortgagePage();
            }
        }
        Assert.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getMortgagePage();
    }


}
