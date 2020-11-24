package com.yjj.class01;

public class Code05_BSNearLeft {
//  # 认识二分法
//1）在一个有序数组中，找某个数是否存在Code04_BSExist
//2）在一个有序数组中，找>=某个数最左侧位置Code05_BSNearLeft
//2）在一个有序数组中，找<=某个数最右侧位置Code05_BSNearRight
    
 // 在arr上，找满足>=value的最左位置
    public static int nearestIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1; // 记录最左的对号
//      计算机中 n/2 =n>>1   ；n位移
//      n*2 =n<<1     ；n向左位移一位
//      n*2+1=( (n<<1)|1)  ；n向左移一位或上一个1   ，即 ((n<<1)|1)
        
        // mid = (L+R) / 2;
        // L 10亿  R 18亿
        // mid = L + (R - L) / 2
        // N / 2    N >> 1
//        mid = L + ((R - L) >> 1); // mid = (L + R) / 2
        while(L<=R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index=mid;
                R=mid-1;
            }else {
                L=mid+1;
            }
        }
        return index;
    }
}
