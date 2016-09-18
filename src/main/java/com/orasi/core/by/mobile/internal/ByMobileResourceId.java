package com.orasi.core.by.mobile.internal;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ByMobileResourceId  extends ByMobileHelper.BaseBy {
    private String resourceId;

    public ByMobileResourceId( String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public WebElement findElement(SearchContext context) {
        if (context instanceof WebDriver) {
            context = null;
        }
        WebElement  o = context.findElement(By.xpath("//*[@resource-id=['"+resourceId+"']"));
        
        errorIfNull(o);
        //WebElement e =(WebElement) o;
        Field privateStringField = null;
        try {
        	privateStringField = o.getClass().getDeclaredField("foundBy");
        	privateStringField.setAccessible(true);
        	privateStringField.set(o, o.toString().replace("unknown locator", "resource-id: " + resourceId));
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
      
        
        return o;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<WebElement> findElements(SearchContext searchContext) {
        if (searchContext instanceof WebDriver) {
            searchContext = null;
        }
        return (List<WebElement>) searchContext.findElements(By.xpath("//*[@resource-id=['"+resourceId+"']"));
    }

}
