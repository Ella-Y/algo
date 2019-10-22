import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Integer>ary[];
	static int indegree[];
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ary=new ArrayList[N];
		indegree =new int [N];
		
		for(int i=0;i<N;i++) ary[i] = new ArrayList<>();
		
		int a,b;
		
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken())-1;
			b=Integer.parseInt(st.nextToken())-1;
			ary[a].add(b); //A뒤에 B가 와야함 // A->B
			indegree[b]++; //B로 향하는 간선이 존재함
		}
		
		//System.out.println(Arrays.toString(indegree));
		
		LinkedList<Integer> q= new LinkedList<>();
		for(int i=0;i<indegree.length;i++) {
			if(indegree[i]==0) {
				q.offer(i);
			}
		}
		
		int cur;
		for(int i=0;i<N;i++) { //위상정렬을 하려면, 총 원소는 vertex의 수만큼 뽑혀야함
			if(q.isEmpty()) { //그러나 중간에 큐가 비어버리면, 위상정렬을 할 수 없다는 뜻
				System.out.println("fail");
				break;
			}
			
			cur = q.pop();
			System.out.print((cur+1)+" ");
			
			for(int next : ary[cur]) {
				//일단 해당 원소는 뽑혔으니까 원소랑 연결된 모든 간선들을 감소 ->indegree감소
				//indegree가 0이면 시작이라는 뜻 -> 큐에 넣기
				if( --indegree[next] == 0) q.offer(next);
			}
			
		}
		

	}

	private static void dfs(int cur) {
		
		
		
	}

}
