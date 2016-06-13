
import java.util.Scanner;

import object.*;
//本程序用建立一棵树的方法实现
public class instructionRunner {
	
	public static Obj run(String instruction, Environment evm){	
		System.out.println(parse(instruction.replaceAll(" ", "")).calc());
		
		//evm.
		return new Num();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 读入一行，删去所有空格，构造一棵树，执行.calc来计算它的值
		run(sc.nextLine(),null);		
		sc.close();		
	}
			
	static node parse(String s) {
		pakage p;
		p = readElement(s);// 获得第一项（常数或者带括号的表达式）
		node head = p.n;
		s = p.s;
		if (s.length() == 0)// 只有一项
			return head;
		if (head.isLeaf == false) {// 如果第一项是带括号的表达式
			char c = s.charAt(0);
			s = s.substring(1);
			node newhead = new node();
			newhead.isLeaf = false;
			newhead.operator = c;
			newhead.leftOperand = head;
			p = readElement(s);
			newhead.rightOperand = p.n;
			s = p.s;
			head = newhead;
		}
		while (!s.isEmpty()) {// 一直执行到表达式被读完
			if (s.charAt(0) == '+'
					|| ((s.charAt(0) == '*' || s.charAt(0) == '/') && (head.operator == '*' || head.operator == '/'))) {
				node newhead = new node();
				newhead.isLeaf = false;
				newhead.operator = s.charAt(0);
				newhead.leftOperand = head;
				s = s.substring(1);
				p = readElement(s);
				newhead.rightOperand = p.n;
				s = p.s;
				head = newhead;
			} else if (s.charAt(0) == '-'
					|| ((s.charAt(0) == '*' || s.charAt(0) == '/') && (head.operator == '*' || head.operator == '/'))) {
				node newhead = new node();
				newhead.isLeaf = false;
				newhead.operator = s.charAt(0);
				newhead.leftOperand = head;
				s = s.substring(1);
				p = readElement(s);
				newhead.rightOperand = p.n;
				s = p.s;
				head = newhead;
			} else if (s.charAt(0) == '*') {
				s = s.substring(1);
				if (head.isLeaf) {
					node newhead = new node();
					newhead.isLeaf = false;
					newhead.operator = '*';
					newhead.leftOperand = head;
					p = readElement(s);
					newhead.rightOperand = p.n;
					s = p.s;
					head = newhead;
				} else {
					node newright = new node();
					newright.isLeaf = false;
					newright.operator = '*';
					newright.leftOperand = head.rightOperand;
					p = readElement(s);
					newright.rightOperand = p.n;
					s = p.s;
					head.rightOperand = newright;
				}
			} else if (s.charAt(0) == '/') {
				s = s.substring(1);
				if (head.isLeaf) {
					node newhead = new node();
					newhead.isLeaf = false;
					newhead.operator = '/';
					newhead.leftOperand = head;
					p = readElement(s);
					newhead.rightOperand = p.n;
					s = p.s;
					head = newhead;
				} else {
					node newright = new node();
					newright.isLeaf = false;
					newright.operator = '/';
					newright.leftOperand = head.rightOperand;
					p = readElement(s);
					newright.rightOperand = p.n;
					s = p.s;
					head.rightOperand = newright;
				}
			}
		}

		return head;

	}

	static pakage readElement(String s) {// 从s中读出一个项，用pakage这个类来把readElement的两个返回值包装成一个返回值
		char c;
		String num = new String();
		num = "";
		c = s.charAt(0);
		node res = new node();

		if (c == '-') {// 如果以负号开头
			pakage p;
			node zero = new node();
			zero.isLeaf = true;
			zero.value = 0;
			res.isLeaf = false;
			res.leftOperand = zero;
			res.operator = '-';
			s = s.substring(1);
			p = readElement(s);
			res.rightOperand = p.n;
			s = p.s;
			return new pakage(res, s);
		}

		if (c == '(') {// 如果以括号开头
			s = s.substring(1);// 剪掉'('
			c = s.charAt(0);
			while (c != ')') {
				num += c;
				s = s.substring(1);
				c = s.charAt(0);
			}
			pakage p = new pakage(parse(num), s.substring(1));
			return p;
		}

		while (c != '+' & c != '-' & c != '/' & c != '*') {
			if (c >= '0' & c <= '9') {
				num += c;
				s = s.substring(1);
				if (s.length() == 0)
					break;
				c = s.charAt(0);
			}
		}

		res.isLeaf = true;
		res.value = Integer.parseInt(num);

		return new pakage(res, s);
	}

	static private class node {// 树的节点
		boolean isLeaf;
		int value;// a node has value only if it is a leaf
		char operator = 0;
		node leftOperand = null;
		node rightOperand = null;

		int calc() {
			if (isLeaf)
				return value;
			else
				switch (operator) {
				case '+':
					return leftOperand.calc() + rightOperand.calc();
				case '-':
					return leftOperand.calc() - rightOperand.calc();
				case '*':
					return leftOperand.calc() * rightOperand.calc();
				case '/': {
					int left = leftOperand.calc();
					int right = rightOperand.calc();
					if (right != 0)
						return left / right;
					else {
						System.out.println("0不能做除数！");
						System.exit(0);
						return 0;
					}
				}
				default: {
					System.out.println("无法识别的式子");
					System.exit(0);
					return 0;
				}
				}
		}
	}

	 static private class pakage {// 用这个类来把readElement的两个返回值包装成一个返回值
		node n;
		String s;

		pakage(node nn, String ss) {
			n = nn;
			s = ss;
		}

	}

}
