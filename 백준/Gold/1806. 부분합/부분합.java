import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int end = 0;
		int sum = 0;
		int len = n+1;
		
		for(int start=0; start<n; start++) {
			
			while(end < n && sum < s) {
//				System.out.println("in: "+start+" "+end);
				sum += arr[end++];
				
				if(sum >= s || end == n) {
					break;
				}
			}
			
			if(sum >= s) {
				len = Math.min(len, end-start);
//				System.out.println(start+" "+end+" "+sum+" "+len);
			}
			
			sum -= arr[start];
		}
		
		System.out.println(len == n+1 ? 0 : len);
		
	}

}