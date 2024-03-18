package algo0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1904_01타일_0305 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());

		int[] dp = new int[n + 1];
		dp[1] = 1;
		if (n == 1) {
			System.out.println(dp[1]);
		} else {
			dp[2] = 2;
			for (int i = 3; i < n + 1; i++) {
				if (i == 3) {
					dp[3] = (dp[1] + dp[2]) % 15746;
				} else {
					dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
				}
			}
			System.out.println(dp[n]);
		}
	}
}
