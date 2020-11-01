package com.yjj.class01;

public class Code02_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // ð������
        // ��� 0---nѡ������Աȣ�ѡ��������ţ�0-1˭��˭�ź�ߣ�Ȼ��1-2˭��˭�ź�ߣ�2-3������
        // 0---n-1 
        // 0---n-2 
        // 0---n-3 
        for (int e = arr.length-1; e>0; e--) {
            //0--1 ˭��˭�ź��
            //1--2 ˭��˭�ź��
            //2--3 ˭��˭�ź��
            for (int i = 0; i <e; i++) {
                if (arr[i]>arr[i+1]) {
                    swap(arr,i,i+1);
                }
            }
        }
        
    }
    
    // ����arr��i��jλ���ϵ�ֵ
    private static void swap(int[] arr, int i, int j) {
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }
        
}
