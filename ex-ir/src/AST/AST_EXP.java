package AST;

import TYPES.*;
import TEMP.*;
import AST.AST_Node;

public abstract class AST_EXP extends AST_Node {
    public void PrintMe() {
        System.out.print("CANNOT PRINT AST_EXP");
    }

    public abstract TYPE getExpType();

    public boolean isConstantInt() {
        return false;
    }

    public int getValue() {
        return -1;
    }
	public TEMP IRme()
	{
		return null;
	}
	
	public void print(){}
	
	public boolean isVar(){
	  return false;}
}
