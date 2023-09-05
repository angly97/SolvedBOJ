import java.util.*;
import java.io.*;
/*
 * 가장 먼저 종료되는 강의실과, 현재 배정될 강의실의 시작시간만 비교해도 괜춘
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;//= new StringTokenizer(in.readLine());

		int n = Integer.parseInt(in.readLine());
		
		int[][] lectures = new int[n][2];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			lectures[i][0] = Integer.parseInt(st.nextToken());
			lectures[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 먼저 시작하는, 먼저 끝나는 강의순으로 정렬
		Arrays.sort(lectures, (a, b) -> {
			if(a[0] == b[0])	return Integer.compare(a[1], b[1]);
			return Integer.compare(a[0], b[0]);
		});
		
		// 강의실들의 강의 종료 시간; 초기에는 0
		PriorityQueue<Integer> endTimes = new PriorityQueue<>();
		endTimes.offer(0);
		
		int nowEndTime;
		
		for(int[] lec : lectures) {
			
			nowEndTime = endTimes.peek();           // 가장 먼저 끝나는 강의실의 종료시간
			
			if(nowEndTime <= lec[0]) {				// 가장 먼저 끝나는 강의실에 강의를 배정
				endTimes.poll();
				endTimes.offer(lec[1]);
			}
			else {									// 가장 먼저 끝나는 강의실의 종료시간도 현재 강의 시작시간보다 늦다면, 강의실 추가
				endTimes.offer(lec[1]);
			}
		}
		
		System.out.println(endTimes.size());
	}
}