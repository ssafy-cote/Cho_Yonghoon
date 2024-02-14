package algo240214;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 * 메모리:11592kb, 시간:80ms
 */
public class Main_B_1992_쿼드트리_연민호_void {
	
	static char[][] img;
	static StringBuilder sb = new StringBuilder();	//결과를 담을 공간
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		img = new char[N][N];
		
		//이미지 값 입력받기
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			img[i] = str.toCharArray();
		}
		
		quadTree(0, 0, N);
		
		System.out.println(sb);
		
	}
	/**
	 * 시작점을 (sr,sc)로 하는 행열 길이가 length인 구역의 압축결과를 만드는 메소드
	 * @param sr 현재 구역의 왼쪽 상단 좌표 행값
	 * @param sc 현재 구역의 왼쪽 상단 좌표 열값
	 * @param length 현재 구역의 행열 크기
	 */
	private static void quadTree(int sr, int sc, int length) {
		//현재 구역이 모두 같은 숫자라면 해당 숫자로 압축(출력)
		if(isSame(sr, sc, length)) {
			sb.append(img[sr][sc]);
			return;
		}
		
		//현재 구역이 모두 같은 숫자가 아니라면 (압축 불가능)
		int half = length/2;
		//1) 여는 소괄호 출력
		sb.append("(");
		
		//2) 현재 구역을 4분할해서 쪼개진 구역의 압축결과를 출력(재귀 호출)
		quadTree(sr, sc, half);				//1구역의 압축결과 만들기
		quadTree(sr, sc+half, half);		//2구역의 압축결과 만들기
		quadTree(sr+half, sc, half);		//3구역의 압축결과 만들기
		quadTree(sr+half, sc+half, half);	//4구역의 압축결과 만들기
		//3) 닫는 소괄호 출력
		sb.append(")");
	}
	
	/**
	 * (sr,sc) 좌표를 기준으로 length 범위만큼의 숫자가 모두 같은 경우 true 반환
	 * @param sr
	 * @param sc
	 * @param length
	 * @return
	 */
	private static boolean isSame(int sr, int sc, int length) {
		
		//현재 구역의 왼쪽 상단 좌표 값을 저장해놓고 
		//모든 값과 비교하며 다른 값이 있다면 false 반환
		char value = img[sr][sc];
		for(int i=sr; i<sr+length; i++) {
			for(int j=sc; j<sc+length; j++) {
				if(value!=img[i][j]) return false;
			}
		}
		return true;
	}
}