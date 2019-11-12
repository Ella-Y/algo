import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException
	{
		int T = Integer.parseInt(br.readLine());

		for(int test=1;test<=T;test++)
		{
			problem(test);
		}

	}

	static boolean visited[];
	static int A[];
	static int B[];
	static ArrayList<Integer> ary[];
	private static void problem(int test) throws IOException{
		StringTokenizer stk=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		ary = new ArrayList[M+1];

		A=new int[M+1];
		B=new int[N+1];

		int a,b;
		for(int i=1;i<=M;i++) {
			stk=new StringTokenizer(br.readLine());
			a=Integer.parseInt(stk.nextToken());
			b=Integer.parseInt(stk.nextToken());
			ary[i] = new ArrayList<>();
			for(int k=a;k<=b;k++) {
				ary[i].add(k);
			}

		}

		int answer=0;
		boolean visited[];

		for(int i=1;i<=M;i++) {
			if(A[i]==0) {
				visited=new boolean[M+1];
				if(matching(visited,i)) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}

	private static boolean matching(boolean[] visited, int a) {
		
		visited[a] = true;
		for(int next : ary[a]) {
			if(B[next]==0 || !visited[B[next]] && matching(visited, B[next])) {
				A[a]=next;
				B[next]=a;
				return true;
			}
		}
		return false;
	}



}
