package com.edu.ssafy_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1247_스도쿠검증_조용훈 {
	static int ans;
	static int[][] map;
	static int[] checked;
	static int[] dx = { 1, 1, -1, -1, 0, 0, -1, 1 };
	static int[] dy = { 1, 0, -1, 0, 1, -1, 1, -1 };

	public static void main(String[] args) throws IOException {	// 18,424kb 메모리, 105ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder builder = new StringBuilder();
		int T = Integer.parseInt(stringTokenizer.nextToken());
		for (int t = 1; t <= T; t++) {
			ans = 1;
			map = new int[9][9];
			for (int i = 0; i < 9; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 0; j < 9; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			for (int i = 0; i < 9; i++) {
				searo(i, i);
				garo(i, i);
			}
			for(int i = 1; i < 9; i += 3) {
				for(int j = 1; j < 9; j += 3) {
					all(i, j);
				}
			}
			builder.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	// 세로 확인하기
	static void searo(int x, int y) {
		checked = new int[10];
		for (int i = 0; i < 9; i++) {
			checked[map[i][y]] = 1;
		}
		checked();
	}
	
	// 가로 확인하기
	static void garo(int x, int y) {
		checked = new int[10];
		for (int i = 0; i < 9; i++) {
			checked[map[x][i]] = 1;
		}
		checked();
	}
	
	// 8방향으로 확인하기
	static void all (int x, int y) {
		checked = new int[10];
		checked[map[x][y]] = 1;
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			checked[map[nx][ny]] = 1;
		}
		checked();
	}

	// 숫자를 모두 잘 갖고 있는지 체크하는 함수
	static void checked() {
		for (int i = 1; i < 10; i++) {
			if (checked[i] == 0)
				ans = 0;
		}
	}
}
