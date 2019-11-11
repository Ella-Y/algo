import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static ArrayList<Integer> works[];
	public static void main(String[] args) throws IOException{
		StringTokenizer stk=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(stk.nextToken());
		int M=Integer.parseInt(stk.nextToken());
		works = new ArrayList[N+1];
		
		int a,b;
		for(int i=0;i<N;i++) {
			stk=new StringTokenizer(br.readLine());
			a=Integer.parseInt(stk.nextToken());
			works[i+1] = new ArrayList<>();
			for(int k=0;k<a;k++) {
				works[i+1].add(Integer.parseInt(stk.nextToken()));
			}
		}
		
		A=new int[N+1]; //직원의 수
		B=new int[M+1]; //일의 수
		
		int answer=0;
		boolean[]visited;
		for(int i=1;i<=N;i++) {
			visited=new boolean[N+1];
			if(A[i]==0 && matching(visited,i)) {
				answer++;
			}
		}
		
		bw.write(answer+"\n");
		
		bw.close();
	}
	private static int A[]; //직원이 맡은 일의 번호
	private static int B[]; //일을 담당한 직원의 번호
	private static boolean matching(boolean[] visited, int cur) {
		//현재 직원 cur
		visited[cur] = true;
		
		for(int nextJob: works[cur]) {
			if(B[nextJob]==0 || !visited[B[nextJob]] && matching(visited,B[nextJob])) {
				A[cur] = nextJob;
				B[nextJob]=cur;
				return true;
			}
		}
		
		
		return false;
	}
}
