import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int table[][];
	static int answer;

	public static void main(String[] args) throws IOException {
		
		StringTokenizer st;
		
		
		N= Integer.parseInt(br.readLine());
		table = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				table[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		answer=Integer.MAX_VALUE;
		combination(new int [N/2],0,0);
		System.out.println(answer);
		
		
	}

	//pidx : picked idx
	private static void combination(int[] picked, int idx, int pidx) {
		if(pidx==picked.length) {
			
			boolean check[]=new boolean[N];
			ArrayList<Integer> teamA=new ArrayList<>();
			ArrayList<Integer> teamB=new ArrayList<>();	
			for(int i=0;i<picked.length;i++) {
				check[picked[i]]=true;
			}
			
			for(int i=0;i<N;i++) {
				if(check[i]) teamA.add(i);
				else teamB.add(i);
			}
			
			lookUpPower(teamA,teamB);
			
			return;
		}
		
		for(int i=idx;i<N;i++) {
			picked[pidx]=i;
			combination(picked,i+1,pidx+1);
		}
		
		
	}

	private static void lookUpPower(ArrayList<Integer> teamA, ArrayList<Integer> teamB) {
		int ta=0;
		for(int a:teamA) {
			for(int b:teamA) {
				ta+=table[a][b];
			}
		}
		int tb=0;
		for(int a:teamB) {
			for(int b:teamB) {
				tb+=table[a][b];
			}
		}
		answer = Math.min(Math.abs(ta-tb), answer);
	}


}
