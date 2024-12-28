package it.must.be.funny.callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FIleProcessor {

    private static FileObject fileObject;

    public static void main(String[] args) throws InterruptedException {

        fileObject = new FileObject();
        readFile(fileObject);

        FileWatcher fileWatcher = new FileWatcher(fileObject);
        fileWatcher.start();

        Thread thread = new Thread( () -> {
            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });
        thread.start();
    }

    public static void readFile(FileObject fileObject){



            try (BufferedReader reader = new BufferedReader(new FileReader(fileObject.FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    fileObject.updateChangeProperties(fields[0], fields[1], fields[2]);
                }
            } catch (IOException e) {
                System.err.println("Lỗi khi đọc file: " + e.getMessage());
            }

    }
}
