package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 병합정렬_구현 {
	static int[] b;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String s = bf.readLine();

		int n = Integer.parseInt(s);

		int[] map = new int[n];

		for (int i = 0; i < n; i++) {
			s = bf.readLine();
			map[i] = Integer.parseInt(s);
		}

		b = new int[map.length];

		mergeSort(map, 0, map.length - 1);
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < n; i++) {
			stringBuilder.append(map[i] + "\n");
		}
		System.out.println(stringBuilder);
	}

	static void mergeSort(int[] map, int start, int end) {
		if (start >= end)
			return;
		int mid = (start + end) >> 1;
		mergeSort(map, start, mid);
		mergeSort(map, mid + 1, end);
		merge(map, start, end, mid);
	}

	static void merge(int[] map, int start, int end, int mid) {
		int i = start;
		int j = mid + 1;
		int index = start;
		while (i <= mid && j <= end) {
			if (map[i] < map[j]) {
				b[index++] = map[i++];
			} else {
				b[index++] = map[j++];
			}
		}

		while (i <= mid) {
			b[index++] = map[i++];
		}

		while (j <= end) {
			b[index++] = map[j++];
		}

		for (int q = start; q <= end; q++) {
			map[q] = b[q];
		}

	}

}
