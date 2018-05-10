package Yufa;

import java.util.TreeSet;

public class FirstSet 
{
	protected TreeSet<Character> first = new TreeSet<Character>();
	
	//单一符号的FIRST集
	public void First(GS gs,char s)
	{
		if(gs.vtset.contains(s))
		{
			this.first.add(s);
		}
		else if(gs.vnset.contains(s))
		{
			for(String item:gs.p.get(s))
			{
				if(gs.vtset.contains(item.charAt(0)))
				{
					this.first.add(item.charAt(0));
				}
				else if(gs.vnset.contains(item.charAt(0)))
				{
					FirstSet f = new FirstSet();
					f.First(gs, item.charAt(0));
					f.first.remove('ε');
					this.first.addAll(f.first);
				}
				else if('ε'==item.charAt(0))
				{
					this.first.add('ε');
				}
			}
		}
	}
	
	//符号串的FIRST集
	public void First(GS gs,String s)
	{
		for(int i = 0; i < s.length();i++)
		{
			FirstSet f1 = new FirstSet();
			char c = s.charAt(i);
			f1.First(gs, c);
			if(!f1.first.contains('ε'))
			{
				this.first.addAll(f1.first);
				return ;
			}
			else if(f1.first.contains('ε')&&i==s.length()-1)
			{
				this.first.addAll(f1.first);
			}
			else if(f1.first.contains('ε'))
			{
				f1.first.remove('ε');
				this.first.addAll(f1.first);
			}
		}
	}
	//文法的所有FIRST集
	public void allFirst(GS gs)
	{
		System.out.println("文法的FIRST集为:");
		for(char item:gs.vnset)
 		{
 			FirstSet f = new FirstSet();
 			f.First(gs, item);
 			System.out.println(item+":"+f.first);
 		}
	}
}
