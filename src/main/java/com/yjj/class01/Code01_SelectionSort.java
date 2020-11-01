package com.yjj.class01;

import java.util.Arrays;

public class Code01_SelectionSort {
    public static void selectionSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        // 选择排序
        // 外层 0---n-1选择最小且移动到当前0位置
        // 1---n-1选择最小且移动到当前1位置
        // 2---n-1选择最小且移动到当前2位置
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;// 最小值在哪个位置上  i～n-1
            for (int j = i + 1; j < arr.length; j++) {// i ~ N-1 上找最小值的下标 
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }
    //i和j位置互换
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
//    public static void main(String[] args) {
//        int[] arr= {3,2,6,5,4,8,6,9};
//        selectionSort(arr);
//    }
    
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
    }
    
    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random()   [0,1)  
        // Math.random() * N  [0,N)
        // (int)(Math.random() * N)  [0, N-1]
        int arr[]=new int[(int) (Math.random()*(1+maxSize))] ;
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
 // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    
    //for test
    public static void comparator(int[]arr) {
        Arrays.sort(arr);
    } 
    
 // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    
    
}
