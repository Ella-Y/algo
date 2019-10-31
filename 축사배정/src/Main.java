import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static boolean visited[];

//A그룹의 i노드에서 B그룹의 j로 갈때, A[i]는 이러한 j들의 모음
	static ArrayList<Integer> ary[]; 

	// A[i], B[i]: 각 정점이 매칭된 반대편 정점 번호를 기록한다.
	static int A[];
	static int B[];
	
	public static void main(String[] args) throws IOException{
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken())+1; //축사의 총 개수  //축사는 1부터 시작함..
		//input ------
		ary=new ArrayList[M];
		
		for(int i=0;i<M;i++) {
			ary[i]=new ArrayList<Integer>();
		}
		
		for(int i=0;i<N;i++) {
			stk=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(stk.nextToken());
			
			for(int j=0;j<s;j++) {
				ary[i+1].add(Integer.parseInt(stk.nextToken()));
			}
		}
		//input end------

		int match=0;
		
		A=new int[M];
		B=new int[M];
		
		
		for(int i=1;i<M;i++) {
			if(A[i] == 0) { //아직 매칭이 되지 않았다면,
				visited=new boolean[M];
				if(matching(i)) {
					match++;
				}
				
			}
		}
		
		System.out.println(match);

	}
	
	private static boolean matching(int a) {
		//A그룹에 속한 정점를 이분매칭 시키자.
		visited[a]=true;
		for(int b : ary[a]) { //연결된 모든 B그룹의 정점들에 한해서,
			//매칭이 안되었으면, 바로 넣고, 매칭되어있는데 지금 뭔가 충돌하는 일이 잇으니까 다른 정점이랑 매칭시키자.
			if(B[b] == 0 || !visited[B[b]] && matching(B[b])) {
				A[a] = b; //A그룹의 a노드는  B그룹의 b와 매칭된다.
				B[b] = a; //B그룹의 b노드는 A그룹의 a와 매칭된다.
				
				//매칭되었으면 true
				return true;
			}
		}
		
		return false; //매칭이 실패했다!
	}

}