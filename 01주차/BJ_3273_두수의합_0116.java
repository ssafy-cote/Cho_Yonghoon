package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_3273_두수의합_0116 {
	
	static int n;
	static int[] map;
	static int ans;
	static int p,q;
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		
		map = new int[n];
		
		st = new StringTokenizer(bf.readLine(), " ");
		for(int i = 0; i < n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(bf.readLine(), " ");
		ans = Integer.parseInt(st.nextToken());
		
		p = 0;
		q = n-1;
		
		Arrays.sort(map);
		
		while(p < q) {
			int sum = map[p] + map[q];
			if(sum == ans) {
				count++;
				p++;
				q--;
			}else if(sum > ans) {
				q--;
			}else {
				p++;
			}
		}
		
		System.out.println(count);
	}

}
