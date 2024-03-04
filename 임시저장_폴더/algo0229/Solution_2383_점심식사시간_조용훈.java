package algo0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간_조용훈 {
	static int ans, N;
	static int[][] map;
	static Pos[] st;
	static ArrayList<Pos> st1;
	static ArrayList<Pos> st2;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int T = 1; T <= t; t++) {
			st1 = new ArrayList<>();
			st2 = new ArrayList<>();
			N = Integer.parseInt(stringTokenizer.nextToken());
			st = new Pos[2];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}
	
	static void go() {
		
	}

	static int timeCal(int stIndex, Pos people) {
		int time = Math.abs(st[stIndex].x - people.x) + Math.abs(st[stIndex].y - people.y) + 1 + st[stIndex].time;
		if (stIndex == 0) {
			for (int i = st1.size() - 3; i >= 0; i -= 3) {
				time += st1.get(i).time;
			}
		} else {
			for (int i = st2.size() - 3; i >= 0; i -= 3) {
				time += st2.get(i).time;
			}
		}
		return time;
	}

	static class Pos {
		int x;
		int y;
		int time;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

}
