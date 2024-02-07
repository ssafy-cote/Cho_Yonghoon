package com.edu.ssafy_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_16926_배열돌리기3_조용훈 { // 303316kb 메모리, 1784ms 시간

	static int n, m, num;
	static int[][] map, visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		num = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		for(int i = 0; i < num; i++) {
			
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				builder.append(map[i][j] + " ");
			}
			builder.append("\n");
		}
		System.out.println(builder);
	}
}
