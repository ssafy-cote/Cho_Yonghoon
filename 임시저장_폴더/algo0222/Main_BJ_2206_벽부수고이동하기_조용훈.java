package algo0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2206_벽부수고이동하기_조용훈 {
	static int N, M;
	static int[][] map, mapLog;
	static boolean[][][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int ans;

	public static void main(String[] args) throws IOException { // 189464kb 메모리, 1244ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[N + 2][M + 2];
		visit = new boolean[N + 2][M + 2][2];
		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String temp = stringTokenizer.nextToken();
			String[] strs = temp.split("");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(strs[j - 1]);
			}
		}
		ans = Integer.MAX_VALUE;

		bfs(1, 1, 0, 1);

		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}

	}

	static void bfs(int x, int y, int cnt, int sum) {

		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(x, y, cnt, sum));
		visit[x][y][0] = true;

		while (!queue.isEmpty()) {
			Pos pos = queue.poll();
			if (pos.x == N && pos.y == M) {
				ans = (pos.sum <= ans) ? pos.sum : ans;
			}
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				if (check(nx, ny)) {
					// 벽 부수기 가능 경우
					if (pos.cnt == 0) {
						// 0 일경우 걍 ㄱㄱ
						if (!visit[nx][ny][0]) {
							if (map[nx][ny] == 0) {
								visit[nx][ny][0] = true;
								queue.add(new Pos(nx, ny, pos.cnt, pos.sum + 1));
							} else {
								// 1일 경우 부수기
								visit[nx][ny][1] = true;
								queue.add(new Pos(nx, ny, pos.cnt + 1, pos.sum + 1));
							}
						}
					} else {
						// 벽 부수기 불가능 경우
						if (!visit[nx][ny][1]) {
							if (map[nx][ny] == 0) {
								visit[nx][ny][1] = true;
								queue.add(new Pos(nx, ny, pos.cnt, pos.sum + 1));
							}
						}
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
		int cnt;
		int sum;

		public Pos(int x, int y, int cnt, int sum) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.sum = sum;
		}
	}

}
