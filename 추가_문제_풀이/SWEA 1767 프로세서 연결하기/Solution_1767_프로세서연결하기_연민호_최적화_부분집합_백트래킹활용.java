package algo240228;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리:24,832kb, 시간:130ms
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
 * [최적화]
 * 
 * 부분집합을 통해 
 * 코어 개수가 5인 경우
 * 5C5 로 5개의 설치할 코어를 선택
 * => 각 방향을 설정하여 모두 전선 설치가 완료된다면 정답으로 확정
 * => 만들어지지 않는다면 5C4 시도
 * 
 * 5C4 로 4개의 설치할 코어를 선택
 * => 4개의 코어의 각 방향을 설정하여 전선 설치가 완료된 것 중 
 * 		최소 전선길이를 사용하는 것을 정답으로 확정
 * => 만들어지지 않는다면 5C3 시도
 * 
 * [문제 해결 프로세스]
 * 1. 조합을 통해 설치할 코어 결정
 * 	=> 배열에 코어 정보 저장
 * 	=> NCN => NC1
 * 2. 백트래킹을 통해 조합에 선택된 코어들의 방향을 결정하며 전선 설치 시도
 * 
 * 	a. 모든 코어가 전설 설치 성공한 경우
 * 		=> 사용한 전선이 최솟값이라면 갱신
 * 		=> 동일한 개수를 뽑는 조합의 결과 중 전선길이가 최솟인 것을 정답으로 출력 
 * 	b. 한번도 전선 설치가 완료되지 못한 경우
 * 		=> 조합에서 뽑을 개수를 하나 줄여 1번 부터 다시 시도
 * 
 * 
 * @author SSAFY
 *
 */
public class Solution_1767_프로세서연결하기_연민호_최적화_부분집합_백트래킹활용 {
	//상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0 , -1, 1};

	static int minLengthSum;	//사용한 최소 전선의 길이

	static int N;   //맵 크기

	static int[][] map;

	static List<Core> cores = new ArrayList<>();

	static Core[] selectedCores = new Core[12];	//전선 설치할 코어 정보
	
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
			
			minLengthSum = Integer.MAX_VALUE;	//최소 전선길이의 합 충분히 큰 값으로 초기화

			// r : 전선을 설치할 코어를 뽑는 개수
			// 전체 부터 ~ 1개 뽑는 조합 시도
			for(int r=cores.size(); r>=1; r--) {
				combination(0, 0, r);
				
				//한 번이라도 전선설치에 성공했다면 멈춤
				if(minLengthSum!=Integer.MAX_VALUE) break;
			}
			
			sb.append('#').append(tc).append(' ').append(minLengthSum).append('\n');
		}
		System.out.println(sb);
	}
	/**
	 * 
	 * @param idx
	 * @param start
	 * @param r	//뽑을 개수
	 */
	private static void combination(int idx, int start, int r) {
		// step 01. 전선을 설치할 r개의 코어 선택 완료
		if(idx==r) {
			//step 02. r개의 코어의 전선 설치 시도
			setCore(0, r, 0);
			return;
		}
		
		for(int i=start; i<cores.size(); i++) {
			selectedCores[idx] = cores.get(i);	//코어 선택
			combination(idx+1, i+1, r);			//다음 코어선택은 재귀로 넘김
		}
	}
	/**
	 * idx번째 코어의 방향을 정해 설치 후, 다음 코어 설치는 재귀로 넘김
	 * @param idx	설치할 코어의 인덱스
	 * @param r		전선을 설치할 코어의 개수
	 * @param lengthSum		사용한 전선길이의 합
	 */
	private static void setCore(int idx, int r, int lengthSum) {
		//모든 코어의 전선설치 성공
		if(idx==r) {
			//사용한 전선의 길이가 최소라면 갱신
			minLengthSum = Math.min(minLengthSum, lengthSum);
			return;
		}

		Core core = selectedCores[idx];	//설치할 코어의 정보

		for(int dir=0; dir<4; dir++) {
			//core를 dir 방향으로 설치했을 때 필요한 전선길이를 반환
			int length = getSetLength(core , dir);

			if(length==0) continue;	//설치 불가한 경우 다음 방향시도

			//1. 해당 방향으로 length만큼의 전선 설치
			setLine(core, dir, length, -1);
			//2. 다음 코어의 전선설치는 재귀로 넘김
			setCore(idx+1, r, lengthSum+length);
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