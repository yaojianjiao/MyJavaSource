package com.yjj.test;

public class TestSum {
	//不使用+-实现加减法
	public static void main(String[] args) {
		System.out.println(getSum(2, 3));
		System.out.println(getSum(6, 3));
		System.out.println(getSum(-2, 3));
		System.out.println("-----------");
		System.out.println(3);
	}
	
	public static int getSum(int a,int b){
		int sum=0;
		while(b!=0){
			sum=a^b;
			b=(a&b)<<1;
			a=sum;
		}
		return sum;
	}
}
