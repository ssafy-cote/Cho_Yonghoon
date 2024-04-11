package algo0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10942_팰린드롬_0409 {

	static int n, m;

	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n + 1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			map[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		m = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < m; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			if (check(start, end)) {
				builder.append(1).append("\n");
			} else {
				builder.append(0).append("\n");
			}
		}
		System.out.println(builder);
	}

	static boolean check(int start, int end) {

		while (start <= end) {
			if (map[start] != map[end]) {
				return false;
			}
			++start;
			--end;
		}
		return true;
	}
}
