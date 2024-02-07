package com.edu.ssafy_0201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808_규영이와인영이의카드게임_조용훈 {
	static int win, lose;	// 승수, 패수
	static int[] map;		// 규영이 숫자 맵
	static int[] check;		// 인영이가 가진 숫자 알아내기 위한 배열
	static int[] inyong;	// 인영이 숫자 맵
	static int[] result;	// 인영이의 숫자 카드 순서를 담는 배열
	static int[] visit;		// 인영이 숫자 카드 순서 정할때 방문여부 확인 배열
	static int aSum, bSum;	// 중간에 게임 진행시 총 점수 담는 변수

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int T = Integer.parseInt(stringTokenizer.nextToken());
		for (int t = 1; t <= T; t++) {
			// 초기화 진행
			check = new int[19];
			map = new int[9];
			visit = new int[9];
			inyong = new int[9];
			result = new int[9];
			win = 0;
			lose = 0;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int i = 0; i < 9; i++) {
				int temp = Integer.parseInt(stringTokenizer.nextToken());
				check[temp] = 1;
				map[i] = temp;
			}
			// 인영이 숫자 찾고 담기
			int index = 0;
			for (int i = 1; i < 19; i++) {
				if (check[i] == 0) {
					inyong[index++] = i;
				}
			}
			// 모든 경우의 수 진행
			dfs(0);
			System.out.println("#" + t + " " + win + " " + lose);
		}
	}

	static void dfs(int index) {
		// 9칸이므로 index가 9가 되면 게임 진행
		if (index == 9) {
			aSum = 0;
			bSum = 0;
			for (int i = 0; i < 9; i++) {
				int aNum = map[i];
				int bNum = result[i];
				game(aNum, bNum);
			}
			gameResult(aSum, bSum);
			return;
		}
		// 인영이 카드 순서 만드는 중
		for(int i = 0; i < 9; i ++) {
			if(visit[i] == 1)continue;
			visit[i] = 1;
			result[index] = inyong[i];
			dfs(index+1);
			visit[i] = 0;
		}
	}
	// 카드 게임 진행
	static void game(int a, int b) {
		if(a > b) {
			aSum += (a + b);
		}else if (b > a) {
			bSum += (a + b);
		}
	}
	// 카드 게임 결과 확인 후 승수, 패수 증가
	static void gameResult(int a, int b) {
		if (a < b) {
			lose++;
		} else if (a > b) {
			win++;
		}
	}
}
