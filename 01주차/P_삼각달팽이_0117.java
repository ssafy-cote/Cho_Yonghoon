package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_삼각달팽이_0117 {

	static int ans;
	static int p, q;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][n];
		int count = 1;
		int x = -1;
		int y = 0;
		int t = n;
		while (t > 0) {
			
			// D
			for (int i = 0; i < t; i++) {
				x += 1;
				map[x][y] = count++;
				
			}
			t--;
			
			// R
			for (int i = 0; i < t; i++) {
				y += 1;
				map[x][y] = count++;
			}
			t--;
			
			// C
			for (int i = 0; i < t; i++) {
				x -= 1;
				y -= 1;
				map[x][y] = count++;
			}
			t--;
			
		}
		int[] list = new int[count-1];
		int c = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j] == 0) {
					continue;
				}else {
					list[c] = map[i][j];
					c++;
				}
			}
		}
		
		for(int i = 0; i < count-1; i++) {
			System.out.print(list[i] + " ");
		}
		

	}

}
/** 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * class Solution {
    public int[] solution(int n) {
        int[][] map = new int[n][n];
		int count = 1;
		int x = -1;
		int y = 0;
		int t = n;
		while (t > 0) {
			
			// D
			for (int i = 0; i < t; i++) {
				x += 1;
				map[x][y] = count++;
				
			}
			t--;
			
			// R
			for (int i = 0; i < t; i++) {
				y += 1;
				map[x][y] = count++;
			}
			t--;
			
			// C
			for (int i = 0; i < t; i++) {
				x -= 1;
				y -= 1;
				map[x][y] = count++;
			}
			t--;
			
		}
        int[] answer = new int[count-1];
		int c = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j] == 0) {
					continue;
				}else {
					answer[c] = map[i][j];
					c++;
				}
			}
		}
        return answer;
    }
}
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * /
 */