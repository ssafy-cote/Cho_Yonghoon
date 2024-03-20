package algo0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9095_123더하기_0320 {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int num = Integer.parseInt(stringTokenizer.nextToken());
			int[] map = new int[num + 1];
			if (num == 1) {
				builder.append(1).append("\n");
			}else if(num == 2) {
				builder.append(2).append("\n");
			}else if(num == 3) {
				builder.append(4).append("\n");
			}else {
				map[1] = 1;
				map[2] = 2;
				map[3] = 4;
				for(int s = 4; s <= num; s++) {
					map[s] = map[s-1] + map[s-2] + map[s-3];
				}
				builder.append(map[num]).append("\n");
			}
		}
		System.out.println(builder);
	}
}
