import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static boolean prime[];
	static char[] A;
	static int B;
	
	public static void main(String[] args) throws IOException{
		getPrime();
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			input();
			problem();
		}
	}
	
	private static void input() throws IOException{
		StringTokenizer stk=new StringTokenizer(br.readLine());
		A= stk.nextToken().toCharArray();
		B= Integer.parseInt(stk.nextToken());
	}
	
	private static void getPrime() {
		prime=new boolean[9999+1];
		Arrays.fill(prime, true);
		prime[0]=prime[1]=false;
		
		int size=(int)Math.sqrt(10000);
		for(int i=2;i<=size;i++) {
			if(prime[i]) {
				for(int j=i+i;j<=9999;j=j+i) {
					prime[j]=false;
				}
			}
		}
		
		/*
		 * for(int i=1;i<=9999;i++) { if(prime[i]) System.out.print(i+" "); }
		 * System.out.println();
		 */
	}

	private static void problem() {
		//두 소수 사이의 변환에 필요한 최소 회수를 구한다.
		//A->B로 바꾸는 과정에서도 항상 4자리 소수임을 유지해야한다.
		//0039같은건 절대 안된다.
		//불가능한 경우는 Impossible을 출력한다.
		boolean []visited=new boolean[9999+1];
		
		for(int i=0;i<1000;i++) {
			visited[i]=true;
		}
		
		Queue<Integer> q=new LinkedList<>();
		int cur= to10(A);
		visited[cur]=true;
		q.offer(cur);
		
		char[] cAry;
		int num,size;
		char ch;
		
		int cnt=0;
		boolean pass=false;
		outer:while(!q.isEmpty()) {
			size=q.size();
			for(int s=0;s<size;s++) {
				cur = q.poll();
				if(cur==B) {
					pass=true;
					break outer;
				}
				//이 값을 바꾸어 본다.
				cAry=toAry(cur);
				
				for(int i=0;i<4;i++) {
					ch = cAry[i];
					for(int k='0';k<='9';k++) {
						cAry[i]=(char)k;
						num=to10(cAry);
						if(prime[num] && !visited[num]) {
							visited[num]=true;
							q.offer(num);
						}
					}
					cAry[i]=ch;
				}
			}
			cnt++;
		}
		
		if(!pass) System.out.println("Impossible");
		System.out.println(cnt);
		
	}

	private static char[] toAry(int cur) {
		char []cary=new char[4];
		int d=1000;
		for(int i=0;i<4;i++) {
			cary[i]=(char)((cur/d)+'0');
			cur-=(cur/d)*d;
			d=d/10;
		}
		return cary;
	}

	private static int to10(char[] a2) {
		int val=0;
		int d=1000;
		for(int i=0;i<4;i++) {
			val+=(a2[i]-'0')*d;
			d/=10;
		}
		return val;
	}
	
}
