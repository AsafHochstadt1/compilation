package AST;

import TYPES.*;
import TEMP.*;
import AST.AST_GRAPHVIZ;
import AST.AST_LIST;
import AST.AST_Node_Serial_Number;

public class AST_DEC_LIST extends AST_LIST {

    public AST_DEC head;
    public AST_DEC_LIST tail;

    public AST_DEC_LIST(AST_DEC head, AST_DEC_LIST tail) {

        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (tail != null) System.out.print("====================== declist -> dec declist\n");
        else System.out.print("====================== declist -> dec\n");

        this.head = head;
        this.tail = tail;

        left = head;
        right = tail;
    }

    public void PrintMe() {

        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(SerialNumber,
                "DecList");


        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

    @Override
    public AST_DEC getHead() {
        return this.head;
    }

    @Override
    public AST_DEC_LIST getTail() {
        return this.tail;
    }

	public TEMP IRme()
	{
		if (head != null) head.IRme();
		if (tail != null) tail.IRme();
		
		return null;			
	}

}
