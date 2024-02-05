package com.edu.ssafy_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 계단오르기 {
	static int[] map, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			map[i] = Integer.parseInt(bufferedReader.readLine());
		}
		dp = new int[n+1];
		dp[n] = map[n];

		if (n == 2) {
			dp[n - 1] = dp[n] + map[n - 1];
		} else {
			dp[n - 1] = dp[n] + map[n - 1];
			for (int i = n - 2; i > -1; i--) {
				dp[i] = Math.max(dp[i + 1], dp[i + 2] + map[i]);
			}
		}
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[0]);
		System.out.println(Arrays.toString(map));
	}
}
