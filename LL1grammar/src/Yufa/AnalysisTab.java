package Yufa;

import java.util.Stack;
import java.util.Vector;

public class AnalysisTab {

	protected  char   terminal;
	protected  char   nonterminal;
	protected  String  protect;
	
	public AnalysisTab(char terminal,char nonterninal,String protect) 
	{
		this.terminal = terminal;
		this.nonterminal = nonterninal;
		this.protect = protect;
	}
	public AnalysisTab()
	{
		
	}
	
	//找到指定产生式
	public String findProduce(char terminal,char nonterninal,Vector<AnalysisTab> s)
	{
		String produc = null;
		for(AnalysisTab atab:s)
		{
			if(atab.terminal==terminal&&atab.nonterminal==nonterninal)
			{
				produc = atab.protect;
			}
		}
		return produc;
	}
	public void inittab(Vector<AnalysisTab> vect,GS gs)
	{
		for(Character item:gs.p.keySet())
		{
			for(String i:gs.p.get(item))
			{
				FirstSet f = new FirstSet();
				f.First(gs, i);
				if(f.first.contains('ε'))
				{
					FollowSet f1 = new FollowSet();
					f1.Fellow(gs, item);
					for(Character j:f1.fellow)
					{
						vect.addElement(new AnalysisTab(item,j,i));
					}
				}
				else
				{
					for(Character j:f.first)
					{
						vect.addElement(new AnalysisTab(item,j,i));
					}
				}
				
			}
		}
	}
	
	//打印LL1分析表
	public void show(Vector<AnalysisTab> vect)
	{
		System.out.println("打印LL1分析表");
		for(AnalysisTab a:vect)
		{
			System.out.println(a.terminal+" "+a.nonterminal+" "+a.protect);
		}
		
	}
	
	//语法分析程序
	public boolean analysis(Vector<AnalysisTab> atab,String pstr,Stack<Character> pstack)
	{
		AnalysisTab tab = new AnalysisTab();
		pstack.push('#');
		pstack.push('E');
		char a = pstr.charAt(1);
		pstr = pstr.substring(2);
		boolean flag = true;
		int num = 0;
		while(flag)
		{
			if(pstack.peek()!=a)
			{
				String produce = tab.findProduce(pstack.peek(),a, atab);
				if(null==produce)
					return false;
				System.out.println(String.format("%1$-6s"," "+num)+String.format("%1$-20s",pstack.toString())+a+"\t\t"+pstack.peek()+"->"+produce+"\t\t"+pstr);
				num++;
				pstack.pop();
				for(int i = produce.length()-1;i>=0;i--)
				{
					char c = produce.charAt(i);
					
					if(c!='ε')
					{
						pstack.push(c);
					}
					
					
				}
			}
			if(pstack.peek()==a)
			{
				
				System.out.println(String.format("%1$-6s"," "+num)+String.format("%1$-20s",pstack.toString())+a+"\t\t"+a+"匹配"+"\t\t"+pstr);
				if(a=='#')
				{
					return true;
				}
				pstack.pop();
				a = pstr.charAt(0);
				pstr = pstr.substring(1);
				
					
			}
		}
		return flag;
	}
}
