package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN =
            "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?:([\\w\\d*?!:;])\\1?(?!\\1))(?=.*[ -\\/:-@\\[-\\`{-~]{1,4})(?=.*[@#$&!]).{18,45})";

    public PasswordValidator() {

        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validate(final String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();

    }

}
