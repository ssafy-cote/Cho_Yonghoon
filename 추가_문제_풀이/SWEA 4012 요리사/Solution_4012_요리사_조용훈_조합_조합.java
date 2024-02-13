package com.edu.ssafy_0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_요리사_조용훈_조합_조합 {
	static int n;
	static int[][] map; // 시너지 점수 맵
	static int[] visit, foodA, foodB, visitP; // A 리스트 뽑고 남은 B를 뽑기 위한 방문 표시 배열, 음식 A 재료 배열, 음식 B 재료 배열, 순열을 만들때 필요한 배열
	static int[] foodASelected, foodBSelected; // 음식 A,B 재료 리스트에서 2개 뽑아서 시너지 확인하기 위한 배열
	static int sumA, sumB, ans; // A 점수 합, B 점수 합, 정답

	public static void main(String[] args) throws IOException { // 34,268kb 메모리, 176ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int T = 1; T <= t; T++) {
			ans = Integer.MAX_VALUE;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			n = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[n + 2][n + 2];
			visit = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 1; j <= n; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}
			foodA = new int[n / 2];
			foodB = new int[n / 2];
			// 조합으로 재료 나누고 조합으로 시너지 더하기
			combi(0, 2);
			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static void combi(int cnt, int start) {

		if (cnt == n / 2) {
			int index = 0;
			for (int i = 1; i <= n; i++) {
				if (visit[i] != 1) {
					foodB[index++] = i;
				}
			}
			sumA = 0;
			sumB = 0;
			foodASelected = new int[2];
			foodBSelected = new int[2];
			visitP = new int[n + 1];
			combi2(0, 0);
			ans = Math.min(ans, Math.abs(sumA - sumB));
			return;
		}

		for (int i = start; i <= n; i++) {
			if (visit[i] != 1) {
				visit[i] = 1;
				foodA[cnt] = i;
				combi(cnt + 1, i + 1);
				visit[i] = 0;
			}
		}
	}
	static void combi2(int cnt, int start) {
		if (cnt == 2) {
			sumA += map[foodASelected[0]][foodASelected[1]];
			sumB += map[foodBSelected[0]][foodBSelected[1]];
			sumA += map[foodASelected[1]][foodASelected[0]];
			sumB += map[foodBSelected[1]][foodBSelected[0]];
			return;
		}
		for (int i = start; i < n / 2; i++) {
			foodASelected[cnt] = foodA[i];
			foodBSelected[cnt] = foodB[i];
			combi2(cnt + 1, i + 1);
		}
	}
}
