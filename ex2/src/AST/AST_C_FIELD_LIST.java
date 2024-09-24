package AST;

public class AST_C_FIELD_LIST extends AST_Node {
    public AST_C_FIELD head;
    public AST_C_FIELD_LIST tail;

    public AST_C_FIELD_LIST(AST_C_FIELD head, AST_C_FIELD_LIST tail) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (tail != null) System.out.print("====================== cFieldList -> cfield cFieldList\n");
        if (tail == null) System.out.print("====================== cFieldList -> cfield\n");

        this.head = head;
        this.tail = tail;
    }

    public void PrintMe() {
        System.out.print("AST C_FIELD LIST\n");

        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "C_FIELD LIST");

        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

}
