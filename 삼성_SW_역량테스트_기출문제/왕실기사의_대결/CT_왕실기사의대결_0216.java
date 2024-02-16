package com.edu.ssafy_0216;

import java.util.*;
import java.io.*;

public class CT_왕실기사의대결_0216 {
	static int[][] map;
	static int[][] nightPos;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int n, m, Q;
	static ArrayList<Night> nightList;
	static boolean[] move;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map = new int[n + 2][n + 2];
		nightPos = new int[n + 2][n + 2];
		nightList = new ArrayList<>();
		for (int i = 0; i < n + 2; i++) {
			if (i > 0 && i < n + 1) {
				st = new StringTokenizer(bf.readLine(), " ");
			}
			for (int j = 0; j < n + 2; j++) {
				if (i == 0 || j == 0 || i == n + 1 || j == n + 1) {
					map[i][j] = 2;
				} else {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int hp = Integer.parseInt(st.nextToken());
			nightList.add(new Night(x, y, h, w, hp, 0));
		}

		init();

		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int nightNum = Integer.parseInt(st.nextToken());
			int command = Integer.parseInt(st.nextToken());
			if (nightList.get(nightNum - 1).hp < 1) {
				continue;
			}
			move = new boolean[m];
			if (canGo(nightList.get(nightNum - 1), command)) {
				go(nightList.get(nightNum - 1), command);
				nightPos = new int[n + 2][n + 2];
				checkHp(nightNum);
				init();
			}
		}

		int ans = 0;
		for (Night n : nightList) {
			if (n.hp > 0) {
				ans += n.damage;
			}
		}
		System.out.println(ans);

	}

	static void init() {
		int num = 1;
		for (Night n : nightList) {
			if (n.hp > 0) {
				for (int i = n.x; i < n.x + n.h; i++) {
					for (int j = n.y; j < n.y + n.w; j++) {
						nightPos[i][j] = num;
					}
				}
			}
			++num;
		}
	}

	static class Night {
		int x;
		int y;
		int h;
		int w;
		int hp;
		int damage;

		public Night(int x, int y, int h, int w, int hp, int damage) {
			this.x = x;
			this.y = y;
			this.h = h;
			this.w = w;
			this.hp = hp;
			this.damage = damage;
		}
	}

	static void checkHp(int nightNum) {
		int n = 1;
		for (Night night : nightList) {
			if (n == nightNum) {
				++n;
				continue;
			}
			if (move[n - 1]) {
				for (int i = night.x; i < night.x + night.h; i++) {
					for (int j = night.y; j < night.y + night.w; j++) {
						if (map[i][j] == 1) {
							--night.hp;
							++night.damage;
						}
					}
				}
			}
			++n;
		}
	}

	static boolean canGo(Night night, int command) {

		int nightNumber = nightPos[night.x][night.y];

		int nx = night.x + dx[command];
		int ny = night.y + dy[command];

		// 벽이랑 부디치는지 확인
		for (int i = nx; i < nx + night.h; i++) {
			for (int j = ny; j < ny + night.w; j++) {
				if (map[i][j] == 2) {
					return false;
				}
			}
		}
		// 기사랑 부디치는지 확인
		for (int i = nx; i < nx + night.h; i++) {
			for (int j = ny; j < ny + night.w; j++) {
				if (nightPos[i][j] == 0) {
					continue;
				}
				if (nightPos[i][j] != nightNumber) {
					if (!canGo(nightList.get(nightPos[i][j] - 1), command)) {
						return false;
					}
				}
			}
		}
		move[nightNumber - 1] = true;
		return true;
	}

	static void go(Night night, int command) {

		for (int i = 0; i < m; i++) {
			if (move[i]) {
				nightList.get(i).x += dx[command];
				nightList.get(i).y += dy[command];
			}
		}
	}
}