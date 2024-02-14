package algorithm_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2806_N_Queen_조용훈 {
	static int n, ans;
	static int[][] map;

	public static void main(String[] args) throws IOException {	// 22,140kb 메모리, 135ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder builder = new StringBuilder();
		int t = Integer.parseInt(stringTokenizer.nextToken());
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			n = Integer.parseInt(stringTokenizer.nextToken());
			ans = 0;
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				// 놓았다가
				map[0][i] = 1;
				make(1);
				// 놓은거 취소
				map[0][i] = 0;
			}
			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static void make(int cnt) {
		if (cnt == n) {
			++ans;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (pick(cnt, i)) {
				map[cnt][i] = 1;
				make(cnt + 1);
				map[cnt][i] = 0;
			}
		}
	}
	
	// 놓을 수 있는지 판단하는 함수
	static boolean pick(int x, int y) {
		for (int i = 0; i < x; i++) {
			if (map[i][y] == 1)
				return false;
		}
		int nx = x-1;
		int ny = y-1;
		while (nx > -1 && ny > -1) {
			if (map[nx--][ny--] == 1)
				return false;
		}
		nx = x-1;
		ny = y+1;
		while (nx > -1 && ny < n) {
			if (map[nx--][ny++] == 1)
				return false;
		}
		return true;
	}

}
