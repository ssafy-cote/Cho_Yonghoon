package algo0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1463_1로만들기_조용훈 {
	public static void main(String[] args) throws IOException {	// 15780kb 메모리, 104ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		
		// 최대 크기로
		int[] dp = new int[1000001];
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		for (int i = 4; i <= n; i++) {
			// 공배수
			if (i % 6 == 0) {
				dp[i] = Math.min(dp[i / 2] + 1, dp[i / 3] + 1);
				dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
			} else if (i % 3 == 0) {
				dp[i] = Math.min(dp[i / 3] + 1, dp[i - 1] + 1);
			} else if (i % 2 == 0) {
				dp[i] = Math.min(dp[i / 2] + 1, dp[i - 1] + 1);
			} else {
				dp[i] = dp[i - 1] + 1;
			}
		}
		System.out.println(dp[n]);
	}
}
