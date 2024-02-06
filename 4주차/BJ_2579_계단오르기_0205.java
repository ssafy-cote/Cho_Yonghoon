package com.edu.ssafy_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2579_계단오르기_0205 {
	static int[] map, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			map[i] = Integer.parseInt(bufferedReader.readLine());
		}
		dp = new int[n + 1];
		dp[1] = map[1];
		if (n >= 2) {
			dp[2] = map[1] + map[2];
		}
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 2], dp[i - 3] + map[i - 1]) + map[i];
		}
		System.out.println(dp[n]);
	}
}
