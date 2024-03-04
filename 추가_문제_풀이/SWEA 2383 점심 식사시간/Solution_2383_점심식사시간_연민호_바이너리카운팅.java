package algo240229;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:29,760kb, 시간:213ms
 * 
 * [문제 해석]
 * NxN 
 * 
 * 밥을 빨리 먹기 위해 아래층으로 내려가야 함
 * 
 * P : 사람
 * S : 계단 입구
 * 
 * 이동완료시간 : 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * 
 * 1) 계단 입구까지 이동시간
 *  => 사람의 위치와 계단 입구의 열차이+행차이
 * 2) 계단을 내려가는 시간
 *   => 계단 입구 도착 후 1분 마다 아래칸으로 1칸
 *      => 계단에는 동시에 3명까자만 올라갈 수 있음
 *      => 계단마다 길이 K가 주어짐
 *      => 계단 입구 도착 후 K 분 후 완전히 내려감
 *  => 같은 높이의 계단에 위치하는 것도 가능
 * 
 * => 모든 사람이 계단을 내려가 이동이 완료되는 최소시간을 출력하는 프로그램
 * 
 * 1: 사람
 * 2~10 : 계단
 * 
 * N 은 최대 10
 * 사람의 수 최대 1~10
 * 계단 입구는 2개
 * 
 * [문제 해결 프로세스]
 * 1. 각 사람마다 내려갈 계단 정하기 ( 부분집합 )
 * 
 * 2.사람이 계단까지 가는데 걸리는 시간과 해당 계단의 번호 저장
 * 
 * 3.해당 정보를 시간(dist) 기준 오름차순 정렬
 * 
 * 4. 위 정보를 토대로 계단 내려가는 시간 구하기
 * 		필요한 정보 : 
 * 			- 계단 0층에서 내려보낼 수 있는 사람 수
 * 		    - 계단의 각 층에 존재하는 사람 수
 * 			
 * 		각 초당 계단 내려가기 프로세스
 * 		1) 계단의 가장 아래층에 있는 사람은 계단을 모두 내려오므로 내려올 수 있는 사람 수 증가
 * 		2) 계단의 1층~마지막 계단에 존재하는 사람 한 칸씩 내려가기(0층 제외)
 * 		3) 계단의 0층에 있는 사람을 1층으로 몇 명 내려보낼지 결정
 * 		4) 계단에 도착하는 시간(dist)과 현재 시간(time)이 일치하는 모든 사람을 계단의 0층에 대기시킴
 *      
 * 5. 현재 구한 시간이 최소라면 갱신
 * 
 * @author minho
 *
 */
public class Solution_2383_점심식사시간_연민호_바이너리카운팅 {
	static int minTime; //최소 소요 시간

	static int N ;  //한 변의 길이

	static int peopleCnt;   //사람의 숫자
	static People[] peoples = new People[10];   //사람의 좌표 정보

	static Stair[] stairs = new Stair[2];     //계단 좌표정보

	//사람이 계단까지 가는데 걸리는 시간과 계단 정보를 저장할 배열
	//distToStair[2].dist : 2번 사람이 계단까지 걸리는 시간
	//distToStair[2].stairNum : 2번 사람이 선택한 계단 번호
	static Distance[] distToStair = new Distance[10];	

	//사람
	static class People{
		int r,c;
		public People(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public int getDist(Stair s) {
			return Math.abs(r-s.r) + Math.abs(c-s.c);
		}
	}
	//계단
	static class Stair{
		int r,c;
		int height;	//계단 높이
		public Stair(int r, int c, int height) {
			this.r = r;
			this.c = c;
			this.height = height;
		}
	}

	//사람과 계단과의 거리 정보
	static class Distance{
		int dist;		//사람과 계단의 거리
		int stairNum;	//계단 번호
		public Distance(int dist, int stairNum) {
			this.dist = dist;
			this.stairNum = stairNum;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			minTime = Integer.MAX_VALUE;    //초기화
			N = Integer.parseInt(br.readLine());

			peopleCnt = 0;
			int stairCnt = 0;
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					int input = Integer.parseInt(st.nextToken());
					//사람의 경우
					if(input==1) {
						//좌표 정보 저장하고 사람수 cnt
						peoples[peopleCnt++] = new People(i,j);
					}
					else if(input!=0) {
						//계단 좌표와 높이 저장하고 계단 수 cnt
						stairs[stairCnt++] = new Stair(i,j, input);
					}
				}
			}

			//step 01. 각 사람마다 내려갈 계단 정하기 ( 부분집합 )
			//flag 자체가 하나의 경우의 수를 의미
			//비트가 0인 경우 0번 좌표의 계단을 선택한 것으로 체크
			for(int flag=0; flag<=1<<peopleCnt; flag++) {

				//step 02.사람이 계단까지 가는데 걸리는 시간과 계단 정보를 저장할 배열 채우기
				for(int i=0; i<peopleCnt; i++) {
					int dist;
					int stairNum;
					//i번 사람이 0번 계단 선택
					if((flag & 1<<i) == 0) {
						dist = peoples[i].getDist(stairs[0]);	//i번 사람과 0번 계단과의 거리
						stairNum = 0;
						distToStair[i] = new Distance(dist, stairNum);
					}
					//i번 사람이 1번 계단 선택
					else {
						dist = peoples[i].getDist(stairs[1]);	//i번 사람과 1번 계단과의 거리
						stairNum = 1;
					}
					distToStair[i] = new Distance(dist, stairNum);
				}
				//step 03.해당 정보를 시간(dist) 기준 오름차순 정렬(배열을 최대크기인 10으로 만들었으므로 사용해야하는 만큼만 정렬) 
				Arrays.sort(distToStair, 0, peopleCnt, (a,b)->a.dist-b.dist);

				//step 04. 위 정보를 토대로 계단 내려가는 시간 구하기
				int time = getDownStairTime();

				//step 05. 현재 시간이 최소라면 갱신
				minTime = Math.min(time, minTime);
			}
			sb.append('#').append(tc).append(' ').append(minTime).append('\n');
		}
		System.out.println(sb);
	}

	/**
	 * 모든 사람이 계단을 내려가는 시간 반환
	 * @return 
	 */
	private static int getDownStairTime() {
		int idx = 0;    	//계단의 0층에 대기시킬 사람의 인덱스
		
		int[] downPossibleCnt = {3, 3};			//downPossibleCnt[1] : 2이라면? 1번 계단에 내려보낼 수 있는 사람이 2명임
		int[][] stairDownCnt = new int[2][11];	//계단의 최대 길이는 10이므로 11로 만듦
		//stairDownCnt[0][3] : 2 이라면? 0번 계단의 3층에 존재하는 사람이 2명이라는 의미
		
		//1초마다 계단 내려가기 작업
		int time = 1;   	//시간
		while(true) {
			for(int num=0; num<=1; num++) {	//num 계단 번호
				//1) num계단의 가장 아래층에 있는 사람은 계단을 모두 내려오므로 내려보낼 수 있는 사람수 증가
				downPossibleCnt[num] += stairDownCnt[num][stairs[num].height];
				
				//2) num계단의 1층~마지막 계단에 존재하는 사람 한 칸씩 내려가기(0층 제외)
				for(int floor=stairs[num].height; floor>=2; floor--)	{	//floor:계단의 층
					stairDownCnt[num][floor] = stairDownCnt[num][floor-1];	//floor-1층의 사람을 floor층으로 내려보내기
				}
				
				//3) num계단의 0층에 있는 사람을 1층으로 몇 명 내려보낼지 결정
				//0층 대기인원 < 내려보낼 수 있는 사람 수(0층의 대기인원만큼을 내려보냄)
				if(stairDownCnt[num][0] < downPossibleCnt[num]) {
					stairDownCnt[num][1] = stairDownCnt[num][0];	//1층으로 내려보냄
					stairDownCnt[num][0] = 0;	//0층 대기인원 0명
				}
				//0층 대기인원 >= 내려보낼 수 있는 사람 수 (가능한만큼 내려보냄)
				else {
					stairDownCnt[num][1] = downPossibleCnt[num];	//1층으로 내려보냄
					stairDownCnt[num][0] -= downPossibleCnt[num];	//0층의 대기인원 감소시킴
				}
				downPossibleCnt[num] -= stairDownCnt[num][1];	//내려보낼 수 있는 사람 수를 1층으로 내려보낸만큼 감소
			}
			
			//계단에 한 명도 없고(내려갈 수 있는 총 인원 6명) & 계단에 도착할 사람 존재X면 계단 내려가기 종료
			if(downPossibleCnt[0]+downPossibleCnt[1]==6 && idx==peopleCnt) break;

			//4) 계단에 도착하는 시간(dist)과 현재 시간(time)이 일치하는 모든 사람을 계단의 0층에 대기시킴
			while(idx < peopleCnt) {	//남은 사람이 없다면 종료
				if(distToStair[idx].dist > time) break;	//아직 계단에 도착하지 못했다면 반복 멈춤
				
				//idx번째 사람이 계단에 도달
				if(distToStair[idx].stairNum==0) stairDownCnt[0][0]++;	//0번 계단 대기인원 +1
				else stairDownCnt[1][0]++;  							//1번 계단 대기인원 +1
				idx++;
			}
			time++;	//시간 증가
		}
		return time;
	}
}