package com.orasi.ui.by;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FindByWindows {
    String windowsAutomation() default "";

    /**
     * It an UI automation accessibility Id which is a convenient to Windows.
     *
     * @return an UI automation accessibility Id
     */
    String accessibilityId() default "";

    /**
     * Priority of the searching. Higher number means lower priority.
     *
     * @return priority of the searching
     */
    int priority() default 0;
}
