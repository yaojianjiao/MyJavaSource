package com.yjj.class03;

public class Code03_PartitionAndQuickSort {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
//    快速排序
//    Partition过程
//    给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
//    要求额外空间复杂度O(1)，时间复杂度O(N) 

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;//定义小于区域
        int index = L;
        while (index < R) {
            // 6,3,4,2,5,3
            //当前数，小于等于num，当前数与小于区域下一个交换，小于区+1
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
       //因为默认用arr[R]做比较参数，一直放在最右边，所以。。。最后在单独比较arr[R]
        //最后在处理一次arr[R]，arr[R]和小于区的下一个互换
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    
//    荷兰国旗问题
//    给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
//    要求额外空间复杂度O(1)，时间复杂度O(N) 

    
    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    //  <arr[R]  ==arr[R]  > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1; // < 区 右边界
        int more = R;     // > 区 左边界
        int index = L;
        while (index < more) {
            //1.当前位置==num，i++，其余不变
            //2.当前位置值小于num，与小于区的下一个（即右一个）交换，小于区域向右扩1，i++
            //3.当前位置大于num，与大于区前一个（即左一个）交换，大于区域向左移动1，i不变（因为i互换了，需要再次比较）
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else { // >
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[] { less + 1, more };
    }
    
//    快速排序1.0
//    在arr[L..R]范围上，进行快速排序的过程：
//    1）用arr[R]对该范围做partition，<= arr[R]的数在左部分并且保证arr[R]最后来到左部分的最后一个位置，记为M； <= arr[R]的数在右部分（arr[M+1..R]）
//    2）对arr[L..M-1]进行快速排序(递归)
//    3）对arr[M+1..R]进行快速排序(递归)
//    因为每一次partition都会搞定一个数的位置且不会再变动，所以排序能完成

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }
    
    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
     // L..R partition arr[R]  [   <=arr[R]   arr[R]    >arr[R]  ]
        int M=partition(arr, L, R);
        process1(arr, L, M-1);
        process1(arr, M+1, R);
    }
    
//  快速排序2.0
    
//    在arr[L..R]范围上，进行快速排序的过程：
//    1）用arr[R]对该范围做partition，< arr[R]的数在左部分，== arr[R]的数中间，>arr[R]的数在右部分。假设== arr[R]的数所在范围是[a,b]
//    2）对arr[L..a-1]进行快速排序(递归)
//    3）对arr[b+1..R]进行快速排序(递归)
//    因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }
    
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0]-1);
        process2(arr,equalArea[1],R);
    }
    
//  快速排序3.0(随机快排+荷兰国旗技巧优化)
//    在arr[L..R]范围上，进行快速排序的过程：
//    1）在这个范围上，随机选一个数记为num，
//    1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
//    2）对arr[L..a-1]进行快速排序(递归)
//    3）对arr[b+1..R]进行快速排序(递归)
//    因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }
    
    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }
    
    
    
//    随机快排的时间复杂度分析
//    1）通过分析知道，划分值越靠近中间，性能越好；越靠近两边，性能越差
//    2）随机选一个数进行划分的目的就是让好情况和差情况都变成概率事件
//    3）把每一种情况都列出来，会有每种情况下的时间复杂度，但概率都是1/N
//    4）那么所有情况都考虑，时间复杂度就是这种概率模型下的长期期望！
//    时间复杂度O(N*logN)，额外空间复杂度O(logN)都是这么来的。

    
    
 // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
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

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
    
    
    
}
