package algo0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2117_홈방범서비스_조용훈 {	// 90,656kb 메모리, 413ms 시간
	static int ans, N, M;
	static int[][] map;
	static int limtK;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Queue<Pos> queue;
	static ArrayList<Pos> homeList;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder stringBuilder = new StringBuilder();
		int t = Integer.parseInt(stringTokenizer.nextToken());

		for (int T = 1; T <= t; T++) {
			ans = 0;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[N][N];
			limtK = 1;
			homeList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					if (map[i][j] == 1) {
						homeList.add(new Pos(i, j));
					}
				}
			}

			for (int i = 1; i < 40; i++) {
				if (((i * i) + ((i - 1) * (i - 1)) >= (homeList.size() * M))) {
					limtK = i - 1;
					break;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}

			stringBuilder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(stringBuilder);
	}

	static void bfs(int x, int y) {
		queue = new ArrayDeque<>();
		queue.add(new Pos(x, y));
		boolean[][] visit = new boolean[N][N];
		visit[x][y] = true;
		int limit = 1;
		while (!queue.isEmpty() && limit <= limtK) {

			// 손익 계산 함수
			total(visit, limit++);
			
			int cnt = queue.size();
			while (cnt-- > 0) {
				Pos pos = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = pos.x + dx[i];
					int ny = pos.y + dy[i];
					if (check(nx, ny) && !visit[nx][ny]) {
						visit[nx][ny] = true;
						queue.add(new Pos(nx, ny));
					}
				}
			}
		}
	}

	static void total(boolean[][] visit, int limit) {
		int cnt = 0;
		for (Pos pos : homeList) {
			if (visit[pos.x][pos.y]) {
				++cnt;
			}
		}

		// 손해 체크
		if ((cnt * M) >= (limit * limit) + ((limit - 1) * (limit - 1))) {
			ans = Math.max(cnt, ans);
		}
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < N && -1 < y && y < N);
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
