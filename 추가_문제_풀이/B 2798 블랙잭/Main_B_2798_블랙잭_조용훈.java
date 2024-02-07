package com.edu.ssafy_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2798_블랙잭_조용훈 {	// 11496kb 메모리, 92ms 시간
	static int n, m, ans;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for (int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		make(0, 0, 0);
		System.out.println(ans);
	}

	static void make(int count, int sum, int start) {
		if (sum > m)
			return;
		if (count == 3) {
			ans = Math.max(sum, ans);
			return;
		}
		for (int i = start; i < n; i++)
			make(count + 1, sum + map[i], i + 1);
	}
}
