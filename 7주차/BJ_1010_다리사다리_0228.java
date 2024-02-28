package algo0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ_1010_다리사다리_0228 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int T = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int a = 1; a <= T; a++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int n = Integer.parseInt(stringTokenizer.nextToken());
			int m = Integer.parseInt(stringTokenizer.nextToken());

			int k = m - n;
			BigInteger ans = new BigInteger("1");
			for (int i = k + 1; i <= m; i++) {
				BigInteger t = new BigInteger(Integer.toString(i));
				ans = ans.multiply(t);
			}

			BigInteger ans2 = new BigInteger("1");
			for (int i = 1; i <= n; i++) {
				BigInteger t = new BigInteger(Integer.toString(i));
				ans2 = ans2.multiply(t);
			}

			BigInteger result = new BigInteger("1");

			result = ans.divide(ans2);

			builder.append(result).append("\n");
		}
		System.out.println(builder);
	}
}
