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
		
		
		// 투포인터 사용을 위해 arr 오름차순 정렬
		Arrays.sort(arr);
		
		int cnt = 0, sum = 0, right;
		
		for(int i=0; i<n; i++) {
			
			// left = 0, right = n-1부터 가운데로 모이면서 투포인터 수행
			right = n-1;
			for(int left = 0; left<right; left++) {
				
				// 자기자신(i) 제외
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
				
				// 자기 자신을 제외한 원소들로 합을 만들 수 있음
				if(sum == arr[i]) {
					cnt++;
					break;
				}
			}
		}
		
		System.out.println(cnt);
	   
   }
}