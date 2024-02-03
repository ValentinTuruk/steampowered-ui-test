package framework.helpers;

import framework.Setup;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonFunctions extends Setup {
    public static int getTwoDigitsBeforeProcent(String discount) {
        String twoDigitsBeforeProcent = "(\\d{1,2})%";
        Pattern pattern = Pattern.compile(twoDigitsBeforeProcent);
        Matcher matcher = pattern.matcher(discount);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }
        return -1;
    }
    
    public static String formatString(String input){
        return input.trim().toLowerCase();
    }
    
}
