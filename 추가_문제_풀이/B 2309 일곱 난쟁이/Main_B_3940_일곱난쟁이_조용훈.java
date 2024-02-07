// 11472KB 메모리
// 80ms 시간 

package com.edu.ssafy_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_B_3940_일곱난쟁이_조용훈 {
	static int[] map, result;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9];
		result = new int[2];
		for (int i = 0; i < 9; i++) {
			map[i] = Integer.parseInt(bufferedReader.readLine());
		}
		find(0, 1);
	}
	static void find(int index, int start) {
		if (index == 2) {
			check();
			return;
		}
		for (int i = start; i < 9; i++) {
			result[index] = i;
			find(index + 1, i + 1);
		}
	}
	static void check() {
		int a = result[0];
		int b = result[1];
		int aV = map[a];
		int bV = map[b];

		int sum = 0;
		for (int i = 0; i < 9; i++) {
			if (i == a || i == b) {
				map[i] = 0;
				continue;
			}
			sum += map[i];
		}

		if (sum == 100) {
			Arrays.sort(map);
			for (int i = 2; i < 9; i++) {
				System.out.println(map[i]);
			}
			return;
		} else {
			map[a] = aV;
			map[b] = bV;
		}
	}
}
