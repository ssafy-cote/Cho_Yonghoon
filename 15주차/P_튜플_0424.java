package algo0424;

import java.util.*;

public class P_튜플_0424 {
	public int[] solution(String s) {
		int[] answer = {};
		Set<Integer> map = new HashSet<>();
		ArrayList<Integer>[] list = new ArrayList[501];
		for (int i = 0; i < 501; i++) {
			list[i] = new ArrayList<>();
		}
		int index = 0;
		int temp = 0;
		boolean flag = true;
		for (int i = 1; i < s.length() - 1; i++) {
			if (s.charAt(i) == '{') {
				flag = false;
			} else if (s.charAt(i) == ',') {
				if (flag) {
					list[index++].add(temp);
					temp = 0;
				} else {
					list[index].add(temp);
					temp = 0;
				}
			} else if (s.charAt(i) == '}') {
				flag = true;
			} else {
				temp = (temp * 10) + Integer.parseInt((s.charAt(i) + ""));
			}
			if (i == s.length() - 2) {
				list[index++].add(temp);
			}
		}

		// for(int i = 0; i < index; i++){
		// System.out.println(list[i]);
		// }
		Number[] indexList = new Number[index];
		for (int i = 0; i < index; i++) {
			indexList[i] = new Number(i, list[i].size());
		}
		Arrays.sort(indexList);
		// for(int i = 0; i < index; i++){
		// System.out.println(indexList[i].size);
		// }
		ArrayList<Integer> ans = new ArrayList();
		for (int i = 0; i < index; i++) {
			for (int j : list[indexList[i].index]) {
				if (map.add(j)) {
					ans.add(j);
					break;
				}
			}
		}

		// System.out.print(ans);
		answer = new int[ans.size()];
		for (int i = 0; i < ans.size(); i++) {
			answer[i] = ans.get(i);
		}
		return answer;
	}

	static class Number implements Comparable<Number> {
		int index;
		int size;

		public Number(int index, int size) {
			this.index = index;
			this.size = size;
		}

		@Override
		public int compareTo(Number o) {
			return this.size - o.size;
		}
	}
}