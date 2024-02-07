package com.edu.ssafy_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1228_암호문1_조용훈 {
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {	// 18,392kb 메모리, 107ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		
		StringBuilder builder = new StringBuilder();
		
		for(int t = 1; t <= 10; t++) {
			list = new ArrayList<>();
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int n = Integer.parseInt(stringTokenizer.nextToken());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for(int i = 0; i < n; i++) {
				list.add(Integer.parseInt(stringTokenizer.nextToken()));
			}
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			n = Integer.parseInt(stringTokenizer.nextToken());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for(int i = 0; i < n; i++) {
				stringTokenizer.nextToken();
				int index = Integer.parseInt(stringTokenizer.nextToken());
				int num = Integer.parseInt(stringTokenizer.nextToken());
				for(int j = 0; j < num; j++) {
					list.add(index++,Integer.parseInt(stringTokenizer.nextToken()));
				}
			} 
			builder.append("#").append(t).append(" ");
			for(int q = 0; q < 10; q++) {
				builder.append(list.get(q) +  " ");
			}
			builder.append("\n");
		}
		System.out.println(builder);
	}
}
