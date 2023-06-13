import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(in.readLine());

    	int num = Integer.parseInt(st.nextToken());
    	int n = Integer.parseInt(st.nextToken());
    	
    	int remain = 0;
    	StringBuilder result = new StringBuilder();
    	
    	int laterflag = 1;
    	
    	if(num < 0 && n > 0) {num *= -1; laterflag = -1;}
    	
    	while(true) {
//    		System.out.println("-----------------");

    		remain = num%n;
    		num /= n;
    		
//    		System.out.println(num+" "+remain+" "+result);
    		if(remain < 0) {
    			num += num<0 ? -1 : 1;
    			remain -= n;
    		}
    		
    		result.append(remain);
//    		System.out.println(num+" "+remain+" "+result);
    			
    		if(num == 0) break;
    		
    	}
    	
    	if(laterflag == -1) {
    		result.append("-");
    	}
    	
    	System.out.println(result.reverse());
    }
}