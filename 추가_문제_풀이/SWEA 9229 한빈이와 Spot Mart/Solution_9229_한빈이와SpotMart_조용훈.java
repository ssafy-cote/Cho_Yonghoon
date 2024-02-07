package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_한빈이와SpotMart_조용훈 {		// 25,132kb 메모리, 172ms 시간
	static int ans;		// 최대 무게 출력 값
	static int n, m;	// 과자수, 제한 무게
	static int[] map, result;	// 과자 배열, 들고가는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		int T = Integer.parseInt(stringTokenizer.nextToken());
		for (int t = 1; t <= T; t++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			n = Integer.parseInt(stringTokenizer.nextToken());
			m = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[n];
			result = new int[2];
			ans = -1;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int i = 0; i < n; i++) {
				map[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			com(0, 0);
			System.out.println("#" + t + " " + ans);
		}
	}

	static void com(int index, int start) {
		// 2개 다들었을 때 최대값 확인
		if (index == 2) {
			int sum = result[0] + result[1];
			if (sum <= m) {
				ans = Math.max(sum, ans);
			}
			return;
		}
		// 조합 2개들고갈때 : 재귀
		for (int i = start; i < n; i++) {
			result[index] = map[i];
			com(index + 1, i + 1);
		}
	}
}
