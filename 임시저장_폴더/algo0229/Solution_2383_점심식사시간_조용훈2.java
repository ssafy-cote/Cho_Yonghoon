package algo0229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간_조용훈2 {
	static int ans, N;
	static int[][] map;
	static Pos[] st;
	static Deque<Pos> st1;
	static Deque<Pos> st2;
	static int[] result;
	static Pos[] people;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder builder = new StringBuilder();

		int t = Integer.parseInt(stringTokenizer.nextToken());
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			st = new Pos[2];
			people = new Pos[10];
			map = new int[N][N];
			int index = 0;
			int peopleCnt = 0;
			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
					if (map[i][j] > 1) {
						st[index++] = new Pos(i, j, map[i][j]);
					}
					if (map[i][j] == 1) {
						people[peopleCnt] = new Pos(i, j, 1);
						++peopleCnt;
					}
				}
			}

			st1 = new ArrayDeque<>();
			st2 = new ArrayDeque<>();
			result = new int[peopleCnt];
			ans = Integer.MAX_VALUE;
			permutaion(0);
			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static void permutaion(int index) {

		if (index == result.length) {
//			System.out.println(Arrays.toString(result));
			int time = go();
			ans = Math.min(time, ans);
			System.out.println("==========");
			return;
		}

		result[index] = 1;
		permutaion(index + 1);
		result[index] = 0;
		permutaion(index + 1);
	}

	static void cal(ArrayList<Pos> remainPeople) {
		for (int i = 0; i < remainPeople.size(); i++) {
			Pos pos = remainPeople.get(i);
//			System.out.println(i);
			pos.time = Math.abs(st[result[i]].x - pos.x) + Math.abs(st[result[i]].y - pos.y) + 1;
			System.out.println(pos.time);
			pos.whatSt = result[i];
		}
	}

	static int go() {

		ArrayList<Pos> remainPeople = new ArrayList<>();
		ArrayList<Pos> removePeople = new ArrayList<>();
		for (Pos pos : people) {
			if (pos == null)
				continue;
			remainPeople.add(pos);
		}
		cal(remainPeople);
		int time = 0;
		while (!remainPeople.isEmpty() || !st1.isEmpty() || !st2.isEmpty()) {
			++time;
			for (Pos pos : remainPeople) {
				pos.time -= 1;
				if (pos.time <= 0) {
					if (pos.whatSt == 0) {
						if (st1.size() < 3) {
							pos.time = st[0].time;
							st1.add(pos);
						}
					} else {
						if (st2.size() < 3) {
							pos.time = st[1].time;
							st2.add(pos);
						}
					}
					removePeople.add(pos);
				}
			}

			for (Pos pos : removePeople) {
				remainPeople.remove(pos);
			}

			int s = st1.size();
			for (int i = 0; i < s; i++) {
				Pos tPos = st1.poll();
				tPos.time -= 1;
				if (time <= 0) {
					st1.add(tPos);
				}
			}
			s = st2.size();
			for (int i = 0; i < s; i++) {
				Pos tPos = st2.poll();
				tPos.time -= 1;
//				System.out.println(tPos.time);
				if (time <= 0) {
					st2.add(tPos);
				}
			}

//			System.out.println("arry: " + remainPeople.size());
//			System.out.println("st1: " + st1.size());
//			System.out.println("st2: " + st2.size());

		}
		System.out.println("time: "+time);
		return time;
	}

	static class Pos {
		int x;
		int y;
		int time;
		int whatSt;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
