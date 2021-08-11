package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку страхование путешественников
 */
public class InsurancePage extends BasePage {

    @FindBy(xpath = "//h1[@data-test-id]")
    private WebElement title;


    @FindBy(xpath = "//*[text()='Оформить онлайн']/../../a[@data-test-id]")
    private WebElement buttonCheckoutOnline;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    @Step("Проверяем что открылась страница 'Страхование путешественников'")
    public InsurancePage checkOpenInsurancePage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Страхование путешественников", title.getText());
        return this;
    }

    /**
     * Кликаем по кнопку оформить онлайн
     *
     * @return SelectInsuranceServicePage - т.е. переходим на страницу {@link SelectInsuranceServicePage}
     */
    @Step("Кликаем по кнопке 'Оформить онлайн' на странице 'Страхование путешественников'")
    public SelectInsuranceServicePage checkoutOnline() {
        waitUtilElementToBeClickable(buttonCheckoutOnline).click();
        return pageManager.getSelectInsuranceServicePage().checkOpenSelectInsuranceServicePage();
    }


}
