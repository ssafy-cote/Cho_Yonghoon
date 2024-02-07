package com.edu.ssafy_0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1208_Flatten_조용훈 {
	
	static int[] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = null;
		for(int t = 1; t <= 10; t++) {
			map = new int[100];
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int n = Integer.parseInt(stringTokenizer.nextToken());
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for(int i = 0; i < 100; i++) {
				map[i] = Integer.parseInt(stringTokenizer.nextToken());
			}
			for(int i = 0; i < n; i++) {
				Arrays.sort(map);
				map[99] -= 1;
				map[0] += 1;
			}
			Arrays.sort(map);
			
			System.out.println("#" + t + " " + (map[99]-map[0]));
		}
	}
}
