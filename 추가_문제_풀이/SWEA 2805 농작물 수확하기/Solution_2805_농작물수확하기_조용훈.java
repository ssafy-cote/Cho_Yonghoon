package com.edu.ssafy_0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2805_농작물수확하기_조용훈 {

	static int[][] map;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int sum;
	static int[][] ans;
	static int a, n;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			n = Integer.parseInt(stringTokenizer.nextToken());

			int num = (n - 1) / 2;
			a = (n + 1) / 2;
			sum = 0;
			map = new int[n][n];
			ans = new int[n][n];
			for (int i = 0; i < n; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				String string = stringTokenizer.nextToken();
				for (int j = 0; j < n; j++) {
					String[] strings = string.split("");
					map[i][j] = Integer.parseInt(strings[j]);
				}
			}
			ans[num][num] = 1;
			bfs(num, num);

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(ans[i][j] <= a) {
						sum += map[i][j];
					}
				}
			}

			System.out.println("#" + T + " " + sum);
		}
	}
	static void bfs(int x, int y) {

		Queue<Pos> queue = new LinkedList<>();

		queue.add(new Pos(x, y));

		while (!queue.isEmpty()) {
			Pos pos = new Pos();
			pos = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				if (ny > -1 && ny < n && nx > -1 && nx < n) {
					if (ans[nx][ny] == 0) {
						queue.add(new Pos(nx, ny));
						ans[nx][ny] = ans[pos.x][pos.y] + 1;
					}

				}

			}
		}
	}

	static class Pos {
		int x;
		int y;

		public Pos() {
		}

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}
