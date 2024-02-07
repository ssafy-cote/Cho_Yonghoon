// 11620KB 메모리
// 80ms 시간

package com.edu.ssafy_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1244_스위치켜고끄기_조용훈 {

	static int[] map;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

		n = Integer.parseInt(stringTokenizer.nextToken());
		map = new int[n+1];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 1; i <= n; i++) {
			map[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		int f = Integer.parseInt(stringTokenizer.nextToken());
		for (int i = 0; i < f; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int isb = Integer.parseInt(stringTokenizer.nextToken());
			int num = Integer.parseInt(stringTokenizer.nextToken());
			if (isb == 1) {
				boy(num);
			} else {
				girl(num);
			}
		}
		for(int i = 1; i <= n; i++) {
			if(i == 21 || i == 41 || i == 61 || i == 81) {
				System.out.println();
			}
			System.out.print(map[i] + " ");
		}
	}

	static void change(int number) {
		if (map[number] == 1) {
			map[number] = 0;
		} else {
			map[number] = 1;
		}
	}

	static void boy(int num) {
		for (int a = 1; a <= n; a++) {
			int number = num * a;
			if (number <= n) {
				change(number);
			} else {
				break;
			}
		}
	}

	static void girl(int num) {
		int cnt = 1;
		change(num);
		while (true) {
			int f = num + cnt;
			int b = num - cnt;
			if ((b > 0) && (f <= n)) {
				if(map[f] == map[b]) {
					change(f);
					change(b);
				}else {
					break;
				}
			}else {
				break;
			}
			cnt++;
		}
	}
}
