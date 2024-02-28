package algo240228;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 메모리:25,092KB, 시간:184ms
 * 
 * 
 * [문제 해석]
 * 
 * NXN
 * 
 * 각 cell엔 1개의 코어 또는 1개의 전선 올 수 있음.
 * 
 * 전선 교차X
 * 
 * 가장자리에 위치한 Core는 전원이 연결된 것으로 간주
 * 
 * 목표
 * => 최대한 많은 Core에 전원을 연결했을 때, 전선 길이의 합
 * 		같은 수의 Core를 사용했다면 전선길이의 합이 최소가되는 값 구하기
 * 
 * 
 * [문제 해결 프로세스]
 * 
 * 1. 코어하나 방향을 정한 뒤, 해당 방향으로 바로 설치해봄
 * 		=> 설치불가한 경우 다음 방향의 설치(가지치기)
 * 
 * 2. 다음 코어의 방향 설정은 재귀로 넘김
 * 
 * 백트래킹 방식
 * 
 * 
 * 재귀 설계
 * 
 * 유도 파트(반복)
 * 
 * 1. 코어의 하나의 방향을 정하기(설치 안하는 것 포함)
 * 
 * 2. 해당 방향으로 설치가능여부 체크
 * 
 * 3. 설치 가능하다면 해당 방향으로 설치 후
 * 
 * 4. 다음 코어 설치는 재귀로 넘김
 * 
 * 5. 다음 방향 설치를 위해 설치 되돌림
 * 
 * 기저 조건
 * 모든 코어의 설치가 완료되었을 때
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_1767_프로세서연결하기_연민호_백트래킹 {
	//상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0 , -1, 1};

	static int maxCoreCnt;		//가장 자리 제외한 Core 전원 연결 수
	static int minLengthSum;	//사용한 최소 전선의 길이

	static int N;   //맵 크기

	static int[][] map;

	static List<Core> cores = new ArrayList<>();

	static class Core{
		int r,c;
		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Core [r=" + r + ", c=" + c + "]";
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			cores.clear();
			N = Integer.parseInt(br.readLine().trim());

			map = new int[N+1][N+1];

			for(int i=1; i<=N; i++) {
				StringTokenizer st= new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					//전선 방향 설정할 Core 정보 추가 가장자리 제외
					if(map[i][j]==1 && i!=1 && i!=N && j!=1 && j!=N) {
						cores.add(new Core(i,j));
					}
				}
			}

//			System.out.println("코어 목록");
//			System.out.println(cores);
			
			maxCoreCnt = 0;		//최대 사용 코어의 개수 초기화			
			minLengthSum = Integer.MAX_VALUE;	//최소 전선길이의 합 충분히 큰 값으로 초기화

			setCore(0, 0, 0);

			sb.append('#').append(tc).append(' ').append(minLengthSum).append('\n');
		}
		System.out.println(sb);
	}
	/**
	 * idx번째 코어의 방향을 정해 설치 후, 다음 코어 설치는 재귀로 넘김
	 * @param idx	설치할 코어의 인덱스
	 * @param useCnt	 사용한 코어의 인덱스	
	 * @param lengthSum		사용한 전선길이의 합
	 */
	private static void setCore(int idx, int useCnt, int lengthSum) {
		//모든 코어의 설치가 끝남.
		if(idx==cores.size()) {
			//사용한 코어개수가 최대라면 갱신
			//사용한 코어개수가 동일하고 전선의길이가 작다면 갱신
			if(useCnt > maxCoreCnt || (useCnt==maxCoreCnt && lengthSum < minLengthSum)) {
				maxCoreCnt = useCnt;
				minLengthSum = lengthSum;
			}
			return;
		}

		Core core = cores.get(idx);	//설치할 코어의 정보

		setCore(idx+1, useCnt, lengthSum);	//idx번째 코어를 사용하지 않은 경우
		for(int dir=0; dir<4; dir++) {
			//core를 dir 방향으로 설치했을 때 필요한 전선길이를 반환
			int length = getSetLength(core , dir);

			if(length==0) continue;	//설치 불가한 경우 다음 방향시도

			//1. 해당 방향으로 length만큼의 전선 설치
			setLine(core, dir, length, -1);
			//2. 다음 코어의 전선설치는 재귀로 넘김
			setCore(idx+1, useCnt+1, lengthSum+length);
			//3. 다음 방향 시도를 위해 설치된 전선 제거
			setLine(core, dir, length, 0);
		}
	}
	
	/**
	 * 코어 기준 해당 방향으로 length 만큼의 체크
	 * @param core	 기준 코어
	 * @param dir	 설치할 전선 방향
	 * @param length 체크해야할 길이
	 * @param value  체크할 값
	 */
	private static void setLine(Core core, int dir, int length, int value) {
		int r=core.r;
		int c=core.c;
		for(int i=0; i<length; i++) {
			r += dr[dir];
			c += dc[dir];
			map[r][c] = value;
		}
	}

	/**
	 * core를 dir 방향으로 설치했을 때 필요한 전선길이를 반환, 설치 불가하면 0반환
	 * @param core
	 * @param dir
	 * @return
	 */
	private static int getSetLength(Core core, int dir) {
		int r = core.r;
		int c = core.c;

		int length = 0;	//설치에 필요한 전선의 길이
		while(true) {
			//현재 방향으로 이동
			r += dr[dir];
			c += dc[dir];

			//경계를 벗어나는 경우,
			//설치 가능하므로 현재까지의 전선 길이 반환
			if(r<1 || r>N || c<1 || c>N) return length;

			//0이 아닌 경우(전선 or 코어)
			//설치 불가하므로 0반환
			if(map[r][c]!=0) return 0;
			
			length++;	//0인 경우 설치 길이 증가
		}
	}
	
	private static void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}