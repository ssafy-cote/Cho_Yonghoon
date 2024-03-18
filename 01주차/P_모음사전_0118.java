package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_모음사전_0118 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		String w = st.nextToken();
		if(w.equals("A")) {
			System.out.println(1);
		}else if(w.equals("AA")) {
			System.out.println(2);
		}else if(w.equals("AAA")) {
			System.out.println(3);
		}else if(w.equals("AAAA")) {
			System.out.println(4);
		}else {
			String[] ww = w.split("");
			
			int[] word = new int[5];
			
			for(int i = 0; i < ww.length; i++) {
				if(ww[i].equals("A")) {
					word[i] = 1;
				}else if(ww[i].equals("E")){
					word[i] = 2;
				}else if(ww[i].equals("I")){
					word[i] = 3;
				}else if(ww[i].equals("O")){
					word[i] = 4;
				}else if(ww[i].equals("U")){
					word[i] = 5;
				}
			}
			int count = 5;
			int[] list = new int[] {1,1,1,1,1};
			while(true) {
				if(check(word, list)) {
					break;
				}
				count++;
				
				increase(list);
			}
			System.out.println(count);
		}

	}
	
	
	static boolean check(int[] a, int[] b) {
		
		for(int i = 0; i < 5; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	
	
	static void increase(int[] list) {
		int flag = 4;
		for(int i = 0; i< 5; i++) {
			if(list[i] == 0) {
				flag = i;
				break;
			}
		}
		
		list[flag] += 1;
		for(int i = 4; i > 0; i--) {
			if(list[i] == 6) {
				list[i] = 0;
				list[i-1] += 1;
			}
		}
		
	}

}

/** 
 * 
 * 
class Solution {
    static boolean check(int[] a, int[] b) {
		
		for(int i = 0; i < 5; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	
	
	static void increase(int[] list) {
		int flag = 4;
		for(int i = 0; i< 5; i++) {
			if(list[i] == 0) {
				flag = i;
				break;
			}
		}
		
		list[flag] += 1;
		for(int i = 4; i > 0; i--) {
			if(list[i] == 6) {
				list[i] = 0;
				list[i-1] += 1;
			}
		}
		
	}
    
    public int solution(String w) {
        int count = 5;
        if(w.equals("A")) {
			count = 1;
		}else if(w.equals("AA")) {
			count = 2;
		}else if(w.equals("AAA")) {
			count = 3;
		}else if(w.equals("AAAA")) {
			count = 4;
		}else {
			String[] ww = w.split("");
			
			int[] word = new int[5];
			
			for(int i = 0; i < ww.length; i++) {
				if(ww[i].equals("A")) {
					word[i] = 1;
				}else if(ww[i].equals("E")){
					word[i] = 2;
				}else if(ww[i].equals("I")){
					word[i] = 3;
				}else if(ww[i].equals("O")){
					word[i] = 4;
				}else if(ww[i].equals("U")){
					word[i] = 5;
				}
			}
			
			int[] list = new int[] {1,1,1,1,1};
			while(true) {
				if(check(word, list)) {
					break;
				}
				count++;
				
				increase(list);
			}
		}
        return count;
    }
}

 * 
 * 
 * 
 * 
 * */

