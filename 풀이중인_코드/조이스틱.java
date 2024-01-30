package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 조이스틱 {
	// A -> 65
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String name = bf.readLine();

		System.out.println(make(name));
	}

	static int make(String name) {
		int ans = 0;
		int[] temp = new int[name.length()];
		String[] str = name.split("");
		int[] map = new int[name.length()];
		for (int i = 0; i < str.length; i++) {
			int tem = str[i].charAt(0);
			if (tem > 78) {
				tem -= 91;
			} else {
				tem -= 65;
			}
			map[i] = Math.abs(tem);
		}

		int cur = 0;
		int index = 1;
		ans += map[0];
		int[] check = new int[map.length];
		check[0] = 1;
		for(int i = 0; i < map.length; i++) {
			if(map[i] == 0) {
				check[i] = 1;
			}
		}
		while (true) {
			int c = 0;
			for (int i = 0; i < check.length; i++) {
				c += check[i];
			}
			if (c == check.length) {
				break;
			}
			int go = cur + index;
			int back = cur - index;
			if (back < 0) {
				back = map.length - index + cur;
			}
			if (go >= map.length) {
				go = cur + index - map.length;
			}

			if (check[go] == 0 && map[go] != 0) {
				ans += map[go];
				ans += index;
				check[go] = 1;
				cur = go;
				index = 1;
			} else if (check[back] == 0 && map[back] != 0) {
				ans += map[back];
				ans += index;
				check[back] = 1;
				cur = back;
				index = 1;
			} else {
				index++;
			}

		}

		return ans;
	}

}
