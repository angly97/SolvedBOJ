import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		for(int t=0; t<T; t++) {
			
			int n = Integer.parseInt(in.readLine());

			st = new StringTokenizer(in.readLine());
			int[] home = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			int[][] conv = new int[n][2];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(in.readLine());
				conv[i][0] = Integer.parseInt(st.nextToken());
				conv[i][1] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(in.readLine());
			int[] dest = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			Queue<int[]> s = new ArrayDeque<>();
			s.add(new int[] {home[0], home[1]});
			
			boolean[] visited= new boolean[n];
			
			String ans = "sad";
			while(!s.isEmpty()) {
				int[] cur = s.poll();
				if(Math.abs(cur[0]-dest[0]) + Math.abs(cur[1]-dest[1]) <= 1000) {
					ans = "happy";
					break;
				}
				
				for(int i=0; i<n; i++) {
					if(!visited[i]) {
						int dis = Math.abs(cur[0]-conv[i][0]) + Math.abs(cur[1]-conv[i][1]);
						if(dis <= 1000) {
							visited[i] = true;
							s.add(new int[] {conv[i][0], conv[i][1]});
						}
					}
				}
			}
			
			out.append(ans+"\n");
		}
		System.out.print(out);
	}

}