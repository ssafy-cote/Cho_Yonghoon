package algo0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ_21939_문제추천시스템Version1_0422 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		TreeMap<Question, Integer> tMap = new TreeMap<>();
		TreeMap<Integer, Question> tMap2 = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			Question question = new Question(num, level);
			tMap.put(question, num);
			tMap2.put(num, question);
		}
		
		StringBuilder builder = new StringBuilder();
		st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			String com = st.nextToken();
			if (com.equals("add")) {
				int num = Integer.parseInt(st.nextToken());
				int level = Integer.parseInt(st.nextToken());
				Question question = new Question(num, level);
				tMap.put(question, num);
				tMap2.put(num, question);
			} else if (com.equals("recommend")) {
				if (st.nextToken().equals("1")) {
					builder.append(tMap.lastKey().num).append("\n");
				} else {
					builder.append(tMap.firstKey().num).append("\n");
				}
			} else if (com.equals("solved")) {
				int key = Integer.parseInt(st.nextToken());
				tMap.remove(tMap2.get(key));
			}
		}
		System.out.println(builder);
	}

	static class Question implements Comparable<Question> {
		int num, level;
		
		public Question(int num, int level) {
			this.num = num;
			this.level = level;
		}

		@Override
		public int compareTo(Question o) {
			if (this.level == o.level) {
				return this.num - o.num;
			}
			return this.level - o.level;
		}
	}
}
