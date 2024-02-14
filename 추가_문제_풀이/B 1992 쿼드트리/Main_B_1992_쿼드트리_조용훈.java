package algorithm_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1992_쿼드트리_조용훈2 {
	static int n;
	static char[][] map;

	public static void main(String[] args) throws IOException {	// 13324kb 메모리, 92ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		n = Integer.parseInt(stringTokenizer.nextToken());
		map = new char[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String string = stringTokenizer.nextToken();
			for (int j = 1; j <= n; j++) {
				map[i][j] = string.charAt(j - 1);
			}
		}
		System.out.println(change(1, 1, n, n));
	}

	static String change(int xStart, int yStart, int xEnd, int yEnd) {

		if ((xEnd - xStart) <= 1) {
			String temp = "(";
			for (int i = xStart; i <= xEnd; i++) {
				for (int j = yStart; j <= yEnd; j++) {
					temp += map[i][j];
				}
			}
			temp += ")";
			if (temp.equals("(1111)")) {
				temp = "1";
			} else if (temp.equals("(0000)")) {
				temp = "0";
			} else if (temp.equals("(1)")) {
				temp = "1";
			} else if (temp.equals("(0)")) {
				temp = "0";
			}
			return temp;
		}

		String str = "(" + change(xStart, yStart, (xStart + xEnd) >> 1, (yStart + yEnd) >> 1)
				+ change(xStart, ((yStart + yEnd) >> 1) + 1, (xStart + xEnd) >> 1, yEnd)
				+ change(((xStart + xEnd) >> 1) + 1, yStart, xEnd, (yStart + yEnd) >> 1)
				+ change(((xStart + xEnd) >> 1) + 1, ((yStart + yEnd) >> 1) + 1, xEnd, yEnd) + ")";

		if (str.equals("(1111)")) {
			str = "1";
		} else if (str.equals("(0000)")) {
			str = "0";
		}
		return str;
	}
}
