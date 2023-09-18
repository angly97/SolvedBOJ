import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());	// 웹 페이지 종류 수
		int q = Integer.parseInt(st.nextToken());	// 작업 갯수
		int c = Integer.parseInt(st.nextToken());	// 캐시 용량
		
		st = new StringTokenizer(in.readLine());
		int[] cap = new int[n+1];					// cap[i] : i번째 페이지의 용량
		for(int i=1; i<=n; i++) {
			cap[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int now = 0;
		int cache = 0;
		Deque<Integer> back = new ArrayDeque<>();
		Stack<Integer> front = new Stack<>();
		
		char type;
		int page;
		
		for(int round=0; round<q; round++) {
			st = new StringTokenizer(in.readLine());
			
			type = st.nextToken().charAt(0);
			if(st.hasMoreTokens()) {			// 접속 작업
				page = Integer.parseInt(st.nextToken());
				
				// 앞으로가기 공간 비우고, 비워진 페이지 용량만큼 캐시 용량 비우기
				while(!front.isEmpty()) {
					cache -= cap[front.pop()];
				}
				
				// 현재 페이지를 뒤로가기 공간에 추가
				if(now != 0) {
					back.offer(now);
				}
				
				// 새로운 페이지를 현재 페이지로 하고, 캐시에 새로운 페이지의 용량 추가
				now = page;
				cache += cap[now];
				
				// 캐시 용량 초과할 경우, 뒤로가기 공간에서 가장 오래된 페이지 삭제
				while(cache > c) {
					cache -= cap[back.pollFirst()];
				}
			}
			else {								// 뒤로가기 or 앞으로가기 or 압축 작업
				switch(type) {
				case 'B':
					if(!back.isEmpty()) {
						front.push(now);
						now = back.pollLast();
					}
					break;
				case 'F':
					if(!front.isEmpty()) {
						back.offer(now);
						now = front.pop();
					}
					break;
				case 'C':
					int prev = 0, cur;
					int size = back.size();
					while(size-->0) {
						cur = back.pollFirst();
						if(cur != prev) {			// 이전 페이지랑 다르면, back에 추가
							back.offerLast(cur);
						}
						else {						// 이전 페이지랑 같으면, back에 추가 안하고, 캐시 용량에서 제거
							cache -= cap[cur];
						}
						prev = cur;					// 이전 페이지 갱신
					}
					break;
				}
			}
		}
		
		
		StringBuilder out = new StringBuilder();
		out.append(now+"\n");
		
		if(!back.isEmpty()) {
			while(!back.isEmpty()) {
				out.append(back.pollLast()+" ");
			}
			out.append("\n");
		}else {
			out.append(-1+"\n");
		}
		
		if(!front.isEmpty()) {
			while(!front.isEmpty()) {
				out.append(front.pop()+" ");
			}
			out.append("\n");
		}else {
			out.append(-1+"\n");
		}
		
		System.out.print(out);
	}
}