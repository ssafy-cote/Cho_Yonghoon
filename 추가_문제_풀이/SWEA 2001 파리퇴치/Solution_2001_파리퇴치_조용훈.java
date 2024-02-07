package com.edu.ssafy_0201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2001_파리퇴치_조용훈 {	// 18,404kb 메모리, 101ms
	static int n, m, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int T = Integer.parseInt(stringTokenizer.nextToken());
		for (int t = 1; t <= T; t++) {
			ans = 0;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[n + 2][n + 2];
			
			// 설명이 필요하다면 구간합_5 장준석님의 코드를 읽어보시오
			for (int i = 1; i <= n; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 1; j <= n; j++) {
					int num = Integer.parseInt(stringTokenizer.nextToken());
					map[i][j] = num + (map[i][j - 1] + map[i - 1][j] - map[i - 1][j - 1]);
				}
			}
			
			int result = 0;
			for (int i = 1; i + m <= n + 1; i++) {
				for (int j = 1; j + m <= n + 1; j++) {
					result = map[i + m - 1][j + m - 1] - map[i + m - 1][j - 1] - map[i - 1][j + m - 1] + map[i - 1][j - 1];
					ans = Math.max(result, ans);
				}
			}
			System.out.println("#" + t + " " + ans);
		}
	}
}