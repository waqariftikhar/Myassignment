package tests;

import utilities.LogUtil;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePassword {
    private static String OldPassword, NewPassword, Password;

    private static final String PASSWORD_REGEX =
            "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?:([\\w\\d*?!:;])\\1?(?!\\1))(?=.*[ -\\/:-@\\[-\\`{-~]{1,4})(?=.*[@#$&!]).{18,45})";

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    public static void main(String[] args) {
        Scanner password = new Scanner(System.in);
        System.out.println("Enter Password to Set:");
        System.out.println("(Must be at least 18 alphanumeric characters,  contain a number, letter, and special character");
        Password = password.nextLine();
        if (PASSWORD_PATTERN.matcher(Password).matches()) {
            System.out.print("The Password " + Password + " is valid and has been successfully set");
        } else {
            System.out.print("The Password " + Password + " isn't valid");
        }

        new ChangePassword(OldPassword, NewPassword);

    }

    public ChangePassword(String OldPassword, String NewPassword) {

        Scanner ResetPassword = new Scanner(System.in);
        System.out.println("\n\nEnter Your Old Password one more time to set new Password:");
        OldPassword = ResetPassword.nextLine();

        if (OldPassword.equals(Password)) {

            System.out.println("\n\nEnter Your New Password:  ");
            NewPassword = ResetPassword.nextLine();
        } else {
            System.out.println("Inavalid Old Password Try Again");
            LogUtil.info("Inavalid Old Password Try Again");
        }

        if (OldPassword == null || NewPassword == null) {
            LogUtil.info("Passwords = null");
            System.out.println("One or both passwords are null");
            LogUtil.info("One or both passwords are null");
        }

        else if (OldPassword.equals(NewPassword)) {
            System.out.println("Password same");
            LogUtil.info("Password Same");
        }

        else if (!OldPassword.equals(NewPassword)) {
            System.out.println("Processing Your Password Please wait...");
            if (PASSWORD_PATTERN.matcher(NewPassword).matches() && !NewPassword.equals(OldPassword)) {
                System.out.print("The New Password " + NewPassword + " is valid and has been successfully set\n");
                LogUtil.info("The New Password " + NewPassword + " is valid and has been successfully set");
            } else if (PASSWORD_PATTERN.matcher(NewPassword).matches() || !NewPassword.equals(OldPassword)) {
                System.out.print("The New Password " + NewPassword + " isn't valid\n");
                LogUtil.info("The New Password " + NewPassword + " isn't valid");
            }
        }


    }


}
