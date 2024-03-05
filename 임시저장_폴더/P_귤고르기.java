package algo0305;

import java.util.*;
import java.io.*;

class P_귤고르기 {
	public int solution(int k, int[] tangerine) {

		int[] map = new int[10000001];
		for (int i = 0; i < tangerine.length; i++) {
			map[tangerine[i]] += 1;
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < k; i++) {
			set.add(tangerine[i]);
		}
		System.out.println(set);
		int answer = set.size();
		for (int i = 1; i <= tangerine.length - k; i++) {
			set.remove(tangerine[i - 1]);
			set.add(tangerine[i]);
			set.add(tangerine[i + k - 1]);
			answer = Math.min(answer, set.size());
			System.out.println(set);
		}
		return answer;
	}
}