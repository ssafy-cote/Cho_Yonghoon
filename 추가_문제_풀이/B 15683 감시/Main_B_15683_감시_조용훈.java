package algo0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B_15683_감시_조용훈 {	// 75548kb 메모리, 672ms 시간
	static int n, m;
	static int[][] map;
	static int[] dx = {};
	static int[] dy = {};
	static List<CCTV> cctv;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		cctv = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < m; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (0 < temp && temp < 6) {
					cctv.add(new CCTV(i, j, temp, 1));
				}
				map[i][j] = temp;
			}
		}
		// 모든 경우의 수 전부 해보기
		dfs(0);

		System.out.println(ans);

	}

	static void dfs(int index) {
		if (index == cctv.size()) {
			int result = check();
			if (ans > result)
				ans = result;
			return;
		}
		for (int i = 1; i <= 4; i++) {
			cctv.get(index).dir = i;
			int nindex = index + 1;
			dfs(nindex);
		}
	}
	static int check() {
		int result = 0;
		int[][] map2 = new int[n][m];
		for (int i = 0; i < n; i++) {
			System.arraycopy(map[i], 0, map2[i], 0, map2[i].length);
		}

		for (int i = 0; i < cctv.size(); i++) {
			if (cctv.get(i).num == 1) {
				if (cctv.get(i).dir == 1) {
					seeUp(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 2) {
					seeDown(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 3) {
					seeLeft(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 4) {
					seeRight(cctv.get(i), map2);
				}
			} else if (cctv.get(i).num == 2) {
				if (cctv.get(i).dir == 1) {
					seeUp(cctv.get(i), map2);
					seeDown(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 2) {
					seeUp(cctv.get(i), map2);
					seeDown(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 3) {
					seeRight(cctv.get(i), map2);
					seeLeft(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 4) {
					seeRight(cctv.get(i), map2);
					seeLeft(cctv.get(i), map2);
				}
			} else if (cctv.get(i).num == 3) {
				if (cctv.get(i).dir == 1) {
					seeUp(cctv.get(i), map2);
					seeRight(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 2) {
					seeRight(cctv.get(i), map2);
					seeDown(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 3) {
					seeDown(cctv.get(i), map2);
					seeLeft(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 4) {
					seeUp(cctv.get(i), map2);
					seeLeft(cctv.get(i), map2);
				}
			} else if (cctv.get(i).num == 4) {
				if (cctv.get(i).dir == 1) {
					seeUp(cctv.get(i), map2);
					seeLeft(cctv.get(i), map2);
					seeRight(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 2) {
					seeUp(cctv.get(i), map2);
					seeDown(cctv.get(i), map2);
					seeRight(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 3) {
					seeDown(cctv.get(i), map2);
					seeRight(cctv.get(i), map2);
					seeLeft(cctv.get(i), map2);
				} else if (cctv.get(i).dir == 4) {
					seeUp(cctv.get(i), map2);
					seeLeft(cctv.get(i), map2);
					seeDown(cctv.get(i), map2);
				}
			} else if (cctv.get(i).num == 5) {
				seeUp(cctv.get(i), map2);
				seeLeft(cctv.get(i), map2);
				seeDown(cctv.get(i), map2);
				seeRight(cctv.get(i), map2);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map2[i][j] == 0) {
					result++;
				}
			}
		}
		return result;
	}

	static void seeUp(CCTV cctv, int[][] map2) {
		for (int i = cctv.x - 1; i > -1; i--) {
			if (map2[i][cctv.y] == 6)
				break;
			map2[i][cctv.y] = -1;
		}
	}

	static void seeDown(CCTV cctv, int[][] map2) {
		for (int i = cctv.x + 1; i < n; i++) {
			if (map2[i][cctv.y] == 6)
				break;
			map2[i][cctv.y] = -1;
		}
	}

	static void seeLeft(CCTV cctv, int[][] map2) {
		for (int i = cctv.y - 1; i > -1; i--) {
			if (map2[cctv.x][i] == 6)
				break;
			map2[cctv.x][i] = -1;
		}
	}

	static void seeRight(CCTV cctv, int[][] map2) {
		for (int i = cctv.y + 1; i < m; i++) {
			if (map2[cctv.x][i] == 6)
				break;
			map2[cctv.x][i] = -1;
		}
	}

	static class CCTV {
		int x;
		int y;
		int num;
		int dir;

		public CCTV(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
}
