package algo240229;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 메모리:12156KB, 시간:92ms
 * 
 * [문제 해석]
 * 행열 R, C
 * 빈곳	: .
 * 물	: *
 * 돌	: X
 * 비버 굴	: D
 * 고슴도치 위치	:S
 * 
 * 매 분 고슴도치는 현재 칸, 또는 인접한 네 칸 중 하나로 이동
 * 물은 매 분 비어있는 칸으로 확장
 * 	- 물은 인접해있는 비어있는 칸으로 확장(적어도 한 변을 공유 - 사방 의미)
 * 물, 고슴도치는 돌 통과X
 * 고슴도치 물 이동X
 * 물 비버 소굴 이동X
 * 
 * 고슴도치가 비버의 굴로 이동하기 위해 필요한 최소 시간 구하기
 * 
 * 조건, 고슴도치는 물이 찰 예정인 칸으로 이동 X
 * 
 * [문제 해결 프로세스]
 * 
 *  step 01. 물 퍼뜨리기 (BFS 레벨 탐색)
 *  	- 물 퍼뜨리는 조건
 *  		경계X, 돌(X)X, 비버소굴(D)X
 *  
 *  step 02. 고슴도치 이동 (BFS 레벨 탐색)
 *  	- 고슴도치 이동 조건
 *  		 경계X, 돌(X)X, 물(*)X
 *  
 *  step 03. 1~2 반복 하다가 고슴도치 이동 중 'D' 발견하면 해당 depth 출력
 */
public class Main_B_3055_탈출_연민호 {
	// 상 하 우 좌
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	static int R,C;	//행 열 크기
	
	static char[][] map;	// 맵 정보
	
	static Queue<Node> waterQ = new ArrayDeque<>();	//물 퍼뜨리기 bfs를 위한 큐
	static Queue<Node> hedgehogQ = new ArrayDeque<>();	//고슴도치 이동 bfs를 위한 큐
	
	static class Node{
		int r,c;	//행, 열 정보
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				//물 또는 고슴도치 위치를 기준으로 BFS 탐색을 할 것이므로 좌표 정보 큐에 담아 놓기
				if(map[i][j] == '*') waterQ.offer(new Node(i,j));
				else if(map[i][j] == 'S') hedgehogQ.offer(new Node(i,j));
			}
		}
		
		boolean[][] visited = new boolean[R][C];
		
		int depth = 0;	//고슴도치 이동 시간
		while(true) {
			
			//step 01. 물 퍼뜨리기(BFS) 현재 레벨 만큼만
			int size = waterQ.size();
			while(size-->0) {
				Node water = waterQ.poll();
				int r = water.r;
				int c = water.c;
				
				for(int dir=0; dir<4; dir++) {
					//다음 좌표
					int nr = r+dr[dir];
					int nc = c+dc[dir];
					
					if(!isInRange(nr, nc)) continue;//경계 벗어남
					if(map[nr][nc]=='X') continue;	//돌
					if(map[nr][nc]=='D') continue;	//비버소굴
					if(visited[nr][nc]) continue;	//이미 방문
					
					visited[nr][nc] = true;
					waterQ.offer(new Node(nr,nc));	//큐에 넣기
					map[nr][nc] = '*';				//물로 체크표시
				}
			}

			//step 02. 고슴도치 이동(BFS) 현재 레벨 만큼만
			size = hedgehogQ.size();
			//큐가 비어있는 상태라면 고슴도치가 이동할 방향이 없다는 의미이므로 안전하게 이동 불가 
			if(size==0) break;
			while(size-->0) {
				Node hedgehog = hedgehogQ.poll();
				int r = hedgehog.r;
				int c = hedgehog.c;
				
				//step 03. 고슴도치 이동 중 'D' 발견하면 해당 depth 출력 후 종료
				if(map[r][c] == 'D') {
					System.out.println(depth);
					System.exit(0);
				}
				
				for(int dir=0; dir<4; dir++) {
					//다음 좌표
					int nr = r+dr[dir];
					int nc = c+dc[dir];
					
					if(!isInRange(nr, nc)) continue;//경계 벗어남
					if(map[nr][nc]=='X') continue;	//돌
					if(map[nr][nc]=='*') continue;	//물
					if(visited[nr][nc]) continue;	//이미 방문
					
					hedgehogQ.offer(new Node(nr,nc));	//큐에 넣기
					visited[nr][nc] = true;
				}
			}
			depth++;
		}
		
		//무사히 while문이 끝났다면 고슴도치가 이동 불가하다는 것이므로 KAKTUS 출력
		System.out.println("KAKTUS");
	}

	//경계 내에 있다면 true 반환
	private static boolean isInRange(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
}