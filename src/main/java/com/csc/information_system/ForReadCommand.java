package com.csc.information_system;

public class ForReadCommand implements Expression {

    String readRegEx = "read.+";

    @Override
    public boolean interpret(String input) {
        return input.matches(readRegEx);
    }

}
