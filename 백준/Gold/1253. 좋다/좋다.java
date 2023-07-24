import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(in.readLine());
		
		st = new StringTokenizer(in.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int cnt = 0, sum = 0, right;
		
		for(int i=0; i<n; i++) {
			
			right = n-1;
			for(int left = 0; left<right; left++) {
				
				if(i == left) continue;
				else if(i == right) {
					right--;
				}
				
				sum = arr[left] + arr[right];
				while(right > left && sum > arr[i]) {
					
					if(i != right){
						sum = arr[left]+arr[right];
						if(sum <= arr[i]) break;
					}
					right--;
				}
				
				if(sum == arr[i]) {
    				cnt++;
					break;
				}
			}
		}
		
		System.out.println(cnt);
	   
   }
}