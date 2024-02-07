// 19,168 kb 메모리
//110 ms 실행시간

package com.edu.ssafy_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1954_달팽이숫자_조용훈 {
	static StringBuilder builder;
	static int[][] visit, map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int dir; // 0 오른쪽, 1 아래, 2 왼쪽, 3 위
	static int n; // 맵 크기
	static int cnt; // 배열에 넣을 값

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bf.readLine());

		for (int T = 1; T <= t; T++) {
			n = Integer.parseInt(bf.readLine());
			map = new int[n][n];
			visit = new int[n][n];
			cnt = 1;
			builder = new StringBuilder();
			go(0, 0, 0);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					builder.append(map[i][j] + " ");
				}
				builder.append("\n");
			}
			System.out.println("#" + T);
			System.out.print(builder);
		}
	}

	static void go(int index, int x, int y) {
		if (visit[x][y] == 0) {
			map[x][y] = cnt++;
			visit[x][y] = 1;
		}
		if (index == (n * n) - 1)
			return;
		if (dir == 0) {
			int ny = y + dy[dir];
			if (ny >= n || visit[x][ny] == 1) {
				dir = 1;
				go(index, x, y);
			} else {
				go(index + 1, x, ny);
			}

		} else if (dir == 1) {
			int nx = x + dx[dir];
			if (nx >= n || visit[nx][y] == 1) {
				dir = 2;
				go(index, x, y);
			} else {
				go(index + 1, nx, y);
			}
		} else if (dir == 2) {
			int ny = y + dy[dir];
			if (ny < 0 || visit[x][ny] == 1) {
				dir = 3;
				go(index, x, y);
			} else {
				go(index + 1, x, ny);
			}
		} else if (dir == 3) {
			int nx = x + dx[dir];
			if (nx < 0 || visit[nx][y] == 1) {
				dir = 0;
				go(index, x, y);
			} else {
				go(index + 1, nx, y);
			}
		}

	}
}
