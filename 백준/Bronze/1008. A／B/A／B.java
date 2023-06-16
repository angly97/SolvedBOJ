import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
//		StringTokenizer st;

		double a = Integer.parseInt(st.nextToken());
		double b = Integer.parseInt(st.nextToken());

		System.out.print(a/b);
		
	}

}