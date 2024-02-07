package com.edu.ssafy_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_1158_요세푸스문제_조용훈 {

	public static void main(String[] args) throws IOException {	// 15372kb 메모리, 268ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		
		int n = Integer.parseInt(stringTokenizer.nextToken());
		int k = Integer.parseInt(stringTokenizer.nextToken());
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for(int i = 1; i <= n; i++) {
			queue.add(i);
		}
		StringBuilder builder = new StringBuilder();
		builder.append("<");
		while(queue.size() != 1) {
			for(int i = 1; i < k; i++) {
				queue.add(queue.poll());
			}
			
			builder.append(queue.poll() + ", ");
		}
		builder.append(queue.poll()+">");
		System.out.println(builder);
	}
}