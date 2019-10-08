import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		char[] str=sc.nextLine().toCharArray();
		char[] p=sc.nextLine().toCharArray();
		
		int[] pi=new int[p.length];
		int j=0;
		for(int i=1;i<p.length;i++) {
			while(j>0 && p[i]!=p[j]) 
				j=pi[j-1];
			if(p[i] == p[j]) 
				pi[i]= ++j;
		}
		
		
		boolean answer=false;
		for(int i=0;i<str.length;i++) {
			while(j>0 && str[i] != p[j]) {
				j=pi[j-1];
			}
			if(str[i] == p[j]) {
				if(j == p.length-1) {
					j=pi[j];
					answer=true;
					break;
				}else {
					j++;
				}
			}
				
		}
		
		System.out.println(answer?1:0);
		
		
		
	}

}
