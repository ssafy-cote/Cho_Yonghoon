package algo0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_2636_치즈_조용훈 {
	static int N, M;
	static int[][] map, mapTemp;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException { // 14116kb 메모리, 132ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N][M];
		mapTemp = new int[N][M];
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				mapTemp[i][j] = map[i][j];
			}
		}

		int time = 0;
		int ans = 0;
		while (true) {
			++time;
			// 복사 (임시저장)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					mapTemp[i][j] = map[i][j];
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(mapTemp[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("================위는 임시저장맵");

			// 2만들기
			visit = new boolean[N][M];
			bfs(0, 0);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("================위는 찐맵");
			// 녹이기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						melt(i, j);
					}
				}
			}

			boolean flag = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						flag = true;
					}
				}
			}
			if (!flag) {
				ans = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (mapTemp[i][j] == 1) {
							++ans;
						}
					}
				}
				break;
			}
		}

		System.out.println(time);
		System.out.println(ans);

	}

	static void melt(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx > -1 && nx < N && ny > -1 && ny < M) {
				if (map[nx][ny] == 2) {
					map[x][y] = 0;
					return;
				}
			}
		}
	}

	// command: true -> 녹일 수 있는 치즈 판별
	static void bfs(int x, int y) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(x, y));
		visit[x][y] = true;
		map[x][y] = 2;
		while (!queue.isEmpty()) {
			Pos pos = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				if (nx > -1 && nx < N && ny > -1 && ny < M) {
					if (!visit[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == 2)) {
						visit[nx][ny] = true;
						map[nx][ny] = 2;
						queue.add(new Pos(nx, ny));
					}
				}
			}
		}
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
