package com.edu.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6603_로또_0131 {
	static int n;
	static int[] map, result;
	static StringBuilder builder;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(bf.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			builder = new StringBuilder();
			if (n == 0)
				break;
			map = new int[n];
			result = new int[6];
			for (int i = 0; i < n; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			
			make(0, 0);
			System.out.println(builder);
		}
	}
	static void make(int index, int start) {
		if (index == 6) {
			for (int i = 0; i < 6; i++) {
				builder.append(result[i] + " ");
			}
			builder.append("\n");
			return;
		}
		for(int i = start; i < n; i++) {
			result[index] = map[i];
			make(index+1, i+1);
		}
	}
}
