package algo0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_11559_PuyoPuyo_0425 {

	static char[][] map;
	static int ans;
	static boolean[][] visit;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static ArrayList<Pos> boomList;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			String temp = st.nextToken();
			for (int j = 0; j < 6; j++) {
				map[i][j] = temp.charAt(j);
			}
		}

		ans = 0;
		while (true) {
			if (check()) {
				ans++;
				continue;
			}
			break;
		}
		System.out.println(ans);
	}

	static boolean check() {
		boolean flag = false;
		visit = new boolean[12][6];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (!visit[i][j] && map[i][j] != '.') {
					visit[i][j] = true;
					if (bfs(i, j, map[i][j]) >= 4) {
						flag = true;
						// 터뜨리기
						for (Pos pos : boomList) {
							map[pos.x][pos.y] = '.';
						}
					}
				}
			}
		}
		if (flag) {
			// 중력으로 내리기
			goDown();
		}

		return flag;
	}

	static void goDown() {
		for (int j = 0; j < 6; j++) {
			for (int i = 11; i > 0; i--) {
				if (map[i][j] == '.') {
					int t = i - 1;
					while (t > -1) {
						if (map[t][j] != '.') {
							map[i][j] = map[t][j];
							map[t][j] = '.';
							break;
						}
						--t;
					}
				}
			}
		}
	}

	static int bfs(int x, int y, char c) {
		Queue<Pos> queue = new ArrayDeque<>();
		boomList = new ArrayList<>();
		boomList.add(new Pos(x, y));
		queue.add(new Pos(x, y));
		int count = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Pos pos = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nx = dx[i] + pos.x;
					int ny = dy[i] + pos.y;
					if (roundCheck(nx, ny) && !visit[nx][ny] && map[nx][ny] == c) {
						visit[nx][ny] = true;
						count++;
						boomList.add(new Pos(nx, ny));
						queue.add(new Pos(nx, ny));
					}
				}
			}
		}
		return count;
	}

	static boolean roundCheck(int x, int y) {
		return (-1 < x && x < 12 && -1 < y && y < 6);
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
