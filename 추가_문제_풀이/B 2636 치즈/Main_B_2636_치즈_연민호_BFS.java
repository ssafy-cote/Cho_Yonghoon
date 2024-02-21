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
 * 메모리:15848KB, 시간124ms
 */
public class Main_B_2636_치즈_연민호_BFS {

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
        	
        	//step 02.치즈 녹이기
        	meltCheese();
        	
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
				meltList.add(new Node(r,c));	//녹일 치즈 목록에 추가
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
    
    //치즈 녹이기
    private static void meltCheese() {
        for(Node n : meltList) {
        	if(map[n.r][n.c]==0) continue;	//이미 처리된 치즈
        	
    		cheeseCnt--;	//남은 치즈개수 -1
    		map[n.r][n.c] = 0;	//녹는 처리 
        }
    }
}    