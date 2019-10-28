import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	private static BufferedReader br=new BufferedReader (new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException{
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem(test);
		}


	}
	private static char ary[];
	private static StringBuilder answer;
	public static void problem(int test) throws IOException{
		int N = Integer.parseInt(br.readLine());
		ary = new char[N];
		for(int i=0;i<N;i++) {
			ary[i] = br.readLine().charAt(0);
		}

		answer=new StringBuilder();

		int f=0;
		int e=ary.length-1;
		int tf,te;

		while(f<=e) {
			if(ary[f]==ary[e]) {
				tf=f; te=e;
				
				while(tf<te && ary[tf]==ary[te]) {
					tf++; te--;
				}
				
				if(tf>te) { //번갈아가면서 뽑기
					while(f<=e) {
						answer.append(ary[f++]);
						answer.append(ary[e--]);
					}
					break;
				}
				
				if(ary[tf]<ary[te]) {
					answer.append(ary[f++]);
				}else answer.append(ary[e--]);

			}else if(ary[f]<ary[e]) {
				answer.append(ary[f++]);
			}else {
				answer.append(ary[e--]);
			}

		}
		
		System.out.println("#"+test+" "+answer);

	}



}
