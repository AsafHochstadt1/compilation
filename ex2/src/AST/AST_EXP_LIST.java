package AST;

public class AST_EXP_LIST extends AST_Node {
    public AST_EXP head;
    public AST_EXP_LIST tail;

    public AST_EXP_LIST(AST_EXP head, AST_EXP_LIST tail) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (tail != null){
            System.out.print("====================== expList -> exp expList\n");
        }
        if (tail == null){
            System.out.print("====================== expList -> exp\n");
        }

        this.head = head;
        this.tail = tail;
    }

    public void PrintMe() {
        System.out.print("AST EXP LIST\n");

        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "EXP LIST");

        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

}
