package com.edu.ssafy_0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_15686_치킨배달_조용훈 {
	static int n, m;
	static ArrayList<Pos> chickenList;
	static ArrayList<Pos> houseList;
	static Pos[] picked;
	static int ans;

	public static void main(String[] args) throws IOException { // 13584kb 메모리, 164ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		chickenList = new ArrayList<>();
		houseList = new ArrayList<>();
		picked = new Pos[m];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 1; j <= n; j++) {
				int temp = Integer.parseInt(stringTokenizer.nextToken());
				if (temp == 2) {
					chickenList.add(new Pos(i, j));
				} else if (temp == 1) {
					houseList.add(new Pos(i, j));
				}
			}
		}
		ans = Integer.MAX_VALUE;
		combi(0, 0);
		System.out.println(ans);
	}

	static void combi(int index, int start) {
		if (index == m) {
			ans = Math.min(ans, getDistence());
			return;
		}
		for (int i = start; i < chickenList.size(); i++) {
			picked[index] = chickenList.get(i);
			combi(index + 1, i + 1);
		}
	}

	static int getDistence() {
		int sum = 0;
		// 모든 집에서 선택된 치킨집만 거리 비교 후 합 구하기
		for (Pos h : houseList) {
			int distence = Integer.MAX_VALUE;
			for (Pos c : picked) {
				distence = Math.min(distence, Math.abs(c.x - h.x) + Math.abs(c.y - h.y));
			}
			sum += distence;
		}
		return sum;
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
