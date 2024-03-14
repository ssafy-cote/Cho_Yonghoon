package algo0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2805_나무자르기_0314 {
	static int m;
	static int[] map;
	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n];
		int start = Integer.MAX_VALUE;
		int end = Integer.MIN_VALUE;
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for (int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(stringTokenizer.nextToken());
			end = (end < map[i]) ? map[i] : end;
			start = (start > map[i]) ? map[i] : start;
		}
		long mid = end / 2;
		// 2진 탐색 시작
		binarySearch(0, mid, end + 1);
		System.out.println(ans);
	}

	static void binarySearch(long start, long mid, long end) {

		if (mid >= end || mid <= start) {
			ans = mid;
			return;
		}
		long sum = 0;
		for (int i = 0; i < map.length; i++) {
			long temp = (map[i] - mid < 0) ? 0 : (map[i] - mid);
			sum += temp;
		}
		if (sum >= m) {
			binarySearch(mid, (mid + end) / 2, end);
		} else {
			binarySearch(start, (start + mid) / 2, mid);
		}
	}
}
