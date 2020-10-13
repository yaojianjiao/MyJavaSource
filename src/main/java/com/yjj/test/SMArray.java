package com.yjj.test;

import java.util.HashMap;
import java.util.Map.Entry;

public class SMArray {

	
//	N数组长度，M变换次数，S目标和;
//	每次变换可以挑一个数把它变成这个数的阶层
//	问有多少种组合，使得组合中数的和为S
//	N 1-25
//	S 10^16
//	0-m次变换
//
//	题目是这样的吧
//
//
//
//	T:还有，实际情况，25个数，你让左部分只搞12个数。因为最后枚举是用左侧的和来找右侧的。这样一来，完全能过。一点问题都没有。
//	T:这样就，刚刚到10的7次方，毫不勉强

	
//	上面的应该是题目和思路
//	分割线-----------------------
	// 阶乘1!~20!，记忆化一下，一旦生成，不再重复生成
	// 因为21!会溢出，所以输入不会出现的20以上的值
	public static long[] jc = null;

	// 假设：
	// M >= 0
	// arr中值一定大于0
	// S的值一定大于0
	// 对于不满足的情况，自己想边界条件吧，对主流程并不重要
	public static long ways(int[] arr, int M, long S) {
		if (jc == null) {
			jc = new long[21];
			jc[1] = 1;
			for (int i = 2; i <= 20; i++) {
				jc[i] = jc[i - 1] * i;
			}
		}
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return (arr[0] == S ? 1 : 0) + (jc[arr[0]] == S ? 1 : 0);
		}
		HashMap<Long, int[]> map1 = new HashMap<Long, int[]>();
		int mid = arr.length / 2;
		process(arr, M, mid + 1, 0, 0, 0, map1);
		
		HashMap<Long, int[]> map2 = new HashMap<>();
		process(arr, M, arr.length, mid + 1, 0, 0, map2);
		int ways = 0;
		for (Entry<Long, int[]> entry : map1.entrySet()) {
			long leftSum = entry.getKey();
			int[] leftMways = entry.getValue();
			// 解释一下
			// 假设凑的S为1000，M = 10
			// 左部分有400这个和：
			// 用了0次变化的方法数a，
			// 用了1次变化的方法数b，
			// ...
			// 那么去找右部分有600这个和存在不存在，如果存在：
			// 方法数是如下情况的累加：
			// a * (右部分搞出600这个和并且用了<= 10次变化情况下的方法数)
			// b * (右部分搞出600这个和并且用了<= 9次变化情况下的方法数)
			// 也就是说，左部分必须是精确的次数，右部分是<=某个次数的形式
			// 所以右部分可以先搞一个前缀和
			if (map2.containsKey(S - leftSum)) {
				int[] rightMways = map2.get(S - leftSum);
				// 在搞右部分的前缀和
				for (int i = 1; i <= M; i++) {
					rightMways[i] += rightMways[i - 1];
				}
				// 再算方法数每个部分累加的东西
				for (int i = 0; i <= M; i++) {
					ways += leftMways[i] * rightMways[M - i];
				}
			}
		}
		// 复杂度估计：左右两部分最多13个数，会产生进100万的和，（左部分100万，右部分100万）
		// 准确来说是：1，594，323 10的6次方
		// 而M一定小于个数，最差估计25，
		// 注意看最复杂的枚举，就是得到了map1和map2之后的过程，
		// 所以计算的指令条数最差为 : 1，594，323 * 25 = 39, 858, 075
		// 这个数量不到10的8次方，所以能通过
		// 实际情况中，arr中的数值不可能很大，因为算阶乘会溢出，
		// 这样一来，13个数的组合，产生的和数量远不及100万，所以能通过
		return ways;
	}

	public static void process(int[] arr, int M, int end, int i, int usedM, long sum, HashMap<Long, int[]> map) {
		if (i == end) {
			if (!map.containsKey(sum)) {
				map.put(sum, new int[M + 1]);
			}
			map.get(sum)[usedM]++;
		} else {
			process(arr, M, end, i + 1, usedM, sum, map);
			process(arr, M, end, i + 1, usedM, sum + arr[i], map);
			if (usedM < M && arr[i] < 21) {
				process(arr, M, end, i + 1, usedM + 1, sum + jc[arr[i]], map);
			}
		}
	}

}
