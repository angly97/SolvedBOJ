import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(in.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<n; i++)
			pq.offer(Integer.parseInt(in.readLine()));
		
		
		int a, b;
		int sum = 0;
		
		while(!pq.isEmpty()) {
			
			a = pq.poll();
			
			if(!pq.isEmpty())
				b = pq.poll();
			else {
				break;
			}
			
			sum += (a+b);
			pq.offer(a+b);
			
		}
		
		System.out.println(sum);
	}
	
	
}