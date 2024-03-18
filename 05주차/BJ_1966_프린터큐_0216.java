package com.edu.ssafy_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1966_프린터큐_0216 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int n = Integer.parseInt(stringTokenizer.nextToken());
			int ansIndex = Integer.parseInt(stringTokenizer.nextToken());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			Queue<Print> queue = new ArrayDeque<>();
			ArrayList<Integer> maxList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				int temp = Integer.parseInt(stringTokenizer.nextToken());
				queue.add(new Print(i, temp));
				maxList.add(temp);
			}
			Collections.sort(maxList, (o1, o2) -> {return o2-o1;});
			int ans = 0;
			while (!queue.isEmpty()) {
				Print print = queue.poll();
				if(maxList.get(0) > print.priy) {
					queue.add(print);
					continue;
				}else {
					++ans;
					if(print.index == ansIndex) {
						System.out.println(ans);
						break;
					}else {
						maxList.remove(0);
					}
				}
			}
		}
	}

	static class Print {
		int index;
		int priy;

		public Print(int index, int priy) {
			this.index = index;
			this.priy = priy;
		}
	}
}
