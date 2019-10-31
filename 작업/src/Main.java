import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int N=Integer.parseInt(br.readLine());
		ArrayList<Integer> ary[]=new ArrayList[N];
		int []indegree=new int[N];
		int []time=new int[N];
		
		
		StringTokenizer stk;
		PriorityQueue<Job> pq=new PriorityQueue<>();
		
		int k,temp;
		for(int i=0;i<N;i++) {
			ary[i]=new ArrayList<>();
			stk=new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(stk.nextToken());
			k=Integer.parseInt(stk.nextToken());
			indegree[i]=k;
			for(int j=0;j<k;j++) {
				temp = Integer.parseInt(stk.nextToken())-1;
				ary[i].add(temp);
				indegree[temp]++;
			}
		}
		
		for(int i=0;i<N;i++) {
			if(indegree[i]==0) pq.offer(new Job(0,time[i],i));
		}
		
		int start=0, fin=0;
		int size;
		
		Job job;
		for(int i=0;i<N;i++) {
			size=pq.size();
			job = pq.poll();
			System.out.println(job);
			for(int next:ary[job.idx]) {
				indegree[next]--;
				if(indegree[next]==0) pq.offer(new Job(job.fin,job.fin+time[next],next));
			}
			
		}
		
		

	}
	
	static class Job implements Comparable<Job>{
		int start;
		int fin;
		int idx;
		
		public Job(int start, int fin, int idx) {
			super();
			this.start = start;
			this.fin = fin;
			this.idx = idx;
		}
		public Job() {
			super();
		}
		@Override
		public String toString() {
			return "Job [start=" + start + ", fin=" + fin + ", idx=" + idx + "]";
		}
		@Override
		public int compareTo(Job o) {
			if(o.start == start) {
				return o.fin-fin;
			}
			return o.start-start;
		}
		
	}


}
