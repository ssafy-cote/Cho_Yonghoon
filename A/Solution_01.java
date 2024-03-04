package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_01 {

	static int ans;
	static int[][] map;
	static boolean[][] visit;
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
						return true;
					}
					visit[nx][ny] = true;
					if (go(len, nx, ny, index + 1)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < N && -1 < y && y < M);
	}
}
