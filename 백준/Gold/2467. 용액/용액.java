import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] liquid = new int[n];
		
		for(int i=0; i<n; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int r = n-1;
		
		int valueToZero = Integer.MAX_VALUE;
		
		int sum = 0;
		int[] ans = new int[2];
		
		for(int l = 0; l<r; l++) {
//			System.out.println("out1: "+l+ " "+r);
			while(true) {

				sum = liquid[l] + liquid[r];
				
				if(Math.abs(sum) < valueToZero) {
//					System.out.println(l+","+r+": "+sum+" "+valueToZero);
					
					valueToZero = Math.abs(sum);
					ans[0] = liquid[l];
					ans[1] = liquid[r];
//					System.out.println(valueToZero+" "+Arrays.toString(ans));
					
				}
				
				if(l == r-1 || sum <= 0) break;
				
				r--;
			}

//			System.out.println("out2: "+l+ " "+r);
			
			if(l == r || sum == 0) break;
		}
		
		System.out.println(ans[0]+" "+ans[1]);
	}
	
}