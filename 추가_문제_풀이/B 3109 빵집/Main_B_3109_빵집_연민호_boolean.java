package algo240214;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:37020kb, 시간:288ms
 */
public class Main_B_3109_빵집_연민호_boolean {

	// 오른쪽위, 오른쪽, 오른쪽 아래 행 변화량
	static int[] dr = {-1, 0, 1};
	
	static char[][] map;	// 맵 정보
	static int R, C;	// 맵의 행열 크기
	
	static int pipeLineCnt; //파이프라인설치 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		for(int i=0; i<R; i++) {
			map[i]= br.readLine().toCharArray();
		}
		
		//step 01.각 행에 대한 파이프라인 설치 시도
		for(int r=0; r<R; r++) {
			if(setPipe(r, 0)) pipeLineCnt++;
		}
		
		System.out.println(pipeLineCnt);
		
	}

	/**
	 * 현재 좌표(r, c)에서 파이프를 세 방향(우상, 우, 우하)으로 설치해보고 
	 * 다음 좌표(nr, nc)기준 세 방향 파이프 설치는 재귀로 넘김
	 * @param r 현재 좌표 행
	 * @param c 현재 좌표 열
	 * @return (r,c) 좌표에서 파이프 설치를 쭉 시도했을 때 파이프라인 구축에 성공하다면 true 반환
	 */
	private static boolean setPipe(int r, int c) {
//		print();
		//step 03. 마지막 열이라면 파이프라인 설치 성공
		if(c==C-1) {
			return true;
		}
		
		//step02.세가지 방향으로 파이프 설치하기
		for(int d=0; d<3; d++) {
			//선택된 방향의  다음좌표
			int nr = r+dr[d];
			int nc = c+1;
			
			//경계 벗어나거나 빈 칸이 아니라면 다음 방향
			if(nr < 0 || nr >= R || map[nr][nc]!='.') continue;
			
			// 파이프 설치
			map[nr][nc] = '-';
			
			// 다음 좌표(nr,nc) 기준 세 방향에 대한 파이프 설치 시도는 재귀로 넘김
			if(setPipe(nr, nc)) return true;
			/*
			 * 이해하기 어려워하는 포인트!
				 현재 설치에 대한 결과가 끝에 파이프라인 구축에 성공한다면 재귀호출에 대한 결과가 true를 반환함( setPipe(nr,nc) == true )
				 따라서, 현재 재귀를 호출한 쪽에 true를 반환함으로써 재귀를 끝냄
				 계속해서 true가 리턴되며 결국 main메소드에서 호출한 setPipe() 메소드의 결과가 true로 반환되며 끝나게 됨
			 */
			 
		}
		//세 가지 방향에 대해 설치해보았을 때 모두 실패했다는 것은 파이프라인 구축 불가하므로 false 리턴
		return false;
	}
	
	private static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}