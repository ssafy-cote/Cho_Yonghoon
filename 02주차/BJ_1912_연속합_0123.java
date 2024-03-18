package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1912_연속합_0123 {

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
		}
		maxList[0] = list[0];
		for (int i = 1; i < n; i++) {
			maxList[i] = Math.max(maxList[i-1] + list[i], list[i]);
			if (maxList[i] > max) {
				max = maxList[i];
			}
		}
		System.out.println(max);
	}
}