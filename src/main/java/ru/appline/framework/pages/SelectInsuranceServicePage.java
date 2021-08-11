package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Arkadiy_Alaverdyan
 * Класс описывающий страничку выбора полиса
 */
public class SelectInsuranceServicePage extends BasePage {

    @FindBy(xpath = "//h3[text()='Минимальная']")
    private WebElement insuranceCoverageAmount;

    @FindBy(xpath = "//button[text()='Оформить']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@_ngcontent-c4]/a[.='Выбор полиса']/..")
    private WebElement title;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return SelectInsuranceServicePage - т.е. остаемся на этой странице
     */
    @Step("Проверяем что открылась страница 'Выбора тарифа страхования и доп услуг'")
    public SelectInsuranceServicePage checkOpenSelectInsuranceServicePage() {
        waitUtilElementToBeVisible(title);
        wait.until(ExpectedConditions.attributeContains(title, "class", "col-4 step-element active"));
        return this;
    }

    /**
     * Выбор тарифа страхования
     *
     * @return SelectInsuranceServicePage - т.е. остаемся на этой странице
     */
    @Step("Выбираем тариф 'минимальный'")
    public SelectInsuranceServicePage selectTariffMin() {
        scrollToElementJs(insuranceCoverageAmount);
        waitUtilElementToBeClickable(insuranceCoverageAmount).click();
        return this;
    }

    /**
     * Клик по кнопке "Оформить"
     *
     * @return RegistrationFormPage - т.е. переходим на страницу {@link RegistrationFormPage}
     */
    @Step("Кликаем по кнопке 'Оформить'")
    public RegistrationFormPage checkoutInsuranceOnline() {
        scrollToElementJs(checkoutButton);
        waitUtilElementToBeClickable(checkoutButton).click();
        return pageManager.getRegistrationFormPage().checkOpenRegistrationFormPage();
    }


}
