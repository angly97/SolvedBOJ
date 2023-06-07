import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
    
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(in.readLine());

    	int n = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    
    	int[] housePos = new int[n];
    	
    	int x = 0;
    	for(int i=0; i<n; i++) {
    		x = Integer.parseInt(in.readLine());
    		housePos[i] = x;
    	}
    	
    	Arrays.sort(housePos);
    	int maxdiff = (housePos[n-1] - housePos[0]) / (c-1);
    	
    	int pos, predictedPos;
    	int idx = 0, selectedIdx;
    	
    	int mindiff = 1;
    	int middiff = 0;
    	while(mindiff <= maxdiff) {
//    		System.out.println("dd: "+mindiff+" "+maxdiff);
    		
    		middiff = (maxdiff + mindiff) / 2;
    		
    		pos = housePos[0];
    		int cnt = c-2;
    		
    		while(cnt > 0) {
    			
    			predictedPos = pos + middiff;
        		selectedIdx = Arrays.binarySearch(housePos, predictedPos);
        		
        		if(selectedIdx == -1) {
        			break;
        		}
        		
        		idx = selectedIdx;
        		if(idx < 0) {
        			idx = (idx+1) * -1;
        		}
        		
        		if(idx == n) {
        			break;
        		}
        		pos = housePos[idx];
        		cnt--;
    		}
    		
//    		System.out.println(cnt+" "+housePos[idx]);
    		if(cnt > 0 || housePos[n-1] - housePos[idx] < middiff) {
    			maxdiff = middiff - 1;
    		}
    		else {
//    			System.out.println(middiff);
    			mindiff = middiff + 1;
    		}
    		
    	}
    	
    	System.out.println(mindiff - 1);
    }
}