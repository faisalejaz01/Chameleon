package com.chameleon.sandbox;

import org.testng.annotations.Test;

import com.chameleon.AutomationException;
import com.chameleon.utils.TestReporter;

public class FailedTestExample {
	
    @Test
    public void testFailureExample() {
        TestReporter.assertTrue(false, "Custom assertion message here");
    }
    
    @Test
    public void testExceptionExample() {
    	throw new AutomationException("Test of automation exception");
    }

}
