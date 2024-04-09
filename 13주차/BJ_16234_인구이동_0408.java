package algo0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234_인구이동_0408 {

	static int n, l, r;
	static int ans;

	static int[][] map;

	static boolean flag;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static ArrayList<Pos>[] list;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		l = Integer.parseInt(stringTokenizer.nextToken());
		r = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		ans = 0;
		flag = true;
		while (flag) {
			flag = false;
			list = new ArrayList[n * n];
			for (int i = 0; i < n * n; i++) {
				list[i] = new ArrayList<>();
			}
			
			open();

//			for (int i = 0; i < n * n; i++) {
//				if (list[i].size() > 0) {
//					System.out.println(list[i]);
//				}
//			}

			// 인구 이동시키기
			visit = new boolean[n][n];
			for (int i = 0; i < n * n; i++) {
				int x = i / n;
				int y = i % n;
				if (!visit[x][y]) {
					move(i);
				}
			}
			
//			for(int i = 0; i < n; i++) {
//				for(int j = 0; j < n; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("+++++++++++++++++++++++++++++++++");
			
			if (flag) {
				++ans;
			}
		}
		System.out.println(ans);
	}

	static void move(int num) {
		Queue<Pos> queue = new ArrayDeque<>();
		Queue<Pos> sumQ = new ArrayDeque<>();
		int x = num / n;
		int y = num % n;
		queue.add(new Pos(x, y));
		visit[x][y] = true;
		int sum = 0;
		int cnt = 0;
		while (!queue.isEmpty()) {

			Pos pos = queue.poll();
			sumQ.add(pos);
			++cnt;
			sum += map[pos.x][pos.y];
			int index = (pos.x * n) + (pos.y);
			for (Pos np : list[index]) {
				if (!visit[np.x][np.y]) {
					visit[np.x][np.y] = true;
					queue.add(np);
				}
			}
		}

		sum /= cnt;

		while (!sumQ.isEmpty()) {
			Pos pos = sumQ.poll();
			map[pos.x][pos.y] = sum;
		}
	}

	static void open() {
		for (int i = 0; i < n * n; i++) {
			int x = i / n;
			int y = i % n;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (check(nx, ny)) {
					int temp = Math.abs(map[x][y] - map[nx][ny]);
					if (l <= temp && temp <= r) {
						list[i].add(new Pos(nx, ny));
						flag = true;
					}
				}
			}
		}
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
	}

	static boolean check(int x, int y) {
		return (-1 < x && x < n && -1 < y && y < n);
	}
}
