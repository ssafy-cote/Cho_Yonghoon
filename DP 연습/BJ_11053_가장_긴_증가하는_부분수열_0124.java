package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11053_가장_긴_증가하는_부분수열_0124 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int[] map = new int[n];
		st = new StringTokenizer(bf.readLine(), " ");
		for (int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		int max = 1;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if ((map[j] < map[i]) && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
					if (max < dp[i]) {
						max = dp[i];
					}
				}
			}
		}
		System.out.println(max);
	}
}
