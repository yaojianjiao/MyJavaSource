package com.yjj.class01;

public class Code04_BSExist {
//   # ��ʶ���ַ�
//1����һ�����������У���ĳ�����Ƿ����Code04_BSExist
//2����һ�����������У���>=ĳ���������λ��Code05_BSNearLeft
//2����һ�����������У���<=ĳ�������Ҳ�λ��Code05_BSNearRight
    
//    Ϊʲôʹ��λ�����㣬����int a �� int b ;a=10�ڣ�b=18��  ��a+b �������ݷ�Χ
//            ������� n/2 =n>>1   ��nλ��
//            n*2 =n<<1     ��n����λ��һλ
//            n*2+1=( (n<<1)|1)  ��n������һλ����һ��1   ���� ((n<<1)|1)
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L=0;
        int R=sortedArr.length-1;
        int mid=0;
//        L��������R
        while(L<R) {
         // mid = (L+R) / 2;
            // L 10��  R 18��
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
