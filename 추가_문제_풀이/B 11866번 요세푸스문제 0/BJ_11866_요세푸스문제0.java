package algo0425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_11866_요세푸스문제0 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		for(int i = 1; i <= n; i++) {
			dq.add(i);
		}
		int[] ans = new int[n];
		int index = 0;
		while (!dq.isEmpty()) {
			
			for(int i = 0; i < k-1; i++) {
				dq.add(dq.poll());
			}
			ans[index++] = dq.poll();
		}
		StringBuilder builder = new StringBuilder();
		builder.append("<");
		for(int i = 0; i < n; i++) {
			if (i != n-1) {
				builder.append(ans[i]).append(", ");
			}else {
				builder.append(ans[i]).append(">");
			}
		}
		System.out.println(builder);
	}

}
