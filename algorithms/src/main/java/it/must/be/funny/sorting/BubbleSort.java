package it.must.be.funny.sorting;

import java.util.Arrays;

public class BubbleSort {


    // bubble sort is worked for the small array size
    public static int[] bubbleSort(int[] arr) {
        // don't need to sort with arr length is less than 2
        if (arr.length < 2){
            return arr;
        }

        for (int i = 0; i < arr.length; i ++) {
            for (int j = 0; j < arr.length - 1; j ++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;

    }

    public static void main(String[] args) {
        int[] array = new int[]{9,5};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
