import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int d[]=new int[99999+1];
	static ArrayList<String> com[]=new ArrayList[99999+1]; //1,2 3 12 13 
	
	public static void main(String[] args) throws IOException{

		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			System.out.println("#"+test+" "+dfs(br.readLine()));
		}
		
	}
	
	private static int dfs(String str) {
		int number = Integer.parseInt(str);
		
		if(number < 10) {
			return 0;
		}
		
		if(d[number]!=0) return d[number];
		
		int max =0;
		for(String item: powerSet(str)) {
			int start=0;
			
			ArrayList<Integer> tt=new ArrayList<>();//0-1, 1-2
			
			for(int i=0;i<item.length();i++) {
				int fin = item.charAt(i)-'0';
				//System.out.println(str.substring(start,fin));
				tt.add(Integer.parseInt(str.substring(start,fin)));
				start=fin;
			}
			tt.add(Integer.parseInt(str.substring(start)));
			
			int sum =1;
			for(int item1:tt) {
				sum*=item1;
			}
			
			int temp=dfs(Integer.toString(sum));
			max = temp>max?temp:max;
			//System.out.println(str.substring(start));
		}
		
		return d[number] = max+1;
	}
	
	private static ArrayList<String> powerSet(String val) {
		if(com[Integer.parseInt(val)] !=null) {
			return com[Integer.parseInt(val)];
		}
		
		ArrayList<String> list = new ArrayList<>();
		for(int i=1;i<val.length();i++) {
			comb(val,i,1,0,new int[i],list);
		}
		return com[Integer.parseInt(val)]=list;
	}
	
	private static void comb(String str,final int target,int idx,int deep,int []picked,ArrayList<String> list){
		if(deep == target) {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<deep;i++) {
				sb.append(picked[i]);
			}
			list.add(sb.toString());
			return;
		}
		for(int i=idx;i<str.length();i++) {
			picked[deep]=i;
			comb(str,target,i+1,deep+1,picked,list);
		}
	}
	
}
