package algo0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468_안전영역_0403 {

	static int n;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][n];

		int max = 0;
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				max = (max < map[i][j]) ? map[i][j] : max;
			}
		}
		int cnt = 0;
		int ans = 0;
		while (cnt <= max) {
			int temp = 0;
			visit = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if ((map[i][j] - cnt > 0) && !visit[i][j]) {
						bfs(i, j, cnt);
						++temp;
					}
				}
			}
			ans = (ans < temp) ? temp : ans;
			++cnt;
		}
		System.out.println(ans);
	}

	static void bfs(int x, int y, int cnt) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(x, y));
		visit[x][y] = true;

		while (!queue.isEmpty()) {

			Pos pos = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				if (check(nx, ny) && !visit[nx][ny] && (map[nx][ny] - cnt > 0)) {
					visit[nx][ny] = true;
					queue.add(new Pos(nx, ny));
				}
			}
		}
	}
	
	static boolean check(int x, int y) {
		return (-1 < x && x < n && -1 < y && y < n);
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
