package algo0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_13023_ABCDE_조용훈 {
	
	static int N, M;
	static ArrayList<Integer>[] map;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {	// 19984kb 메모리, 324ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		map = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			map[i] = new ArrayList<>();
		}
		visit = new boolean[N];
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int a = Integer.parseInt(stringTokenizer.nextToken());
			int b = Integer.parseInt(stringTokenizer.nextToken());
			map[a].add(b);
			map[b].add(a);
		}
		
		for(int i = 0; i < N; i++) {
			if(dfs(i, 0)) {
				System.out.println(1);
				System.exit(0);
			}
		}
		System.out.println(0);
	}
	
	static boolean dfs(int start, int index) {
		if(index == 5) {
			return true;
		}
		for(int num : map[start]) {
			if(!visit[num]) {
				visit[num] = true;
				if(dfs(num, index + 1)) {
					return true;
				}
				visit[num] = false;
			}
		}
		return false;
	}
}
