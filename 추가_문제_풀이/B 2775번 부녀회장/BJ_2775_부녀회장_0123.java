package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2775_부녀회장_0123 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int t = Integer.parseInt(st.nextToken());

		for (int T = 0; T < t; T++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bf.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int[][] map = new int[k+1][n+1];
			for(int i = 1; i <= n; i++) {
				map[0][i] = i;
			}
			for(int i = 1; i <= k; i++) {
				for(int j = 1; j <= n; j++) {
					if(j==1) {
						map[i][j] = 1;
					}
					map[i][j] = map[i-1][j] + map[i][j-1];
				}
			}
			System.out.println(map[k][n]);
		}
	}

}
