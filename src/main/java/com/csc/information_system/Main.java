package com.csc.information_system;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static volatile Queue<Resultable> queue = new LinkedList<>();
    public static DataBaseClass db = DataBaseClass.getInstance();

    /** Main Method. */
    public static void main(String[] args) {
        boolean exit = false;
        Thread thread = new Thread(() -> {
            while (true) {
                for (Resultable el : queue) {
                    if (el.finished()) {
                        el.printResult();
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("enter a command");
        thread.start();
        while(!exit) {
            Scanner console = new Scanner(System.in);
            String input = console.next();
            System.out.println("you wrote: " + input);
            if (input.equals("exit")) {
                exit = true;
            } else {
                parser(input);
            }
        }
    }

    /**
     * Method for the parsing of an input string from console.
     * @param input is a line from the console.
     */
    public static void parser(String input) {
        Expression exp1 = new ForReadCommand();
        Expression exp2 = new ForSleepCommand();
        input = input.trim();
        input = input.toLowerCase();
        boolean nomatch = true;

        if (exp2.interpret(input)) {
            int sleepTimeSec = Integer.parseInt(input.substring(5));
            SleepCommand sc = new SleepCommand(sleepTimeSec);
            db.insertToDB(input);
            queue.add(sc);
            nomatch = false;
        }

        if (exp1.interpret(input)) {
            String path = input.substring(4);
            ReadCommand rc = new ReadCommand(path);
            db.insertToDB(input);
            queue.add(rc);
            nomatch = false;
        }
        if (input.equals("ps")) {
            db.getFromDB();
            nomatch = false;
        }

        if (input.equals("help") || nomatch) {
            writeHelp();
        }
    }

    /** Method to write help. */
    public static void writeHelp() {
        System.out.println("type 'help' to get help, 'ps' to get all commands, 'sleep<sec>' to sleep, 'read<full_path_file>' to read file ");
    }
}