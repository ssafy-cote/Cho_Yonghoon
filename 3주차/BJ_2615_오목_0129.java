package com.edu.ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2615_오목_0129 {

	static int[] dx = { -1, 0, 1, 1 };
	static int[] dy = { 1, 1, 1, 0 };
	static int[][] map = new int[21][21];
	static int flag, resultX, resultY;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int i = 1; i <= 19; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		A: for (int i = 1; i <= 19; i++) {
			for (int j = 1; j <= 19; j++) {
				if (map[i][j] != 0) {
					find(i, j, map[i][j]);
					if (flag != 0) {
						resultX = i;
						resultY = j;
						break A;
					}
				}
			}
		}
		if (flag == 0) {
			System.out.println(flag);
		} else {
			System.out.println(flag);
			System.out.println(resultX + " " + resultY);
		}

	}

	static void find(int x, int y, int num) {
		for (int i = 0; i < 4; i++) {
			int nx = x;
			int ny = y;
			if (num == map[x - dx[i]][y - dy[i]])
				continue;
			int cnt = 1;
			while (true) {
				// 다음 위치로 한 칸 이동
				nx += dx[i];
				ny += dy[i];

				// 같은 바둑알이 아니라면
				if (map[nx][ny] != num)
					break;

				// 경계 내에 있고 같은 바둑알의 경우
				if (++cnt > 5) {
					break;
				} // 바둑알 개수 cnt
			}
			if(cnt==5) {
				flag = num;
				return;	//종료
			}


		}
	}
}
