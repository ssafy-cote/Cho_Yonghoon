package algo240213;
import java.util.Scanner;



public class Main_B_2615_오목_연민호_경계체크 {

	static int N = 19;

	public static void main(String[] args){
		Scanner s = new Scanner(System.in);

		int[][] map = new int[N+1][N+1];	//바둑판

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] = s.nextInt();
			}
		}

		//우상, 우, 우하, 하
		int[] dr = { -1, 0, 1, 1};
		int[] dc = {  1, 1, 1, 0};	


		//step 01. 바둑알 탐색
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				int cur = map[i][j];	//현재 바둑알
				if(cur!=0) {

					//step 02. 오목 탐색
					//하, 우, 우상, 우하
					for(int d=0; d<4; d++) {

						//이전 현재 위치가 오목의 시작점인 경우만 탐색을 이어감
						//이전 위치 좌표
						int prevR = i-dr[d];
						int prevC = j-dc[d];

						//경계 내에 있고 같은 색의 바둑알인 경우, 오목의 시작점이 아니므로 다음 방향 탐색
						if(isInRange(prevR, prevC) && cur==map[prevR][prevC] ) continue;


						//d=0인경우 - 발견한 바둑알 기준 상하 탐색
						//현 탐색 위치
						int r = i;
						int c = j;
						int cnt=1; //연속한 바둑알 개수

						while(true) {
							//다음 위치로 한 칸 이동
							r += dr[d];
							c += dc[d];

							if(!isInRange(r,c)) break;	//경계를 벗어나는 경우 멈춤
							if(map[r][c]!=cur) break;	//같은 바둑알이 아닌 경우 멈춤

							//연속된 바둑알의 개수가 5개를 초과한다면 탐색을 멈춤
							if(++cnt>5) break;	//바둑알 개수 cnt
						}
						
						//현재 방향의 탐색을 마침
						//cnt 값이 5라면? 정답 출력 후 종료
						if(cnt==5) {
							System.out.println(cur);
							System.out.println(i+" "+j);//오목 시작 좌표
							return;	//종료
						}
					}
				}
			}
		}
		//무사히 반복문을 끝냈다면? 오목을 발견하지 못했으므로 0출력
		System.out.println(0);
	}
	/**
	 * (r,c)좌표가 경계내에 있다면 true 반환
	 * @param r
	 * @param c
	 */
	private static boolean isInRange(int r, int c) {
		return r>=1 && r<=N && c>=1 && c<=N;
	}
}

