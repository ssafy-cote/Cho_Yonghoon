package algo0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
// 15196 kb
// 160 ms
public class Main_B_2636_치즈_김민지 {
	static int n, m, cheese, tempCheese, time;
	static boolean[][] grid, visited;
	static Deque<int[]> q;
	static int[] dxs = {-1, 0, 1, 0};
	static int[] dys = {0, 1, 0, -1};

	static boolean inRange(int x, int y) { // 범위체크
		return 0<=x && x<n && 0<=y && y<m;
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int x = current[0];
			int y = current[1];
			
			for(int i=0; i<4; i++) {
				int nx = x+dxs[i];
				int ny = y+dys[i];
				
				if(inRange(nx, ny) && !visited[nx][ny] ) { // 범위에 있고, 방문 안했고
					visited[nx][ny] = true; 
					if(!grid[nx][ny]) q.add(new int[] {nx, ny}); // 공기면 큐에 넣어줌
					else {
						grid[nx][ny] = false;
						cheese--; //치즈 개수 빼주고
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		grid = new boolean[n][m];
		q = new ArrayDeque<int[]>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true: false;
				if(grid[i][j]) cheese++; // 치즈의 총 개수 세줌
			}
		}
		tempCheese = cheese;
		
		while(true) {
			time++; // 시간 증가
			visited = new boolean[n][m];
			
			q.add(new int[] {0, 0});
			visited[0][0] = true;
			bfs();

			if (cheese==0) break;
			tempCheese = cheese;
		}
		System.out.println(time); // 시간
		System.out.println(tempCheese); // 치즈 출력
	}
}