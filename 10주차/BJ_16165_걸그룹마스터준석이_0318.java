package algo0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_16165_걸그룹마스터준석이_0318 {

	static int N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		
		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		
		Map<String, String> nameToGroup = new HashMap<>();
		Map<String, String[]> GroupToName = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String group = stringTokenizer.nextToken();		
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int n = Integer.parseInt(stringTokenizer.nextToken());
			String[] nameList = new String[n];
			int index = 0;
			for(int j = 0; j < n; j++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				String name = stringTokenizer.nextToken();
				nameToGroup.put(name, group);
				nameList[index++] = name;
			}
			Arrays.sort(nameList);
			GroupToName.put(group, nameList);
		}
		for(int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			String string = stringTokenizer.nextToken();
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int command = Integer.parseInt(stringTokenizer.nextToken());
			if(command == 0) {
				for(String temp : GroupToName.get(string)) {
					System.out.println(temp);
				}
			}else {
				System.out.println(nameToGroup.get(string));
			}
		}
	}
}
