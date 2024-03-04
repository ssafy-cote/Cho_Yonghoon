package algo0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17836_공주님을구해라_0304 {
	static int N, M, K;
	static int[][] map;
	static Pos sward;
	static int toSward;
	static int ans;
	static boolean[][] visit;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		K = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 2) {
					sward = new Pos(i, j);
				}
			}
		}
		toSward = 0;

		// 그냥 공주까지의 최단 거리
		int basic = bfs(N, M);
		// 칼까지의 최단거리
		int dis = Math.abs(sward.x - N) + Math.abs(sward.y - M);
		toSward = bfs(sward.x, sward.y) + dis;

		ans = Math.min(toSward, basic);

		if (ans > K || ans == 100000000) {
			System.out.println("Fail");
		} else {
			System.out.println(ans);
		}
	}

	static int bfs(int fx, int fy) {
		Queue<Pos> queue = new ArrayDeque<>();
		visit = new boolean[N + 2][M + 2];
		queue.add(new Pos(1, 1));
		visit[1][1] = true;

		int cnt = 0;
		while (!queue.isEmpty()) {
			++cnt;
			int size = queue.size();
			while (size-- > 0) {
				Pos pos = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = pos.x + dx[i];
					int ny = pos.y + dy[i];

					if (check(nx, ny) && !visit[nx][ny] && map[nx][ny] != 1) {
						if (nx == fx && ny == fy) {
							return cnt;
						}
						visit[nx][ny] = true;
						queue.add(new Pos(nx, ny));
					}
				}
			}
		}
		return 100000000;
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
