// 68740KB 메모리
// 636ms 시간

package com.edu.ssafy_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_11660_구간합구하기5_조용훈 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		int[][] sum = new int[N+1][N+1];
		
		for (int i = 0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j< N+1; j++) {
				sum[i][j] = sum[i][j-1] + arr[i-1][j-1];
			}
		}
		
		for(int i = 1; i < N+1; i++) {
			for (int j = 1; j < N+1; j++) {
				sum[j][i] = sum[j-1][i] + sum[j][i];
			}
		}
        
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int result = 0;
			result = sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
			System.out.println(result);
		}
	}
}
