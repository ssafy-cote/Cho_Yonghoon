package algo0614;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14940_쉬운최단거리_0614 {

	static int n;
	static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		int[][] map = new int[n][m];
		int x = 0;
		int y = 0;
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				if (map[i][j] == 2) {
					x = i;
					y = j;
				}
			}
		}

		int[][] ans = new int[n][m];

		int[] dx = { 0, 0, 1, -1 };
		int[] dy = { 1, -1, 0, 0 };

		Queue<pos> queue = new ArrayDeque<>();
		boolean[][] visit = new boolean[n][m];
		visit[x][y] = true;
		queue.add(new pos(x, y));
		int dis = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			++dis;
			while (size-- > 0) {

				pos pos = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nx = pos.x + dx[i];
					int ny = pos.y + dy[i];
					if (check(nx, ny) && !visit[nx][ny] && (map[nx][ny] == 1)) {
						queue.add(new pos(nx, ny));
						visit[nx][ny] = true;
						ans[nx][ny] = dis;
					}
				}
			}

		}
		ans[x][y] = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ans[i][j] == 0 && map[i][j] != 0) {
					ans[i][j] = -1;
				}
				if (i == x && j == y) {
					ans[i][j] = 0;
				}
				sb.append(ans[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < n && -1 < y && y < m);
	}

	static class pos {
		int x;
		int y;

		public pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
