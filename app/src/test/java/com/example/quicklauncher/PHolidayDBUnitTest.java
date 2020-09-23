//package com.example.quicklauncher;
//
//import org.junit.Test;
//
//import Model.Database;
//import Model.PasswordDB;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//public class PHolidayDBUnitTest {
//    PHolidayDB pass = new PHolidayDB("1", "Melbourne Cup", "03/11/2020");
//    PHolidayDB fail = new PHolidayDB("2", "Christmas", "25/12/2020");
//
//    @Test
//    public void id_test() {
//        Database.pubHolArray.add(pass);
//        String a = Database.pubHolArray.get(0).holidayID;
//        assertEquals(a, "1");
//    }
//    @Test
//    public void pw_test() {
//        Database.pubHolArray.add(pass);
//        String a = Database.pubHolArray.get(0).holidayName;
//        assertEquals(a, "Melbourne Cup");
//    }
//    @Test
//    public void inNewEmployee_test() {
//        Database.pubHolArray.add(pass);
//        boolean a = Database.pubHolArray.get(0).holidayDate;
//        assertEquals(a, "03/11/2020");
//    }
//    @Test
//    public void FailinNewEmployee_test() {
//        Database.pubHolArray.add(fail);
//        String a = Database.pubHolArray.get(1).holidayName;
//        assertNotEquals("Melbourne Cup", "Christmas");
//    }
//}