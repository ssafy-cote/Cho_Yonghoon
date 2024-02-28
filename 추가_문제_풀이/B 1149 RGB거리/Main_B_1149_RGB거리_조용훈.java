package algo0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1149_RGB거리_조용훈 { // 12104kb 메모리, 92ms 시간

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());

		// 0: R, 1: G, 2: B
		int[][] map = new int[n][3];

		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			map[i][0] = Integer.parseInt(stringTokenizer.nextToken());
			map[i][1] = Integer.parseInt(stringTokenizer.nextToken());
			map[i][2] = Integer.parseInt(stringTokenizer.nextToken());
		}

		// 각 색을 선택했을 경우의 최소 값
		int[][] dp = new int[n][3];

		for (int i = 0; i < 3; i++) {
			dp[0][i] = map[0][i];
		}

		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.min(map[i][0] + dp[i - 1][1], map[i][0] + dp[i - 1][2]);
			dp[i][1] = Math.min(map[i][1] + dp[i - 1][0], map[i][1] + dp[i - 1][2]);
			dp[i][2] = Math.min(map[i][2] + dp[i - 1][0], map[i][2] + dp[i - 1][1]);
		}
		System.out.println(Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]));
	}
}
