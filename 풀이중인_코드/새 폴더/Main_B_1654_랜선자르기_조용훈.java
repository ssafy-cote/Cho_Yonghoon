package com.edu.ssafy_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1654_랜선자르기_조용훈 {
	static int n, needCnt, mok;
	static long[] map;

	public static void main(String[] args) throws IOException {	// 16020kb 메모리, 168ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		needCnt = Integer.parseInt(stringTokenizer.nextToken());

		map = new long[n];
		// 숫자가 커서 long
		long end = 1;
		long start = 1;

		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int temp = Integer.parseInt(stringTokenizer.nextToken());
			map[i] = temp;
			end = (end < temp) ? temp : end;
		}
		long mid = 1;
		// 이진 탐색 시작
		while (start <= end) {	// 종료조건 잘 확인
			mid = (start + end) >> 1;
			int tempMok = 0;
			for (int i = 0; i < n; i++) {
				tempMok += (map[i] / mid);
			}
			if (tempMok < needCnt) {
				end = mid - 1;
			} else if (tempMok >= needCnt) {
				start = mid + 1;
			}
		}
		System.out.println(end);
	}
}
