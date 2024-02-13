package com.edu.ssafy_0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_16435_스네이크버드_조용훈 {	// 11828kb 메모리, 84ms 시간
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		int size = Integer.parseInt(stringTokenizer.nextToken());
		int[] map = new int[n];
		int cnt = 0;
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(stringTokenizer.nextToken());
			map[i] = temp;
			if (temp <= size)
				++cnt;
		}
		// 계속 더하기 하면서 비교
		Arrays.sort(map);
		size += cnt;
		while (true) {
			if (cnt > n - 1)
				break;
			if (map[cnt] <= size) {
				++size;
				++cnt;
			} else {
				break;
			}
		}
		System.out.println(size);
	}
}
