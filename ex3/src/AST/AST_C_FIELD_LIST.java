package AST;

public class AST_C_FIELD_LIST extends AST_LIST {

    /* DATA MEMBERS */
    public AST_C_FIELD head;
    public AST_C_FIELD_LIST tail;

    /* CONSTRUCTOR(S) */
    public AST_C_FIELD_LIST(AST_C_FIELD head, AST_C_FIELD_LIST tail) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (tail != null) System.out.print("====================== cFieldList -> cfield cFieldList\n");
        if (tail == null) System.out.print("====================== cFieldList -> cfield\n");

        this.head = head;
        this.tail = tail;
    }

    public void PrintMe() {

        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "CFIELD LIST");

        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

    @Override
    public AST_C_FIELD getHead() {
        return this.head;
    }

    @Override
    public AST_C_FIELD_LIST getTail() {
        return this.tail;
    }

}
