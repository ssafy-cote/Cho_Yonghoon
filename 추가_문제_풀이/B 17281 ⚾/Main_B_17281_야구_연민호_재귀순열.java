package algo240223;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:14860KB, 시간:504ms
 * 
 * 조건 1번 선수는 4번타자로 고정
 * 
 * 0: 아웃
 * 1: 안타
 * 2: 2루타
 * 3: 3루타
 * 4: 홈런
 * 
 * 
 * 
 * 1. 4번 타자로 결정된 1번 선수를 제외한 2~9번 선수의 타순 정하기
 * 	=> 8P8 = 8!
 *  => 나열하는 것이므로 np가 제일 빠를 듯
 * 
 * 2. 고른 순서대로 주어진 N 이닝 수 만큼 게임을 진행
 *  => 주자는 총 3명이 있을 수 있음
 *  	크기가 4인 불린 배열 만들기
 *  		1 , 2, 3 인덱스의 요소가 true라면 해당 루에 주자 있음을 의미
 *  	0아웃
 *  		=> 아웃 cnt 누적
 *  		=> 아웃 cnt가 3이 된다면 다음 이닝으로 넘어가기
 *  			=> 이닝은 1부터 N까지 진행
 *  	1안타
 *  		=> 3루 true라면 점수 +1
 *  		=> 2 -> 3, 1 -> 2, 1=true
 *  	2루타  
 *  		=> 3루 true라면 점수 +1
 *  		=> 2루 true라면 점수 +1
 *  		=> 1 -> 3, 2=true
 *  	3루타
 *  		=> 3루 true라면 점수+1
 *  		=> 2루 true라면 점수+1
 *  		=> 1루 true라면 점수+1
 *  		=> 3=true
 *  	홈런 
 *  		=> 3루 true라면 점수+1
 *  		=> 2루 true라면 점수+1
 *  		=> 1루 true라면 점수+1
 *  		=> 점수+1
 * 3. 득점한 값이 최대라면 갱신
 * @author SSAFY
 *
 */
public class Main_B_17281_야구_연민호_재귀순열 {

	static int N;	//이닝 수
	
	static int[][] batResult;	//이닝당 타자의 결과
	
	static int[] battingOrder = new int[10];	//1번 타자를 제외한 순서
	static boolean[] isSelected = new boolean[10];		//선택 여부
	
	static int maxScore;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		batResult = new int[N+1][10];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=9; j++) {
				batResult[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		battingOrder[4] = 1;	//1번 선수가 4번타자
		permutation(1);
		
		System.out.println(maxScore);
	}

	/**
	 * cnt 번째 타자를 선정하고 다음 타순의 타자 선정은 재귀에 넘기기
	 * @param cnt	//현재 정해야할 타순
	 */
	private static void permutation(int cnt) {
		//step 01. 9번 타순까지 완료했다면 
		if(cnt==10) {
			//순열 잘 뽑았는지 체크
//			System.out.println(Arrays.toString(battingOrder));
			
			//step 02. 고른 순서대로 주어진 N 이닝 수 만큼 게임을 진행
			goGame();
			
			return;
		}
		
		//number - 선수의 번호를 의미
		for(int number=2; number<=9; number++) {
			if(isSelected[number]) continue;
			
			battingOrder[cnt] = number;
			isSelected[number] = true;
			
			//3번타자를 고른 경우 4번타자는 이미 정해져있으므로 5번타자 고르기로 건너뛰기 위함.
			permutation( cnt==3? cnt+2: cnt+1 );
			
			isSelected[number] = false;
		}
	}

	private static void goGame() {
		int score = 0;
		
		int inning = 1;	//현재 이닝
		int battingNum = 1;	//현재 순서
		int outCnt = 0;		//현재 이닝의 아웃 카운트
		boolean[] isInBase = new boolean[4];	//1, 2, 3 사용
		while(inning<=N) {
			
			//현재 타순의 선수 번호
			int playerNum = battingOrder[battingNum];
			
			//현재 순서의 타자의 결과 가져오기
			int result = batResult[inning][playerNum];

			//아웃의 경우
			if(result==0) {
				//쓰리 아웃
				if(++outCnt == 3) {
					inning++;	//이닝 수 증가
					outCnt=0;		//아웃 카운트 초기화
					//베이스 정보 초기화
					for(int i=1; i<=3; i++) isInBase[i] = false;
				}
			}
			//1루타
			else if(result==1) {
				//점수 계산
				if(isInBase[3]) score++;	//3루 true라면 점수 +1
				
				//출루 선수 이동
				isInBase[3] = isInBase[2];	//2->3
				isInBase[2] = isInBase[1];	//1->2
				
				//타자 이동
				isInBase[1] = true;			//타자 -> 1루
			}
			//2루타
			else if(result==2) {
				//점수 계산
				if(isInBase[3]) score++;	//3루 true라면 점수 +1
				if(isInBase[2]) score++;	//2루 true라면 점수 +1
				
				//출루 선수 이동
				isInBase[3] = isInBase[1];	//1->3 이동
				isInBase[1] = false;		//1루 비우기
				
				//타자 이동
				isInBase[2] = true;			//타자 -> 2루
			}
			//3루타
			else if(result==3) {
				//점수 계산
				if(isInBase[3]) score++;	//3루 true라면 점수 +1
				if(isInBase[2]) score++;	//2루 true라면 점수 +1
				if(isInBase[1]) score++;	//1루 true라면 점수 +1
				
				//출루 선수 이동
				isInBase[2] = false;		//2루 비우기
				isInBase[1] = false;		//1루 비우기
				
				//타자 이동
				isInBase[3] = true;			//타자 -> 3루
			}
			//홈런
			else {
				//점수 계산
				if(isInBase[3]) score++;	//3루 true라면 점수 +1
				if(isInBase[2]) score++;	//2루 true라면 점수 +1
				if(isInBase[1]) score++;	//1루 true라면 점수 +1
				score++;					//타자 점수 cnt
				
				//출루 선수 이동
				for(int i=1; i<=3; i++) isInBase[i] = false;	//1,2,3루 비우기
			}
			
			//다음 타자
			battingNum = battingNum%9 + 1;
		}
		
		//step 03. 득점한 값이 최대라면 갱신
		maxScore = Math.max(maxScore, score);
	}
}