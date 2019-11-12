import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int N,D,K,C; //접시의 수, 초밥의 종류, 연속해서 먹을 수 있는 수, 쿠폰 번호
	static int dishes[];
	static int didx;
	static int susi[];
	static int max;
	public static void main(String[] args) throws IOException{
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		dishes=new int [N];
		max =0;
		susi=new int [D+1];
		for(int i=0;i<N;i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}
		didx=0;
		
		
		
		for(int i=0;i<K;i++) {
			if(susi[dishes[i]]==0) ++max;
			++susi[dishes[i]];
		}
		
		if(susi[C]==0) {
			max++;
		}
		
		int prev = 0;
		didx = K;
		
		int temp = max;
		
		if(susi[C]==0) { temp--;}
		
		while(didx != K-1) {
			//이전에 있던 접시 빼고
			--susi[dishes[prev]];
			if(susi[dishes[prev]]==0) {
				temp--;
			}
			
			//앞 접시 더하고
			++susi[dishes[didx]];
			if(susi[dishes[didx]]==1) {
				temp++;
			}
			
			//지금까지 먹은 것 중에 쿠폰스시가 없으면
			if(susi[C]==0) {
				max = temp+1>max?temp+1 : max;
			}
			else {
				max=temp>max?temp:max;
			}
			
			prev++;
			didx++;
			
			prev = prev%N;
			didx= didx%N;
			
		}
		
		System.out.println(max);
		
	}
}
