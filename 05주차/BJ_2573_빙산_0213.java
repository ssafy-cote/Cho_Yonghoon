package com.edu.ssafy_0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2573_빙산_0213 {

	static int n, m;
	static int[][] visit, is;
	static Ice[][] map;
	static int landCnt, year;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new Ice[n + 2][m + 2];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 1; j <= m; j++) {
				int temp = Integer.parseInt(stringTokenizer.nextToken());
				int isIce = 0;
				if (temp != 0) {
					isIce = 1;
				}
				map[i][j] = new Ice(i, j, temp, isIce);
			}
		}
		year = 0;
		while (melting()) {
			++year;
			landCnt = 0;
			visit = new int[n + 2][m + 2];
			landCheck();

			if (landCnt > 1) {
				System.out.println(year);
				System.exit(0);
			}
		}
		System.out.println(0);

	}

	static void landCheck() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (visit[i][j] != 1 && map[i][j].height != 0) {
					bfs(i, j);
					++landCnt;
				}
			}
		}
	}

	static void bfs(int x, int y) {
		Queue<Ice> queue = new ArrayDeque<>();
		queue.add(new Ice(x, y));
		visit[x][y] = 1;
		while (!queue.isEmpty()) {
			Ice ice = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = ice.x + dx[i];
				int ny = ice.y + dy[i];
				if (visit[nx][ny] != 1 && map[nx][ny].height != 0) {
					queue.add(new Ice(nx, ny));
					visit[nx][ny] = 1;
				}
			}
		}
	}

	static boolean melting() {
		boolean flag = false;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (map[i][j].height != 0) {
					flag = true;
					map[i][j].height = ((map[i][j].height - check(i, j)) < 0) ? 0 : map[i][j].height - check(i, j);
				}
			}
		}
		// 한 번에 동시에 전부 녹기 때문에 나중에 녹은 땅을 처리 해야함
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (map[i][j].height == 0) {
					map[i][j].isIce = 0;
				}
			}
		}
		return flag;
	}

	static int check(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (map[nx][ny].height == 0 && map[nx][ny].isIce != 1)
				++cnt;
		}
		return cnt;
	}

	static class Ice {
		int x;
		int y;
		int height;
		int isIce;

		public Ice(int x, int y, int height, int isIce) {
			this.x = x;
			this.y = y;
			this.height = height;
			this.isIce = isIce;
		}

		public Ice(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
