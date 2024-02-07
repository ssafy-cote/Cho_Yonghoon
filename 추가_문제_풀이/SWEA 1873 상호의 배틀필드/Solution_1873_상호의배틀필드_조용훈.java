package com.edu.ssafy_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1873_상호의배틀필드_조용훈 {	// 27,272kb 메모리, 151ms 시간
	static int H, W, C;
	static String[][] map;
	static String[] command;
	static Tank tank;
	// 1 상, 2 우, 3 하, 4 좌
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder builder = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new String[H + 2][W + 2];
			for (int i = 0; i < H + 2; i++) {
				for (int j = 0; j < W + 2; j++) {
					map[i][j] = "";
				}
			}
			for (int i = 1; i <= H; i++) {
				st = new StringTokenizer(br.readLine());
				String[] strings = st.nextToken().split("");
				for (int j = 1; j <= W; j++) {
					String mapTemp = strings[j - 1];
					if (tankPos(mapTemp, i, j)) {
						map[i][j] = ".";
						continue;
					}
					map[i][j] = mapTemp;
				}
			}
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			command = temp.split("");

			for (int i = 0; i < C; i++) {
				todo(command[i]);
			}
			finalTankPos();
			builder.append("#").append(t).append(" ");
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= W; j++) {
					builder.append(map[i][j]);
				}
				builder.append("\n");
			}
		}
		System.out.println(builder);
	}

	static void todo(String command) {
		if (command.equals("U")) {
			tank.dir = 1;
			go(tank.dir);
		} else if (command.equals("R")) {
			tank.dir = 2;
			go(tank.dir);
		} else if (command.equals("D")) {
			tank.dir = 3;
			go(tank.dir);
		} else if (command.equals("L")) {
			tank.dir = 4;
			go(tank.dir);
		} else if (command.equals("S")) {
			shot();
		}
	}

	static void shot() {
		int dir = tank.dir;
		int x = tank.x;
		int y = tank.y;
		if (dir == 1) {
			for (int i = x - 1; i > 0; i--) {
				String temp = map[i][y];
				if (temp.equals("#")) {
					return;
				} else if (temp.equals("*")) {
					map[i][y] = ".";
					return;
				}
			}
		} else if (dir == 2) {
			for (int i = y + 1; i <= W; i++) {
				String temp = map[x][i];
				if (temp.equals("#")) {
					return;
				} else if (temp.equals("*")) {
					map[x][i] = ".";
					return;
				}
			}
		} else if (dir == 3) {
			for (int i = x + 1; i <= H; i++) {
				String temp = map[i][y];
				if (temp.equals("#")) {
					return;
				} else if (temp.equals("*")) {
					map[i][y] = ".";
					return;
				}
			}
		} else if (dir == 4) {
			for (int i = y - 1; i > 0; i--) {
				String temp = map[x][i];
				if (temp.equals("#")) {
					return;
				} else if (temp.equals("*")) {
					map[x][i] = ".";
					return;
				}
			}
		}
	}

	static void go(int dir) {
		int nx = tank.x + dx[dir];
		int ny = tank.y + dy[dir];

		if (map[nx][ny].equals(".")) {
			tank.x = nx;
			tank.y = ny;
		}
	}

	static class Tank {
		int x;
		int y;
		int dir; // 1 상, 2 우, 3 하, 4 좌

		public Tank(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	static boolean tankPos(String dir, int x, int y) {
		boolean flag = false;
		if (dir.equals("^")) {
			tank = new Tank(x, y, 1);
			flag = true;
		} else if (dir.equals(">")) {
			tank = new Tank(x, y, 2);
			flag = true;
		} else if (dir.equals("v")) {
			tank = new Tank(x, y, 3);
			flag = true;
		} else if (dir.equals("<")) {
			tank = new Tank(x, y, 4);
			flag = true;
		}
		return flag;
	}

	static void finalTankPos() {
		int finalX = tank.x;
		int finalY = tank.y;
		int finalDir = tank.dir;
		String dir = "";
		if (finalDir == 1) {
			dir = "^";
			map[finalX][finalY] = dir;
		} else if (finalDir == 2) {
			dir = ">";
			map[finalX][finalY] = dir;
		} else if (finalDir == 3) {
			dir = "v";
			map[finalX][finalY] = dir;
		} else if (finalDir == 4) {
			dir = "<";
			map[finalX][finalY] = dir;
		}

	}
}
