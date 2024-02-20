package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_10026_적록색약_조용훈_DFS {
	static int n;
	static char[][] map;
	static boolean[][] visit;
	static int common, colorBlindness;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException { // 12376kb 메모리, 88ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());

		// 범위 체크 안할라고 패딩 주기
		map = new char[n + 2][n + 2];

		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String temp = stringTokenizer.nextToken();
			for (int j = 1; j <= n; j++) {
				map[i][j] = temp.charAt(j - 1);
			}
		}

		// 색약
		// 방문 배열 초기화
		visit = new boolean[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (visit[i][j])
					continue;
				visit[i][j] = true;
				dfs(true, i, j);
				++colorBlindness;
			}
		}

		// 일반인
		// 방문 배열 초기화
		visit = new boolean[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (visit[i][j])
					continue;
				visit[i][j] = true;
				dfs(false, i, j);
				++common;
			}
		}
		System.out.println(common + " " + colorBlindness);
	}

	// isColorBlindness: true -> 색약
	// isColorBlindness: false -> 일반인
	static void dfs(boolean isColorBlindness, int x, int y) {
		char whatColor = map[x][y];
		char equalColor;
		if (whatColor == 'R') {
			equalColor = 'G';
		} else if (whatColor == 'G') {
			equalColor = 'R';
		} else {
			equalColor = 'B';
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isColorBlindness) {
				if (map[nx][ny] == whatColor || map[nx][ny] == equalColor) {
					if (!visit[nx][ny]) {
						visit[nx][ny] = true;
						dfs(isColorBlindness, nx, ny);
					}
				}
			} else {
				if (map[nx][ny] == whatColor) {
					if (!visit[nx][ny]) {
						visit[nx][ny] = true;
						dfs(isColorBlindness, nx, ny);
					}
				}
			}
		}

	}

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
