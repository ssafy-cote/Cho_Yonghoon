package algo240220;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * 
 * 메모리:14928KB, 시간:1424ms
 * 
 * @author SSAFY
 *
 */
public class Main_B_1987_알파벳_연민호_Map {
	static int R, C;	//보드 행렬 크기
	static char[][] board;	//보드
	
	//상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	//알파벳 사용 여부 체크를 위해
	//isUsed.get('A')  - true면 'A' 사용 중
	static Map< Character, Boolean> isUsed = new HashMap<>();
	
	
	//최대 이동 횟수 저장
	static int maxMove;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//보드 값 입력 받기(편의상 0~R-1, 0~C-1 사용)
		board = new char[R][C];
		for(int i=0; i<R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		//Map에 알파벳 사용여부 설정
		for(char i='A'; i<='Z'; i++) {
			isUsed.put(i, false);
		}
		
		maxMove = 0;
		
		isUsed.put(board[0][0], true);	//시작 위치에 대한 알파벳 체크
		visit(0, 0, 1);
		
		System.out.println(maxMove);
	}

	/**
	 * 현재 좌표(r,c)까지 이동한 칸 수가 최댓값이라면 갱신하고
	 * 현재좌표(r,c) 기준 사방 중 이동가능한 좌표로 이동 후, 다음 좌표 이동은 재귀로 넘김 
	 * @param r
	 * @param c
	 * @param cnt 현재까지 말이 지나온 이동 칸 수
	 */
	private static void visit(int r, int c, int cnt) {
		//현재좌표까지의 이동횟수가 최대 이동횟수라면 갱신(언제가 최대인지 모르므로 갱신을 계속함)
		maxMove = Math.max(maxMove, cnt);
		
		//사방 탐색
		for(int d=0; d<4; d++) {
			//해당 방향으로 이동했을 때 좌표
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=R || nc<0 || nc>=C) continue;	//경계 벗어남
			if( isUsed.get( board[nr][nc] ) ) continue;		//사용한 알파벳
			
			isUsed.put( board[nr][nc] , true );	//알파벳 사용 체크
			visit(nr, nc, cnt+1);				//다음 좌표 방문
			isUsed.put( board[nr][nc] , false);	//현재 위치 알파벳 사용 여부 되돌리기
		}
	}
}