import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        String[] skills = skill.split("");
        A : for(String str : skill_trees){
            String[] checkSkillTrees = str.split("");
            int index = 0;
            for(String check : checkSkillTrees){
                if(skill.contains(check)){
                    if(skills[index].equals(check)){
                        ++index;
                    }else{
                        continue A;
                    }
                }
            }
            ++answer;
        }        
        return answer;
    }
}