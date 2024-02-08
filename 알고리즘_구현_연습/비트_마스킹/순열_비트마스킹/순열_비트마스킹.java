package com.edu.ssafy_0208;

import java.util.Arrays;
import java.util.Scanner;

public class 순열_비트마스킹 {
	static int N, R, input[], numbers[];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		R = scanner.nextInt();
		input = new int[N];
		numbers = new int[R];

		for (int i = 0; i < N; i++) {
			input[i] = scanner.nextInt();
		}
		permuatation(0, 0);
	}

	static void permuatation(int cnt, int flag) {
		if (cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			numbers[cnt] = input[i];
			permuatation(cnt + 1, flag | (1 << i));
		}
	}
}
