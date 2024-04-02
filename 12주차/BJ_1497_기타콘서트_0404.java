package algo0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1497_기타콘서트_0404 {

	static int n;
	static int size;
	static String[] map;
	static int ans, ansCnt;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		size = Integer.parseInt(stringTokenizer.nextToken());
		map = new String[n];
		ans = -1;
		ansCnt = 0;

		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			map[i] = stringTokenizer.nextToken();
			map[i] = stringTokenizer.nextToken();
		}
		for (int i = 1; i <= n; i++) {
			result = new int[i];
			combi(0, 0, i);
		}
		System.out.println(ans);
	}

	static void combi(int index, int start, int len) {
		if (index == len) {
			check();
			return;
		}

		for (int i = start; i < n; i++) {
			result[index] = i;
			combi(index + 1, i + 1, len);
		}
	}

	static void check() {
		int len = result.length;
		boolean[] ansMap = new boolean[size];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < size; j++) {
				if ('Y' == map[result[i]].charAt(j)) {
					ansMap[j] = true;
				}
			}
		}
		int cnt = 0;
		for (boolean flag : ansMap) {
			if (flag) {
				++cnt;
			}
		}
		if (ansCnt < cnt) {
			ansCnt = cnt;
			ans = len;
		}
	}
}
