package ru.appline.framework.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.AllureJunit4;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.util.ResultsUtils;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.framework.managers.DriverManager;

public class MyAllureListener extends AllureJunit4 {
    public void testFailure(Failure failure) {
        byte[] byteImage = ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
        getLifecycle().addAttachment("Screenshot", "image/png", ".png", byteImage);
        super.testFailure(failure);
    }
}
