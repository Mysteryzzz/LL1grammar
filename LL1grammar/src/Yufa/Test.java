package Yufa;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Test {
	
	static public void main(String args[])
	{
		//声明向量集，用于存储分析表
		Vector<AnalysisTab> atab = new Vector<AnalysisTab>();
		//声明STACK栈
		Stack<Character> pstack = new Stack<Character>();
		//声明输入串
		String pstr = " ";
		//初始化文法
		GS gs = new GS();
		ArrayList<String>  gslist = new ArrayList<String>();
		gs.init(gslist);
 		gs.gsarraylist = gslist;
 		gs.getVnVt();
 		gs.setP();
 		gs.setS('E');
 		//打印FIRST集
 		FirstSet f1 = new FirstSet();
 		f1.allFirst(gs);
 		//打印FOLLOW集
 		FollowSet f2 = new FollowSet();
 		f2.allFollow(gs);
		//初始化分析表
		AnalysisTab ana = new AnalysisTab();
		ana.inittab(atab, gs);
		//打印分析表
		ana.show(atab);
		//输入所要匹配的字符串
		System.out.println("请输入符号串:(请以#开头和结尾)");
		//接受键盘指令
		Scanner sc = new Scanner(System.in);
		pstr = sc.nextLine();
		//打印分析过程
		System.out.println("步骤 \t符号栈\t\t当前值a\t\t所用产生式 \t剩余字符串");
		boolean bool = ana.analysis(atab, pstr, pstack);
		if(bool)
			System.out.println("输入串符合文法");
		else
			System.out.println("输入串不符合文法");
	}
}