package algo240205;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 메모리:13704KB, 시간:128ms
 * 
 */
public class Main_B_요세푸스문제_연민호_리스트_ArrayList{

	public static void main(String[] args) throws IOException {

		Scanner s = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = s.nextInt();	//숫자의 개수
		int K = s.nextInt();	//K번째
		
		List<Integer> list = new ArrayList<>();
		
		//숫자 세팅
		for(int i=1;i<=N;i++) {
			list.add(i);
		}
		
		//출력
		sb.append('<');
		
		int idx = K-1;	//처음 삭제될 요소의 인덱스
		
		while(true) {
			
			sb.append( list.remove(idx) ).append(", ");
			
			//삭제 된 후 남아있는 요소가 0개라면 반복 종료
			if(list.size()==0) break;
			
			//인덱스 변화량
			idx += (K-1);	//삭제된 요소 때문에 K칸이 아닌 K-1칸만 이동
			idx %= list.size();	//인덱스 변화량
			
//			idx = ( idx+=(K-1) ) % list.size();	//축약
		}
		
		sb.setLength(sb.length()-2);	//뒤에 붙은 ", " 제거
		sb.append('>');

		
		System.out.println(sb);
	}

}
