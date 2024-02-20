package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_10026_���ϻ���_������_DFS {
	static int n;
	static char[][] map;
	static boolean[][] visit;
	static int common, colorBlindness;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException { // 12376kb �޸�, 88ms �ð�
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());

		// ���� üũ ���Ҷ�� �е� �ֱ�
		map = new char[n + 2][n + 2];

		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String temp = stringTokenizer.nextToken();
			for (int j = 1; j <= n; j++) {
				map[i][j] = temp.charAt(j - 1);
			}
		}

		// ����
		// �湮 �迭 �ʱ�ȭ
		visit = new boolean[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (visit[i][j])
					continue;
				visit[i][j] = true;
				dfs(true, i, j);
				++colorBlindness;
			}
		}

		// �Ϲ���
		// �湮 �迭 �ʱ�ȭ
		visit = new boolean[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (visit[i][j])
					continue;
				visit[i][j] = true;
				dfs(false, i, j);
				++common;
			}
		}
		System.out.println(common + " " + colorBlindness);
	}

	// isColorBlindness: true -> ����
	// isColorBlindness: false -> �Ϲ���
	static void dfs(boolean isColorBlindness, int x, int y) {
		char whatColor = map[x][y];
		char equalColor;
		if (whatColor == 'R') {
			equalColor = 'G';
		} else if (whatColor == 'G') {
			equalColor = 'R';
		} else {
			equalColor = 'B';
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isColorBlindness) {
				if (map[nx][ny] == whatColor || map[nx][ny] == equalColor) {
					if (!visit[nx][ny]) {
						visit[nx][ny] = true;
						dfs(isColorBlindness, nx, ny);
					}
				}
			} else {
				if (map[nx][ny] == whatColor) {
					if (!visit[nx][ny]) {
						visit[nx][ny] = true;
						dfs(isColorBlindness, nx, ny);
					}
				}
			}
		}

	}

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
