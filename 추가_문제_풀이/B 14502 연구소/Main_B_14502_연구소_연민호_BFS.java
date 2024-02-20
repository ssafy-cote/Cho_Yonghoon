package algo240220;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:68332KB, 시간:280ms
 */
public class Main_B_14502_연구소_연민호_BFS {
	//상하좌우 델타값 저장
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
	
    static int N, M;	//가로 세로 크기
    
    static int[][] map;	//연구소 맵
    static int[][] spreadMap;	//세워진 벽에서 바이러스 퍼뜨릴 때 사용

    static List<Node> virusInfo = new ArrayList<>();	//바이러스 위치정보 저장
    
    static int maxSafeArea = 0;	//최대 안전영역 개수 저장

    static class Node{
    	int r,c;
    	public Node(int r, int c) {
    		this.r = r;
    		this.c = c;
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        //연구소 맵 정보 입력
        map = new int[N][M];
        spreadMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virusInfo.add(new Node(i, j)); //바이러스 위치정보 저장
            }
        }
        combination(0, 0);
        
        System.out.println(maxSafeArea);
    }

    /**
     * 벽 한개 세우고 다음 벽은 재귀 호출
     * @param cnt
     * @param start
     */
	private static void combination(int cnt, int start) {
		//step 01. 벽 세개를 세웠다면
		if(cnt==3) {
			//step 02. 바이러스 퍼뜨리기
			spreadVirus();
			
			//step 03. 안전영역 계산하기
			int safeArea = getSafeArea();
			
			//step 04. 안전영역 최댓값 갱신
			maxSafeArea = Math.max(maxSafeArea, safeArea);
			return;
		}
		
		for(int i=start; i<N*M; i++) {
			int r = i/M;	//행의 좌표
			int c = i%M;	//열의 좌표
			
			if(map[r][c]!=0) continue;	//빈 칸이 아닌 경우 다음 좌표
			
			map[r][c] = 1;	//벽 세우기
			combination(cnt+1, i+1);	//다음 세울 벽은 재귀에 넘기기
			map[r][c] = 0;	//다음 탐색 위해 되돌리기
		}
	}

	/**
	 * BFS를 통해 바이러스 시작지 부터 퍼뜨리기
	 */
	private static void spreadVirus() {
		//현재 map 정보 spreadMap 에 깊은 복사
		arrayCopy();
		
		Queue<Node> q = new ArrayDeque<>();
		for(Node n : virusInfo) q.offer(n);	//모든 바이러스 위치정보를 큐에 담기
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			
			//사방탐색
			for(int d=0; d<4; d++) {
				//다음 위치 좌표 얻어오기
				int nr = n.r + dr[d];
				int nc = n.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;	//경계 벗어
				if(spreadMap[nr][nc] != 0) continue;			//빈 칸 아님
					
				//바이러스 퍼뜨리기
				spreadMap[nr][nc] = 2;
				q.offer(new Node(nr, nc));	//퍼진 바이러스 기준으로 또 사방으로 바이러스 퍼질 수 있으므로 저장
			}
		}
	}

	private static void arrayCopy() {
		for(int i=0; i<N; i++) {
			System.arraycopy(map[i], 0, spreadMap[i], 0, M);
		}
	}
	
	/**
	 * spreadMap 의 안전영역 개수 반환
	 * @return
	 */
	private static int getSafeArea() {
		int safeArea = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(spreadMap[i][j]==0) safeArea++;
			}
		}
		return safeArea;
	}
}