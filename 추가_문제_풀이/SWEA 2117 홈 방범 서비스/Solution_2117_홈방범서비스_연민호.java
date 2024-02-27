package algo240227;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
/**
 * 메모리:87,668KB, 시간:382ms
 * 
 * [문제 해석]
 * NxN
 * 
 * 마름모 모양의 영역에서만 제공 됨
 * 
 * 홈방범 서비스엔 운영 비용이 필요
 * 
 * 서비스 영역의 크기 K
 * 
 * 운영 비용 = K^2 + (k-1)^2
 * 
 * K는 1이상의 정수
 * 
 * 도시를 벗어난 영역에 서비스 제공시에도 운영 비용은 변경X
 * 
 * 홈방범서비스를 제공받는 집들은 각각 M의 비용 지불 가능
 * 
 * 보안회사는 손해를 보지 않는 선에서 최대한 많은 집에 홈방범 서비스를 제공하려 함.
 * 
 * 도시의 크기 N, 하나의 집이 지불할 수 있는 비용 M, 도시정보 주어짐
 * 
 * 목표
 * 손해보지 않고 최대한 많은 집들에 홈방범서비를 제공하는 영역을 찾고
 * 그 때의 홈방범 서비스를 제공받는 집들의 수를 출력
 * 
 * 집 : 1
 * 나머지 : 0
 * 
 * 최소 1개의 집이 존재
 * 
 * [문제 해결에 대한 고민]
 * 운영 비용은 K에 대해 변하지 않음
 * => 미리 운영 비용을 구해놓고 사용 가능
 * 
 * NxN의 N에 대해 중앙에서 탐색을 했을 때, 모든 도시를 포함하기 위한 서비스 영역의 크기(K)는
 * N이 홀수인 경우, N
 * N이 짝수인 경우, N+1
 * 따라서, 짝수인 경우를 고려해 탐색을 진행 시, 서비스영역의 크기(K)를 N+1까지만 고려하여 탐색하면 됨
 * 
 * 
 * [문제 해결 프로세스]
 * 0. 운영역역의 크기별 운영 비용을 미리 계산해놓기
 * 1. 모든 좌표를 BFS탐색의 시작점으로 선택하여 BFS 탐색
 *   레벨별 탐색
 *   level(K) : 1 ~ N+1 탐색
 *  a) 1레벨부터 탐색하며 각 레벨의 탐색이 끝났을 때, 누적된 비용과 운영비용을 비교
 *  b) 누적된 비용 >= 운영비용 인 경우, 현재 서비스 중인 집의 수가 최댓값이라면 갱신
 *          
 * 
 * [시간 복잡도]
 * 각 정점을 시작점으로 N+1만큼의 거리까지 탐색을 수행
 * 최대 정점의 개수 : 20x20 = 400개
 * 각 정점당 탐색 횟수 : N=20인 경우, K=21일 때, 모든 집을 포함할 수 있으므로
 * 각 점마다 대략 41x41 = 1,681 의 탐색 수행
 * 400 x 841 = 672,400
 * 
 * 
 * 
 * @author minho
 *
 */
public class Solution_2117_홈방범서비스_연민호 {
    //상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
     
    static int N, M;        //도시의 크기, 하나의 집이 지불할 수 있는 비용 M
     
    static int[][] map;     //집 정보
     
    static int maxCnt;  //최대 홈방범 서비스 가능 집의 수
     
    static int[] squares = new int[22];
    static int[] costs = new int[22];		//운영영역의 크기별 운영 비용	
    //costs[3] : 운영영역(K) 가 3일 때의 운영 비용
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        //step 0.운영역역의 크기별 운영 비용을 미리 계산해놓기
        for(int i=1; i<=21; i++) {
            squares[i] = i*i;
        }
        for(int K=1; K<=21; K++) {
            costs[K] = squares[K] + squares[K-1];
        }
        
        
        
         
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            maxCnt = 1; //최소 1은 정답이 나오므로 1로 초기화
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
             
            map = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    bfs(i, j);
                }
            }
            
            sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
        }
        System.out.println(sb);
    }
 
    private static void bfs(int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
         
        //시작점 넣기
        q.offer(new int[] {sr,sc});
        visited[sr][sc] = true;
         
        int K = 1;  //운영 영역의 크기(depth)
         
        int totalM=0;   //현재까지 포함된 구역의 집에서 낼 수 있는 비용
        int cnt=0;      //현재 탐색한 구역에 포함된 집의 수

        while(!q.isEmpty()) {
            int size=q.size();
            while(size-- > 0) {
                int[] pos = q.poll();
                int r = pos[0];
                int c = pos[1];
                
                //현재 좌표가 집이라면 낼 수 있는 비용에 M 누적, 집의 수 cnt
                if(map[r][c]==1) {
                    totalM+=M;
                    cnt++;
                }
                
                for(int dir=0; dir<4; dir++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
                     
                    if(nr<0 || nr>=N || nc<0 || nc>=N) continue;//경계 벗어남
                    if(visited[nr][nc]) continue;				//이미 방문
                     
                    q.offer(new int[] {nr,nc});
                    visited[nr][nc] = true;
                }
            }
            //각 depth 탐색이 끝났을 때
            //누적된 비용이 운영비용보다 크거나 같은 경우,
            if(totalM >= costs[K]) { 
            	//현재 서비스 중인 집의 수가 최댓값이라면 갱신
            	maxCnt = Math.max(maxCnt, cnt);
            }
            //N+1까지의 탐색을 마쳤다면 멈춤 ( N이 짝수인 경우 K를 N+1까지 시도해봐야 모든 범위를 포함할 수 있음)
            if(K==N+1) break;
            
            K++;
        }
    }
}