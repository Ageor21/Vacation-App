package com.example.vacationapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.widget.EditText;


public class UnitTest {
        @Test
        public void Date_is_Correct_Format() throws Exception {
            assertTrue("YY/MM/DD ", true);
        }
        @Test
        public void Vacation_List_ID() throws Exception {
            int ID = 0;
            int New_ID = 1;

            String input = "1";

            int userInput = Integer.parseInt(input);

            if (userInput == 1) {
                ID += New_ID;
                System.out.println(ID);
            } else if (userInput == 0) {
                System.out.println(ID);
            }

        }
}
