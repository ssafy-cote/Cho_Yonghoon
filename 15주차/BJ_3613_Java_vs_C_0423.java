package algo0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3613_Java_vs_C_0423 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		String string = stringTokenizer.nextToken();
		char[] str = new char[string.length()];
		boolean command = true;
		for (int i = 0; i < string.length(); i++) {
			str[i] = string.charAt(i);
			if (str[i] == '_') {
				command = false;
			}
		}
		StringBuilder builder = new StringBuilder();
		if (str[0] < 97 || str[string.length() - 1] < 65 || str[string.length() - 1] == '_') {
			builder.append("Error!1");
		} else {
			int index = 0;
			if (command) {
				// 자바를 C로
				while (index < string.length()) {
					if (str[index] < 97) {
						builder.append("_").append((str[index++] + "").toLowerCase());
					} else {
						builder.append(str[index++]);
					}
				}
			} else {
				// C를 자바로
				while (index < string.length()) {
					if (str[index] < 91) {
						builder = new StringBuilder();
						builder.append("Error!2");
						break;
					}
					if (str[index] == '_') {
						if (str[index + 1] < 97) {
							builder = new StringBuilder();
							builder.append("Error!3");
							break;
						}
						builder.append((str[++index] + "").toUpperCase());
						++index;
					} else {
						builder.append(str[index++]);
					}
				}
			}
		}
		System.out.println(builder);
	}
}
