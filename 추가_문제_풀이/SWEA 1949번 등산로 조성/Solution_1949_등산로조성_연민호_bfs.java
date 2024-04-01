package algo240401;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
/**
 * 메모리:85,800kb, 시간:288ms
 * 
 * [문제 해석]
 * NxN
 * 
 * 최대한 긴 등산로 만들기
 * 
 * 각 숫자는 지형의 높이를 의미
 * 
 * 등산로 만드는 규칙
 * 1. 가장 높은 봉우리에서 시작
 * 2. 등산로는 높은 곳에서 낮은 곳으로 가로 또는 세로 방향이어야 함.
 *  => 같은 높이나 대각선 불가능
 * 3. 한 곳을 최대 K깊이만큼 지형을 깎는 공사를 할 수 있음.
 * 
 * NxN 크기의 지도와 최대공사가능깊이 K가 주어짐
 * 
 * 만들 수 있는 가장 긴 등산로를 찾아 그 길이를 출력하는 프로그램 만들기
 * 
 * [해결 프로세스]
 * 
 * 1. 입력값을 받으며 가장 높은 높이를 구하기
 * 2. 가장 높은 높이의 좌표정보 저장
 *      => 가장 높이가 높은 좌표의 높이도 깎을 수 있다는 것을 고려
 * 3. 좌표하나를 골라 해당 좌표를 공사하지 않는 것부터 K 만큼 공사하는 것으로 설정
 *      => 지형을 깎아 높이를 1보다 작게 만드는 것도 가능 (0까지만 시도해보면 됨)
 *      => 다른 곳에선 1보다 작은 높이가 없기 떄문에 0이나 -1이나 같은 결과가 나오기 때문
 * 
 * 4. 가장 높은 높이의 좌표들을 큐에 넣고 레벨별 BFS탐색을 통해 해당 탐색의 길이가 최대값이라면 갱신
 *      함수의 가장 윗부분에서 등산로 길이 갱신 코드
 *      
 * 		등산로 불가 조건
 *      - 경계 벗어남
 *      - 현재 높이 <= 다다음 좌표
 * 
 * [시간 복잡도]
 * ??
 * 
 * @author minho
 */
public class Solution_1949_등산로조성_연민호_bfs {
    //상하좌우 델타값
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
     
    static int N,K; //지형의 길이, 최대 가능 가공 높이
     
    static int[][] map; //지형의 높이 정보
 
    static int maxRoad; //최대 등산로 길이
     
    static List<int[]> maxHeightList = new ArrayList<>();
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new  StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            int maxHeight = 0;
            map = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new  StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }
             
            //step 01. 가장 높은 높이의 좌표값 구하기
            maxHeightList.clear();
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j]==maxHeight) maxHeightList.add(new int[] {i, j});
                }
            }
             
            //step 02. 좌표하나를 골라 해당 좌표를 공사하지 않는 것부터 K 만큼 공사하는 것으로 설정
            maxRoad = 0;    //최대 높이 초기화
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    int height = map[i][j];  //현재 높이
                    for(int minus=0; minus<=K && height-minus>=0; minus++) {
                        map[i][j] = height-minus;    //깎은 높이를 적용
                         
                        //step 03. 가장 높은 높이의 좌표기준 레벨별 bfs 탐색을 통해 해당 탐색의 길이가 최대값이라면 갱신
                        bfs();
                    }
                    //탐색이 끝난 후 원래 높이로 되돌리기
                    map[i][j] = height;
                }
            }
            sb.append('#').append(tc).append(' ').append(maxRoad).append('\n');
        }
        System.out.println(sb);
    }
 
    /**
     * 
     */
    private static void bfs() {
    	Queue<int[]> q = new ArrayDeque<>();
    	for(int[] position : maxHeightList) {
            q.offer(new int[] {position[0], position[1]});
        }
    	int length = 0;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		while(size-- > 0) {
    			int[] cur = q.poll();
    			int r = cur[0];
    			int c = cur[1];
    			
    			for(int dir=0; dir<4; dir++) {
    				//다음 좌표
    				int nr = r+dr[dir];
    				int nc = c+dc[dir];
    				
    				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;	//경계 벗어남
    				if(map[r][c] <= map[nr][nc]) continue;			//현재 높이 <= 다음 좌표 높이
    				
    				q.offer(new int[] {nr,nc});
    			}
    		}
    		length++;
    	}
    	maxRoad = Math.max(maxRoad, length);
    }
}