package algo0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_10971_외판원순회2_조용훈 {
	static int N, ans;
	static int[][] map;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {	// 12140kb 메모리, 332ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		ans = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			visit = new boolean[N + 1];
			visit[i] = true;
			dfs(0, 0, i, i);
		}
		System.out.println(ans);
	}

	static void dfs(int sum, int cnt, int start, int home) {
//		if (ans < sum) {
//			return;
//		}
		if (cnt == N - 1) {
			if (map[start][home] != 0) {
				ans = Math.min(ans, sum + map[start][home]);
			}
//			System.out.println(ans);
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (map[start][i] != 0 && !visit[i]) {
				visit[i] = true;
//				System.out.println(Arrays.toString(visit));
				dfs(sum + map[start][i], cnt + 1, i, home);
				visit[i] = false;
			}
		}
	}
}
