package com.edu.ssafy_0216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_7576_토마토_조용훈 {
	static int n, m;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Queue<Pos> queue;
	static int max;

	public static void main(String[] args) throws Exception {	// 101316kb 메모리, 548ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		m = Integer.parseInt(stringTokenizer.nextToken());
		n = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n][m];
		visit = new boolean[n][m];
		queue = new ArrayDeque<Pos>();
		max = 1;
		
		// 입력을 받으면서 1 체크 하고 queue에 넣기
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < m; j++) {
				int temp = Integer.parseInt(stringTokenizer.nextToken());
				map[i][j] = temp;
				if (temp == 1) {
					queue.add(new Pos(i, j));
					visit[i][j] = true;
				}
			}
		}
		// bfs로 날짜 세기
		bfs();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					System.out.println(-1);
					System.exit(0);
				}
			}
		}
		// 날짜에서 1 빼줘야함 1부터 시작하기 때문에
		System.out.println(max-1);
	}

	static void bfs() {
		while (!queue.isEmpty()) {
			Pos pos = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				if (check(nx, ny) && !visit[nx][ny] && map[nx][ny] != -1) {
					visit[nx][ny] = true;
					map[nx][ny] = map[pos.x][pos.y] + 1;
					max = (max < map[nx][ny]) ? map[nx][ny] : max;	
					queue.add(new Pos(nx, ny));
				}
			}
		}
	}

	static boolean check(int x, int y) {
		return (x > -1 && x < n && y > -1 && y < m);
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
