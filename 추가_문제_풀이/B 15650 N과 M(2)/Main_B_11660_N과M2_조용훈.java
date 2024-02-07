// 11500KB 메모리
// 76ms 시간

package com.edu.ssafy_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_11660_N과M2_조용훈 {
	static int[] result;
	static StringBuilder stringBuilder;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		stringBuilder = new StringBuilder();
		result = new int[m];
		nm(0, n, m, 1);
		System.out.println(stringBuilder);
	}
	
	static void nm(int index, int n, int m, int start) {
		
		if(index == m) {
			for(int i = 0; i < m; i++) {
				stringBuilder.append(result[i] + " ");
			}
			stringBuilder.append("\n");
			return;
		}
		
		for(int i = start; i <= n; i ++) {
			result[index] = i;
			nm(index+1, n, m, i+1);
		}
	}
}
