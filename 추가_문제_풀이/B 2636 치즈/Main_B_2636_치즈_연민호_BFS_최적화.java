package algo240221;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 최적화
 * - 녹일 치즈목록을 모아놓고 한 번에 녹이는 것이 아니라
 * - 녹일 치즈 칸을 발견하자마자 바로 녹임
 *     => 녹일 수있는 이유는 visited 배열을 관리하기 때문에
 *     		탐색과정에서 녹여서 다른 좌표로부터 탐색으로 결과가 달라질 일이 없음
 *     
 * 메모리:15512KB, 시간 120ms
 */
public class Main_B_2636_치즈_연민호_BFS_최적화 {

	//사방탐색 델타 값(상하좌우)
	static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static int H, W;	//배열 크기
    static int[][] map;	//
    
    static boolean[][] visited;	//방문 체크
    
    static List<Node> meltList = new ArrayList<>();	//녹일 치즈 정보 저장
    
    static int cheeseCnt;	//남은 치즈 칸수
    
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
        
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        map = new int[H][W];
        cheeseCnt = 0;
        for(int i = 0; i < H; i++) {
        	st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheeseCnt++;
            }
        }
        
        int prevCheeseCnt;	//녹기 바로 전 치즈 개수 저장 변수
        int time=0;	//현재까지 걸린 시간
        while(true) {
        	time++;
        	
        	prevCheeseCnt = cheeseCnt;	//녹기 바로 전 치즈 개수 저장
        	
        	meltList.clear();	//녹일 치즈 목록 초기화
        	
        	//녹일 치즈 탐색
        	visited = new boolean[H][W];
        	bfs();
        	
        	
        	//치즈 녹이기 후 남아있는 치즈의 개수 0개라면 종료
        	if(cheeseCnt==0) break;
        }
        
        System.out.println(time);
        System.out.println(prevCheeseCnt);
    }
    
    //공기 밀접한 치즈 탐색 및 녹이기
    public static void bfs() {
        Queue<Node> q = new ArrayDeque<>();	//탐색 위치 저장 큐
        q.offer(new Node(0, 0));
        
        while(!q.isEmpty()) {
            Node n = q.poll();	//탐색할 위치 정보 받아오기
            int r = n.r;
            int c = n.c;
            
    		if(r<0 || r>=H || c<0 || c>=W) continue;	//경계 벗어남
    		if(visited[r][c]) continue;					//이미 방문
    		
			visited[r][c] = true;	//방문 체크
			
			//현재 좌표의 값이 치즈인 경우
			if(map[r][c]==1) {
				map[r][c] = 0;	//녹임
				cheeseCnt--;
			}
			//현재 좌표의 값이 공기인 경우
			else{
				//현재 좌표 기준 사방을 기준으로 탐색
				for(int d=0; d<4; d++) {
					//해당 방향 다음 위치 좌표
					int nr = r+dr[d];
					int nc = c+dc[d];
					
					q.offer(new Node(nr, nc));
				}
			}
        }
    }
}    