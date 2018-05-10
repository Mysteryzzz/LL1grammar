package Yufa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class FollowSet 
{
		protected TreeSet<Character> fellow = new TreeSet<Character>();
		
		//获取单个字符的FOLLOW集
		public void Fellow(GS gs,char c)
		{
			if(c==gs.s)
			{
				this.fellow.add('#');
			}
			if(gs.vnset.contains(c))
			{
				for(Character item:gs.p.keySet())
				{
					for(String i:gs.p.get(item))
					{
						if(i.charAt(0)!=c&&i.charAt(i.length()-1)!=c)
						{
							for(int j = 0; j <i.length();j++)
							{
								if(i.charAt(j)==c)
								{
									String str = i.substring(j+1);
									FirstSet f = new FirstSet();
									f.First(gs, str);
									f.first.remove('ε');
									this.fellow.addAll(f.first);
								}
							}
						}
					}
				}
			}
			for(Character item:gs.p.keySet())
			{
				for(String i:gs.p.get(item))
				{
					if(i.charAt(i.length()-1)==c&&item!=c)
					{
						FollowSet f = new FollowSet();
						f.Fellow(gs, item);
						this.fellow.addAll(f.fellow);
					}
					else if(i.charAt(0)!=c&&i.charAt(i.length()-1)!=c)
					{
						for(int j = 0; j <i.length();j++)
						{
							if(i.charAt(j)==c)
							{
								String str = i.substring(j+1);
								FirstSet f = new FirstSet();
								f.First(gs, str);
								if(f.first.contains('ε'))
								{
									FollowSet fo = new FollowSet();
									fo.Fellow(gs, item);
									this.fellow.addAll(fo.fellow);
								}
							}
						}
					}
				}
			}
			
		}
		
		//获取所有字符的FOLLOW集
		public void allFollow(GS gs)
		{
			System.out.println("文法的FOLLOW集为:");
			for(char item:gs.vnset)
	 		{
	 			FollowSet f = new FollowSet();
	 			f.Fellow(gs, item);
	 			System.out.println(item+":"+f.fellow);
	 		}
		}
}
