package com.edu.ssafy_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_16926_배열돌리기1_조용훈 {	// 303316kb 메모리, 1784ms 시간

	static int n, m, num;
	static int[][] map, visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		num = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		for (int i = 0; i < num; i++) {
			visit = new int[n][m];
			go(0, 0, n, m);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	// x, y 시작 위치 n, m 세로 가로 길이
	static void go(int x, int y, int n, int m) {
		// 범위 벗어나면 리턴
		if (x > n - 1 || y > m - 1 || visit[x][y] == 1)
			return;
		// 처음 위치 값 따로 저장 (값 덮어 쓰면 사라지니까)
		int temp = map[x][y];
		visit[x][y] = 1;
		
		// 위쪽 면 ->
		for (int i = y; i < m - 1; i++) {
			map[x][i] = map[x][i + 1];
			visit[x][i] = 1;
		}
		// 오른쪽 면 v
		for (int i = x; i < n - 1; i++) {
			map[i][m - 1] = map[i + 1][m - 1];
			visit[i][m - 1] = 1;
		}
		// 아래쪽 면 <-
		for (int i = m - 1; i > y; i--) {
			map[n - 1][i] = map[n - 1][i - 1];
			visit[n - 1][i] = 1;
		}
		// 왼쪽 면 ^
		for (int i = n - 1; i > x + 1; i--) {
			map[i][y] = map[i - 1][y];
			visit[i][y] = 1;
		}
		// 마지막 값 따로 빼준거 저장
		map[x + 1][y] = temp;
		visit[x + 1][y] = 1;
		// 다음 안쪽 줄 바꿔주기
		go(x + 1, y + 1, n - 1, m - 1);
	}
}
