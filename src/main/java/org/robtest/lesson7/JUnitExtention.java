package org.robtest.lesson7;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import java.io.ByteArrayInputStream;
public class JUnitExtention implements TestWatcher {
    ByteArrayInputStream screenShot;
    public void setScreenShot(ByteArrayInputStream screenShot) {
        this.screenShot = screenShot;
    }
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.addAttachment("Скрин перед закрытие браузера", screenShot);
    }
}