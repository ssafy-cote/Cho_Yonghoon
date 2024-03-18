package com.edu.ssafy_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P_게임맵최단거리_0206 {
	static int[][] map = new int[1][2];
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), ",");
		for(int i = 0; i < 1; i++) {
			for(int j = 0; j < 2; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		System.out.println(bfs(map));
	}

	static int bfs(int[][] map) {
		int ans = 0;
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(0, 0));
		int[][] result = new int[map.length][map[0].length];
		int[][] visit = new int[map.length][map[0].length];
		result[0][0] = 1;
		visit[0][0] = 1;
		while (!queue.isEmpty()) {
			Pos pos = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				if(check(nx, ny, map.length, map[0].length) && map[nx][ny] == 1 && visit[nx][ny] == 0) {
					result[nx][ny] = result[pos.x][pos.y] + 1;
					visit[nx][ny] = 1;
					queue.add(new Pos(nx, ny));
				}
			}
		}
		ans = result[map.length-1][map[0].length-1];
		if(result[map.length-1][map[0].length-1] == 0) {
			ans = -1;
		}
		return ans;
	}

	static boolean check(int nx, int ny, int a, int b) {
		if (nx > -1 && nx < a && ny > -1 && ny < b ) {
			return true;
		}
		return false;
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
