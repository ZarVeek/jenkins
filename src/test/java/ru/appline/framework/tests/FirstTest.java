package ru.appline.framework.tests;

import org.junit.Test;
import ru.appline.framework.basetestsclass.BaseTests;

public class FirstTest extends BaseTests {

    @Test
    public void startTest() {
        app.getHomePage()
                .closeCookiesDialog()
                .selectBaseMenu("Ипотека")
                .selectSubMenu("Ипотека на вторичное жильё")
                .checkOpenMortgagePage()
                .goOtherFrame()
                .fillField("Стоимость недвижимости", "5180000".toCharArray())
                .fillField("Первоначальный взнос", "3058000".toCharArray())
                .fillField("Срок кредита", "30".toCharArray())
                .clickInsurance()
                .checkFields("Сумма кредита", "2122000")
                .checkFields("Ежемесячный платеж", "21664")
                .checkFields("Необходимый доход", "36829")
                .checkFields("Процентная ставка", "11");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
