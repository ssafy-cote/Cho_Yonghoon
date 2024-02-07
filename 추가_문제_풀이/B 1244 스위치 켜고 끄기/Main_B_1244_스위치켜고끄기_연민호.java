package algo230131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1244_스위치켜고끄기_연민호 {
	
	private static int cnt, switches[];	//static 메소드에서 사용하기 위해 static으로 만듦
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		cnt = Integer.parseInt(br.readLine());	//스위치 개수
		switches = new int[cnt+1];	//스위치 정보 1번부터 시작이므로 1부터 사용
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		//값 입력 받기
		for (int i = 1; i <= cnt; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		int n = Integer.parseInt(br.readLine());	//학생 수 입력
		
		int gender,no;	//성별과 받은 번호
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			
			gender = Integer.parseInt(st.nextToken());	//성별 입력
			no = Integer.parseInt(st.nextToken());	//받은 번호 입력
			
			//남학생의 경우
			if(gender==1) change1(no);
			//여학생의 경우
			else change2(no);
			

		}
		
		//출력
		for(int i=1; i<=cnt; i++) {
			sb.append(switches[i]).append(' ');
			if(i%20==0) sb.append('\n');
		}
		System.out.println(sb);
		
	}

	//여학생의 스위치 처리 메소드
	private static void change2(int no) {
		//1. 받은 번호의 스위치 변경
		switches[no] = switches[no]==1?0:1;
		
		//2.받은 번호로부터 거리 1씩 증가하며 좌우 스위치 체크 후 변경
		int dis=1;
		while(true) {
			
			int left = no-dis;	//왼쪽 스위치 번호
			int right = no+dis;	//오른쪽 스위치 번호
			
			//경계 넘어가거나 대칭이 아니라면 break;
			if( left < 1 || right >cnt || switches[left]!=switches[right]) break;
			
			//좌우 스위치 변경
			switches[left] = switches[left]==1?0:1;
			switches[right] = switches[right]==1?0:1;
			
			//체크할 스위치 거리 증가
			dis++;
		}
		
	}

	//남학생의 스위치 처리 메소드
	private static void change1(int no) {
		
		 int num=1; //no에 곱할 숫자
		 
		 //no*num가 스위치의 개수 이하일 때까지 반복
		 while(no*num<=cnt) {
			 switches[no*num] = switches[no*num]==1?0:1;	//스위치 변경
			 num++;
		 }
	}

}