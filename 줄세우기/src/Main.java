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
			ary[a].add(b); //A�ڿ� B�� �;��� // A->B
			indegree[b]++; //B�� ���ϴ� ������ ������
		}
		
		//System.out.println(Arrays.toString(indegree));
		
		LinkedList<Integer> q= new LinkedList<>();
		for(int i=0;i<indegree.length;i++) {
			if(indegree[i]==0) {
				q.offer(i);
			}
		}
		
		int cur;
		for(int i=0;i<N;i++) { //���������� �Ϸ���, �� ���Ҵ� vertex�� ����ŭ ��������
			if(q.isEmpty()) { //�׷��� �߰��� ť�� ��������, ���������� �� �� ���ٴ� ��
				System.out.println("fail");
				break;
			}
			
			cur = q.pop();
			System.out.print((cur+1)+" ");
			
			for(int next : ary[cur]) {
				//�ϴ� �ش� ���Ҵ� �������ϱ� ���Ҷ� ����� ��� �������� ���� ->indegree����
				//indegree�� 0�̸� �����̶�� �� -> ť�� �ֱ�
				if( --indegree[next] == 0) q.offer(next);
			}
			
		}
		

	}

	private static void dfs(int cur) {
		
		
		
	}

}
