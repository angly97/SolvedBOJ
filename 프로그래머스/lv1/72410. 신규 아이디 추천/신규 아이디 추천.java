import java.util.regex.*;
import java.util.*;

class Solution {
    public String solution(String new_id) {
        
        // new_id를 소문자로 다 바꾸고, 알바벳, 숫자, _, -, . 아닌것들을 전부 제외
        StringBuilder str = new StringBuilder();
        for(String token : new_id.toLowerCase().split("[^a-z0-9-_\\.]")) {
            str.append(token);
        }
        
        //
        str = new StringBuilder(str.toString().replaceAll("\\.+", "\\."));
        System.out.println(str);
        
        if(str.charAt(0) == '.')
            str.deleteCharAt(0);
        
        if(str.length() > 0){
            if(str.charAt(str.length()-1) == '.')
                str.deleteCharAt(str.length()-1);
        }
        else
            str.append('a');
        
        System.out.println(str);
        
        int end = Math.min(str.length(), 15);
        str = new StringBuilder(str.substring(0, end));
        
        
        System.out.println(str);
        if(str.charAt(str.length()-1) == '.')
            str.deleteCharAt(str.length()-1);
        
        System.out.println(str);
        
        if(str.length() <= 2) {
            char lastC = str.charAt(str.length()-1);
            for(int i=str.length(); i<3; i++) {
                str.append(lastC);
            }
        }
        
        
        return str.toString();
    }
}