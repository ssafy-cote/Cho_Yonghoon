package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_14502_연구소_조용훈2 {
	static int n, m;
	static int[][] map, tempMap;
	static Pos[] result = new Pos[3];
	static ArrayList<Pos> VirusList;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[][] visit;
	static int ans;

	public static void main(String[] args) throws IOException { // 98460kb 메모리, 424ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		ans = 0;
		map = new int[n][m];

		VirusList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < m; j++) {
				int temp = Integer.parseInt(stringTokenizer.nextToken());
				if (temp == 2) {
					VirusList.add(new Pos(i, j));
				}
				map[i][j] = temp;
			}
		}
		result = new Pos[3];
		combi(0, 0);
		System.out.println(ans);
	}

	static void combi(int index, int start) {

		if (index == 3) {

			for (Pos pos : result) {
				System.out.print("x: " + pos.x + " y: " + pos.y + " | ");
			}
			System.out.println();
			System.out.println("=============");

			tempMap = new int[n][m];
			visit = new boolean[n][m];

			// 배열 복사
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tempMap[i][j] = map[i][j];
				}
			}

			// 벽 세우기
			for (Pos pos : result) {
				tempMap[pos.x][pos.y] = 1;
			}

			// 바이러스 퍼트리기
			for (Pos pos : VirusList) {
				bfs(pos.x, pos.y);
			}

			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (tempMap[i][j] == 0) {
						++cnt;
					}
				}
			}
			ans = (ans < cnt) ? cnt : ans;
			return;
		}

		for (int i = start; i < n * m; i++) {
			int x = i / m;
			int y = i % m;
			result[index] = new Pos(x, y);
			combi(index + 1, i + 1);
		}
	}

	static boolean check(int x, int y) {
		return (x > -1 && x < n && y < m && y > -1);
	}

	static void bfs(int x, int y) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(x, y));
		visit[x][y] = true;
		while (!queue.isEmpty()) {

			Pos pos = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				if (check(nx, ny) && tempMap[nx][ny] == 0) {
					tempMap[nx][ny] = 2;
					visit[nx][ny] = true;
					queue.add(new Pos(nx, ny));
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
