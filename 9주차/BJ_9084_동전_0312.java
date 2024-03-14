package algo0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9084_동전_0313 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int n = Integer.parseInt(stringTokenizer.nextToken());
			int[] map = new int[n];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int i = 0; i < n; i++) {
				map[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int k = Integer.parseInt(stringTokenizer.nextToken());

			int[] dp = new int[k + 1];
			dp[0] = 1;
			for (int i : map) {
				for (int j = i; j <= k; j++) {
					dp[j] += dp[j - i];
				}
			}
			System.out.println(dp[k]);
		}
		
		int i = 10;
		String string = Long.toBinaryString(i);
		System.out.println(string);
		string = "0" + string;
		System.out.println(string);

	}
}
