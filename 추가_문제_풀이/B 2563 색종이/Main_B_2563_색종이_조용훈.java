package com.edu.ssafy_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2563_색종이_조용훈 {

	public static void main(String[] args) throws IOException { // 11596kb 메모리, 84ms 시간
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int sum = 0;

		int[][] map = new int[101][101];

		// 색종이를 입력 받는 동시에 색종이가 차지하는 영역에 1 표시
		for (int s = 0; s < N; s++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			for (int p = i; p < i + 10; p++) {
				for (int q = j; q < j + 10; q++) {
					map[p][q] = 1;
				}
			}
		}

		// 마지막에 1의 갯수만 체크하면 겹친 부분 고려해서 넓이가 구해짐
		for (int r = 0; r < 101; r++) {
			for (int r2 = 0; r2 < 101; r2++) {
				if (map[r][r2] == 1)
					sum++;
			}
		}
		System.out.println(sum);
	}
}
