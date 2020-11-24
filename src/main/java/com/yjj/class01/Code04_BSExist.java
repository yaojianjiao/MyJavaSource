package com.yjj.class01;

public class Code04_BSExist {
//   # 认识二分法
//1）在一个有序数组中，找某个数是否存在Code04_BSExist
//2）在一个有序数组中，找>=某个数最左侧位置Code05_BSNearLeft
//2）在一个有序数组中，找<=某个数最右侧位置Code05_BSNearRight
    
//    为什么使用位移运算，比如int a 和 int b ;a=10亿，b=18亿  ，a+b 超出数据范围
//            计算机中 n/2 =n>>1   ；n位移
//            n*2 =n<<1     ；n向左位移一位
//            n*2+1=( (n<<1)|1)  ；n向左移一位或上一个1   ，即 ((n<<1)|1)
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L=0;
        int R=sortedArr.length-1;
        int mid=0;
//        L。。。。R
        while(L<R) {
         // mid = (L+R) / 2;
            // L 10亿  R 18亿
            // mid = L + (R - L) / 2
            // N / 2    N >> 1
            mid = L + ((R - L) >> 1); // mid = (L + R) / 2
            if(sortedArr[mid]==num) {
                return true;
            }else if(sortedArr[mid]>num) {
                //1,2,2,3,3,3,3,4,4,5,6,7
                //L        mid          R
                  
                R=mid-1;
            }else {//sortedArr[mid]<num
                L=mid+1;
            }
            
        }
        return false;
    }
}
