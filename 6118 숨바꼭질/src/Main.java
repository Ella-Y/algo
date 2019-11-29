import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static int N,M;
	static ArrayList<Integer> ary[];
	public static void main(String[] args) throws IOException{
		//br=new BufferedReader(new FileReader("input.txt"));
		br=new BufferedReader(new InputStreamReader(System.in));
		
		input();
		problem();

	}
	
	private static void input() throws IOException {
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		ary = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			ary[i]= new ArrayList<Integer>();
		}
		int a,b;
		for(int i=0;i<M;i++) {
			stk = new StringTokenizer(br.readLine());
			a = Integer.parseInt(stk.nextToken());
			b = Integer.parseInt(stk.nextToken());
			ary[a].add(b);
			ary[b].add(a);
		}
		
	}

	private static void problem() {
		
		//다익스트라, 헛간번호와 헛간까지의 거리, 헛간과 같은 거리를 갖는 헛간의 개수 출력하기
		
		int info[]=new int [N+1];
		for(int i=0;i<=N;i++) {
			info[i]=Integer.MAX_VALUE;
		}
		
		info[0]=0;
		info[1]=0;
		
		PriorityQueue<Pair> queue= new PriorityQueue<>();
		queue.offer(new Pair(0,1)); //시작 헛간 1
		
		while(!queue.isEmpty()) {
			Pair cur=queue.poll();
			//System.out.println(cur);
			for(int next : ary[cur.index]) {
				//cur에 연결된 모든 노드들에 대해서
				//cur에서 가는게 빠를까 다른데서 오는게 빠를까?
				if(info[next] > info[cur.index] + 1) {
					info[next]=info[cur.index]+1;
					queue.offer(new Pair(info[next],next));
				}
				
			}
			
		}
		
		//System.out.println(Arrays.toString(info));
		int a=0; //헛간번호
		int b=0; //헛간거리
		int c=0; //헛간의 개수
		for(int i=1;i<=N;i++) {
			if(b< info[i]) {
				b=info[i];
				a=i;
				c=1;
			}
			else if(b==info[i]) c++;
		}
		
		System.out.println(a+" "+b+" "+c);
		
		
	}
	
	static class Pair implements Comparable<Pair>{
		int val;
		int index;
		public Pair(int val, int index) {
			super();
			this.val = val;
			this.index = index;
		}
		@Override
		public String toString() {
			return "Pair [val=" + val + ", index=" + index + "]";
		}
		@Override
		public int compareTo(Pair o) {
			return val-o.val;
		}
		
	}

}
