import java.util.*;
import java.io.*;

public class BJ_집합_11723_0828 {

    static Set<Integer> map;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        map = new HashSet<>();
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine(), " ");
            String command = st.nextToken();
            int number = 0;
            if (command.equals("all") || command.equals("empty")) {

            } else {
                number = Integer.parseInt(st.nextToken());
            }
            command(command, number);
        }
        System.out.println(sb);
    }

    static void command(String command, int number) {
        if (command.equals("add")) {
            map.add(number);
        } else if (command.equals("remove")) {
            map.remove(number);
        } else if (command.equals("check")) {
            if (map.contains(number)) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        } else if (command.equals("toggle")) {
            if (map.contains(number)) {
                map.remove(number);
            } else {
                map.add(number);
            }
        } else if (command.equals("all")) {
            for (int i = 1; i < 21; i++) {
                map.add(i);
            }
        } else {
            map = new HashSet<>();
        }
    }
}
