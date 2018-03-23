package com.orasi.ui.by;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import com.orasi.utils.JavaUtilities;

public class ByWindowsAnnotations {
    private Field field;

    /**
     * @param field
     *            expected to be an element in a Page Object
     */
    public ByWindowsAnnotations(Field field) {
        this.field = field;
    }

    /**
     * {@inheritDoc}
     *
     * Looks for one of {@link org.openqa.selenium.support.FindBy},
     * {@link org.openqa.selenium.support.FindBys} or
     * {@link org.openqa.selenium.support.FindAll} field annotations. In case
     * no annotaions provided for field, uses field name as 'id' or 'name'.
     *
     * @throws IllegalArgumentException
     *             when more than one annotation on a field provided
     */
    public ByWindows buildBy() {
        ByWindows by = null;

        FindByWindows findByCommon = field.getAnnotation(FindByWindows.class);
        if (JavaUtilities.isValid(findByCommon)) {
            by = buildByNGFindBy(findByCommon);
        }

        if (by == null) {
            throw new IllegalArgumentException("Cannot determine how to locate element " + field);
        }

        return by;
    }

    /**
     * Return the corresponding ByNG class for the type used in FindByNG
     *
     * @param findByNG
     * @return
     */
    protected ByWindows buildByNGFindBy(FindByWindows findByWindows) {
        if (StringUtils.isNotEmpty(findByWindows.accessibilityId())) {
            return ByWindows.accessibilityId(findByWindows.accessibilityId());
        } /*
           * else if (StringUtils.isNotEmpty(findByWindows.id())) {
           * return ByWindows.textValueContains(findByWindows.id());
           * }
           */
        return null;
    }

}
