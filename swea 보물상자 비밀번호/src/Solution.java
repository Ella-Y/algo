import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int K;
	static char numbers[];
	static TreeSet<Long> ts;

	public static void main(String[] args) throws IOException{

		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			problem(test); }

		//test();

	}



	private static void problem(int test) throws IOException{
		StringTokenizer stk= new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		ts = new TreeSet<>();
		numbers=br.readLine().toCharArray();

		final int cnt = N/4; //한 변의 길이
		int s=N-cnt+1;
		
		for(int i=0;i<cnt;i++) {
			//회전
			//이제 시작 지점을 구해야함
			//숫자 개수 구하기
			int t=s%N;
			for(int k=0;k<4;k++) {
				Long num=getNumber(t,(t+cnt)%N);
				ts.add(num);
				t+=cnt;
				t=t%N;
			}
			s++;
		}
		long answer=0;
		
		for(int i=0;i<K;i++) {
			answer=ts.pollLast();
		}
		System.out.println("#"+test+" "+answer);

	}

	private static Long getNumber(int start, int end) {
		//start 부터 end 포함 안함
		StringBuilder sb= new StringBuilder();

		long answer=to10(numbers[start]);
		sb.append(numbers[start]);
		int idx=(start+1)%(N);
		long temp = 0;
		while(idx!=end) {
			temp = to10(numbers[idx]);
			sb.append(numbers[idx]);
			answer= answer*16 + temp;
			++idx;
			idx = idx%(N);
		}
		//System.out.println(sb);
		return answer;
	}

	private static int to10(char ch) {
		int val=0;
		if(ch >= 'A') {
			val = ch-'A'+10;
		}else if(ch>='0') {
			val = ch-'0';
		}
		return val;
	}


	
}
