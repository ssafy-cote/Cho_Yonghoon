package com.edu.ssafy_0201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2961_도영이가만든맛있는음식_조용훈 { // 11416kb 메모리, 76ms 시간
	static int n;
	static int ans = Integer.MAX_VALUE;
	static Taste[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		n = Integer.parseInt(stringTokenizer.nextToken());
		map = new Taste[n];
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int sin = Integer.parseInt(stringTokenizer.nextToken());
			int seun = Integer.parseInt(stringTokenizer.nextToken());
			map[i] = new Taste(sin, seun, 0);
		}
		if (n != 1) {
			choes(0, 0);
		}
		map[0].checked = 1;
		choes(0, 1);
		System.out.println(ans);
	}

	static void choes(int index, int ok) {
		if (index == n - 1) {
			sum();
			return;
		}

		choes(index + 1, 0);
		map[index + 1].checked = 1;
		choes(index + 1, 1);
		map[index + 1].checked = 0;
	}

	static void sum() {
		int sin = 1;
		int seun = 0;
		int flag = 0;
		for (int i = 0; i < n; i++) {
			if (map[i].checked == 1) {
				sin *= map[i].sin;
				seun += map[i].seun;
				flag += 1;
			}
		}
		if (flag == 0) {
			return;
		}
		int sum = Math.abs(sin - seun);
		ans = Math.min(sum, ans);
	}

	static class Taste {
		int sin;
		int seun;
		int checked;

		public Taste(int sin, int seun, int checked) {
			this.sin = sin;
			this.seun = seun;
			this.checked = checked;
		}
	}
}
