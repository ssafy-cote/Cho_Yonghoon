import java.util.*;
import java.io.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        PriorityQueue<Word> pq = new PriorityQueue<>();
        // 97 ~ 122 : a ~ z
        // 48 ~ 57 : 0 ~ 9
        int index = 0;
        for(String str : files) {
            String[] tempArray = str.split("");
            int numberStart = 0;
            int tailStart = -1;
            StringBuilder head = new StringBuilder();
            StringBuilder number = new StringBuilder();
            StringBuilder tail = new StringBuilder();
            tail.append("");
            for(int i = 0; i < tempArray.length; i++){
                int asci = tempArray[i].charAt(0);
                if ( 48 <= asci && asci <= 57){
                    numberStart = i;
                    break;
                }else{
                    head.append(tempArray[i]);
                }
            }
            for(int i = numberStart; i < tempArray.length; i++){
                int asci = tempArray[i].charAt(0);
                if(numberStart+5 <= i){
                    tailStart = i;
                    break;
                }
                if ( !(48 <= asci && asci <= 57) ){
                    tailStart = i;
                    break;
                }else{
                    number.append(tempArray[i]);
                }
            }
            
            Word word = new Word();
            
            if(tailStart != -1){
                for(int i = tailStart; i < tempArray.length; i++){
                    tail.append(tempArray[i]);
                }
            }
            word.head = head.toString();
            word.number = Integer.parseInt(number.toString());
            word.tail = tail.toString();
            word.index = index++;
            word.realNumber = number.toString();
            pq.add(word);
        }
        index = 0;
        while(!pq.isEmpty()){
            Word word = pq.poll();
            StringBuilder sb = new StringBuilder();
            answer[index] = 
                sb.append(word.head).append(word.realNumber).append(word.tail).toString();
            index++;         
            System.out.print(word);
        }
        
        return answer;
    }
    static class Word implements Comparable<Word> {
        String head;
        String realNumber;
        int number;
        String tail;
        int index;
        
        @Override
        public String toString(){
            return "[head: " + head + "] [realNumber: " + realNumber + "] [number: " + number + "] [tail: " + tail + "] [index: " + index + "]\n";
        }
        
        @Override
        public int compareTo(Word w){
            String thisWordHead = this.head.toLowerCase();
            String wWordHead = w.head.toLowerCase();
            if(thisWordHead.equals(wWordHead)){
                if(this.number == w.number){
                    return this.index - w.index;
                }
                return this.number - w.number;
            }
            return thisWordHead.compareTo(wWordHead);
        }
    }
}