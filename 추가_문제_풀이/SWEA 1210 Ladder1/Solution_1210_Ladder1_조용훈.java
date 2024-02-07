package com.edu.ssafy_0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_Ladder1_조용훈 {
	static int[][] map = new int[100][100];
	static int[][] visit = new int[100][100];
	static int flag;
	static int[] dy = { -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = null;

		for (int t = 1; t <= 10; t++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int T = Integer.parseInt(stringTokenizer.nextToken());

			for (int i = 0; i < 100; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			visit = new int[100][100];
			int ans = 0;
			flag = 0;
			for (int i = 0; i < 100; i++) {
				if (map[99][i] == 2) {
					go(99, i);
				}
			}

			System.out.println("#" + t + " " +flag);
		}
	}

	static void go(int x, int y) {
		if (x == 0) {
			flag = y;
			return;
		}
		visit[x][y] = 1;
		int ly = y -1;
		int ry = y +1;
		int f = 0;
		if(ly > -1) {
			if(map[x][ly] == 1 && visit[x][ly] == 0) {
				f = 1;
				go(x, ly);
			}
		}
		if(ry < 100) {
			if(map[x][ry] == 1 && visit[x][ry] == 0) {
				f = 1;
				go(x, ry);
			}
		}
		if(f== 0){
			int nx = x - 1;
			if (map[nx][y] == 1 && visit[nx][y] == 0) {
				go(nx, y);
			}
		}
	}
}
