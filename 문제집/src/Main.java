
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		StringTokenizer stk=new StringTokenizer(br.readLine());
		int N,M;
		N=Integer.parseInt(stk.nextToken());
		M=Integer.parseInt(stk.nextToken());
		
		ArrayList<Integer> ary[]=new ArrayList[N];
		int []indegree=new int [N];
		for(int i=0;i<N;i++) {
			ary[i]=new ArrayList<>();
		}
		int a,b;
		for(int k=0;k<M;k++) {
			stk=new StringTokenizer(br.readLine());
			a=Integer.parseInt(stk.nextToken())-1;
			b=Integer.parseInt(stk.nextToken())-1;
			ary[a].add(b); //a->b
			indegree[b]++;
		}
		
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		
		for(int i=0;i<N;i++) {
			if(indegree[i]==0) pq.offer(i);
		}
		
		for(int i=0;i<N;i++) {
			int temp = pq.poll();
			System.out.print((temp+1)+" ");
			for(int next: ary[temp]) {
				if(indegree[next]!=0) {
					indegree[next]--;
					if(indegree[next]==0)pq.offer(next);
				}
			}
		}
		
		
	}
	
}
