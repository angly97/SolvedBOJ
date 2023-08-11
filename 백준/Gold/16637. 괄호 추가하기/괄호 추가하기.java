import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		
		// dfs에서 계산의 편의를 위해, 맨 앞에 +를 추가; 5+4  나  0+5+4나 똑같음
		char[] formula = new char[n+1];
		formula[0] = '+';
		
		char[] inputFormula = in.readLine().toCharArray();
		for(int i=1; i<=n; i++) {
			formula[i] = inputFormula[i-1];
		}
		
		// dfs
		dfs(1, n, 0, formula);
		
		System.out.println(ans);
	}
	
	
	static int ans = Integer.MIN_VALUE;
	
	
	static int cal(int a, char op, int b) {
		switch(op) {
		case '+': 	return a+b;
		case '-': 	return a-b;
		case '*':	return a*b;
		default:	return a;
		}
	}
	
	// formula[now]는 언제나 숫자. 연산자 아니라
	static void dfs(int now, int n, int sum, char[] formula) {
		// 수식의 끝까지 모두 계산하였으면, 최댓값 갱신
		if(now > n) {
			ans = Math.max(ans, sum);
			return;
		}
		
		// now위치에서 괄효 연산하지 않는 경우
		dfs(now+2, n, cal(sum, formula[now-1], formula[now]-'0'), formula);
		
		
		// now위치에서 괄호 연산하는 경우
		int nxtSum = sum;
		
		if(0 < now && now < n-1) {
			nxtSum = cal(nxtSum, formula[now-1], cal(formula[now]-'0', formula[now+1], formula[now+2]-'0'));
			dfs(now+4, n, nxtSum, formula);
		}
		
	}
}