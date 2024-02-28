package algo0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_1600_말이되고픈원숭이_조용훈 {

	static int[] horseX = { 2, 1, -1, -2, 2, 1, -1, -2 };
	static int[] horseY = { 1, 2, 2, 1, -1, -2, -2, -1 };

	static int[] dx = { 1, 0, 0, -1 };
	static int[] dy = { 0, 1, -1, 0 };

	static int[][] map;

	static int K, N, M;

	static boolean[][][] visit;

	public static void main(String[] args) throws IOException { // 50112kb 메모리, 488ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		K = Integer.parseInt(stringTokenizer.nextToken());

		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		M = Integer.parseInt(stringTokenizer.nextToken());
		N = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		visit = new boolean[N][M][31];
		int ans = bfs();
		System.out.println(ans);
	}

	static int bfs() {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(0, 0, 0));
		visit[0][0][0] = true;

		int depth = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Pos pos = queue.poll();
				if (pos.x == N - 1 && pos.y == M - 1) {
					return depth;
				}
				if (pos.cnt < K) {
					for (int i = 0; i < 8; i++) {
						int nx = pos.x + horseX[i];
						int ny = pos.y + horseY[i];
						if (check(nx, ny) && map[nx][ny] != 1 && !visit[nx][ny][pos.cnt + 1]) {
							visit[nx][ny][pos.cnt + 1] = true;
							queue.add(new Pos(nx, ny, pos.cnt + 1));
						}
					}
				}

				for (int i = 0; i < 4; i++) {
					int nx = pos.x + dx[i];
					int ny = pos.y + dy[i];
					if (check(nx, ny) && map[nx][ny] != 1 && !visit[nx][ny][pos.cnt]) {
						visit[nx][ny][pos.cnt] = true;
						queue.add(new Pos(nx, ny, pos.cnt));
					}
				}
			}
			++depth;
		}
		return -1;
	}

	static class Pos {
		int x;
		int y;
		int cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < N && -1 < y && y < M);
	}

}
