package algo0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_2660_회장뽑기_0319 {
	static ArrayList<Integer>[] map;
	static int[] ans;
	static int n;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());

		map = new ArrayList[n + 1];
		ans = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			map[i] = new ArrayList<>();
		}

		while (true) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int a = Integer.parseInt(stringTokenizer.nextToken());
			int b = Integer.parseInt(stringTokenizer.nextToken());
			if (a == -1 && b == -1) {
				break;
			}
			map[a].add(b);
			map[b].add(a);
		}

		min = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			bfs(i);
		}
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (ans[i] == min) {
				++count;
				builder.append(i + " ");
			}
		}
		System.out.println(min + " " + count);
		System.out.println(builder);
	}

	static void bfs(int num) {
		boolean[] visit = new boolean[n + 1];
		Deque<Integer> deque = new ArrayDeque<>();
		deque.add(num);
		visit[num] = true;
		int cnt = -1;
		while (!deque.isEmpty()) {
			int size = deque.size();
			while (size-- > 0) {
				int now = deque.poll();
				for (int next : map[now]) {
					if (!visit[next]) {
						visit[next] = true;
						deque.add(next);
					}
				}
			}
			++cnt;
		}
		min = (cnt < min) ? cnt : min;
		ans[num] = cnt;
	}
}
