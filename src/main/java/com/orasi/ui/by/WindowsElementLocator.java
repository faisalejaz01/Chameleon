package com.orasi.ui.by;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

@SuppressWarnings("unused")
public class WindowsElementLocator implements ElementLocator {
    private final WebDriver driver;
    private final ByWindows by;

    public WindowsElementLocator(final WebDriver driver, final Field field) {
        this(driver, new ByWindowsAnnotations(field));
    }

    public WindowsElementLocator(final WebDriver driver, final ByWindowsAnnotations field) {
        this.driver = driver;
        this.by = field.buildBy();
    }

    @Override
    public WebElement findElement() {
        return null;
    }

    @Override
    public List<WebElement> findElements() {
        return null;
    }
}
