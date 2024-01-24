package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_주식가격_0124 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int[] map = new int[n];
		st = new StringTokenizer(bf.readLine(), " ");
		for (int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		for (int i : stock(map)) {
			System.out.print(i + " ");
		}
	}

	static int[] stock(int[] map) {
		int[] result = new int[map.length];
		for (int i = 0; i < map.length; i++) {
			int time = 0;
			for (int j = i + 1; j < result.length; j++) {
				time++;
				if (map[i] > map[j]) {
					break;
				}
			}
			result[i] = time;
		}
		return result;
	}
}
