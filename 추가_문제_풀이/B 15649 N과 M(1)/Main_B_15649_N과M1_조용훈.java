package com.edu.ssafy_0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_15649_N과M1_조용훈 {
	
	static StringBuilder builder;
	static int[] check;
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());
		builder = new StringBuilder();
		check = new int[n + 1];
		result = new int[m];
		f(0, n, m);
		System.out.println(builder);
	}
	
	static void f(Integer index, int n, int m) {
		if (index == m) {
			for (int i = 0; i < m; i++) {
				builder.append(result[i] + " ");
			}
			builder.append("\n");
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (check[i] == 1)
				continue;
			check[i] = 1;
			result[index] = i;
			f(index + 1, n, m);
			check[i] = 0;
		}
	}
}
