package algo0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11724_연결요소개수_0226 {
	static int N, M;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		boolean[] visit = new boolean[N + 1];
		for (int i : p) {
			visit[find(i)] = true;
		}
		int ans = 0;
		visit[0] = false;
		for (boolean v : visit) {
			if (v) {
				++ans;
			}
		}
		System.out.println(ans);
	}

	static void union(int a, int b) {
		int ap = find(a);
		int bp = find(b);

		if (ap == bp)
			return;

		if (ap < bp) {
			p[bp] = ap;
		} else {
			p[ap] = bp;
		}
	}

	static int find(int a) {
		if (p[a] == a) {
			return p[a];
		}

		return p[a] = find(p[a]);
	}

}
