package com.example.quicklauncher;

import org.junit.Test;

import Model.Database;
import Model.PasswordDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PasswordDBUnitTest {
    PasswordDB pass = new PasswordDB("b", "pop", true);
    PasswordDB fail = new PasswordDB("c", "lols", false);

    @Test
    public void id_test() {
        Database.passwordArr.add(pass);
        String a = Database.passwordArr.get(0).eID;
        assertEquals(a, "b");
    }
    @Test
    public void pw_test() {
        Database.passwordArr.add(pass);
        String a = Database.passwordArr.get(0).password;
        assertEquals(a, "pop");
    }
    @Test
    public void inNewEmployee_test() {
        Database.passwordArr.add(pass);
        boolean a = Database.passwordArr.get(0).isNewEmp;
        assertEquals(a, true);
    }
    @Test
    public void FailinNewEmployee_test() {
        Database.passwordArr.add(fail);
        String a = Database.passwordArr.get(1).eID;
        assertNotEquals("b", "c");
    }
}