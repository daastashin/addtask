package com.csc.information_system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadCommand extends Thread  implements Resultable {

    private String path;
    private String input;
    private String result = "file not found";

    public ReadCommand(String path, String input) {
        this.path = path;
        this.input = input;
        this.start();
    }

    @Override
    public void printResult() {
        System.out.println(result);
    }

    @Override
    public boolean finished() {
        return !this.isAlive();
    }

    @Override
    public void run() {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("file not found");
        } else {
            DataBaseClass.getInstance().insertToDB(input);
            Main.queue.add(this);
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                result = "First line of " + path + "\n" + line;
                while (line != null) {
                    //System.out.println(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
