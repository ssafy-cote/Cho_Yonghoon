package com.edu.ssafy_0213;

import java.util.Scanner;

public class Main_B_2839_설탕배달_조용훈 {
	public static void main(String[] args) {	// 13160kb 메모리, 124ms 시간
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] map = new int[N + 1];
		for (int i = 0; i < map.length; i++) {
			map[i] = 5000;
		}
		map[3] = 1;
		if (N == 3 || N == 4 || N == 7) {
			if (N == 3) {
				System.out.println(1);
				System.exit(0);
			}
			System.out.println(-1);
			System.exit(0);
		} else {
			map[5] = 1;
			for (int i = 6; i < map.length; i++) {
				map[i] = Math.min(map[i - 3] + 1, map[i - 5] + 1);
			}
		}
		System.out.println(map[N]);
		sc.close();
	}
}
