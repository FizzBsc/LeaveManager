package com.example.quicklauncher;

import org.junit.Test;

import Model.Database;
import Model.PasswordDB;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        PasswordDB pass = new PasswordDB("b", "pop", true);
        Database.passwordArr.add(pass);
        String a = Database.passwordArr.get(0).eID;
        assertEquals(a, "b");
    }
}