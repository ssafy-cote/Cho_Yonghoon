package com.edu.ssafy_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_정사각형방_조용훈 {
	static int[][] map;
	static int[] list;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {	// 29,752kb 메모리, 150ms 시간
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int T = Integer.parseInt(st.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			map = new int[n + 2][n + 2];
			list = new int[n * n + 1];
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for (int j = 1; j <= n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					// 4방면으로 1보다 큰 수 있는지 확인
					// 있다면 해당 숫자 체크
					cheeck(i,j);
				}
			}
			int cnt = 1;
			int max = 0;
			int index = 0;
			
			// 배열에 연속된 1이 나타난다면 이어지는 숫자
			for(int i = n*n; i > 0; i--) {
				if(list[i] == 1) {
					++cnt;
					if(max <= cnt) {
						max = cnt;
						index = i;
					}
				}else {
					cnt = 1;
				}
			}
			builder.append("#").append(t).append(" ").append(index).append(" ").append(max).append("\n");
		}
		System.out.println(builder);
	}

	static void cheeck(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (map[x][y] + 1 == map[nx][ny]) {
				list[map[x][y]] = 1;
			}
		}
	}
}
