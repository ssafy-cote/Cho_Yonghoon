package algo240215;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:22,220kb, 시간:1301ms
 */
public class Solution_5644_무선충전_연민호 {
	// 상 우 하 좌
	static int[] dx = {0,  0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
	
	static int M, A;	//이동정보 개수, 배터리 개수
	
	static BC[] bc;	//무선 충전기 정보
	
	static User userA;	//사용자 A
	static User userB;	//사용자 B
	
	static int amount;	//전체 충전량
	
	static class BC{
		int x, y;	//좌표
		int C; //범위
		int P; //성능
		public BC(int x, int y, int C, int P) {
			this.x = x;
			this.y = y;
			this.C = C;
			this.P = P;
		}
		//현재 무선 충전기 범위 내에 사용자(User)가 있다면 true 반환
		public boolean isInArea(User u) {
			int dis = Math.abs(this.x-u.x) + Math.abs(this.y-u.y);
			
			if(this.C >= dis) return true;
			return false;
		}
	}
	
	static class User{
		int x,y;	//좌표
		public User(int x, int y) {
			this.x = x;
			this.y = y;
		}
		//사용자 이동 메소드
		public void move(int dir) {
			this.x += dx[dir];
			this.y += dy[dir];
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			//User A, B 이동 정보 입력 배열
			int[] moveA = new int[M+1];
			int[] moveB = new int[M+1];
			//User A, B 이동 정보 입력 받기
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) moveA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) moveB[i] = Integer.parseInt(st.nextToken());
			
			//무선충전기 정보 입력
			bc = new BC[A];
			for(int i=0; i<A;i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y= Integer.parseInt(st.nextToken());
				int C= Integer.parseInt(st.nextToken());
				int P= Integer.parseInt(st.nextToken());
				bc[i] = new BC(x, y, C, P);
			}
			
			//유저 생성
			userA = new User(1, 1);
			userB = new User(10, 10);
			
			//전체 충전량 초기화
			amount = 0;
			
			//이동정보만큼 반복
			//t가 0인 경우는 움직이지 않은 초기상태도 고려 
			for(int t=0; t<=M; t++) {
				//step 01. 사용자 이동
				userA.move( moveA[t] );
				userB.move( moveB[t] );
				
				//step 02. 현재 사용자 위치에 대한 최대 충전량 구해 합산
				amount += getCurPower();
			}
			
			sb.append('#').append(tc).append(' ').append(amount).append('\n');
		}
		System.out.println(sb);
	}

	/**
	 * 현재 초에서 얻을 수 있는 최대 충전량 반환
	 * @return
	 */
	private static int getCurPower() {
		//현재 초의 최대 충전량 저장
		int max = 0;
		
		//배터리 A개에서 2개를 뽑아 나열하는 중복순열
		for(int i=0; i<A; i++) {		//i:A가 선택한 배터리
			for(int j=0; j<A; j++) {	//j:B가 선택한 배터리 
				
				boolean checkA = bc[i].isInArea(userA);	//i 무선충전기 범위 내에 사용자 A가 있는지 여부
				boolean checkB = bc[j].isInArea(userB); //j 무선충전기 범위 내에 사용자 B가 있는지 여부
				
				//i,j 무선충전기 선택했을 때 충전량 저장
				int curSum = 0;
				
				//같은 무선충전기(i==j)이고 둘 다 범위 내에 있는 경우
				if(i==j && checkA && checkB) {
					curSum += bc[i].P;
				}
				//그 외에 각각의 배터리가 범위 내에 있다면 각 충전량 합산
				else {
					if(checkA) curSum += bc[i].P;
					if(checkB) curSum += bc[j].P;
				}
				
				//현재 i,j 무선 충전기를 고려한 충전량이 최댓값이라면 갱신
				max = Math.max(max, curSum);
			}
		}
		
		return max;
	}
}