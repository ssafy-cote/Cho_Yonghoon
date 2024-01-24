package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15683_감시 {
	static int n, m;
	static int[][] map;
	static int[] dx = {};
	static int[] dy = {};
	static List<CCTV> cctv;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		cctv = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < m; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (0 < temp && temp < 6) {
					cctv.add(new CCTV(i, j, temp, 1));
				}
				map[i][j] = temp;
			}
		}
		
		dfs(0);
		
		System.out.println(ans);

	}

	static void dfs(int index) {
		if (index == cctv.size()) {
			int result = check();
			if (ans > result)ans=result;
			return;
		}
		
		for (int i = 1; i <= 4; i++) {
			cctv.get(index).dir = i;
			int nindex = index + 1;
			dfs(nindex);
		}
		
	}
	static int check() {
		int result = 0;
		int[][] map2 = map.clone();
		for(int i = 0; i < cctv.size(); i ++) {
			if(cctv.get(i).num == 1) {
				see1(cctv.get(i), map2);
			}else if(cctv.get(i).num == 2) {
				see2(cctv.get(i), map2);
			}else if(cctv.get(i).num == 3) {
				see3(cctv.get(i), map2);
			}else if(cctv.get(i).num == 4) {
				see4(cctv.get(i), map2);
			}else if(cctv.get(i).num == 5) {
				see5(cctv.get(i), map2);
			}
		}
		return result;
	}
static void see1(CCTV cctv, int[][] map2) {
	if(cctv.dir == 1) {
		for(int i = 1; i < cctv.x; i++) {
			map[cctv.x-i][cctv.y] = -1;
		}
	}else if(cctv.dir == 2) {
		for(int i = 1; i < m - cctv.y; i++) {
			map[cctv.x][cctv.y+i] = -1;
		}
	}else if(cctv.dir == 3) {
		for(int i = 1; i < n - cctv.x; i++) {
			map[cctv.x+i][cctv.y] = -1;
		}
	}else if(cctv.dir == 4) {
		for(int i = 1; i < cctv.y; i++) {
			map[cctv.x][cctv.y-i] = -1;
		}
	}
}
static void see2(CCTV cctv, int[][] map2) {
	if(cctv.dir == 1) {
		
	}else if(cctv.dir == 2) {
		
	}else if(cctv.dir == 3) {
		
	}else if(cctv.dir == 4) {
		
	}
}
static void see3(CCTV cctv, int[][] map2) {
	if(cctv.dir == 1) {
		
	}else if(cctv.dir == 2) {
		
	}else if(cctv.dir == 3) {
		
	}else if(cctv.dir == 4) {
		
	}
}
static void see4(CCTV cctv, int[][] map2) {
	if(cctv.dir == 1) {
		
	}else if(cctv.dir == 2) {
		
	}else if(cctv.dir == 3) {
		
	}else if(cctv.dir == 4) {
		
	}
}
static void see5(CCTV cctv, int[][] map2) {
	if(cctv.dir == 1) {
		
	}else if(cctv.dir == 2) {
		
	}else if(cctv.dir == 3) {
		
	}else if(cctv.dir == 4) {
		
	}
}

	static class CCTV {
		int x;
		int y;
		int num;
		int dir;

		public CCTV(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}

}
