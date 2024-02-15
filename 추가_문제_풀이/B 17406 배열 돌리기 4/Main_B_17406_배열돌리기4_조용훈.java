package com.edu.ssafy_0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_17406_배열돌리기4_조용훈 {
	static int n, m, command;
	static int[][] map, tempMap, visit;
	static C[] list; // 명령어 저장 배열
	static C[] permutation; // 순열 저장 배열
	static int[] perVisit; // 순열 방문 표시 배열
	static int ans; // 정답

	public static void main(String[] args) throws IOException { // 67540kb 메모리, 324ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());
		command = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[n + 2][m + 2];
		tempMap = new int[n + 2][m + 2];

		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		list = new C[command];

		ans = Integer.MAX_VALUE;
		for (int i = 0; i < command; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int x = Integer.parseInt(stringTokenizer.nextToken());
			int y = Integer.parseInt(stringTokenizer.nextToken());
			int s = Integer.parseInt(stringTokenizer.nextToken());
			list[i] = new C(x, y, s);
		}
		permutation = new C[command];
		perVisit = new int[command];
		make(0);
		System.out.println(ans);
	}

	// 순열 만드는 함수
	static void make(int index) {
		if (index == command) {
			copy();	// 배열 복사하여 사용, 원래 배열은 유지시켜야함
			for (int i = 0; i < command; i++) {
				C c = permutation[i];
				visit = new int[n + 2][m + 2];
				go(c.x - c.s, c.y - c.s, c.x + c.s, c.y + c.s);
			}
			ans = (cal() < ans) ? cal() : ans;
			return;
		}

		for (int i = 0; i < command; i++) {
			if (perVisit[i] == 1)
				continue;
			perVisit[i] = 1;
			permutation[index] = list[i];
			make(index + 1);
			perVisit[i] = 0;
		}

	}
	
	// 배열 복사 함수
	static void copy() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				tempMap[i][j] = map[i][j];
			}
		}
	}

	// 행 합의 최소값 구하는 함수
	static int cal() {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= m; j++) {
				sum += tempMap[i][j];
			}
			min = (sum < min) ? sum : min;
		}
		return min;
	}

	// 배열 돌리는 함수
	static void go(int x, int y, int n, int m) {
		// 범위 벗어나면 리턴
		if (x > n - 1 || y > m - 1 || visit[x][y] == 1)
			return;
		// 처음 위치 값 따로 저장 (값 덮어 쓰면 사라지니까)
		int temp = tempMap[x][m];
		visit[x][m] = 1;

		// 위쪽 면
		for (int i = m; i > y; i--) {
			tempMap[x][i] = tempMap[x][i - 1];
			visit[x][i - 1] = 1;
		}
		// 왼쪽 면
		for (int i = x; i < n; i++) {
			tempMap[i][y] = tempMap[i + 1][y];
			visit[i + 1][y] = 1;
		}
		// 아래쪽 면
		for (int i = y; i < m; i++) {
			tempMap[n][i] = tempMap[n][i + 1];
			visit[n][i + 1] = 1;
		}
		// 오른쪽 면
		for (int i = n; i > x + 1; i--) {
			tempMap[i][m] = tempMap[i - 1][m];
			visit[i - 1][m] = 1;
		}
		// 마지막 값 따로 빼준거 저장
		tempMap[x + 1][m] = temp;
		visit[x + 1][m] = 1;

		// 다음 안쪽 줄 바꿔주기
		go(x + 1, y + 1, n - 1, m - 1);
	}

	// 돌리는 명령어 저장 클래스
	static class C {
		int x;
		int y;
		int s;

		public C(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
}
