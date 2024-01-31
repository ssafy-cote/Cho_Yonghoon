package com.edu.ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 순열_구현_연습 {
	static int N = 5; // 1 ~ N
	static int R = 3; // 뽑는 요소 개수

	// 재귀에서 뽑은 요소 정보
	static int[] map;
	static int[] check;

	static StringBuilder builder;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		builder = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				for (int k = 1; k <= N; k++) {
					if (i == k || j == k)
						continue;
					builder.append(i + " " + j + " " + k + "\n");
				}
			}
		}
		System.out.println(builder);
		builder = new StringBuilder();

		System.out.println("===========================\n");
		map = new int[r];
		check = new int[n+1];
		f(0, n, r);
		System.out.println(builder);

	}

	static void f(int index, int n, int r) {

		if (index == r) {
			for(int i = 0; i < r; i++) {
				builder.append(map[i] + " ");
			}
			builder.append("\n");
			return;
		}
		for (int i = 1; i <= N; i++) {
			if(check[i] == 1) continue;
			map[index] = i;
			check[i] = 1;
			f(index+1, n, r);
			check[i] = 0;
		}

	}
}
