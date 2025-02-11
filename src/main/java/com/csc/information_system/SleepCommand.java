package com.csc.information_system;

public class SleepCommand extends Thread implements Resultable {

    private int sec;
    private String result;

    public SleepCommand(int sec) {
        this.sec = sec;
        this.start();
    }

    @Override
    public boolean finished() {
        return this.finished();
    }

    @Override
    public void printResult() {
        System.out.println(result);
    }

    @Override
    public void run() {
        try {
            sleep(sec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        result = "dream for " + sec + " is done";
        System.out.println(result);
    }

}
