package AST;

public class AST_ID_LIST extends AST_Node {
    public AST_TYPE type;
    public String head;
    public AST_ID_LIST tail;

    public AST_ID_LIST(AST_TYPE type, String head, AST_ID_LIST tail) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (tail != null){
            System.out.print("====================== IDList -> type ID, IDList\n");
        }
        if (tail == null){
            System.out.print("====================== IDList -> type ID\n");
        }

        this.type = type;
        this.head = head;
        this.tail = tail;
    }

    public void PrintMe() {
        if(type != null)
            type.PrintMe();
        System.out.format("AST ID LIST: TYPE ID(%s)\n", head);
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "ID LIST");

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("TYPE ID(%s)", head));
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

}
