package tests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.testng.Assert;
import utilities.LogUtil;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
//@SuppressWarnings("static-access")
public class PasswordValidatorTests {
    private String arg;
    private static PasswordValidator passwordValidator;
    private Boolean expectedValidation;

    public PasswordValidatorTests(String str, Boolean expectedValidation) {
        this.arg = str;
        this.expectedValidation = expectedValidation;
    }

    @BeforeClass
    public static void initialize() {
        passwordValidator = new PasswordValidator();
    }
    @Parameters
    public static Collection<Object[]> data(){
        Object[][] data = new Object[][] {
                {"n!k@s",false },                         // it's less that 18 characters long
                { "gregorymarjames-law", false },         // it doesn't contain an digits or upper case characters
                { " abcdFg45*", false },                  // characters ~ in not allowed
                { "n!koabcD#AX", false },                 // there should be a digit
                { "ABCASWF2!", false   },                 // there should be a lower case character
                {"gregoryma6^&*(-law",false},            // More Than 4 special Characters
                {"J@vaC0deGo#ksjiylc", false},            //
                {"n!k1abcD#!jiylctyt", false},            //
                // valid passwords

                {"n!k@sn1Kosffghjiylc",true },            //Atleast 18 alphanumeric characters 1 Upper, 1lower, 1 numeric, 1 special characters
                {"thk!sn5Kosffg@Agoda", true},            // NO More than 4 special Characters
                {"AgodaisinThai1&wqrt", true}};          //No Duplicate Repeats more than 4

        return Arrays.asList(data);
    }


    @Test
    public void validatePassword() {
        Boolean res = passwordValidator.validate(this.arg);
        String validv = (res) ? "valid" : "invalid";
        System.out.println("Password "+arg+ " is " + validv);
        LogUtil.info("Password "+arg+ " is " + validv);
        assertEquals("Result", this.expectedValidation, res);

    }



    }

