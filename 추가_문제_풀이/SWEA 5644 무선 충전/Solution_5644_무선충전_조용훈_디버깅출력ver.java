package com.edu.ssafy_0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5644_무선충전_조용훈_디버깅출력ver {
	static int ans;
	static int M, A;
	static int[][] move; // 0 : A, 1 : B

	static int xA, yA;
	static int xB, yB;

	static BC[] bcList;

	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };

	static ArrayList<BC> isA;
	static ArrayList<BC> isB;

	static int sumA;
	static int sumB;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			M = Integer.parseInt(stringTokenizer.nextToken());
			A = Integer.parseInt(stringTokenizer.nextToken());
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

			move(0, 0);
			move(0, 1);
			checkBC();

			for (int i = 0; i < M; i++) {
				move(move[0][i], 0);
				move(move[1][i], 1);
				checkBC();
			}
			System.out.println(sumA + " " + sumB);
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

		if (isA.size() > 0 && isB.size() > 0) {
			charge();
		} else {
			if(isA.size() == 0 && isB.size() == 0) {
				return;
			}
			if(isA.size() == 0 || isB.size() == 0) {
				if(isA.size() == 0) {
					int max = Integer.MIN_VALUE;
					for(BC bc : isB) {
						max = (max < bc.power) ? bc.power : max;
					}
					sumB += max;
				}else {
					int max = Integer.MIN_VALUE;
					for(BC bc : isA) {
						max = (max < bc.power) ? bc.power : max;
					}
					sumA += max;
				}
			}
		}
	}

	static void charge() {
		System.out.println("겹침");
		int sum = Integer.MIN_VALUE;
		int selectedA = 0;
		int selectedB = 0;
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
		System.out.println("겹침" + "A :: " + sumA + "-- sumB :: " + sumB);
	}

	static void move(int command, int AorB) {
		if (AorB == 0) {// A 이동
			xA += dx[command];
			yA += dy[command];
		} else { // B 이동
			xB += dx[command];
			yB += dy[command];
		}
	}

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
