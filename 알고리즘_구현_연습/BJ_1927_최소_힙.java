package com.edu.ssafy_0201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1927_최소_힙 {
	static int heepIndex;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine());

		int[] heep = new int[10];
		heepIndex = 0;

		for (int i = 0; i < n; i++) {
			int command = Integer.parseInt(bufferedReader.readLine());
			if (command == 0) {
				System.out.print(pop(heep) + "\n");
			} else {
				push(heep, command);
			}
		}
	}

	static int pop(int[] heep) {
		if (heepIndex == 0) {
			return 0;
		}
		int value = heep[1];
		heep[1] = heep[heepIndex];
		heep[heepIndex--] = Integer.MAX_VALUE;

		for (int i = 1; i * 2 <= heepIndex;) {

			if (heep[i] < heep[i * 2] && heep[i] < heep[i * 2 + 1]) {
				break;
			}
			if (heep[i * 2 + 1] > heep[i * 2]) {
				int temp = heep[i];
				heep[i] = heep[i * 2];
				heep[i * 2] = temp;
				i = i * 2;
			} else {
				int temp = heep[i];
				heep[i] = heep[i * 2 + 1];
				heep[i * 2 + 1] = temp;
				i = i * 2 + 1;
			}
		}
		return value;
	}

	static void push(int[] heep, int value) {
		heep[++heepIndex] = value;
		for (int i = heepIndex; i > 1; i /= 2) {
			if (heep[i / 2] <= heep[i]) {
				break;
			}
			int temp = heep[i / 2];
			heep[i / 2] = heep[i];
			heep[i] = temp;
		}
	}
}
