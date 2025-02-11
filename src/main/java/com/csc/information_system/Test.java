package com.csc.information_system;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String readRegEx = "sleep{1}[1-9]{1}[0-9]{0,1}";
        while (true) {
            Scanner console = new Scanner(System.in);
            String input = console.next();
            System.out.println(Pattern.matches(readRegEx, input));
        }

    }
}
