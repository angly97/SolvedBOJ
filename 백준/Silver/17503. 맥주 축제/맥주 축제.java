import java.util.*;
import java.io.*;


/*
 * 이분탐색보다 우선순위큐가 훨씬 빠르다!
 * 왜그런건진 모르겠다
 * 이분탐색으로 했다가 시간초과남
 * 
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] beers = new int[k][2];
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(in.readLine());
			beers[i][0] = Integer.parseInt(st.nextToken());
			beers[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(beers, (b1, b2) -> {
			return Integer.compare(b1[1], b2[1]);
		});
		
		
		// 처음 n개 마시는 도수와 선호도 계산
		int minLevel = beers[n-1][1];
		int likeSum = 0;										// 현재까지 만족도 합
		PriorityQueue<Integer> topN = new PriorityQueue<>();	// 상위 N개 만족도 목록
		for(int i=0; i<n; i++) {
			topN.offer(beers[i][0]);
			likeSum +=beers[i][0];
		}
		
		if(likeSum < m) {

			minLevel = -1;

			int min = 0;
			for(int i=n; i<k; i++) {
				
				min = topN.peek();
				
				// 지금까지 제일 작은 만족도와 현재 맥주의 만족도 비교
				if(min < beers[i][0]) {
					likeSum -= min;
					likeSum += beers[i][0];
					
					// 제일 작은거 빼고 현재 만족도 추가
					topN.poll();
					topN.offer(beers[i][0]);
					
					// 만족도 m 이상이면 탈출
					if(likeSum >= m) {
						minLevel = beers[i][1];
						break;
					}
				}
			}
 		}
		
		System.out.println(minLevel);
	}
	
	
	// arr에서 key보다 작거나 같은 원소의 위치 찾기
	static int bisearch(int[] arr, int key, int n) {
		int left = 0;
		int right = n-1;
		int mid = 0;
		
		while(left < right) {
			mid = (left + right)/2;
			if(arr[mid] <= key)		left = mid;
			else 					right = mid-1;
		}
		
		return left;
	}
}