package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1912_연속합_0123_시간초과 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int max = Integer.MIN_VALUE;
		int n = Integer.parseInt(st.nextToken());
		int[] list = new int[n];
		int[] maxList = new int[n];
		st = new StringTokenizer(bf.readLine(), " ");
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			list[i] = temp;
			maxList[i] = temp;
			if (temp > max)
				max = temp;
		}
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j <= n - i; j++) {
				maxList[j] = maxList[j] + list[j + (i - 1)];
				if (maxList[j] > max) {
					max = maxList[j];
				}
			}
		}
		System.out.println(max);
	}

}