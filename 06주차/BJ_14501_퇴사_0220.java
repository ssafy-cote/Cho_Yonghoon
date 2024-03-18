package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14501_Επ»η_0220 {
	static int n;
	static Info[] map;
	static int ans;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		ans = 0;
		n = Integer.parseInt(stringTokenizer.nextToken());
		map = new Info[n];
		visit = new boolean[n];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int t = Integer.parseInt(stringTokenizer.nextToken());
			int m = Integer.parseInt(stringTokenizer.nextToken());
			map[i] = new Info(t, m);
		}
		dfs(0, 0);
		System.out.println(ans);
	}

	static void dfs(int sum, int start) {
		if (start >= n) {
			if (sum > ans) {
				ans = sum;
			}
			return;
		}
		for (int i = start; i < n; i++) {
			if (!visit[i]) {
				if (i + map[i].time <= n) {
					visit[i] = true;
					dfs(sum + map[i].money, i + map[i].time);
					visit[i] = false;
				} else {
					dfs(sum, i + map[i].time);
				}
			}
		}
	}

	static class Info {
		int time;
		int money;

		public Info(int time, int money) {
			this.time = time;
			this.money = money;
		}
	}
}
