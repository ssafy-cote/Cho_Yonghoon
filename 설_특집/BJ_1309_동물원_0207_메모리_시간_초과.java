package com.edu.ssafy_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1309_동물원_0207_메모리_시간_초과 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());

		int[][] dp = new int[n + 1][n + 1];

		dp[1][1] = 2;

		for (int i = 2; i <= n; i++) {
			dp[1][i] = dp[1][i - 1] + 2;
		}
		for (int i = 2; i <= n; i++) {
			dp[i][i] = 2;
		}
		for (int i = 2; i <= n; i++) {
			for (int j = 3; j <= n; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] + dp[i][j-1];
			}
		}
		long sum = 0;
		for(int i = 1; i <= n; i++) {
			sum += dp[i][n];
		}
		++sum;
		System.out.println(sum % 9901);
	}
}
