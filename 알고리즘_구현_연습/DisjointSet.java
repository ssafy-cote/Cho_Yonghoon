
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
	static int N;	//1~N ����
	static int M;	//������ ���� ����
	
	static int[] parents;	//���μ� ���� ����
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		makeSet();	//���� ���� �����
		System.out.println("���μ� ����:"+Arrays.toString(parents));
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			union(a, b);
		}
		
		System.out.println(Arrays.toString(parents));
	}
	/**
	 * ���μ� ���� �����
	 */
	private static void makeSet() {
		parents = new int[N+1];
		for(int i=1; i<=N; i++) parents[i] = i;
	}
	
	/**
	 * a���Ұ� ���� ������ ��ǥ���� ��ȯ
	 * @param a
	 * @return a�� ���� ������ ��ǥ ����
	 */
	private static int find(int a) {
		//���� ���� - a�� �ش� ������ ��ǥ������ ���
		if(parents[a] == a) return a;
		
		//parents[a] 	a��� ���Ұ� ����Ű�� ������ ��ȣ
//		return find(parents[a]);
		return parents[a] = find(parents[a]);	//Path compression
	}
	
	/**
	 * �� a,b ���Ұ� ���� ������ ������
	 * @param a
	 * @param b
	 */
	private static boolean union(int a, int b) {
		//step 01. �� ���Ұ� ���� ������ �� ��ǥ���� ���ϱ�
		int rootA = find(a);
		int rootB = find(b);
		
		//�̹� ���� ������
		if(rootA == rootB) return false;
		
		//step 02. �ش� ��ǥ ���ҳ��� �ٶ󺸰� �����
		parents[rootA] = rootB;
//		parents[rootB] = rootA;	//�� �� �ϳ�
		return true;
	}
}
