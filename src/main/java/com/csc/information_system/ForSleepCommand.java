package com.csc.information_system;

public class ForSleepCommand implements Expression {

    String sleepRegEx = "sleep[1-9]{1}[0-9]{0,1}";

    @Override
    public boolean interpret(String input) {
        return input.matches(sleepRegEx);
    }

}
