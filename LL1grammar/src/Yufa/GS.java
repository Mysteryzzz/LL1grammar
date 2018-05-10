package Yufa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class GS 
{
	//非终结符集
	protected TreeSet<Character> vnset;
	//终结符集
	protected TreeSet<Character> vtset;
	//开始符号
	protected Character s;
	//产生式集（用来获取终结符和非终结符）
	protected ArrayList<String> gsarraylist;
	//产生式map
	protected HashMap<Character, ArrayList<String>> p;
	
	public GS()
	{
		vnset = new TreeSet<Character>();
		vtset = new TreeSet<Character>();
		gsarraylist = new ArrayList<String>();
		p = new HashMap<Character, ArrayList<String>>();
	}
	
	public void setVnset(TreeSet<Character> vnset) {
		this.vnset = vnset;
	}

	public void setVtset(TreeSet<Character> vtset) {
		this.vtset = vtset;
	}

	public void setS(Character s) {
		this.s = s;
	}

	public void setGsarraylist(ArrayList<String> gsarraylist) {
		this.gsarraylist = gsarraylist;
	}

	public void setP(HashMap<Character, ArrayList<String>> p) {
		this.p = p;
	}
	
	//获取非终结符和终结符
	public void getVnVt()
	{
		//获取vn
		for(String item:this.gsarraylist)
		{
			String [] produce = item.split("->");
			String vn = produce[0];
			char  vnchar = vn.charAt(0);
			this.vnset.add(vnchar);
		}
		//获取vt
		for(String item:this.gsarraylist)
		{
			String [] produce = item.split("->");
			String vt = produce[1];
			for(int i = 0;i < vt.length();i++)
			{
				char vtchar = vt.charAt(i);
				if(!this.vnset.contains(vtchar))
				{
					this.vtset.add(vtchar);
				}
			}
		}
		
	}
	//设置产生式映射
	public void setP()
	{
		for(String item:this.gsarraylist)
		{
			String[] produce = item.split("->");
			String proleft = produce[0];
			String proright = produce[1];
			char prochar = proleft.charAt(0);
			if(!this.p.containsKey(prochar))
			{
				ArrayList<String> ptr = new ArrayList<String>();
				ptr.add(proright);
				this.p.put(prochar, ptr);
			}
			else
			{
				ArrayList<String> ptr = this.p.get(prochar);
				ptr.add(proright);
				this.p.put(prochar, ptr);
			}
		}
	}
	//初始化文法
	public void init(ArrayList<String> gslist)
	{
		System.out.println("输入LL1型文法（文法无左递归,以ok结束）:");
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String s = sc.nextLine();
			if(s.equals("ok"))
			 return;
			gslist.add(s);
		  
		}
		
		  
	}
}
