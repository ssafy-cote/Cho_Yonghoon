package algorithm_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1759_암호만들기_0207 {
	static int L, C;
	static String[] list;
	static String[] result;
	static StringBuilder builder;
	static List<String> aeiou;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		aeiou = new ArrayList<>();
		aeiou.add("a");
		aeiou.add("e");
		aeiou.add("i");
		aeiou.add("o");
		aeiou.add("u");
		list = new String[C];
		for (int i = 0; i < C; i++) {
			list[i] = st.nextToken();
		}
		builder = new StringBuilder();
		result = new String[L];
		Arrays.sort(list);
		make(0, 0, 0, 0);
		System.out.println(builder);
	}

	static void make(int index, int start, int mo, int ja) {
		if (index == L) {
			if (mo >= 1 && ja >= 2) {
				for (int i = 0; i < L; i++) {
					builder.append(result[i]);
				}
				builder.append("\n");
			}
			return;
		}
		for (int i = start; i < C; i++) {
			int mm = 0;
			int jaa = 0;
			String tempString = list[i];
			if (aeiou.contains(tempString))
				mm++;
			else
				jaa++;
			result[index] = tempString;
			make(index + 1, i + 1, mo + mm, ja + jaa);
		}
	}
}
