package com.edu.ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2829_설탕배달_0129 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int K = Integer.parseInt(st.nextToken());
		int ans = 0;
		if (K == 7 || K == 4)
			System.out.println(-1);
		else {
			if (K % 5 == 1)
				ans = K / 5 + 1;
			else if (K % 5 == 2)
				ans = K / 5 + 2;
			else if (K % 5 == 3)
				ans = K / 5 + 1;
			else if (K % 5 == 4)
				ans = K / 5 + 2;
			else if (K % 5 == 0)
				ans = K / 5;

			System.out.println(ans);
		}
	}
}
