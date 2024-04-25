package algo0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_1938_통나무옮기기 {
	static int n;
	static char[][] map;
	static boolean[][][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Pos[] endPos;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());

		int tempB = 0;
		int tempE = 0;
		Pos startPos = null;
		Pos tempPos = null;
		endPos = new Pos[3];
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String string = stringTokenizer.nextToken();
			for (int j = 0; j < n; j++) {
				map[i][j] = string.charAt(j);
				if (map[i][j] == 'B') {
					if (tempB == 0) {
						tempPos = new Pos(i, j, -1);
					}
					if (tempB == 1) {
						startPos = new Pos(i, j, -1);
					}
					tempB++;
				}
				if (map[i][j] == 'E') {
					endPos[tempE++] = new Pos(i, j, -1);
				}
			}
		}

		if (tempPos.x == startPos.x) {
			startPos.dir = 1;
		} else {
			startPos.dir = 0;
		}
//		System.out.println(startPos + " " + endPos[0] + ", " + endPos[1] + ", " + endPos[2]);
		visit = new boolean[n][n][2];
		System.out.println(bfs(startPos));

	}

	static int bfs(Pos pos) {
		Deque<Pos> queue = new ArrayDeque<>();
		queue.add(pos);
		visit[pos.x][pos.y][pos.dir] = true;
		int count = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Pos nowPos = queue.poll();
//				System.out.println(nowPos);
				if (nowPos.dir == 0 && ansCheck0(nowPos)) {
					return count;
				} else if (nowPos.dir == 1 && ansCheck1(nowPos)) {
					return count;
				}
				for (int i = 0; i < 4; i++) {
					int nx = dx[i] + nowPos.x;
					int ny = dy[i] + nowPos.y;
					if (check(nx, ny, nowPos.dir) && !visit[nx][ny][nowPos.dir]) {
						visit[nx][ny][nowPos.dir] = true;
						queue.add(new Pos(nx, ny, nowPos.dir));
					}
				}

				// 가로 -> 세로
				if (nowPos.dir == 0) {
					if (checkSpin(nowPos) && !visit[nowPos.x][nowPos.y][1]) {
						visit[nowPos.x][nowPos.y][1] = true;
						queue.add(new Pos(nowPos.x, nowPos.y, 1));
					}
				} else {
					if (checkSpin(nowPos) && !visit[nowPos.x][nowPos.y][0]) {
						visit[nowPos.x][nowPos.y][0] = true;
						queue.add(new Pos(nowPos.x, nowPos.y, 0));
					}
				}
			}
			count++;
//			System.out.println(count);
		}
		return 0;
	}

	static boolean checkSpin(Pos pos) {
		for (int i = pos.x - 1; i <= pos.x + 1; i++) {
			for (int j = pos.y - 1; j <= pos.y + 1; j++) {
				if (!check2(i, j) || map[i][j] == '1') {
					return false;
				}
			}
		}
		return true;
	}

	static boolean check(int x, int y, int dir) {
		if (dir == 0) {
			return (check2(x - 1, y) && check2(x, y) && check2(x + 1, y));
		} else {
			return (check2(x, y - 1) && check2(x, y) && check2(x, y + 1));
		}
	}

	static boolean check2(int x, int y) {
		return (-1 < x && x < n && -1 < y && y < n) && (map[x][y] != '1');
	}

	static boolean ansCheck0(Pos pos) {
		if (pos.x - 1 == endPos[0].x && pos.y == endPos[0].y && pos.x == endPos[1].x && pos.y == endPos[1].y
				&& pos.x + 1 == endPos[2].x && pos.y == endPos[2].y) {
			return true;
		}
		return false;
	}

	static boolean ansCheck1(Pos pos) {
		if (pos.x == endPos[0].x && pos.y - 1 == endPos[0].y && pos.x == endPos[1].x && pos.y == endPos[1].y
				&& pos.x == endPos[2].x && pos.y + 1 == endPos[2].y) {
			return true;
		}
		return false;
	}

	static class Pos {
		// dir : 0 세로, dir : 1 가로
		int x, y, dir;

		private Pos(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", dir=" + dir + "]";
		}

	}

}