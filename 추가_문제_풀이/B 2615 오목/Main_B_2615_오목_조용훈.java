package com.edu.ssafy_0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2615_오목_조용훈 {
	static int[][] map = new int[21][21];
	static int[] dx = { -1, 0, 1, 1 };
	static int[] dy = { 1, 1, 1, 0 };

	public static void main(String[] args) throws IOException {	// 13044kb 메모리, 92ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		for (int i = 1; i <= 19; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		for (int i = 1; i <= 19; i++) {
			for (int j = 1; j <= 19; j++) {
				check(map[i][j], i, j);
			}
		}
		System.out.println(0);
	}

	static void check(int num, int x, int y) {
		if (num == 0)
			return;

		for (int i = 0; i < 4; i++) {
			int bx = x - dx[i];
			int by = y - dy[i];
			
			// 이전 방향 체크
			if (map[bx][by] == num)
				continue;
			int cnt = 1;
			int nx = x + dx[i];
			int ny = y + dy[i];
			while (checkMap(nx, ny)) {
				if (map[nx][ny] == num) {
					++cnt;
					nx += dx[i];
					ny += dy[i];
				} else {
					break;
				}
			}
			if(cnt == 5) {
				System.out.println(num);
				System.out.println(x + " " + y);
				System.exit(0);
			}
		}
	}
	// 범위 체크
	static boolean checkMap(int x, int y) {
		return x > 0 && x < 20 && y < 20 && y > 0;
	}
}
