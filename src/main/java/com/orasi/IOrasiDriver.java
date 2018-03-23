package com.orasi;

import java.util.List;

import org.openqa.selenium.By;

import com.orasi.web.webelements.Button;
import com.orasi.web.webelements.Checkbox;
import com.orasi.web.webelements.Element;
import com.orasi.web.webelements.Label;
import com.orasi.web.webelements.Listbox;
import com.orasi.web.webelements.RadioGroup;
import com.orasi.web.webelements.Textbox;
import com.orasi.web.webelements.Webtable;

public interface IOrasiDriver {
    Button findButton(By by);

    List<Button> findButtons(By by);

    Checkbox findCheckbox(By by);

    List<Checkbox> findCheckboxes(By by);

    Element findElement(By by);

    List<Element> findElements(By by);

    Label findLabel(By by);

    List<Label> findLabels(By by);

    Listbox findListbox(By by);

    List<Listbox> findListboxes(By by);

    RadioGroup findRadioGroup(By by);

    Textbox findTextbox(By by);

    List<Textbox> findTextboxes(By by);

    Webtable findWebtable(By by);

    List<Webtable> findWebtables(By by);

}
