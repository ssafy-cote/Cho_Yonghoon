package algo0411;

import java.util.*;

public class P_뉴스클러스터링_0411 {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        
        char[] temp1 = str1.toCharArray();
        char[] temp2 = str2.toCharArray();
        ArrayList<String> map1 = new ArrayList<>(temp1.length-1);
        ArrayList<String> map2 = new ArrayList<>(temp2.length-1);        
        for(int i = 0; i < temp1.length-1; i++){
            StringBuilder builder = new StringBuilder();
            if((64 < temp1[i] && temp1[i] < 91) && (64 < temp1[i+1] && temp1[i+1] < 91)){
                builder.append(temp1[i]).append(temp1[i+1]);
                map1.add(builder.toString());
            }
        }
        for(int i = 0; i < temp2.length-1; i++){
            StringBuilder builder = new StringBuilder();
            if((64 < temp2[i] && temp2[i] < 91) && (64 < temp2[i+1] && temp2[i+1] < 91)){
                builder.append(temp2[i]).append(temp2[i+1]);
                map2.add(builder.toString());
            }
        }
        System.out.println(map1);
        System.out.println(map2);
        
        // 합집합 만들기
        ArrayList<String> sum = new ArrayList<>();
        for(String str : map1){
            sum.add(str);
        }
        for(String str : map2){
            sum.add(str);
        }
        // 나중에 교집합 빼주기

        // 교집합 만들기
        ArrayList<String> same = new ArrayList<>();
        for(String str : map1){
            if(map2.contains(str)){
                same.add(str);
                map2.remove(str);
            }
        }
        System.out.println("교집합:"+same);
        // 합집합에서 교집합빼기
        for(String str : same){
            sum.remove(str);
        }
        System.out.println("합집합:" + sum);
        
        
        double ans = 0.0;
        System.out.println(sum.size() + " :: " + same.size());
        if(sum.size() == 0){
            ans = 1.0;
        }else{
            ans = (same.size()*1.0) / (sum.size()*1.0);
        }
        ans *= 65536;
        System.out.println(ans);
        answer = (int)Math.floor(ans);
        return answer;
    }
}