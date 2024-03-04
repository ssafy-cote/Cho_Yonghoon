package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_01_Debug {

	static int ans;
	static int[][] map;
	static boolean[][] visit;
	static int[][] printMap;
	static int N, M;
	// 상 우 좌 하
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, 1, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder builder = new StringBuilder();
		int t = Integer.parseInt(stringTokenizer.nextToken());

		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[N][M];
			printMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			ans = 1;
			for (int i = 1; i <= N; i++) {
				visit = new boolean[N][M];
				if (go(i, N - 1, 0, 0)) {
					ans = i;
					break;
				}
//				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}

			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static boolean go(int len, int x, int y, int index) {

		for (int j = 1; j <= len; j++) {
			for (int i = 0; i < 4; i++) {
				int nx = 0;
				int ny = 0;
				if (i == 1 || i == 2) {
					nx = x + dx[i];
					ny = y + dy[i];
				} else {
					nx = x + (dx[i] * j);
					ny = y + (dy[i] * j);
				}
				if (check(nx, ny) && !visit[nx][ny] && map[nx][ny] != 0) {
					if (map[nx][ny] == 3) {
//						System.out.println(len + "여기");
						return true;
					}
					visit[nx][ny] = true;
					printMap[nx][ny] = index;
//					print();
//					System.out.println("j: " + j);
					if (go(len, nx, ny, index + 1)) {
						return true;
					}
//					System.out.println("다음");
//					visit[nx][ny] = false;
				}
			}
		}

		return false;
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < N && -1 < y && y < M);
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(printMap[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("==================");
	}

}
