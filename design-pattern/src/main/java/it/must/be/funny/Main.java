package it.must.be.funny;

import java.sql.SQLException;
import java.util.*;

public class Main {
    private static String demoNullPointerEx;
    public static void main(String[] args) throws SQLException {


        Map<String, String> testMap = new HashMap<>();
        testMap.put("firstKey", "firstValue");
        testMap.put("secondKey", "secondValue");

        //
        Collections.singletonList(testMap).parallelStream().forEach( a -> {
             if (a.get("firstKey").equals("firstValue")) {
                 System.out.println("hello world");
             }
        });
    }

}