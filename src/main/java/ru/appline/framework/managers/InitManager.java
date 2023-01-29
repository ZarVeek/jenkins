package ru.appline.framework.managers;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.utils.PropConst.*;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для инициализации фреймворка
 */
public class InitManager {

    private static final TestPropManager props = TestPropManager.getTestPropManager();

    private static final DriverManager driverManager = DriverManager.getDriverManager();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
