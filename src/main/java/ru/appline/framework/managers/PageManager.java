package ru.appline.framework.managers;

import ru.appline.framework.pages.MortgagePage;
import ru.appline.framework.pages.HomePage;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка ипотеки
     */
    private MortgagePage mortgagePage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return StartPage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link MortgagePage}
     *
     * @return RegistrationFormPage
     */
    public MortgagePage getMortgagePage() {
        if (mortgagePage == null) {
            mortgagePage = new MortgagePage();
        }
        return mortgagePage;
    }
}
