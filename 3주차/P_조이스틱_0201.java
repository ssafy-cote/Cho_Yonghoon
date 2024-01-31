package com.edu.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_조이스틱_0201 {
	// A -> 65
	static int[] map; // 버튼을 눌러야하는 회수를 담은 배열
	static int[] check; // 바꿔줬는지 체크하는 배열
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String name = bf.readLine();

		System.out.println(make(name));
	}

	static int make(String name) {
		ans = Integer.MAX_VALUE;
		String[] str = name.split("");
		map = new int[name.length()];
		for (int i = 0; i < str.length; i++) {
			int temp = str[i].charAt(0);
			if (temp > 78) {
				temp -= 91;
			} else {
				temp -= 65;
			}
			map[i] = Math.abs(temp);
		}
		check = new int[map.length];
		int cur = 0;
		int index = 1;
		int sum = 0;
		sum += map[0];
		check[0] = 1;
		for (int i = 0; i < map.length; i++) {
			if (map[i] == 0) {
				check[i] = 1;
			}
		}

		find(cur, index, sum);

		return ans;
	}

	static void find(int cur, int index, int sum) {
		if(ans < sum) {
			return;
		}
		int c = 0;
		for (int i = 0; i < check.length; i++) {
			c += check[i];
		}
		if (c == check.length) {
			ans = Math.min(ans, sum);
			return;
		}

		int go = cur + index;
		int back = cur - index;
		if (back < 0) {
			back = map.length - index + cur;
		}
		if (go >= map.length) {
			go = cur + index - map.length;
		}

		int tempIndex = index;
		if (check[go] == 0) {
			int tempSum = sum;
			tempSum += map[go];
			tempSum += tempIndex;
			check[go] = 1;
			int ncur = go;
			index = 1;
			find(ncur, index, tempSum);
			check[go] = 0;
		}
		if (check[back] == 0) {
			int tempSum = sum;
			tempSum += map[back];
			tempSum += tempIndex;
			check[back] = 1;
			int ncur = back;
			index = 1;
			find(ncur, index, tempSum);
			check[back] = 0;
		}
		
		tempIndex++;
		if (tempIndex > map.length) {
			return;
		}else {
			find(cur, tempIndex, sum);
		}
		
	}
}
