package algo240214;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:12304kb, 시간:140ms
 */
public class Main_B_17406_배열돌리기4_연민호_기본순열 {
	//하 우 상 좌
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};
	
	static int min;	//배열의 최솟값(정답값)
	
	static int N, M, K;	//배열의 크기와 회전 수
	
	static int[][] origin;	//원본 배열 정보
	static int[][] map;	// 실제 회전할 배열(deepCopy 된 배열)
	
	static Rotation[] rotation;	//회전 정보
	static boolean[] isSelected; //순열 만들 때 중복체크 위해
	static Rotation[] picked;	//만들어진 순열을 담을 배열(회전 정보의 순서가 정해진 배열)
	
	//회전 정보
	static class Rotation{
		int r, c, s;	//행, 열, 중심거리
		public Rotation(int r, int c, int s) {
			this.r=r;
			this.c=c;
			this.s=s;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		origin = new int[N+1][M+1];
		map = new int[N+1][M+1];
		
		rotation = new Rotation[K];
		isSelected = new boolean[K];
		
		picked = new Rotation[K];
		
		min = Integer.MAX_VALUE;	//최솟값 초기화
		
		//맵 정보 입력
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//회전 정보 입력
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rotation[i] = new Rotation(r, c, s);
		}
		
		permutation(0);
		
		System.out.println(min);
	}
	/**
	 * cnt번째 회전할 정보를 선택하고 다음 회전정보선택은 재귀에 넘김
	 * @param cnt	현재까지 선택된 회전정보 개수 or 선택할 회전보를 담을 picked 의 인덱스
	 */
	private static void permutation(int cnt) {
		//step 01. 회전 연산 순서 정하기 완료
		if(cnt == K) {
			copyArray();	//배열 복사(원본 배열 origin은 내버려두고 map을 바꿔가며 수행할 것이기 때문)
//			System.out.println("원본 배열");
//			print();	//원본 배열 출력
			
			//step 02. 회전 순서대로 배열 회전
			for(int i=0;i<K;i++) {
				rotate(picked[i]);
//				System.out.println(o[i].r+", "+o[i].c+", "+o[i].s+" 회전 정보의 경우");
//				print();	//회전 1번 후 잘 회전 됐는지 출력
			}
			
			//step 03. map배열의 행의 합 중 최솟값 구하고 해당 값이 최솟값이라면 갱신
			min = Math.min(getRowMin(), min);
			return;
		}
		for(int i=0;i<K;i++) {
			if(isSelected[i]) continue;
			
			picked[cnt] = rotation[i];
			isSelected[i] = true;
			
			permutation(cnt+1);
			
			isSelected[i] = false;
		}
	}

	/**
	 * 매개변수로 받은 회전 정보대로 배열(map)을 회전
	 * @param rot 회전 정보를 갖고 있음
	 */
	private static void rotate(Rotation rot) {
		for(int s=1; s<=rot.s; s++) {
			//시작점(왼쪽 상단의 꼭지점)
			int r = rot.r-s;
			int c = rot.c-s;
			int dir = 0;		//첫 방향 (우)
			
			//1. 첫째 값 담아두기
			int temp = map[r][c];
			
			//2. 4번 의 방향전환 동안 값을 당겨오는 동작을 반복
			while(dir<4) {
				//다음 좌표
				int nr = r+dr[dir];
				int nc = c+dc[dir];
				
				//테두리 벗어나지 않는 경우
				if(nr>=rot.r-s && nr<=rot.r+s && nc>=rot.c-s && nc<=rot.c+s) {
					//값 당겨오기
					map[r][c] = map[nr][nc];
					
					//이동
					r = nr;
					c = nc;
				}
				//테두리 벗어나는 경우
				else dir++;	//방향 전환
			}
			//3. 시작점 바로 오른쪽에 temp값 담기
			//while문이 끝났을 때, (r,c)엔 왼쪽 상단 꼭짓점 좌표가 들어있음
			map[r][c+1] = temp;
		}
	}
	
	/**
	 * origin[][]을  map[][]에 복사(deepCopy)
	 */
	private static void copyArray() {
		for(int i=1;i<=N;i++) {
			System.arraycopy(origin[i], 1, map[i], 1, M); 
//			System.out.println("map["+i+"] 주소:"+map[i]+", origin["+i+"] 주소: "+origin[i]);
		}
	}
	
	/**
	 * @return map 배열의 행의 합 중 최솟값 반환
	 */
	private static int getRowMin() {
		int arrMin = Integer.MAX_VALUE;
		for(int i=1;i<=N;i++) {
			int rowSum=0;
			for(int j=1;j<=M;j++) {
				rowSum+=map[i][j];
			}
			arrMin = Math.min(arrMin, rowSum);
		}
		return arrMin;
	}
	
	/**
	 * map배열 출력해보는 용도의 메소드
	 */
	private static void print() {
		for (int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}