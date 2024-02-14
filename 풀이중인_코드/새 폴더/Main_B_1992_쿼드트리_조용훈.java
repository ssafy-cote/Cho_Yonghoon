package com.edu.ssafy_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1992_쿼드트리_조용훈 {
	static int n;
	static String[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		n = Integer.parseInt(stringTokenizer.nextToken());
		map = new String[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String string = stringTokenizer.nextToken();
			for (int j = 1; j <= n; j++) {
				map[i][j] = string.charAt(j - 1) + "";
			}
		}
		System.out.println(change(1, 1, n, n));

	}

	static String change(int xStart, int yStart, int xEnd, int yEnd) {

		if ((xEnd - xStart) == 1) {
			String temp = "(";
			for (int i = xStart; i <= xEnd; i++) {
				for (int j = yStart; j <= yEnd; j++) {
//					System.out.println(i +  " :: " + j);
					temp += map[i][j];
				}
			}
			temp += ")";
			if (temp.equals("(1111)")) {
				temp = "1";
			} else if (temp.equals("(0000)")) {
				temp = "0";
			}
//			System.out.println("===="+temp);
			return temp;
		}

		int midX = (xStart + xEnd) >> 1;
		int midY = (yStart + yEnd) >> 1;

		String str1 = change(xStart, yStart, midX, midY);
		String str2 = change(xStart, midY + 1, midX, yEnd);
		String str3 = change(midX + 1, yStart, xEnd, midY);
		String str4 = change(midX + 1, midY + 1, xEnd, yEnd);

		String str = "(" + str1 + str2 + str3 + str4 + ")";

		if (str.equals("(1111)")) {
			str = "1";
		} else if (str.equals("(0000)")) {
			str = "0";
		}

//		System.out.println(str + "=====");
		return str;
	}
}
