package com.edu.ssafy_0201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_12891_DNA비밀번호_조용훈 {	// 89748kb 메모리, 540ms 시간
	static int A, C, G, T;	// 비밀번호 만족 하는 기준 값 
	static int S, P, cnt;	// S: 문자열 총 길이, P: 비밀번호 길이, cnt: 가능한 비밀번호 수
	static int a, c, g, t;	// 현재 문자열에서의 보유하고 있는 각 알파벳 수
	static String[] map;	// 전체 문자 받을 배열

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		S = Integer.parseInt(stringTokenizer.nextToken());
		P = Integer.parseInt(stringTokenizer.nextToken());
		map = new String[S];
		String string = bufferedReader.readLine();
		map = string.split("");

		// 기준치 입력 받기
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		A = Integer.parseInt(stringTokenizer.nextToken());
		C = Integer.parseInt(stringTokenizer.nextToken());
		G = Integer.parseInt(stringTokenizer.nextToken());
		T = Integer.parseInt(stringTokenizer.nextToken());
		
		// 0 ~ P-1 까지의 비밀번호 가능성 체크 (첫번째 한 번만)
		for (int i = 0; i < P; i++) {
			if (map[i].equals("A")) {
				a++;
			} else if (map[i].equals("C")) {
				c++;
			} else if (map[i].equals("G")) {
				g++;
			} else if (map[i].equals("T")) {
				t++;
			}
		}
		// 비밀번호가 가능한지 체크하는 함수
		check();
		
		// 맨 뒤 자리 문자 확인 후 값 빼주고
		// 맨 앞 자리 문자 확인 후 값 더해주기
		for (int i = 0; i + P < S; i++) {
			checkAlphabet(map[i], -1);
			checkAlphabet(map[i + P], +1);
			check();
		}

		System.out.println(cnt);
	}

	static void check() {
		if (A <= a && C <= c && G <= g && T <= t) {
			cnt++;
		}
	}

	static void checkAlphabet(String str, int command) {
		if (str.equals("A")) {
			a += command;
		} else if (str.equals("C")) {
			c += command;
		} else if (str.equals("G")) {
			g += command;
		} else if (str.equals("T")) {
			t += command;
		}

	}

}
