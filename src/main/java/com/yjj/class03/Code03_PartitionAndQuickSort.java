package com.yjj.class03;

public class Code03_PartitionAndQuickSort {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
//    ��������
//    Partition����
//    ����һ������arr����һ������num�����С�ڵ���num���������������ߣ�����num��������������ұߡ�
//    Ҫ�����ռ临�Ӷ�O(1)��ʱ�临�Ӷ�O(N) 

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;//����С������
        int index = L;
        while (index < R) {
            // 6,3,4,2,5,3
            //��ǰ����С�ڵ���num����ǰ����С��������һ��������С����+1
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
       //��ΪĬ����arr[R]���Ƚϲ�����һֱ�������ұߣ����ԡ���������ڵ����Ƚ�arr[R]
        //����ڴ���һ��arr[R]��arr[R]��С��������һ������
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    
//    ������������
//    ����һ������arr����һ������num�����С��num���������������ߣ�����num���������м䣬����num��������������ұߡ�
//    Ҫ�����ռ临�Ӷ�O(1)��ʱ�临�Ӷ�O(N) 

    
    // arr[L...R] �������������Ļ��֣���arr[R]������ֵ
    //  <arr[R]  ==arr[R]  > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1; // < �� �ұ߽�
        int more = R;     // > �� ��߽�
        int index = L;
        while (index < more) {
            //1.��ǰλ��==num��i++�����಻��
            //2.��ǰλ��ֵС��num����С��������һ��������һ����������С������������1��i++
            //3.��ǰλ�ô���num���������ǰһ��������һ�����������������������ƶ�1��i���䣨��Ϊi�����ˣ���Ҫ�ٴαȽϣ�
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
    
//    ��������1.0
//    ��arr[L..R]��Χ�ϣ����п�������Ĺ��̣�
//    1����arr[R]�Ը÷�Χ��partition��<= arr[R]�������󲿷ֲ��ұ�֤arr[R]��������󲿷ֵ����һ��λ�ã���ΪM�� <= arr[R]�������Ҳ��֣�arr[M+1..R]��
//    2����arr[L..M-1]���п�������(�ݹ�)
//    3����arr[M+1..R]���п�������(�ݹ�)
//    ��Ϊÿһ��partition����㶨һ������λ���Ҳ����ٱ䶯���������������

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
    
//  ��������2.0
    
//    ��arr[L..R]��Χ�ϣ����п�������Ĺ��̣�
//    1����arr[R]�Ը÷�Χ��partition��< arr[R]�������󲿷֣�== arr[R]�����м䣬>arr[R]�������Ҳ��֡�����== arr[R]�������ڷ�Χ��[a,b]
//    2����arr[L..a-1]���п�������(�ݹ�)
//    3����arr[b+1..R]���п�������(�ݹ�)
//    ��Ϊÿһ��partition����㶨һ������λ���Ҳ����ٱ䶯���������������

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
    
//  ��������3.0(�������+�������켼���Ż�)
//    ��arr[L..R]��Χ�ϣ����п�������Ĺ��̣�
//    1���������Χ�ϣ����ѡһ������Ϊnum��
//    1����num�Ը÷�Χ��partition��< num�������󲿷֣�== num�����м䣬>num�������Ҳ��֡�����== num�������ڷ�Χ��[a,b]
//    2����arr[L..a-1]���п�������(�ݹ�)
//    3����arr[b+1..R]���п�������(�ݹ�)
//    ��Ϊÿһ��partition����㶨һ������λ���Ҳ����ٱ䶯���������������
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
    
    
    
//    ������ŵ�ʱ�临�Ӷȷ���
//    1��ͨ������֪��������ֵԽ�����м䣬����Խ�ã�Խ�������ߣ�����Խ��
//    2�����ѡһ�������л��ֵ�Ŀ�ľ����ú�����Ͳ��������ɸ����¼�
//    3����ÿһ��������г���������ÿ������µ�ʱ�临�Ӷȣ������ʶ���1/N
//    4����ô������������ǣ�ʱ�临�ӶȾ������ָ���ģ���µĳ���������
//    ʱ�临�Ӷ�O(N*logN)������ռ临�Ӷ�O(logN)������ô���ġ�

    
    
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
