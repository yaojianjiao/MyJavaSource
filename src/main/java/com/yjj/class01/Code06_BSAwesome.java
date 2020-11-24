package com.yjj.class01;

public class Code06_BSAwesome {
//  # ��ʶ���ַ�
//1����һ�����������У���ĳ�����Ƿ����Code04_BSExist
//2����һ�����������У���>=ĳ���������λ��Code05_BSNearLeft
//2����һ�����������У���<=ĳ�������Ҳ�λ��Code05_BSNearRight
    
    
//    �ֲ���С ��arr�������ڲ���ȣ��ҵ�ĳ���ֲ���СCode06_BSAwesome
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if(arr[mid]>arr[mid-1]) {
                right=mid-1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            }else {
                return mid;
            }
            
        }
        
        return left;
        
    }
    
}
