// 68740KB 메모리
// 636ms 시간

package com.edu.ssafy_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11659_구간합구하기4_조용훈 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());

		int[] map = new int[n + 1];

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= n; i++) {
			map[i] = Integer.parseInt(stringTokenizer.nextToken()) + map[i-1];
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < m; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int f = Integer.parseInt(stringTokenizer.nextToken());
			int s = Integer.parseInt(stringTokenizer.nextToken());
			builder.append(map[s] - map[f-1] + "\n");
		}
		System.out.println(builder);
	}
}
