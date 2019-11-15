import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static TreeSet<String> treeSet;
	static final String LEAVE ="leave";
	static final String ENTER="enter";
	public static void main(String[] args) throws IOException{
		br=new BufferedReader(new InputStreamReader(System.in));
		//br=new BufferedReader(new FileReader("src/input.txt"));
		bw=new BufferedWriter(new OutputStreamWriter(System.out));
		//기록 로그가 주어졌을 때, 현재 회사에 있는 모든 사람을 구하는 프로그램을 작성한다.
		//출력 : 회사에 있는 사람의 이름을 사전순의 역순으로 한줄에 한명씩 출력한다.
		treeSet= new TreeSet<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return -o1.compareTo(o2);
			}
			
		});
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stk;
		for(int i=0;i<N;i++) {
			stk= new StringTokenizer(br.readLine());
			String name = stk.nextToken();
			String type = stk.nextToken();
			if(type.equals(ENTER)) {
				treeSet.add(name);
			}else if(treeSet.contains(name)) {
				treeSet.remove(name);
			}
		}
		
		for(String name:treeSet) {
			bw.write(name+"\n");
		}
		bw.close();
	}

}
