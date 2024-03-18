package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1475_방_번호{
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		String l = st.nextToken();
		
		String[] line = l.split("");
		int[] list = new int[line.length];
		int[] map = new int[10];
		int ans = 0;
		for(int i = 0; i < line.length; i++) {
			list[i] = Integer.parseInt(line[i]);
		}
		for(int i = 0; i <list.length; i++) {
			if(list[i] == 6 || list[i] == 9) {
				map[6] += 1;
			}else {
				map[list[i]] += 1;
			}
		}
		if(map[6]%2 == 1) {
			map[6] = (map[6] / 2)+1;
		}else {
			map[6] = (map[6] / 2);
		}
		
		for(int i = 0; i < 9; i++) {
			ans = Math.max(ans, map[i]);
		}
		System.out.println(ans);
		
	}
}
