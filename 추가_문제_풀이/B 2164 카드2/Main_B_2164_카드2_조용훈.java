package com.edu.ssafy_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_B_2164_카드2_조용훈 {	// 23856kb 메모리, 124ms ArrayDeque
										// 43096kb 메모리, 144ms LinkedList
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bufferedReader.readLine());
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for(int i = 1; i <= n; i++) {
			queue.add(i);
		}
		while(queue.size() != 1) {
			// 팝!
			queue.poll();
			// 푸시(팝!)!
			queue.add(queue.poll());
		}
		System.out.println(queue.poll());
	}
}
