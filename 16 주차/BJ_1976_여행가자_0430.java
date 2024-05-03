package algo0503;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1976_여행가자_0430 {
	static int[][] map;
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine(), " ");
		int m = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		p = new int[n+1];
		for(int i = 1; i<n+1 ; i++) {
			p[i] = i;
		}
		
		for(int i = 1; i < n+1; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j = 1; j < n+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i < n+1; i++) {
			for(int j = 1; j < n+1; j++) {
				if(map[i][j] == 1) {
					union(i,j);
				}
			}
		}
		st = new StringTokenizer(bf.readLine(), " ");
		int[] arr = new int[m];
		for(int i = 0; i<m; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int flag = 0;
		for(int i = 1; i < m; i++) {
			if(find(arr[i-1]) != find(arr[i])) flag = 1;
		}
		if(flag == 1) {
			System.out.println("NO");
		}
		else
			System.out.println("YES");
		

	}
	
	public static void union(int a, int b) {
		p[find(b)] = find(a);
	}
	
	
	public static int find(int a) {
		if ( a == p[a]) return a;
		return p[a] = find(p[a]);
	}

}
