import java.util.*;
import java.io.*;


/*
 * str[left] != str[right]일 때, 먼저 str[left]를 삭제하고 for문 돌려보고, str[right]를 삭제하고 for문 돌려보는데
 * 먼저 str[left]를 삭제하고 for문 돌려볼때, right--를 하는 바람에, 
 *  str[right]를 삭제하고 for문 돌려볼때도 쓰는 right가 훼손되어서 틀림
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());
	
		int n = Integer.parseInt(in.readLine());
		
		int right;
		char[] str;
		boolean matched = true;
		
		StringBuilder out = new StringBuilder();
		nxtStr: for(int i=0; i<n; i++) {
		
			str = in.readLine().toCharArray();
			
			right = str.length-1;
			
			for(int left=0; left<right; left++) {
				
				// 좌우 비대칭인 곳 발견! 유사회문은 되는지 확인!
				if(str[left] != str[right]) {
					
					// a b c (d) b a == 유사 회문 
					if(left+1 == right) {
						out.append(1+"\n");
						continue nxtStr;		// 다음 문자열로 넘어가기
					}

					
					// str[left] 삭제할 경우
					matched = true;
					for(int l = left+1, r = right; l<r; l++) {
						
						// 근데 또 달라! 그럼 str[left] 삭제했을 때는 유사회분 아닌거
						if(str[l] != str[r]) {
							matched = false;
							break;
						}
						// 여기를 right로 했다가 아래 str[right]삭제할 때는 right가 원래대로 돌아와야하는데 안그래서 틀림
						r--;		
					}
					
					
					if(!matched) {
						matched = true;
						
						// str[right] 삭제할 경우
						for(int l = left,r = right-1; l<r; l++) {
							
							// 근데 또 달라! 그럼 str[right] 삭제했을 때 유사회분도 아닌거
							if(str[l] != str[r]) {
								matched = false;
								break;
							}
							r--;
						}
					}
					
					// str[left] 삭제했을 때도, str[right]삭제했을 때도 회문이 되지 못한 경우
					if(!matched) {
						out.append(2+"\n");
						continue nxtStr;		// 다음 문자열로 넘어가기
					}
					
					
					// 유사회문은 가능한 경우
					out.append(1+"\n");
					continue nxtStr;		// 다음 문자열로 넘어가기
				}
				
				right--;
			}
			
			// 회문임
			out.append(0+"\n");
			
		}
		
		System.out.println(out);
		
	}
}