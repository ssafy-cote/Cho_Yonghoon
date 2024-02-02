package com.edu.ssafy_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 최소힙연습 {
	static int[] map = new int[100001];
	static int index;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(bufferedReader.readLine());

		for (int i = 0; i < n; i++) {
			int command = Integer.parseInt(bufferedReader.readLine());

			if (command == 0) {
				System.out.println(pop());
			} else {
				push(command);
			}
		}

	}

	static void push(int value) {

		map[++index] = value;

		for (int i = index; i > 1; i /= 2) {
			if (map[i] > map[i / 2])
				break;
			int temp = map[i];
			map[i] = map[i / 2];
			map[i / 2] = temp;
		}
	}

	static int pop() {
		if (index == 0) {
			return 0;
		}
		int pop = map[1];
		map[1] = map[index];
		map[index] = Integer.MAX_VALUE;
		index--;
		for (int i = 1; i * 2 <= index;) {
			if (map[i] < map[i * 2] && map[i] < map[i * 2 + 1]) {
				break;
			}
			if (map[i * 2] < map[i * 2 + 1]) {
				int temp = map[i];
				map[i] = map[i * 2];
				map[i * 2] = temp;
				i = i * 2;
			} else {
				int temp = map[i];
				map[i] = map[i * 2 + 1];
				map[i * 2 + 1] = temp;
				i = i * 2 + 1;
			}
		}
		return pop;
	}
}
