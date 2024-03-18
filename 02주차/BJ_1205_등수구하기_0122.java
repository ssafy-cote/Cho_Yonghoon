package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1205_등수구하기_0122 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		if(n == 0) {
			System.out.println(1);
		}else {
			int jumsu = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int flag = 0;
			ArrayList<Integer> list = new ArrayList<>();
			st = new StringTokenizer(bf.readLine(), " ");
			int rank = -1;
			for(int i = 1; i <= n; i++) {
				int temp = Integer.parseInt(st.nextToken());
				if(jumsu > temp && flag == 0) {
					list.add(jumsu);
					flag = 1;
				}
				list.add(temp);
			}
			
			if(flag == 0) {
				list.add(jumsu);
			}
			
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i) == jumsu) {
					rank = i+1;
				}
			}
			
			if(rank <= p) {
				System.out.println(list.indexOf(jumsu) + 1);
			}else {
				System.out.println(-1);
			}
		}
	}
}
