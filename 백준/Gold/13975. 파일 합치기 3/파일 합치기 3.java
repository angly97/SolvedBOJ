import java.util.*;
import java.io.*;

/*
 * Long 주의
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		StringBuilder out = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		int k;
		long a, b, ans = 0;
		PriorityQueue<Long> files;
		
		for(int t=0; t<T; t++) {
			k = Integer.parseInt(in.readLine());
			files = new PriorityQueue<>();
			
			st = new StringTokenizer(in.readLine());
			
			for(int i=0; i<k; i++) {
				files.offer(Long.parseLong(st.nextToken()));
			}
			
			ans = 0;
			while(true) {
				a = files.poll();
				if(files.isEmpty()) {
					break;
				}
				
				b = files.poll();
				
				files.offer(a+b);
				ans += (a+b);
			}
			
			out.append(ans+"\n");
		}
		System.out.println(out);
	}
}