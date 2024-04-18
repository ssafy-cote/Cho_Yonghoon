package algorithm;

import java.util.*;

public class P_메뉴리뉴얼_0418 {
    
    static Map<String, Integer> map;
    static String[] list;
    static int[] maxList;
    
    public String[] solution(String[] orders, int[] course) {
        Set<Integer> courseList = new HashSet<>();
        for(int i = 0; i < course.length; i++){
            courseList.add(course[i]);
        }
        map = new HashMap<>();
        maxList = new int[11];
        for(int i = 0; i < orders.length; i++){
            list = orders[i].split("");
            Arrays.sort(list);
            combi(0, "", list.length);
        }
        ArrayList<String> ans = new ArrayList<>();
        for(String str : map.keySet()){
            // System.out.println(str + ", "+ map.get(str));
            if(courseList.contains(str.length())){
                if(maxList[str.length()] != 1 && map.get(str) == maxList[str.length()]){
                    ans.add(str);
                }
            }
        }
        // System.out.println(Arrays.toString(maxList));
        // System.out.println(ans);
        String[] answer = new String[ans.size()];
        int index = 0;
        for(String str : ans){
            answer[index++] = str;
        }
        Arrays.sort(answer);
        return answer;
    }
    
    static void combi(int index, String word, int last){
        
        if(index == last){
            // System.out.println(word);
            if(word.length() > 1){
                if(map.containsKey(word)){
                    map.replace(word, map.get(word)+1);
                }else{
                    map.put(word, 1);
                }
                maxList[word.length()] = (map.get(word) > maxList[word.length()])
                    ? map.get(word) : maxList[word.length()];
            }
            return;
        }
        combi(index+1, word+list[index], last);
        combi(index+1, word, last);
    }
}