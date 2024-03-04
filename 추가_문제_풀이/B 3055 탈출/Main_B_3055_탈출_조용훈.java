package algo0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_3055_탈출_조용훈 {

	static int N, M, time;
	static char[][] map;
	static int landCnt;
	static Queue<Pos> waterList;
	static Queue<Pos> gosumMove;
	static boolean[][] waterVisit;
	static boolean[][] visit;
	static Pos home;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException { // 12176kb 메모리, 96ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[N][M];
		waterList = new ArrayDeque<>();
		waterVisit = new boolean[N][M];
		visit = new boolean[N][M];
		gosumMove = new ArrayDeque<>();
		landCnt = 0;
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String string = stringTokenizer.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = string.charAt(j);
				if (map[i][j] == '*') {
					waterList.add(new Pos(i, j));
					waterVisit[i][j] = true;
				}
				if (map[i][j] == '.') {
					++landCnt;
				}
				if (map[i][j] == 'S') {
					gosumMove.add(new Pos(i, j));
					visit[i][j] = true;
					map[i][j] = '.';
					++landCnt;
				}
				if (map[i][j] == 'D') {
					home = new Pos(i, j);
				}
			}
		}
		time = 0;
		while (landCnt > 0) {
			++time;
			waterFool();
			goGosum();
		}
		System.out.println("KAKTUS");
	}

	static void goGosum() {
		int size = gosumMove.size();
		if (gosumMove.size() == 0) {
			System.out.println("KAKTUS");
			System.exit(0);
		}
		while (size-- > 0) {
			Pos pos = gosumMove.poll();
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];

				if (check(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == 'D') && !visit[nx][ny]) {
					if (map[nx][ny] == 'D') {
						System.out.println(time);
						System.exit(0);
					}
					visit[nx][ny] = true;
					gosumMove.add(new Pos(nx, ny));
				}
			}
		}

	}

	static void waterFool() {
		int size = waterList.size();
		while (size-- > 0) {
			Pos pos = waterList.poll();
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];

				if (check(nx, ny) && map[nx][ny] == '.' && !waterVisit[nx][ny]) {
					map[nx][ny] = '*';
					waterVisit[nx][ny] = true;
					waterList.add(new Pos(nx, ny));
					--landCnt;
				}
			}
		}

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
