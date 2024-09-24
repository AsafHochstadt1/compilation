package AST;

import TYPES.*;
import TEMP.*;
import IR.*;
import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;

public class AST_EXP_INT extends AST_EXP {
    public int value;

    public AST_EXP_INT(int value) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== exp -> INT( %d )\n", value);

        this.value = value;
    }

    public void PrintMe() {

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("INT(%d)", value));
    }

    public TYPE SemantMe() {
        return TYPE_INT.getInstance();
    }


    @Override
    public TYPE getExpType() {
        return null;
    }


    @Override
    public boolean isConstantInt() {
        return true;
    }

    public int getValue() {
        return value;
    }
    @Override
	public TEMP IRme()
	{
		TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
		IR.getInstance().Add_IRcommand(new IRcommandConstInt(t,value));
		return t;
	}
	@Override
	public void print(){
	System.out.print(this.value + " ");}
	
}
