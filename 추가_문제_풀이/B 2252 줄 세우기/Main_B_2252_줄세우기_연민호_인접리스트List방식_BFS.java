package algo240220;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * N 명의 학생
 * 
 * 키 순서대로 줄 세우기
 * 
 * 두 학생의 키를 비교
 * 
 * 일부 학생들의 키만을 비교
 * 
 * 일부 학생들의 키를 비교한 결과가 주어질 때, 줄을 세우는 프로그램 작성
 * 
 * N : 1~32,000
 * M : 1~100,000	키를 비교한 횟수
 * 
 * M 줄의
 * A B 입력
 * A가 B의 학생 앞에 서야함
 * 
 * 학생의 번호 1~N번
 * 
 * 답이 여러가지인 경우 아무거나 출력
 * 
 * 
 * 
 * A B라는 정보가 주어졌을 때
 * A => B로 이동가능하다고 가정
 * 
 * 
 * 3 2
 *   1 2 3
 * 1     O
 * 2     O
 * 3
 * 
 * 4 2
 *   1 2 3 4
 * 1 
 * 2
 * 3 O  
 * 4   O
 * 
 * 메모리:45820KB, 시간:416ms
 * 
 * @author SSAFY
 *
 */
public class Main_B_2252_줄세우기_연민호_인접리스트List방식_BFS {
	
	static List<Integer>[] adjList;
	
	static int[] degrees;	//차수 degrees[3]:5 3번 학생보다 작은 학생의 수가 5명이라는 의미
	
	static int N, M;	//학생 수, 키 정보 개수
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new List[N+1];
		for(int i=1; i<=N; i++) adjList[i] = new ArrayList<>();
		
		degrees = new int[N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			degrees[to]++;
		}
		
		BFS();

		System.out.println(sb);
	}

	private static void BFS() {
		Queue<Integer> q = new ArrayDeque<>();

		//자신 보다 작은 키를 가진 번호를 모르는 경우, 일단 큐에 추가
		for(int i=1; i<=N; i++)
			if(degrees[i]==0) q.offer(i);
		
		
		while(!q.isEmpty()) {
			int from = q.poll();
			
			sb.append(from).append(' ');
			
			for(int to : adjList[from]) {
				//출력하지 않은 학생 중 to번호 학생보다 작은 학생이 1명인 경우만 큐에 추가
				if(--degrees[to]==0) q.offer(to);
			}
		}
	}

}