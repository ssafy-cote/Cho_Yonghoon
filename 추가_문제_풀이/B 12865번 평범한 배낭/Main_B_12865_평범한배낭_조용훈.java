package algo0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_12865_평범한배낭_조용훈 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		int k = Integer.parseInt(stringTokenizer.nextToken());
		int[][] map = new int[n+1][2];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			map[i][0] = Integer.parseInt(stringTokenizer.nextToken());
			map[i][1] = Integer.parseInt(stringTokenizer.nextToken());
		}

		int[][] dp = new int[n + 1][k + 1];

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < k + 1; j++) {
				if (check(j - map[i][0])) {
					dp[i][j] = (dp[i-1][j] > (dp[i-1][j-map[i][0]] + map[i][1])) ? dp[i-1][j] : (dp[i-1][j-map[i][0]] + map[i][1]);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		System.out.println(dp[n][k]);
	}

	static boolean check(int n) {
		return -1 < n;
	}
}
