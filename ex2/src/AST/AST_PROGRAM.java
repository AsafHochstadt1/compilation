package AST;

public class AST_PROGRAM extends AST_Node {
    public AST_DEC_LIST decList;

    public AST_PROGRAM(AST_DEC_LIST decList) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.format("====================== Program -> LBRACK decList RBRACK\n");
        this.decList = decList;
    }

    public void PrintMe() {
        System.out.format("AST PROGRAM\n");
        decList.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("START OF PROGRAM"));
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, decList.SerialNumber);
    }
}
