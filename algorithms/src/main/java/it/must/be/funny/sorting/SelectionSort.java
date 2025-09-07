package it.must.be.funny.sorting;

import java.util.*;

public class SelectionSort {


    public static void mergeSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int tmp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = tmp;
            }
        }
    }


    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[k] = nums[i];
                k ++;
            }
        }

        return k;
    }
    public static int lengthOfLastWord(String s) {
        String[] splittedString = s.trim().split(" ");
        return splittedString[splittedString.length -1].length();

    }
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n/2];
        /**
         int halfOfNumsLength = nums.length/2;
         for (int i = 0; i < nums.length; i++) {
         int majorityNumber = nums[i];
         int majorityTimes = 1;
         for (int j = i+1; j < nums.length; j++) {
         if (majorityNumber == nums[j]) {
         majorityTimes++;
         }
         if (majorityTimes > halfOfNumsLength) {
         return majorityNumber;
         }
         }
         }
         return 0;
         */
    }

    // IV = 4
    // IX = 9
    // XL = 40
    // XC = 90
    // CD = 400
    // CM = 900;
    //I             1
    //V             5
    //X             10
    //L             50
    //C             100
    //D             500
    //M             1000
    public static int romanToInt(String s) {

        if (s.isEmpty()){
            return 0;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 100);
        int finalE = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isMatchRoman(s.charAt(i), s.charAt(i+1))){
                if (s.charAt(i) == 'I' && s.charAt(i +1) == 'V') {
                    finalE += 4;
                }
            }
        }
        return 0;
    }
    private static boolean isMatchRoman(char first, char second) {
        List<String> x = Arrays.asList("IV","IX","XL","XC","CD","CM");
        String i = java.lang.String.valueOf(first) + second;
        return x.contains(i);
    }


    public static String longestCommonPrefix(String[] strs) {
        String[] firstStrs = new String[1];
        firstStrs[0] = strs[0];

        for (int i = 1; i < strs.length; i++) {
            int equalIndex = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (firstStrs[0].charAt(j) != strs[i].charAt(j)) {
                    break;
                }
                equalIndex ++;
            }
        }

    }
    public static void main(String[] args) {
       char f = 'I';
       char s = 'V';
        System.out.println(java.lang.String.valueOf(f) + s);
    }
}
