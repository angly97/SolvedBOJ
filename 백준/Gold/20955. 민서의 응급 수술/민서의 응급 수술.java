import java.io.*;
import java.util.*;

public class Main {

	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
//		StringTokenizer st;
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		

		// 레벨 & 부모 초기화
		parents = new int[n+1];
		for(int i=1; i<=n; i++)
			parents[i] = i;

		
		int cut = 0;
				
		int a, b;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(find(a) != find(b)) {
				union(a, b);
			}
			else {
				cut++;
			}
		}
		
		Set<Integer> groups = new HashSet<>();
		for(int i=1; i<=n; i++) {
			groups.add(find(i));
		}
		
		System.out.println(groups.size()-1 + cut);
		
	}
	
	public static int find(int x) {
		if(x != parents[x])
			parents[x] = find(parents[x]);
		return parents[x];
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(x < y) 	parents[x] = y;
		else 		parents[y] = x;
	}
}