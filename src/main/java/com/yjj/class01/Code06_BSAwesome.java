package com.yjj.class01;

public class Code06_BSAwesome {
//  # 认识二分法
//1）在一个有序数组中，找某个数是否存在Code04_BSExist
//2）在一个有序数组中，找>=某个数最左侧位置Code05_BSNearLeft
//2）在一个有序数组中，找<=某个数最右侧位置Code05_BSNearRight
    
    
//    局部最小 ，arr无序，相邻不相等，找到某个局部最小Code06_BSAwesome
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
