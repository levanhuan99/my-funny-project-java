package it.must.be.funny.sorting;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;

public class MergeSort {

    // this merge to is about divide to conquer
    // [1, 5, 3, 6, 7]
    public static void mergeSort(int[] array) {
        // don't need to sort with array length less than 2!!
        if (array.length < 2) {
            return;
        }
        // divide given array into 2 other arrays
        int dividedLength = array.length / 2;
        int[] leftArray = Arrays.copyOfRange(array, 0, dividedLength);
        int[] rightArray = Arrays.copyOfRange(array, dividedLength, array.length);

        // sử dụng đệ qui
        mergeSort(leftArray);
        mergeSort(rightArray);

        sort(array, leftArray, rightArray);
    }
    public static void sort(int[] originalArr, int[] leftArr, int[] rightArr) {
        // initial index for 3 given arrays
        int originalIndex= 0, leftIndex = 0, rightIndex = 0;

        // copy cac phan tu cua element nao nho hon sang mang hien goc
        while (leftIndex < leftArr.length && rightIndex < rightArr.length) {

            if (leftArr[leftIndex] <= rightArr[rightIndex]) {
                originalArr[originalIndex++] = leftArr[leftIndex++];
            } else {
                originalArr[originalIndex++] = rightArr[rightIndex++];
            }
        }

        // copy nhung phan tu cua mang trai neu co
        while (leftIndex < leftArr.length) {

            originalArr[originalIndex++] = leftArr[leftIndex++];
        }

        // copy nhung phan tu cua mang phai neu co
        while (rightIndex < rightArr.length) {
            originalArr[originalIndex++] = rightArr[rightIndex++];
        }

    }

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
