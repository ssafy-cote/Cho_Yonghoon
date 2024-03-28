package algo0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_20920_영단어암기는괴로워_0326 {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int n = Integer.parseInt(stringTokenizer.nextToken());
		int m = Integer.parseInt(stringTokenizer.nextToken());
		Map<String, Book> map = new HashMap<>();
		ArrayList<Book> books = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String string = stringTokenizer.nextToken();
			if (string.length() >= m) {
				if (!map.containsKey(string)) {
					Book book = new Book(string, 1);
					map.put(string, book);
					books.add(book);
				} else {
					map.get(string).setCnt();
				}
			}
		}
		Collections.sort(books);
		StringBuilder builder = new StringBuilder();
		for(Book book : books) {
			builder.append(book.word).append("\n");
		}
		System.out.println(builder);
	}

	static class Book implements Comparable<Book> {
		String word;
		int cnt;

		public Book(String word, int cnt) {
			this.word = word;
			this.cnt = cnt;
		}

		public void setCnt() {
			this.cnt+=1;
		}

		@Override
		public String toString() {
			return word;
		}

		@Override
		public int compareTo(Book o) {
			if (this.cnt == o.cnt) {
				if (this.word.length() == o.word.length()) {
					return this.word.compareTo(o.word);
				} else {
					return -(this.word.length() - o.word.length());
				}
			} else {
				return -(this.cnt - o.cnt);
			}
		}
	}
}
