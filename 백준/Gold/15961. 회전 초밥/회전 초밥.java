import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());	// 벨트 위 접시 수
		int d = Integer.parseInt(st.nextToken());	// 초밥 종류 수
		int k = Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken());	// 쿠폰 번호
		
		int[] belt = new int[n];
		for(int i=0; i<n; i++) {
			belt[i] = Integer.parseInt(in.readLine());
		}
		
		Map<Integer, Integer> ate = new HashMap<>();
		
		int ans = 0;
		
		for(int start = 0; start < n; start++) {
			if(start == 0)
				for(int end=0; end<k; end++)
					if(ate.containsKey(belt[end]))
						ate.put(belt[end], ate.get(belt[end])+1);
					else
						ate.put(belt[end], 1);
			else if(start <= n-k) {
				int end = start+k-1;
				if(ate.containsKey(belt[end]))
					ate.put(belt[end], ate.get(belt[end])+1);
				else
					ate.put(belt[end], 1);
			}
			else {
				int end = (start+k-1)%n;
				if(ate.containsKey(belt[end]))
					ate.put(belt[end], ate.get(belt[end])+1);
				else
					ate.put(belt[end], 1);
			}
			
			if(ate.containsKey(c))
				ans = Math.max(ans, ate.size());
			else
				ans = Math.max(ans, ate.size()+1); 
			
			int remain = ate.get(belt[start]);
			if(remain == 1)
				ate.remove(belt[start]);
			else
				ate.put(belt[start], remain-1);
		}
		
		System.out.println(ans);
		
	}

}