package algo240205;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/**
 * 메모리:106,184KB, 시간:576ms
 * 
 * @author SSAFY
 *
 */
public class Main_BJ_2493_탑_연민호_deque {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());	//탑의 개수
		
		Deque<int[]> stack = new ArrayDeque<>();	//0:번호 , 1:높이
		
		//탑의 개수 만큼 반복
		StringTokenizer st = new StringTokenizer(br.readLine());
		A : for(int number=1; number<=N; number++) {
			int height = Integer.parseInt(st.nextToken());	//현재 탑 높이
			
			while(!stack.isEmpty()) {
				int[] top = stack.peek(); //비교할 탑 정보
				
				//현재 탑 높이 > 비교할 탑 높이 ( 필요없는 정보 버림)
				if(height > top[1]) stack.pop();
				//현재 탑 높이 < 비교할 탑 높이 ( 수신할 탑 찾음 )
				else {
					stack.push(new int[] {number, height});	//수신 탑 번호 출력
					sb.append(top[0]).append(' ');	//현재 탑 정보 저장
					continue A;	//다음 탑 탐색하러 가기
				}
			}
			
			//고려할 탑정보가 없음
			//스택에 현재 탑 번호,높이 저장 후, 0 출력
			stack.push(new int[] {number, height});
			sb.append(0).append(' ');
		}
		
		
		System.out.println(sb);
	}
}