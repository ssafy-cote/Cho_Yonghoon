package algo240220;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/**
 * 
 * 메모리:12120KB, 시간:800ms
 * 
 * @author SSAFY
 *
 */
public class Main_B_1987_알파벳_연민호_비트마스킹 {
	static int R, C;	//보드 행렬 크기
	static char[][] board;	//보드
	
	//상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
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
		
		maxMove = 0;
		
		visit(0, 0, 1, 1<<(board[0][0]-'A'));
		
		System.out.println(maxMove);
	}

	/**
	 * 현재 좌표(r,c)까지 이동한 칸 수가 최댓값이라면 갱신하고
	 * 현재좌표(r,c) 기준 사방 중 이동가능한 좌표로 이동 후, 다음 좌표 이동은 재귀로 넘김 
	 * @param r
	 * @param c
	 * @param cnt 현재까지 말이 지나온 이동 칸 수
	 * @param isUsed 알파벳 사용정보에 대한 비트마스킹 정보 
	 * 			알파벳의 개수는 26개이므로 (isUsed & 1 << 2) != 0  라면 C 사용 중
	 */
	private static void visit(int r, int c, int cnt, int isUsed) {
		//현재좌표까지의 이동횟수가 최대 이동횟수라면 갱신(언제가 최대인지 모르므로 갱신을 계속함)
		maxMove = Math.max(maxMove, cnt);
		
		if(cnt==26) return;
		
		//사방 탐색
		for(int d=0; d<4; d++) {
			//해당 방향으로 이동했을 때 좌표
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=R || nc<0 || nc>=C) continue;			//경계 벗어남
			if( (isUsed & 1<< board[nr][nc] - 'A') !=0 ) continue;	//사용한 알파벳
			
			visit(nr, nc, cnt+1, isUsed | 1<<board[nr][nc]-'A');				//다음 좌표 방문
		}
	}
}