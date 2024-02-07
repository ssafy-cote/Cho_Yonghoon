package com.edu.ssafy_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_햄버거다이어트_조용훈 {	// 20,408kb 메모리, 245ms 시간
	static int n, limit, ans;
	static Material[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			ans = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			list = new Material[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				list[i] = new Material(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			// 재료 선택 안하는 경우
			find(0, 0, 0, 0);
			// 재료 선택 하는 경우
			find(0, 0, 0, 1);

			System.out.println("#" + t + " " + ans);
		}

	}

	static void find(int index, int sumKal, int sumTaste, int choose) {
		// 만약 중간에 칼로리 초과한 경우 종료!
		if (sumKal > limit)
			return;
		if (index == n) {
			ans = Math.max(sumTaste, ans);
			return;
		}

		// 재료 선택 안하는 경우
		find(index + 1, sumKal, sumTaste, 0);
		
		// 재료 선택 하는 경우
		sumKal += list[index].kal;
		sumTaste += list[index].taste;
		find(index + 1, sumKal, sumTaste, 1);

	}

	static class Material {
		int taste;
		int kal;

		public Material(int taste, int kal) {
			this.taste = taste;
			this.kal = kal;
		}
	}
}
