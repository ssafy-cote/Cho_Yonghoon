package algo0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_9205_맥주마시면서걸어가기_조용훈 {

	static String ans;
	static Pos home;
	static Pos festival;
	static ArrayList<Integer>[] map;
	static Pos[] list;
	static int n;

	public static void main(String[] args) throws IOException { // 13776kb 메모리, 128ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());

		StringBuilder builder = new StringBuilder();

		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			n = Integer.parseInt(stringTokenizer.nextToken());
			map = new ArrayList[n + 2];
			list = new Pos[n + 2];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			list[0] = new Pos(x, y, 0);

			for (int i = 0; i < n + 2; i++) {
				map[i] = new ArrayList<>();
			}
			for (int i = 1; i < n + 1; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				x = Integer.parseInt(stringTokenizer.nextToken());
				y = Integer.parseInt(stringTokenizer.nextToken());
				list[i] = new Pos(x, y, i);
			}
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			x = Integer.parseInt(stringTokenizer.nextToken());
			y = Integer.parseInt(stringTokenizer.nextToken());
			list[n + 1] = new Pos(x, y, n + 1);

			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < n + 2; j++) {
					if (j == i) {
						continue;
					}
					int dis = Math.abs(list[i].x - list[j].x) + Math.abs(list[i].y - list[j].y);
					if (dis <= 1000) {
						map[i].add(list[j].index);
						map[j].add(list[i].index);
					}
				}
			}

			bfs();

			builder.append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(0);
		boolean[] visit = new boolean[n + 2];
		visit[0] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int i : map[now]) {
				if (!visit[i]) {
					visit[i] = true;
					queue.add(i);
				}
			}
		}
		if (visit[n + 1]) {
			ans = "happy";
		} else {
			ans = "sad";
		}
	}

	static class Pos {
		int x;
		int y;
		int index;

		public Pos(int x, int y, int index) {
			this.x = x;
			this.y = y;
			this.index = index;
		}
	}
}
