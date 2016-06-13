
import java.util.Scanner;

import object.*;
//�������ý���һ�����ķ���ʵ��
public class instructionRunner {
	
	public static Obj run(String instruction, Environment evm){	
		System.out.println(parse(instruction.replaceAll(" ", "")).calc());
		
		//evm.
		return new Num();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// ����һ�У�ɾȥ���пո񣬹���һ������ִ��.calc����������ֵ
		run(sc.nextLine(),null);		
		sc.close();		
	}
			
	static node parse(String s) {
		pakage p;
		p = readElement(s);// ��õ�һ��������ߴ����ŵı��ʽ��
		node head = p.n;
		s = p.s;
		if (s.length() == 0)// ֻ��һ��
			return head;
		if (head.isLeaf == false) {// �����һ���Ǵ����ŵı��ʽ
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
		while (!s.isEmpty()) {// һֱִ�е����ʽ������
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

	static pakage readElement(String s) {// ��s�ж���һ�����pakage���������readElement����������ֵ��װ��һ������ֵ
		char c;
		String num = new String();
		num = "";
		c = s.charAt(0);
		node res = new node();

		if (c == '-') {// ����Ը��ſ�ͷ
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

		if (c == '(') {// ��������ſ�ͷ
			s = s.substring(1);// ����'('
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

	static private class node {// ���Ľڵ�
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
						System.out.println("0������������");
						System.exit(0);
						return 0;
					}
				}
				default: {
					System.out.println("�޷�ʶ���ʽ��");
					System.exit(0);
					return 0;
				}
				}
		}
	}

	 static private class pakage {// �����������readElement����������ֵ��װ��һ������ֵ
		node n;
		String s;

		pakage(node nn, String ss) {
			n = nn;
			s = ss;
		}

	}

}
