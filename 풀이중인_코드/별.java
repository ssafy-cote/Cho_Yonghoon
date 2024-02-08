package com.edu.ssafy_0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 별 {
	static int n;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n + 2][n + 2];

		doIt2(n, n/3, n/3);

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

	static void doIt2(int x, int startX, int startY) {

		if (x == 1)
			return;

		int nx = x / 3;

		for (int i = startX + 1; i <= startX * 2; i++) {
			for (int j = startY + 1; j <= startY * 2; j++) {
				map[i][j] = 1;
			}
		}

		doIt2(nx, nx, nx);
		doIt2(nx, nx, nx + nx);
		doIt2(nx, nx, nx + (nx * 2));
		doIt2(nx, nx + nx, nx);
		doIt2(nx, nx + nx, (nx * 2));
		doIt2(nx, nx + (nx * 2), nx);
		doIt2(nx, nx + (nx * 2), nx + nx);
		doIt2(nx, nx + (nx * 2), nx + (nx * 2));

	}

	static void doIt(int x, int start, int xx, int yy) {

		if (x == 1)
			return;

		int nx = x / 3;

		for (int i = start + 1 + xx; i <= start * 2 + xx; i++) {
			for (int j = start + 1 + yy; j <= start * 2 + yy; j++) {
				map[i][j] = 1;
			}
		}

		doIt(nx, start / 3, 0, 0);
		doIt(nx, start / 3, 0, nx);
		doIt(nx, start / 3, 0, nx * 2);
		doIt(nx, start / 3, nx, 0);
		doIt(nx, start / 3, nx, nx * 2);
		doIt(nx, start / 3, nx * 2, 0);
		doIt(nx, start / 3, nx * 2, nx);
		doIt(nx, start / 3, nx * 2, nx * 2);
	}
}
