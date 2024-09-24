package AST;

import TEMP.*;
import TYPES.TYPE;

public abstract class AST_Node {

    public static int currLine = 0;
    public int myLine = currLine;

    public AST_Node left = null;
    public AST_Node right = null;

    public int SerialNumber;

    public void PrintMe() {
        System.out.print("AST NODE UNKNOWN\n");
    }
    public AST_Node getLeft() {
        return left;
    }
    public AST_Node getRight(){
        return right;
    }
    public TYPE SemantMe() {return null;}
	/*****************************************/
	/* The default IR action for an AST node */
	/*****************************************/
	public TEMP IRme()
	{
		return null;
	}
}
