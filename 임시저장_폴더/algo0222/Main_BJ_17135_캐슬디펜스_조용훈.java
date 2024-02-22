package algo0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17135_ĳ�����潺_������ {
	static int N, M, Dis;
	static int[][] map, mapTemp;
	static int[] archerList;
	static int ans;
	static int enemyCnt, enemyCntInGame;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
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
//		System.out.println(enemyCnt);
		ans = 0;
		makeComie(0, 0);
		System.out.println(ans);

	}

	static void makeComie(int index, int start) {
		if (index == 3) {
//			System.out.println(Arrays.toString(archerList));
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
			// ���� �� ����Ʈ
			Pos[] killList = new Pos[3];

			// ���� �� Ž��
			for (int i = 0; i < 3; i++) {
				killList[i] = aiming(i);
//				System.out.println(killList[i].x + " || " + killList[i].y);
			}

			// ���̱�
			for (Pos ePos : killList) {
				if (ePos.x != -1) {
					// �̹� ���ΰ��̶�� ��������
					if (mapTemp[ePos.x][ePos.y] == 0)
						continue;
					// ���̰� �� �� �� ���̱�
					mapTemp[ePos.x][ePos.y] = 0;
					--enemyCntInGame;
					++kill;
//					System.out.println(enemyCntInGame);
				}
			}
//			System.out.println("=====================�Ʒ��� ���̰� ����");
//			for (int i = 0; i < N+1; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(mapTemp[i][j] + " ");
//				}
//				System.out.println();
//			}
			go();
//			System.out.println(enemyCntInGame);
		}
//		System.out.println("******************���� ��: " + kill);
		return kill;
	}

	static void go() {
		// �� �̵�
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
		// ������ �� �� ó��
		for (int i = 0; i < M; i++) {
			if (mapTemp[N][i] == 1) {
				mapTemp[N][i] = 0;
				--enemyCntInGame;
			}
		}
//		System.out.println("=====================�Ʒ��� �����̰� ����");
//		for (int i = 0; i < N + 1; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(mapTemp[i][j] + " ");
//			}
//			System.out.println();
//		}
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