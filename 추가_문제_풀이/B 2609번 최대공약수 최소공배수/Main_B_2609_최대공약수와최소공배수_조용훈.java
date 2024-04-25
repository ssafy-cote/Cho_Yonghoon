package algo0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2609_최대공약수와최소공배수_조용훈 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int a = Integer.parseInt(stringTokenizer.nextToken());
		int b = Integer.parseInt(stringTokenizer.nextToken());
		int temp = 0;
		if (b > a) {
			temp = a;
			a = b;
			b = temp;
		}

		int tempA = a;
		int tempB = b;
		int tempAns = 0;
		while (true) {
			if (tempA % tempB == 0) {
				tempAns = tempB;
				break;
			}
			tempAns = tempA % tempB;
			tempA = tempB;
			tempB = tempAns;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(tempAns).append("\n").append(a * b / tempAns);
		System.out.println(builder);
	}
}
