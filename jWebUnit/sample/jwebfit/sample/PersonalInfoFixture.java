/**
 * User: djoiner
 * Date: Nov 7, 2002
 * Time: 2:01:32 PM
 */
package jwebfit.sample;

import net.sourceforge.jwebunit.fit.WebFixture;

import java.util.Map;
import java.util.HashMap;

public class PersonalInfoFixture extends WebFixture {
    private static Map filters = new HashMap();

    static {
        initFilterMap();
    }

    private static void initFilterMap() {
        filters.put("name", "fullName");
        filters.put("m", "male");
        filters.put("f", "female");
        filters.put("tn", "Tennessee");
        filters.put("ga", "Georgia");
        filters.put("", "None");
    }

///////////////////////////////////////////////////////////////////////////////
// actions
///////////////////////////////////////////////////////////////////////////////

    public void gotoScreen() {
        tester.beginAt("personalInfoForm");
    }

    public void enter() throws Exception {
        String field = cells.more.text();
        String value = cells.more.more.text();
        if (field.toLowerCase().equals("citizen")) {
            if (value.toLowerCase().equals("yes")) {
                tester.checkCheckbox("citizenCheckbox");
            }
        } else if (field.toLowerCase().equals("state")) {
            tester.selectOption("state", filter(value));
        } else {
            tester.setFormElement(filter(field), filter(value));
        }
    }

    private String filter(String s) {
        return (filters.containsKey(s.toLowerCase())) ? (String) filters.get(s.toLowerCase()) : s;
    }

///////////////////////////////////////////////////////////////////////////////
// checks
///////////////////////////////////////////////////////////////////////////////

    public void checkNameResponse(String value) {
        tester.assertTextPresent(value);
    }

    public void checkCitizenResponse(String value) {
        tester.assertTextPresent(value);
    }

    public void checkStateResponse(String value) {
        tester.assertTextPresent(value);
    }

    public void checkSexResponse(String value) {
        tester.assertTextPresent(value);
    }

}
