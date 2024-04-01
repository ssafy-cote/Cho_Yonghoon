package algo0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2467_용액_0401 {

	public static void main(String[] args) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int[] map = new int[n];
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		for(int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(stringTokenizer.nextToken());
		}
		
		int front = 0;
		int back = n-1;
		int ans = Integer.MAX_VALUE;
		int posA = 0;
		int posB = 0;
		while (front != back) {
			int result = map[front] + map[back];
			if (Math.abs(result) < ans) {
				ans = Math.abs(result);
				posA = map[front];
				posB = map[back];
			}
			if (result < 0) {
				++front;
			}else {
				--back;
			}
		}
		System.out.println(posA + " " + posB);
		
	}
}
