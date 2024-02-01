package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_11279_최대_힙 {
	static int index;
	static int[] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());
		map = new int[100000];
		index = 0;
		for (int i = 0; i < n; i++) {
			int command = Integer.parseInt(bufferedReader.readLine());
			if (command == 0) {
				System.out.println(pop());
			} else {
				push(command);
			}
		}
	}

	static int pop() {
		if (index == 0) {
			return 0;
		}
		int temp = map[1];
		map[1] = map[index];
		map[index] = Integer.MIN_VALUE;
		index--;
		for (int i = 1; i*2 <= index;) {
			if (map[i] > map[i * 2] && map[i] > map[i * 2 + 1]) {
				break;
			}
			if (map[i * 2] > map[i * 2 + 1]) {
				int tem = map[i * 2];
				map[i * 2] = map[i];
				map[i] = tem;
				i = i * 2;
			} else {
				int tem = map[i * 2 + 1];
				map[i * 2 + 1] = map[i];
				map[i] = tem;
				i = i * 2 + 1;
			}
		}
		return temp;
	}

	static void push(int value) {
		map[++index] = value;
		for (int i = index; i > 1; i /= 2) {
			if(map[i] < map[i/2])	break;
			int temp = map[i];
			map[i] = map[i/2];
			map[i/2] = temp;
		}

	}
}
