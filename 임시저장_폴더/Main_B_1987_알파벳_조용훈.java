package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1987_알파벳_조용훈 {
	static char[][] map;
	static int ans;
	static boolean[][] visit;
	static int n, m;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[] alpabetList = new boolean[26];

	public static void main(String[] args) throws IOException { // 12568kb 메모리, 1072ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String temp = stringTokenizer.nextToken();
			for (int j = 0; j < m; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		
		visit = new boolean[n][m];
		// 처음 시작 부분 체크
		visit[0][0] = true;
		alpabetList[map[0][0] - 65] = true;
		dfs(0, 0, 1);
		System.out.println(ans);
	}

	// dfs 함수
	static void dfs(int x, int y, int cnt) {
		if (cnt >= ans) {
			ans = cnt;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx > n - 1 || ny < 0 || ny > m - 1)
				continue;
			int number = map[nx][ny] - 65;
			if (!alpabetList[number]) {
				if (!visit[nx][ny]) {
					visit[nx][ny] = true;
					alpabetList[number] = true;
					dfs(nx, ny, cnt + 1);
					visit[nx][ny] = false;
					alpabetList[number] = false;
				}
			}
		}
	}
}
