package algo0228;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution_1767_프로세서연결하기_조용훈 {

	static int maxCoreCnt, minLen, N;
	static int[][] map;
	static ArrayList<Core> coreList;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String args[]) throws Exception {	// 24,404kb 메모리, 844ms 시간
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		StringBuilder stringBuilder = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(bf.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			coreList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					map[i][j] = temp;
					if (temp == 1) {
						if (i == 0 || j == 0 || i == N - 1 || j == N - 1)
							continue;
						coreList.add(new Core(i, j));
					}
				}
			}

			minLen = Integer.MAX_VALUE;
			maxCoreCnt = Integer.MIN_VALUE;

			dfs(0, 0, 0);
			stringBuilder.append("#").append(t).append(" ").append(minLen).append("\n");
		}
		System.out.println(stringBuilder);
	}

	public static void dfs(int index, int coreCnt, int len) {

		if (index == coreList.size()) {
			if (coreCnt > maxCoreCnt) {
				maxCoreCnt = coreCnt;
				minLen = len;
			} else if (coreCnt == maxCoreCnt) {
				minLen = (minLen < len) ? minLen : len;
			}
			return;
		}

		int x = coreList.get(index).x;
		int y = coreList.get(index).y;

		for (int num = 0; num < 4; num++) {

			int tempX = x;
			int tempY = y;
			int cnt = 0;

			// 전선 닿을 수 있는지 체크와 동시에 전선 길이 확인
			while (true) {
				tempX += dx[num];
				tempY += dy[num];

				if (tempX < 0 || tempY < 0 || tempY >= N || tempX >= N)
					break;
				if (map[tempX][tempY] == 1) {
					cnt = 0;
					break;
				}
				cnt++;
			}

			int tX = x;
			int tY = y;

			// 전선 그리기
			for (int p = 0; p < cnt; p++) {
				tX += dx[num];
				tY += dy[num];
				map[tX][tY] = 1;
			}

			// 전선이 없을 경우
			if (cnt == 0) {
				dfs(index + 1, coreCnt, len);
			} else {
				// 전신이 있을경우
				dfs(index + 1, coreCnt + 1, len + cnt);

				tX = x;
				tY = y;

				// 전선 그린거 다시 지우기
				for (int p = 0; p < cnt; p++) {
					tX += dx[num];
					tY += dy[num];
					map[tX][tY] = 0;
				}
			}
		}
	}

	static class Core {
		int x, y;

		Core(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}