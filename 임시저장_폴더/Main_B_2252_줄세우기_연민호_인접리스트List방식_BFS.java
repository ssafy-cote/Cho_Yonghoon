package algo0220;
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
 * N ���� �л�
 * 
 * Ű ������� �� �����
 * 
 * �� �л��� Ű�� ��
 * 
 * �Ϻ� �л����� Ű���� ��
 * 
 * �Ϻ� �л����� Ű�� ���� ����� �־��� ��, ���� ����� ���α׷� �ۼ�
 * 
 * N : 1~32,000
 * M : 1~100,000	Ű�� ���� Ƚ��
 * 
 * M ����
 * A B �Է�
 * A�� B�� �л� �տ� ������
 * 
 * �л��� ��ȣ 1~N��
 * 
 * ���� ���������� ��� �ƹ��ų� ���
 * 
 * 
 * 
 * A B��� ������ �־����� ��
 * A => B�� �̵������ϴٰ� ����
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
 * �޸�:45820KB, �ð�:416ms
 * 
 * @author SSAFY
 *
 */
public class Main_B_2252_�ټ����_����ȣ_��������ƮList���_BFS {
	
	static List<Integer>[] adjList;
	
	static int[] degrees;	//���� degrees[3]:5 3�� �л����� ���� �л��� ���� 5���̶�� �ǹ�
	
	static int N, M;	//�л� ��, Ű ���� ����
	
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

		//�ڽ� ���� ���� Ű�� ���� ��ȣ�� �𸣴� ���, �ϴ� ť�� �߰�
		for(int i=1; i<=N; i++)
			if(degrees[i]==0) q.offer(i);
		
		
		while(!q.isEmpty()) {
			int from = q.poll();
			
			sb.append(from).append(' ');
			
			for(int to : adjList[from]) {
				//������� ���� �л� �� to��ȣ �л����� ���� �л��� 1���� ��츸 ť�� �߰�
				if(--degrees[to]==0) q.offer(to);
			}
		}
	}

}