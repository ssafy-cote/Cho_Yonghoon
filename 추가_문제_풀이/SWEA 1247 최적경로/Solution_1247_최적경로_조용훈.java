package com.edu.ssafy_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_최적경로_조용훈 { // 21,128kb 메모리, 2,424ms 시간

	static int ans, n;
	static Pos[] map, result;
	static int[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder builder = new StringBuilder();
		int T = Integer.parseInt(stringTokenizer.nextToken());
		for (int t = 1; t <= T; t++) {
			ans = Integer.MAX_VALUE;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			// 고객수
			n = Integer.parseInt(stringTokenizer.nextToken());
			map = new Pos[n + 2];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int i = 0; i < n + 2; i++) {
				map[i] = new Pos(Integer.parseInt(stringTokenizer.nextToken()),
						Integer.parseInt(stringTokenizer.nextToken()));
			}
			result = new Pos[n];
			visit = new int[n];
			make(0);

			builder.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static void make(int index) {

		if (index == n) {
			int sum = getLen(map[0], result[0]);
			for (int i = 1; i < n; i++) {
				sum += getLen(result[i - 1], result[i]);
			}
			sum += getLen(result[n - 1], map[1]);
			ans = Math.min(ans, sum);
		}

		for (int i = 0; i < n; i++) {
			if (visit[i] == 1)
				continue;
			result[index] = map[i + 2];
			visit[i] = 1;
			make(index + 1);
			visit[i] = 0;
		}
	}

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	// 경로 거리 구하는 함수
	static int getLen(Pos p1, Pos p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}
