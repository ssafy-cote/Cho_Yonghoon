package com.edu.ssafy_0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5644_무선충전_조용훈_강사님코드참고하여다시작성 {
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

	static int total;
	
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
			xA = 1;
			yA = 1;
			xB = 10;
			yB = 10;
			total = 0;
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
			builder.append("#").append(T).append(" ").append(total).append("\n");
		}
		System.out.println(builder);
	}
	
	static boolean isArea(int index, int isA) {
		if(isA == 1) {
			int d = Math.abs(bcList[index].x - xA) + Math.abs(bcList[index].y - yA);
			if(d <= bcList[index].length) {
				return true;
			}
		}else {
			int d = Math.abs(bcList[index].x - xB) + Math.abs(bcList[index].y - yB);
			if(d <= bcList[index].length) {
				return true;
			}
		}
		return false;
	}
	

	static void checkBC() {
		int max = 0;
		for(int i = 0; i < A; i++) {
			for(int j = 0; j < A; j++) {
				int tempSum = 0;
				boolean aSelect = isArea(i, 1);
				boolean bSelect = isArea(j, 0);
				
				if(i == j && aSelect && bSelect) {
					tempSum += bcList[i].power;
				}else {
					if(aSelect) {
						tempSum += bcList[i].power;
					}
					if (bSelect) {
						tempSum += bcList[j].power;
					}
				}
				max = Math.max(tempSum, max);
			}
		}
		total += max;
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
