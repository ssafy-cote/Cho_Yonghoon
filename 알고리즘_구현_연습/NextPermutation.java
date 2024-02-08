package com.edu.ssafy_0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NextPermutation {
	static int N, input[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		// step0: 배열 정렬
		Arrays.sort(input);
		do {
			// 문제 로직 처리는 여기서
			System.out.println(Arrays.toString(input));
		} while (nextPermutation(input));
	}

	// 순열의 뒷쪽부터 작은 변화를 준다
	static boolean nextPermutation(int[] p) { // p: 현 순열
		// step1: 교환 위치 찾기(꼭대기 찾으면 꼭대기 이전 위치가 교환 위치)
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			--i;

		// 현순열의 상태가 가장 큰 순열이므로 재귀 중단
		if (i == 0)
			return false;

		// step2: 교환위치(i-1)에 넣을 값 뒤쪽부터 찾기 ( 큰 값 중 최소값 )
		int j = N - 1;
		while (p[i - 1] >= p[j])
			--j;

		// step3: 교환위치(i-1) 값과 찾은 위치(j)값 교환
		swap(p, i - 1, j);

		// step4: 꼭대기(i) 위치부터 맨뒤까지 오름차순 정렬
		int k = N - 1;
		while (i < k)
			swap(p, i++, k--);

		return true;

	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
