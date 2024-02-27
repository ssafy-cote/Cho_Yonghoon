package algo0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2206_벽부수고이동하기_조용훈_시간초과 {
	static int N, M;
	static int[][] map, mapLog;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static ArrayList<Pos> brickList;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		ans = Integer.MAX_VALUE;
		map = new int[N + 2][M + 2];
		mapLog = new int[N + 2][M + 2];
		mapLog[1][1] = 1;
		brickList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String temp = stringTokenizer.nextToken();
			String[] strs = temp.split("");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(strs[j - 1]);
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 1 && brickCheck(i, j)) {
					brickList.add(new Pos(i, j));
				}
			}
		}

		for (Pos brickPos : brickList) {
			map[brickPos.x][brickPos.y] = 0;
			visit = new boolean[N + 2][M + 2];
			bfs(1, 1);
			if (mapLog[N][M] != 0) {
				ans = (ans >= mapLog[N][M]) ? mapLog[N][M] : ans;
			}
			map[brickPos.x][brickPos.y] = 1;
		}

//		for(int i = 0; i < N+2; i++) {
//			for(int j = 0; j < M +2; j++) {
//				System.out.print(mapLog[i][j]  + " ");
//			}
//			System.out.println();
//		}

		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}

	static boolean brickCheck(int x, int y) {
		int upX = x - 1;
		int downX = x + 1;
		if (map[upX][y] == 0 && map[downX][y] == 0) {
			return true;
		}
		int rY = y + 1;
		int lY = y - 1;
		if (map[x][rY] == 0 && map[x][lY] == 0) {
			return true;
		}
		return false;
	}

	static void bfs(int x, int y) {

		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(x, y));
		visit[x][y] = true;

		while (!queue.isEmpty()) {

			Pos pos = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				// 벽 부수기 가능 경우
				if (check(nx, ny) && !visit[nx][ny]) {
					if (map[nx][ny] == 0) {
						visit[nx][ny] = true;
						mapLog[nx][ny] = mapLog[pos.x][pos.y] + 1;
						queue.add(new Pos(nx, ny));
					}
				}
			}
		}

	}

	static boolean check(int x, int y) {
		return (0 < x && x < N + 1 && 0 < y && y < M + 1);
	}

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
