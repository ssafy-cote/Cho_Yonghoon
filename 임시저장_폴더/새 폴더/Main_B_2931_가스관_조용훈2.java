package algo0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2931_가스관_조용훈2 {

	static String[][] map;

	static int n, m;

	static int startX;
	static int startY;

	static int endX;
	static int endY;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };
	
	static Pos ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new String[n + 2][m + 2];

		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String[] ttttemp = stringTokenizer.nextToken().split("");
			for (int j = 1; j <= m; j++) {
				map[i][j] = ttttemp[j - 1];
				if (map[i][j].equals("M")) {
					startX = i;
					startY = j;
				}
				if (map[i][j].equals("Z")) {
					endX = i;
					endY = j;
				}
			}
		}

//		for(int i = 0; i < n+2; i++) {
//			for(int j = 0; j < m+2; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		for (int i = 0; i < 4; i++) {
			int nx = startX + dx[i];
			int ny = startY + dy[i];
			if (check(nx, ny) && map[nx][ny].equals("."))
				continue;
			else if (check(nx, ny) && !map[nx][ny].equals(".")) {
				go(startX, startY, nx, ny, 0);
				break;
			}
		}

	}

	static boolean check(int x, int y) {
		return (0 < x && x <= n && 0 < y && y <= m);
	}

	static void go(int x, int y, int nx, int ny, int flag) {
		System.out.println(x+ " " + y + " -> " + nx + " " + ny + " " + map[nx][ny]);

		if (map[nx][ny].equals("Z")) {
			System.out.println(ans.x + " " + ans.y + " " + map[ans.x][ans.y]);
			System.exit(0);
		}
		
		Pos pos = check(x, y, nx, ny, map[nx][ny], flag);
		if (pos == null) {
			return;
		}
		go(nx, ny, pos.x, pos.y, flag);
	}

	static Pos check(int x, int y, int nx, int ny, String str, int flag) {
		Pos pos = null;

		if (str.equals("|")) {
			if (nx > x) {
				pos = new Pos(nx + 1, ny);
			} else {
				pos = new Pos(nx - 1, ny);
			}
		} else if (str.equals("-")) {
			if (ny > y) {
				pos = new Pos(nx, ny + 1);
			} else {
				pos = new Pos(nx, ny - 1);
			}
		} else if (str.equals("+")) {
			if (ny == y) {
				pos = new Pos(nx + 1, ny);
			} else {
				pos = new Pos(nx, ny + 1);
			}
		} else if (str.equals("1")) {
			if (ny == y) {
				pos = new Pos(nx, ny + 1);
			} else {
				pos = new Pos(nx + 1, ny);
			}
		} else if (str.equals("2")) {
			if (ny == y) {
				pos = new Pos(nx, ny + 1);
			} else {
				pos = new Pos(nx - 1, ny);
			}
		} else if (str.equals("3")) {
			if (ny == y) {
				pos = new Pos(nx, ny - 1);
			} else {
				pos = new Pos(nx - 1, ny);
			}
		} else if (str.equals("4")) {
			if (ny == y) {
				pos = new Pos(nx, ny - 1);
			} else {
				pos = new Pos(nx + 1, ny);
			}
		} else if ((flag != 1) && str.equals(".")) {
			ans = new Pos(nx, ny);
			map[nx][ny] = "|";
			go(x, y, nx, ny, 1);
			map[nx][ny] = "-";
			go(x, y, nx, ny, 1);
			map[nx][ny] = "+";
			go(x, y, nx, ny, 1);
			map[nx][ny] = "1";
			go(x, y, nx, ny, 1);
			map[nx][ny] = "2";
			go(x, y, nx, ny, 1);
			map[nx][ny] = "3";
			go(x, y, nx, ny, 1);
			map[nx][ny] = "4";
			go(x, y, nx, ny, 1);
		}
		return pos;
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
