package algo240214;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 /**
  * 메모리:12780kb, 시간:92ms
  */
public class Main_B_1992_쿼드트리_연민호_String {
	
	static char[][] img;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		img = new char[N][N];
		
		//이미지 값 입력받기
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			img[i] = str.toCharArray();
		}
		
		String result = quadTree(0, 0, N);
		System.out.println(result);
	}
	/**
	 * 시작점을 (sr,sc)로 하는 행열 길이가 length인 구역의 압축결과 반환
	 * @param sr 현재 구역의 왼쪽 상단 좌표 행값
	 * @param sc 현재 구역의 왼쪽 상단 좌표 열값
	 * @param length 현재 구역의 행열 크기
	 */
	private static String quadTree(int sr, int sc, int length) {
		//현재 구역이 모두 같은 숫자라면 해당 숫자로 압축(출력)
		if(isSame(sr, sc, length)) {
			return img[sr][sc]+"";	//char=>문자열로 변경
		}
		
		//현재 구역이 모두 같은 숫자가 아니라면 (압축 불가능)
		int half = length/2;
		return "("
				+quadTree(sr, sc, half)
				+quadTree(sr, sc+half, half)
				+quadTree(sr+half, sc, half)
				+quadTree(sr+half, sc+half, half)
				+")";
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