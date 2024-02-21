
package algo0221;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
5 3
1 3
4 5
2 4

 * @author SSAFY
 *
 */
public class DisjointSet {
	static int N;	//1~N 정점
	static int M;	//합집합 정보 개수
	
	static int[] parents;	//서로소 집합 정보
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		makeSet();	//서롯 집합 만들기
		System.out.println("서로소 집합:"+Arrays.toString(parents));
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			union(a, b);
		}
		
		System.out.println(Arrays.toString(parents));
	}
	/**
	 * 서로소 집합 만들기
	 */
	private static void makeSet() {
		parents = new int[N+1];
		for(int i=1; i<=N; i++) parents[i] = i;
	}
	
	/**
	 * a원소가 속한 집합의 대표원소 반환
	 * @param a
	 * @return a가 속한 집합의 대표 원소
	 */
	private static int find(int a) {
		//기저 조건 - a가 해당 집합의 대표원소인 경우
		if(parents[a] == a) return a;
		
		//parents[a] 	a라는 원소가 가리키는 원소의 번호
//		return find(parents[a]);
		return parents[a] = find(parents[a]);	//Path compression
	}
	
	/**
	 * 각 a,b 원소가 속한 집합을 합집합
	 * @param a
	 * @param b
	 */
	private static boolean union(int a, int b) {
		//step 01. 두 원소가 속한 집합의 각 대표원소 구하기
		int rootA = find(a);
		int rootB = find(b);
		
		//이미 같은 집합임
		if(rootA == rootB) return false;
		
		//step 02. 해당 대표 원소끼리 바라보게 만들기
		parents[rootA] = rootB;
//		parents[rootB] = rootA;	//둘 중 하나
		return true;
	}
}
