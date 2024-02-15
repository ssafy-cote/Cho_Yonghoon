package com.edu.ssafy_0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5644_무선충전_조용훈 {
	static int ans;
	static int M, A;
	static int[][] move; // 0 : A, 1 : B

	static int xA, yA; // A 위치
	static int xB, yB; // B 위치

	static BC[] bcList; // BC 전체 리스트

	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };

	static ArrayList<BC> isA; // 현재 A가 밟고 있는 위치에서의 충전 가능한 BC 리스트
	static ArrayList<BC> isB; // 현재 B가 밟고 있는 위치에서의 충전 가능한 BC 리스트

	static int sumA; // A의 총 충전량
	static int sumB; // B의 총 충전량

	public static void main(String[] args) throws IOException { // 21,620kb 메모리, 135ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			M = Integer.parseInt(stringTokenizer.nextToken());
			A = Integer.parseInt(stringTokenizer.nextToken());
			// 값 초기화
			ans = 0;
			sumA = 0;
			sumB = 0;
			xA = 1;
			yA = 1;
			xB = 10;
			yB = 10;
			move = new int[2][M];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int i = 0; i < M; i++) {
				move[0][i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int i = 0; i < M; i++) {
				move[1][i] = Integer.parseInt(stringTokenizer.nextToken());
			}

			bcList = new BC[A];
			for (int i = 0; i < A; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				int y = Integer.parseInt(stringTokenizer.nextToken());
				int x = Integer.parseInt(stringTokenizer.nextToken());
				int C = Integer.parseInt(stringTokenizer.nextToken());
				int P = Integer.parseInt(stringTokenizer.nextToken());
				bcList[i] = new BC(x, y, C, P, i);
			}

			// 초기 위치 충전
			move(0, 0);
			move(0, 1);
			checkBC();

			// 이동 위치 충전
			for (int i = 0; i < M; i++) {
				move(move[0][i], 0); // A 이동
				move(move[1][i], 1); // B 이동
				checkBC(); // 충전
			}
			ans = sumA + sumB;
			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static void checkBC() {

		isA = new ArrayList<>();
		isB = new ArrayList<>();

		for (BC bc : bcList) {
			int D = Math.abs(bc.x - xA) + Math.abs(bc.y - yA);
			if (D <= bc.length) {
				isA.add(bc);
			}
			D = Math.abs(bc.x - xB) + Math.abs(bc.y - yB);
			if (D <= bc.length) {
				isB.add(bc);
			}
		}

		// A, B 둘다 충전 가능한 BC가 1개 이상일때
		if (isA.size() > 0 && isB.size() > 0) {
			charge();
		} else {
			// 둘다 선택지가 없는경우 리턴
			if (isA.size() == 0 && isB.size() == 0) {
				return;
			}
			// 둘중 한 개라도 선택지가 없는경우
			if (isA.size() == 0 || isB.size() == 0) {
				// A가 선택지가 없는경우
				if (isA.size() == 0) {
					int max = Integer.MIN_VALUE;
					for (BC bc : isB) {
						max = (max < bc.power) ? bc.power : max;
					}
					// 최대 충전량 도출 후 값 더하기
					sumB += max;
				} else {	// B가 선택지가 없는경우
					int max = Integer.MIN_VALUE;
					for (BC bc : isA) {
						max = (max < bc.power) ? bc.power : max;
					}
					// 최대 충전량 도출 후 값 더하기
					sumA += max;
				}
			}
		}
	}

	static void charge() {
		int sum = Integer.MIN_VALUE;
		int selectedA = 0;
		int selectedB = 0;
		// 모든 경우의 수 돌아서 최대 값 구하기
		for (BC bcA : isA) {
			for (BC bcB : isB) {
				int Asum = 0;
				int Bsum = 0;
				if (bcA.num == bcB.num) {
					Asum += bcA.power / 2;
					Bsum += bcA.power / 2;
				} else {
					Asum += bcA.power;
					Bsum += bcB.power;
				}
				int temp = Asum + Bsum;
				if (sum < temp) {
					sum = temp;
					selectedA = Asum;
					selectedB = Bsum;
				}
			}
		}
		sumA += selectedA;
		sumB += selectedB;
	}

	// 이동 함수
	static void move(int command, int AorB) {
		if (AorB == 0) {// A 이동
			xA += dx[command];
			yA += dy[command];
		} else { // B 이동
			xB += dx[command];
			yB += dy[command];
		}
	}

	// 충전 클래스
	static class BC {
		int x;
		int y;
		int length;
		int power;
		int num;

		public BC(int x, int y, int length, int power, int num) {
			this.x = x;
			this.y = y;
			this.length = length;
			this.power = power;
			this.num = num;
		}
	}
}
