package algo0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14719_빗물_0328 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] list = new int[m];
		st = new StringTokenizer(bf.readLine(), " ");
		for (int i = 0; i < m; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}

		boolean[][] map = new boolean[n][m];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < list[i]; j++) {
				map[j][i] = true;
			}
		}

		// 왼쪽에서 오른쪽으로
		A: for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j]) {
					continue A;
				}
				map[i][j] = true;
			}
		}
		// 오른쪽에서 왼쪽으로
		A: for (int i = 0; i < n; i++) {
			for (int j = m - 1; j > -1; j--) {
				if (map[i][j]) {
					continue A;
				}
				map[i][j] = true;
			}
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!map[i][j]) {
					++ans;
				}
			}
		}
		System.out.println(ans);
	}
}