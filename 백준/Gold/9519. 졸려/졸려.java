import java.util.*;
import java.io.*;


public class Main {
	
   public static void main(String[] args) throws Exception {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//      StringTokenizer st = new StringTokenizer(in.readLine());

      int x = Integer.parseInt(in.readLine());
      
      char[] word = in.readLine().toCharArray();
      
      int n = word.length;
      char[] prevword = new char[n];
      
      // history.get(i) : i번 깜빡이기 전의 단어 상태
      Map<Integer, char[]> history = new HashMap<>();
      
      // 해당 단어가 이전에 나온적 있는지 저장
      Set<String> visited = new HashSet<>(); 
      
      int cnt = 0;
      
      for(; cnt<x; cnt++) {
    	  
    	  // 현재 단어가 이전에 나온 적 있으면 탈출
    	  String strword = tostr(word);
    	  if(visited.contains(strword)) break;
    	  visited.add(strword);
    	  
    	  history.put(cnt, word.clone());
    	  
    	  // 한번 깜빡이기 전으로 되돌리기
    	  for(int i=0; i<n; i++) {
    		  if(i%2 == 0) {
    			  prevword[i/2] = word[i];
    		  }
    		  else {
    			  prevword[n-1-i/2] = word[i];
    		  }
    	  }
    	  
    	  // word 갱신
    	  for(int i=0; i<n; i++) {
    		  word[i] = prevword[i];
    	  }
    	  
      }

//      System.out.println(cnt+" "+Arrays.toString(word));
//      
//      for(int c : history.keySet()) {
//    	  System.out.println(c+" "+Arrays.toString(history.get(c)));
//      }
//      
      if(cnt < x) {
    	  System.out.println(tostr(history.get(x%cnt)));
      }
      else {
    	  System.out.println(tostr(word));
      }
   }
   
   static String tostr(char[] word) {
	   StringBuilder str = new StringBuilder();
	   for(char c : word)
		   str.append(c);
	   return str.toString();
   }
   
}