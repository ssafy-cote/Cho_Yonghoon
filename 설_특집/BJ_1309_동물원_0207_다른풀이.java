package com.edu.ssafy_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1309_동물원_0207_다른풀이 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());
		int d[] = new int[n + 1];
		d[0] = 1;
		d[1] = 3;
		for (int i = 2; i <= n; i++) {
			d[i] = d[i - 2] + d[i - 1] * 2;
			d[i] %= 9901;
		}
		System.out.println(d[n] % 9901);
	}
}