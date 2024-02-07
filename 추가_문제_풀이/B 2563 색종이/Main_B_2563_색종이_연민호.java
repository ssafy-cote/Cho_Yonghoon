package algo240207;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2563_색종이_연민호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//색종이 영역 표시 공간
		boolean[][] paper = new boolean[101][101];
		
		//step 01. 색종이 붙인 영역 체크와 영역 cnt를 한꺼번에 처리
		int area = 0 ;	//면적
		int N = Integer.parseInt(br.readLine());	//색종이의 개수
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());	//색종이의 시작꼭지점 행값
			int sc = Integer.parseInt(st.nextToken());	//색종이의 시작꼭지점 열값
			
			//색종이를 붙인 영역 표시
			for(int r=sr; r<sr+10; r++) {
				for(int c=sc; c<sc+10; c++) {
					if(!paper[r][c]) {
						paper[r][c] = true;
						area++;
					}
				}
			}
		}
		System.out.println(area);
	}
}