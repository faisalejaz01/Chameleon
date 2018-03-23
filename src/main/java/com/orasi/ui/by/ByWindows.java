package com.orasi.ui.by;
/*
Copyright 2007-2011 Selenium committers

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import java.io.Serializable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.orasi.Beta;
import com.orasi.utils.JavaUtilities;

import io.appium.java_client.FindsByFluentSelector;
import io.appium.java_client.MobileSelector;

/**
 * Mechanism used to locate elements within a document. In order to create your own locating
 * mechanisms, it is possible to subclass this class and override the protected methods as required,
 * though it is expected that that all subclasses rely on the basic finding mechanisms provided
 * through static methods of this class:
 *
 * <code>
 * public WebElement findElement(WebDriver driver) {
 *     WebElement element = driver.findElement(By.id(getSelector()));
 *     if (element == null)
 *       element = driver.findElement(By.name(getSelector());
 *     return element;
 * }
 * </code>
 */
public abstract class ByWindows extends By {

    /**
     * @param accessibilityId
     *            The value of the "accessibilityId" attribute to search for
     * @return a By which locates elements by the accessibilityId
     */
    @Beta
    public static ByWindows accessibilityId(final String accessibilityId) {
        if (!JavaUtilities.isValid(accessibilityId)) {
            throw new IllegalArgumentException(
                    "Cannot find elements when show text is null.");
        }

        return new ByAccessibilityId(accessibilityId);
    }

    @Beta
    public static class ByAccessibilityId extends ByWindows implements Serializable {

        private static final long serialVersionUID = 376317282960469555L;

        private final String accessibilityId;

        public ByAccessibilityId(String accessibilityId) {
            this.accessibilityId = accessibilityId;
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            return (List<WebElement>) ((FindsByFluentSelector<?>) context)
                    .findElements(MobileSelector.ACCESSIBILITY.toString(), accessibilityId);
        }

        @Override
        public WebElement findElement(SearchContext context) {
            return ((FindsByFluentSelector<?>) context)
                    .findElement(MobileSelector.ACCESSIBILITY.toString(), accessibilityId);
        }

        @Override
        public String toString() {
            return "By.accessibilityId: " + accessibilityId;
        }
    }
}