package com.edu.ssafy_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_3109_빵집_조용훈 {

	static char[][] map;
	static int n, m;
	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };
	static boolean[][] visit;
	static int ans;

	public static void main(String[] args) throws IOException { // 43156kb 메모리, 380ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[n + 2][m + 2];
		visit = new boolean[n + 2][m + 2];

		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String string = stringTokenizer.nextToken();
			for (int j = 1; j <= m; j++) {
				map[i][j] = string.charAt(j - 1);
				if (map[i][j] == 'x') {
					visit[i][j] = true;
				}
			}
		}
		for (int i = 1; i <= n; i++) {
			go(i, 1);
		}
		System.out.println(ans);
	}

	static boolean go(int x, int y) {
		if (y == m) {
			++ans;
			return true;
		}
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (map[nx][ny] == '.' && !visit[nx][ny]) {
				visit[nx][ny] = true;
				if (go(nx, ny))
					return true;
			}
		}
		return false;
	}
}
