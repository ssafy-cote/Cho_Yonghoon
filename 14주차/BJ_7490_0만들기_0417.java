package algo0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_7490_0만들기_0417 {

	static int N;
	static String[] map;
	static StringBuilder builder;
	
	static String[] list = {" ", "+", "-"};

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());

		builder = new StringBuilder();
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());

			map = new String[2 * N - 1];
			int number = 1;
			for (int i = 0; i < map.length; i++) {
				if (i % 2 == 0) {
					map[i] = number + "";
					++number;
				}
			}
			dfs(1);
			builder.append("\n");
		}
		System.out.println(builder);
	}

	static void dfs(int index) {

		if (index >= map.length) {

			if (check()) {
				for (int i = 0; i < map.length; i++) {
					builder.append(map[i]);
				}
				builder.append("\n");
			}
			return;
		}
		for(int i = 0; i < 3; i++) {
			map[index] = list[i];
			dfs(index + 2);
		}
	}

	static boolean check() {
		int sum = 0;
		String sick = "";
		for(int i = 0; i < map.length; i++) {
			sick += map[i];
		}
		sick = sick.replace(" ", "");
		String[] temp = sick.split("\\+|\\-");
		int tempIndex = 1;
		sum = Integer.parseInt(temp[0]);
		for(int i = 1; i < map.length; i += 2) {
			if (map[i].equals(" ")) {
				continue;
			}else if (map[i].equals("+")) {
				sum += Integer.parseInt(temp[tempIndex++]);
			}else if (map[i].equals("-")) {
				sum -= Integer.parseInt(temp[tempIndex++]);
			}
		}
		if (sum == 0) {
			return true;
		}
		return false;
	}
}
