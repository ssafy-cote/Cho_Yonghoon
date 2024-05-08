package algo0503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1991_트리순회_0502 {

	static int n;
	static Node[] map;
	static StringBuilder builder;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		n = Integer.parseInt(st.nextToken());

		map = new Node[n + 1];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			char P = st.nextToken().charAt(0);
			int p = P - 64;
			char L = st.nextToken().charAt(0);
			char R = st.nextToken().charAt(0);
			map[p] = new Node(P, L, R);
		}
		// 전위 순회
		builder = new StringBuilder();
		pre(1);
		System.out.println(builder);
		builder = new StringBuilder();
		middle(1);
		System.out.println(builder);
		builder = new StringBuilder();
		after(1);
		System.out.println(builder);
	}

	static void middle(int p) {
		if (map[p].l != '.') {
			middle(map[p].l - 64);
		}
		builder.append(map[p].p);
		if (map[p].r != '.') {
			middle(map[p].r - 64);
		}
	}

	static void after(int p) {
		if (map[p].l != '.') {
			after(map[p].l - 64);
		}
		if (map[p].r != '.') {
			after(map[p].r - 64);
		}
		builder.append(map[p].p);
	}

	// 루트 왼쪽 오른쪽
	static void pre(int p) {
		builder.append(map[p].p);
		if (map[p].l != '.') {
			pre(map[p].l - 64);
		}
		if (map[p].r != '.') {
			pre(map[p].r - 64);
		}
	}

	static class Node {
		char p, l, r;

		public Node(char p, char l, char r) {
			this.p = p;
			this.l = l;
			this.r = r;
		}
	}
}
