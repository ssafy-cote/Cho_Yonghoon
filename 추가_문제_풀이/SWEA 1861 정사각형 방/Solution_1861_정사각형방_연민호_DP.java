package algo240207;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:31,008kb, 시간:255ms
 * 
 * f(i,j) - (i,j)를 시작점으로 했을 때 이동횟수 
 * 
 * 3 4 8
 * 2 1 5
 * 6 9 7
 * 
 * f(i,j)의 값을 구하기 위해 
 * 
 * @author minho
 *
 *
 */
public class Solution_1861_정사각형방_연민호_DP {

	static int[][] map;
	
	//dp[i][j] - (i,j)를 시작점으로 이동가능한 횟수
	static int[][] dp;
	
	static int N;
	
	//상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			StringBuilder sb = new StringBuilder();
			int T = Integer.parseInt(br.readLine());
			for(int tc=1; tc<=T; tc++) {
				
				N = Integer.parseInt(br.readLine());
				map = new int[N][N];
				for(int i=0; i<N; i++) {
					StringTokenizer st = new StringTokenizer(br.readLine());
					for(int j=0; j<N; j++) {
						map[i][j] = Integer.parseInt(st.nextToken());
					}
				}
				
				dp = new int[N][N];
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						dp[i][j] = f(i,j);
					}
				}
				
				
				//dp배열에서 값이 가장 큰 것중 번호가 작은 것 찾기
				int maxMove = 0;	//최대 이동횟수
				int num = 0;		//번호
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(dp[i][j] > maxMove 
								|| (dp[i][j]==maxMove && num > map[i][j])) {
							maxMove = dp[i][j];
							num = map[i][j];
						}
					}
				}
				sb.append('#').append(tc).append(' ').append(num).append(' ').append(maxMove).append('\n');
				
			}
			
			System.out.println(sb);
			
	}

	/**
	 * 
	 * @param r
	 * @param c
	 * @return (r,c)를 시작점으로 하는 이동횟수 반환
	 */
	private static int f(int r, int c) {
		
		//이미 구해진 상황에서 여러번 호출되는 것을 방지하기 위해 값이 채워져있다면 바로 반환
		if(dp[r][c]>0) return dp[r][c];
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;	//경계 벗어남
			if(map[r][c]+1 != map[nr][nc]) continue;	//1차이가 아님(이동불가)
				
			//이동 가능한 경우
			//(nr,nc)를 시작점으로 두었을 때보다 +1 더 이동가능하므로 해당 값을 dp[r][c]에 담아줌
			return f(nr, nc)+1;
		}
		
		//네 방향에 갈 곳이 없는 경우 자기 자신 번호 뿐이 이동할 수 없으므로 1리턴
		return 1;
	}

}