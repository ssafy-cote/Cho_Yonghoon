package com.edu.ssafy_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 첫번째 열 = 빵집의 가스관
// 마지막 열 = 원웅이의 빵집
// 건물있으면 파이프 설치 안됨
// 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선

// 메모리 : 188280kb
// 시간 : 540ms
public class Main_B_3109_빵집_김민지 {
	static char[][] grid;
	static boolean[][] visited;
	static int R, C, result;
	static boolean inRange(int x, int y) { // 범위 체크 함수
		return 0<=x && x<R && 0<=y && y<C;
	}
	static boolean start(int x, int y) {
		int[] dxs = {-1, 0, 1}; // 우선 순위에 따라 방향 설정
		int[] dys = {1, 1, 1};
		if(y==C-1) {
			return true;
		}
		for(int i=0; i<3; i++) {
			int nx = x+dxs[i];
			int ny = y+dys[i];
			if(inRange(nx, ny) && grid[nx][ny]=='.' && !visited[nx][ny]) {
				visited[nx][ny] = true;
				if(start(nx, ny)) return true;
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new char[R][C];
		visited = new boolean[R][C]; // 방문배열 생성
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				grid[i][j] = s.charAt(j);
				if(grid[i][j]=='x') visited[i][j] = true; // 벽은 방문처리
			}
		}
		
		for(int i=0; i<R; i++) {
			visited[i][0] = true;
			if(start(i, 0)) result++; // 각 행별로 첫번째 열에서 파이프 연결 시작
		}
		System.out.println(result);
	}
}