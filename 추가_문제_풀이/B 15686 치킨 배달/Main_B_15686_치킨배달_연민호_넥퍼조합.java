package algo240213;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 맵 정보를 받아놓고 문제를 풀다보니 맵 정보는 필요가 없음.
 * 필요한 좌표 값들은 따로 저장해놓았기 때문
 * @author minho
 * 
 * 메모리:13180kb, 시간:160ms
 */
public class Main_B_15686_치킨배달_연민호_넥퍼조합 {
	static int N, M;	//도시의 행열 크기, 최대치킨 집의 개수
	//	static int[][] map;	//지도 정보
	static int ans;	//최소 도시 치킨 거리

	static List<Node> houses = new ArrayList<>();	//집 정보
	static List<Node> chickenHouses = new ArrayList<>();	//치킨집 정보
	static int[] isSelected;	//치킨집 조합 정보 (1,0을 마치 플래그처럼 활용 예정)

	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r =  r;
			this.c =  c;
		}
		//다른 노드와의 거리계산 메소드
		public int getDist(Node n) {
			return Math.abs(this.r-n.r)+Math.abs(this.c-n.c);
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());	//도시의 행렬 크기 입력
		M = Integer.parseInt(st.nextToken());	//치킨집 최대 개수 입력

		//		map = new int[N+1][N+1];	//맵 생성
		ans=Integer.MAX_VALUE;	//최소 도시 치킨거리 초기화

		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int info = Integer.parseInt(st.nextToken());
				//				map[i][j] = info; //맵 정보 입력
				if(info==1) houses.add(new Node(i,j));	// 집정보 받아놓기
				else if(info==2) chickenHouses.add(new Node(i,j));	//치킨집 정보 받아놓기
			}
		}

		isSelected = new int[chickenHouses.size()];

		int cnt=0;

		//가장 작은 수 생성 치킨집개수=5, M=3의 경우 00111 생성
		while(cnt++<M) isSelected[chickenHouses.size()-cnt] = 1;

		do {
			//step 02. 도시 치킨 거리 구하기
			int dis = getCityChick();
			//step 03. 구한 도시 치킨 거리가 최소값이라면 갱신
			ans = Math.min(ans, dis);

		} while(np(isSelected));	//step 01. M개의 치킨 집 선택완료

		System.out.println(ans);
	}

	//현재 선택된 치킨집들의 도시 치킨거리 구해서 반환
	private static int getCityChick() {

		int cityChick = 0; //도시 치킨거리 담을 변수
		//집들의 개수 만큼 반복
		for(Node house : houses) {

			//1. 각 집들의 치킨 거리 구하기
			int chickDis=Integer.MAX_VALUE;	//해당 집의 치킨 거리 담을 변수
			//치킨집 만큼 반복
			for(int i=0; i<chickenHouses.size(); i++) {
				//치킨집들 중 선택된 치킨집인 경우
				if(isSelected[i]==1) {
					Node chicken = chickenHouses.get(i); 
					chickDis = Math.min(chickDis, chicken.getDist(house));	// 거리구하기
				}
			}
			//2. 해당 집의 치킨 거리를 도시 치킨거리에 누적
			cityChick += chickDis;
		}
		return cityChick;
	}

	private static boolean np(int[] arr) {
		int N = arr.length;

		//step1. 뒤에서부터 (i-1인덱스요소 < i인덱스요소 ) 만족하는 i값 찾기 
		int i = N-1;
		while( i>0 && arr[i-1] >= arr[i]) i--;

		//step1인 지점이 없다면 현재가 순열 중 가장 큰 숫자임을 의미(더이상 만들 순열 없음)
		if(i==0) return false;

		//step2. i-1인덱스 뒤의 요소 중 첫 번째로 i-1의 요소보다 값이 큰 녀석을 찾기(j인덱스)
		int j = N-1;
		while(arr[i-1]>= arr[j]) j--;

		//step3. i-1인덱스 요소와 j인덱스 요소 스왑
		swap(arr, i-1, j); 

		//step4.i-1인덱스 뒷부분을 가장 작은 수로 만들어 버리기
		int k = N-1;
		while(i<k) swap(arr, i++, k--);

		//다음 순열 만들기 성공
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp= arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}


}