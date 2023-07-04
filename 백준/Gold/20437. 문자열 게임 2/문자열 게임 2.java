import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder out = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int t=0; t<T; t++) {
			
			char[] str = in.readLine().toCharArray();
			int k = Integer.parseInt(in.readLine());
			
			int len = str.length;
			
			
			// 최소 길이
			int min = len+1;
			
			for(int i=0; i<26; i++) {		// a ~ z
				
				char c = (char)('a'+i);
				
				int e = 0;					// 끝 인덱스
				int cnt = 0;
				
				for(int s=0; s<len; s++) {
					
					while(e < len && cnt < k) {
						if(str[e++] == c) {
							cnt++;
						}
						
						if(e == len || cnt >= k) break;
					}
					
					if(cnt == k) {
						min = Math.min(min, e-s);
					}
					
					if(str[s] == c)	cnt--;
				}
				
			}
			
			if(min == len+1) {
				out.append(-1+"\n");
				continue;
			}
			
			
			// 최대 길이
			int max = 0;
			
			for(int i=0; i<26; i++) {		// a ~ z

				char c = (char)('a'+i);
				
				int e = 0;				// 끝 인덱스 구하기
				for(; e<len; e++)
					if(str[e] == c) break;
				
				
				int cnt = 0;
				
				
				for(int s=0; s<len; s++) {
					
					if(str[s] != c) {
						continue;
					}
					
					while(e < len && cnt < k) {
						if(str[e++] == c) {
							cnt++;
						}
						
						if(e == len || cnt >= k) break;
					}
					
					if(cnt == k) {
						max = Math.max(max, e-s);
					}
					
					cnt--;
				}
				
			}
			
			out.append(max == 0 ? (-1+"\n") : (min+" "+max+"\n"));
		}
		
		System.out.print(out);
		
	}
	
}