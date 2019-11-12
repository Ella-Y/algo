import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
	static int ary[];
	static int A[];
	static boolean prime[];
	public static void main(String[] args) throws IOException{
		//소수구하기
		getPrime();
		//System.out.println(Arrays.toString(prime));
		
		int N=Integer.parseInt(br.readLine());
		StringTokenizer stk=new StringTokenizer(br.readLine());
		ary = new int[N];
		
		for(int i=0;i<N;i++) {
			ary[i] = Integer.parseInt(stk.nextToken());
		}
		
		boolean visited[]=new boolean[N];
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		boolean flag;
		
		for(int k=1;k<N;k++) {
			A=new int [N];
			visited = new boolean[N];
			if(prime[ary[0] + ary[k]]) {
				A[0] = k;
				A[k] = 0;
				flag=true;
				inner:for(int i=1;i<N;i++) {
					if(A[i] ==0) { //매칭이 안되었으면 매칭하기
						if(!matching(visited, i, k)) {
							flag=false;
							break inner;
						}
					}
				}
				System.out.println(Arrays.toString(A));
				if(flag) {
					pq.offer(ary[A[0]]);
				}
				
			}
			
		}
		
		while(!pq.isEmpty()) {
			bw.write(pq.poll()+" ");
		}
	}
	
	private static boolean matching(boolean[] visited,int cur,int k) {
		visited[cur] = true;
		for(int i=1;i<ary.length;i++) {
			if(cur == i || i == k) continue;
			if(prime[ary[cur]+ary[i]]) {
				if(A[i]==0 || !visited[A[i]] && matching(visited,A[i],k)) {
					//매칭 가능함
					A[i] = cur;
					A[cur]=i;
				}
			}
			
		}
		return false;
	}

	private static void getPrime() {
		prime = new boolean [2000+1];
		Arrays.fill(prime, true);
		
		double size = Math.sqrt(prime.length);
		for(int i=2;i<size;i++) {
			if(prime[i]) {
				for(int k=i*i;k<prime.length;k+=i) {
					prime[k]=false;
				}
			}
		}
		
	}
	
}
