package algo0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14267_회사문화1_0416 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());

		int[] ans = new int[n + 1];

		int[] map = new int[n + 1];

		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(stringTokenizer.nextToken());
			if (num == -1) {
				continue;
			}
			map[i] = num;
		}
		
		int[][] good = new int[n+1][1];
		
		for (int i = 0; i < m; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int num = Integer.parseInt(stringTokenizer.nextToken());
			int w = Integer.parseInt(stringTokenizer.nextToken());
			good[num][0] += w;
		}
		
		for(int i = 2; i <= n; i++) {
			ans[i] = ans[map[i]] + good[i][0]; 
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			builder.append(ans[i] + " ");
		}
		System.out.println(builder);
	}
}
