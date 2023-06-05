import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
    
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	
    	char[] str = in.readLine().toCharArray();
    	char[] boom = in.readLine().toCharArray();
    	StringBuilder out = new StringBuilder();
    	
    	int slen = str.length;
		int blen = boom.length;
    	
    	for(int i=0; i<slen; i++) {
    		out.append(str[i]);
    		
    		if(out.length() >= blen) {
    			boolean ok = true;
    		
    			for(int j=0; j<blen; j++) {
    				char outChar = out.charAt(out.length() - blen + j);
    				if(outChar != boom[j]) {
    					ok = false;
    					break;
    				}
    			}
    			
    			if(ok) {
    				out.delete(out.length() - blen, out.length());
    			}
    		}
    	}
    	
        if(out.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(out);
        }
    	
    }
}