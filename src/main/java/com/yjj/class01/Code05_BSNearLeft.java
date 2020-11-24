package com.yjj.class01;

public class Code05_BSNearLeft {
//  # ��ʶ���ַ�
//1����һ�����������У���ĳ�����Ƿ����Code04_BSExist
//2����һ�����������У���>=ĳ���������λ��Code05_BSNearLeft
//2����һ�����������У���<=ĳ�������Ҳ�λ��Code05_BSNearRight
    
 // ��arr�ϣ�������>=value������λ��
    public static int nearestIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1; // ��¼����ĶԺ�
//      ������� n/2 =n>>1   ��nλ��
//      n*2 =n<<1     ��n����λ��һλ
//      n*2+1=( (n<<1)|1)  ��n������һλ����һ��1   ���� ((n<<1)|1)
        
        // mid = (L+R) / 2;
        // L 10��  R 18��
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
