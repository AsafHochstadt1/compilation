package AST;

public class AST_STMT_IF extends AST_STMT
{
	public AST_EXP cond;
	public AST_STMT_LIST body;

	/*******************/
	/*  CONSTRUCTOR(S) */
	/*******************/
	public AST_STMT_IF(AST_EXP cond,AST_STMT_LIST body)
	{
		SerialNumber = AST_Node_Serial_Number.getFresh();
		System.out.print("====================== stmt -> IF LPAREN exp RPAREN LBRACE stmtList RBRACE\n");
		this.cond = cond;
		this.body = body;
	}
	public void PrintMe() {
        	System.out.print("AST NODE IF STMT\n");
        	if (cond != null) cond.PrintMe();
        	if (body != null) body.PrintMe();
		
        	AST_GRAPHVIZ.getInstance().logNode(
                	SerialNumber,
                	"STMT IF");

        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cond.SerialNumber);
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }
}
