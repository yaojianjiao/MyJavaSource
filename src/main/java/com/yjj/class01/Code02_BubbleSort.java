package com.yjj.class01;

public class Code02_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 冒泡排序
        // 外层 0---n选择逐个对比，选择大的往后放，0-1谁大谁放后边，然后1-2谁大谁放后边，2-3。。。
        // 0---n-1 
        // 0---n-2 
        // 0---n-3 
        for (int e = arr.length-1; e>0; e--) {
            //0--1 谁大谁放后边
            //1--2 谁大谁放后边
            //2--3 谁大谁放后边
            for (int i = 0; i <e; i++) {
                if (arr[i]>arr[i+1]) {
                    swap(arr,i,i+1);
                }
            }
        }
        
    }
    
    // 交换arr的i和j位置上的值
    private static void swap(int[] arr, int i, int j) {
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
        
}
