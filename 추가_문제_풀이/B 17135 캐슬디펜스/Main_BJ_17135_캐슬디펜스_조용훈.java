package algo0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17135_캐슬디펜스_조용훈 {
	static int N, M, Dis;
	static int[][] map, mapTemp;
	static int[] archerList;
	static int ans;
	static int enemyCnt, enemyCntInGame;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException { // 32588kb 메모리, 200ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		Dis = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[N + 1][M];

		archerList = new int[3];

		enemyCnt = 0;
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = temp;
				if (temp == 1)
					++enemyCnt;
			}
		}
		ans = 0;
		makeComie(0, 0);
		System.out.println(ans);

	}

	static void makeComie(int index, int start) {
		if (index == 3) {
			mapTemp = new int[N + 1][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					mapTemp[i][j] = map[i][j];
				}
			}
			enemyCntInGame = enemyCnt;
			int cnt = doGame();
			ans = (ans <= cnt) ? cnt : ans;
			return;
		}

		for (int i = start; i < M; i++) {
			archerList[index] = i;
			makeComie(index + 1, i + 1);
		}

	}

	static int doGame() {

		int kill = 0;
		while (enemyCntInGame > 0) {
			// 죽일 적 리스트
			Pos[] killList = new Pos[3];

			// 죽일 적 탐색
			for (int i = 0; i < 3; i++) {
				killList[i] = aiming(i);
			}

			// 죽이기
			for (Pos ePos : killList) {
				if (ePos.x != -1) {
					// 이미 죽인곳이라면 다음으로
					if (mapTemp[ePos.x][ePos.y] == 0)
						continue;
					// 죽이고 총 적 수 줄이기
					mapTemp[ePos.x][ePos.y] = 0;
					--enemyCntInGame;
					++kill;
				}
			}
			go();
		}
		return kill;
	}

	static void go() {
		// 적 이동
		for (int i = N - 1; i > -1; i--) {
			for (int j = 0; j < M; j++) {
				if (mapTemp[i][j] == 1) {
					if (mapTemp[i + 1][j] == 1) {
						mapTemp[i + 1][j] = 2;
						mapTemp[i][j] = 0;
					} else {
						mapTemp[i + 1][j] = 1;
						mapTemp[i][j] = 0;
					}
				} else if (mapTemp[i][j] == 2) {
					if (mapTemp[i + 1][j] == 1) {
						mapTemp[i + 1][j] = 2;
						mapTemp[i][j] = 1;
					} else {
						mapTemp[i + 1][j] = 1;
						mapTemp[i][j] = 0;
					}
				}
			}
		}
		// 성으로 들어간 적 처리
		for (int i = 0; i < M; i++) {
			if (mapTemp[N][i] == 1) {
				mapTemp[N][i] = 0;
				--enemyCntInGame;
			}
		}
	}

	static Pos aiming(int archerNumber) {
		int x = N;
		int y = archerList[archerNumber];
		visit = new boolean[N + 1][M];
		Arrays.fill(visit[N], true);
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(x, y));
		int dis = 1;
		int[] dx = { 0, -1, 0 };
		int[] dy = { -1, 0, 1 };
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {

				Pos pos = queue.poll();

				for (int i = 0; i < 3; i++) {
					int nx = pos.x + dx[i];
					int ny = pos.y + dy[i];

					if (check(nx, ny) && !visit[nx][ny]) {
						if (mapTemp[nx][ny] == 1) {
							return new Pos(nx, ny);
						}
						visit[nx][ny] = true;
						queue.add(new Pos(nx, ny));
					}
				}
			}
			if (dis == Dis) {
				return new Pos(-1, -1);
			}
			++dis;
		}

		return new Pos(-1, -1);
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < N && -1 < y && y < M);
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