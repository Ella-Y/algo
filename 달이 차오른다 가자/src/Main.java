import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M; //세로, 가로
    static boolean keys[]; //a,b,c,d,e,f
    static char mmap[][];
    static boolean visited[][][]; //[i][j][열쇠의 타입]
    static int di[]={-1,0,1,0};
    static int dj[]={0,-1,0,1};
    static Pair start;
    static BufferedReader br;
    
    public static void main(String[] args) throws Exception {
        //여행길을 떠나기 위해 미로를 탈출하려고 한다.
        //# : 이동불가 , ABCDEF 열쇠가 있어야 갈 수 있음
        //. ,abcdef : 열쇠
        //탈출하는 곳은 여러 곳일 수도 있다.
        //열쇠를 얻어서 길을 돌아갈 수도 있다.
    	input();
    	
        Queue<Pair>queue=new LinkedList<>();
        queue.offer(start);
        int cnt=0;
        int ti,tj;
        Pair cur;
        
        outer:while(!queue.isEmpty()) {
        	int size=0;
        	for(int s=0;s<size;s++) {
        		cur = queue.poll();
        		if(mmap[cur.i][cur.j]=='1') {
        			break outer;
        		}
        		for(int z=0;z<4;z++) {
        			ti = cur.i+di[z];
        			tj = cur.j+dj[z];
        			if(0<=ti && ti<N && 0<=tj && tj<M && mmap[ti][tj]!='#') {
        				//아래는 벽이 아닌곳 어떻게든 갈 수 있는 곳
        				if('A'<=mmap[ti][tj] && mmap[ti][tj] <='F') { //문이었고, 
        					int keyIndex=mmap[ti][tj]-'A';
        					//열쇠를 가지면 visited....
        					if(keys[keyIndex]) { //열쇠를 가지고 있으면
        						//갈 수 있다.
        						queue.offer(new Pair(ti,tj));
        					}
        				}else {
        					if('a'<=mmap[ti][tj]&& mmap[ti][tj]=='f') {
        						keys[mmap[ti][tj]-'a']=true;
        					}
        					queue.offer(new Pair(ti,tj));
        				}
        				
        			}
        		}//end forz
        		
        		
        	}
        	cnt++;
        }

        System.out.println(cnt);

    }
    
    private static void input() throws Exception{
		br=new BufferedReader(new FileReader("src/input.txt"));
		StringTokenizer stk=new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		mmap=new char[N][];
		keys=new boolean[6];
		visited=new boolean[N][M][keys.length];
		
		for(int i=0;i<N;i++) {
			mmap[i]=br.readLine().toCharArray();
			for(int j=0;j<M;j++) {
				if(mmap[i][j]=='0') {
					start = new Pair(i,j);
				}
			}
		}
		print();
	}
    
    private static void print() {
    	for(int i=0;i<N;i++) {
    		for(int j=0;j<M;j++) {
    			System.out.print(mmap[i][j]);
    		}
    		System.out.println();
    	}
    }
    
	static class Pair{
    	int i;
    	int j;
		
    	public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
    	
    }

    

}