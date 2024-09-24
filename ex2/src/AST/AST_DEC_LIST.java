package AST;

public class AST_DEC_LIST extends AST_Node {
    public AST_DEC head;
    public AST_DEC_LIST tail;
  
    public AST_DEC_LIST(AST_DEC head, AST_DEC_LIST tail) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (tail != null){
            System.out.print("====================== declist -> dec declist\n");
        }
        else{
            System.out.print("====================== declist -> dec\n");
        }
        this.head = head;
        this.tail = tail;
    }

    public void PrintMe() {
        System.out.print("AST DEC LIST\n");
        if (head != null) head.PrintMe();
        if (tail != null) tail.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "DEC LIST");

        if (head != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, head.SerialNumber);
        if (tail != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, tail.SerialNumber);
    }

}
