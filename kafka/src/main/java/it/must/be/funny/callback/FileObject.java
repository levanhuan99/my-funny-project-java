package it.must.be.funny.callback;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileObject {
    private String test1;
    private String test2;
    private String test3;
    private final Lock lock = new ReentrantLock();
    // TODO change this file path to absolute path
    public final String FILE_PATH = "file.csv";
    public String getTest1() {
        try {
            lock.lock();
            return test1;
        } finally {
            lock.unlock();
        }
    }

    public void setTest1(String test1) {
        try {
            lock.lock();
            this.test1 = test1;

        } finally {
            lock.unlock();
        }
    }

    public String getTest2() {
        try {
            lock.lock();
            return test2;

        } finally {
            lock.unlock();
        }
    }

    public void setTest2(String test2) {
        try {
            lock.lock();
            this.test2 = test2;

        } finally {
            lock.unlock();
        }
    }

    public String getTest3() {
        try {
            lock.lock();
            return test3;

        } finally {
            lock.unlock();
        }
    }

    public void setTest3(String test3) {
        try {
            lock.lock();
            this.test3 = test3;

        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
            return "FileObject{" +
                    "test1='" + test1 + '\'' +
                    ", test2='" + test2 + '\'' +
                    ", test3='" + test3 + '\'' +
                    '}';

    }
    public void updateChangeProperties(String test1, String test2, String test3) {
        try {
            lock.lock();
            this.setTest1(test1);
            this.setTest2(test2);
            this.setTest3(test3);
        } finally {
            lock.unlock();
        }
    }
}
