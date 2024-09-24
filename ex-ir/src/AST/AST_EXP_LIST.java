package AST;

import TEMP.*;
import AST.AST_GRAPHVIZ;
import AST.AST_LIST;
import AST.AST_Node_Serial_Number;

public class AST_EXP_LIST extends AST_LIST {
    public AST_EXP head;
    public AST_EXP_LIST tail;

    public AST_EXP_LIST(AST_EXP head, AST_EXP_LIST tail) {

        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (tail != null) System.out.print("====================== expList -> exp expList\n");
        if (tail == null) System.out.print("====================== expList -> exp\n");

        this.head = head;
        this.tail = tail;
        left = head;
        right = tail;
    }

    public void PrintMe() {

        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "EXP LIST");

        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

    @Override
    public AST_EXP getHead() {
        return this.head;
    }

    @Override
    public AST_EXP_LIST getTail() {
        return this.tail;
    }
    @Override
	public TEMP IRme()
	{
		return head.IRme();
	}
}
