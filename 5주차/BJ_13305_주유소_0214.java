package com.edu.ssafy_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_13305_주유소_0214 {
	static int n;
	static long min;
	static long[] road;
	static long[] city;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());

		road = new long[n - 1];
		city = new long[n];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for (int i = 0; i < n - 1; i++) {
			road[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for (int i = 0; i < n; i++) {
			city[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		long[] dp = new long[n];
		dp[0] = 0;
		min = city[0];
		for (int i = 1; i < city.length; i++) {
			dp[i] = dp[i - 1] + (min * road[i - 1]);
			min = (min > city[i]) ? city[i] : min;
		}
		System.out.println(dp[n - 1]);
	}
}
