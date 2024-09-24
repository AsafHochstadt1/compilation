package AST;

public class AST_ARRAY_TYPEDEF extends AST_DEC {
    String name;
    AST_TYPE type;

    public AST_ARRAY_TYPEDEF(String name, AST_TYPE type) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.format("====================== arrayTypeDef -> ARRAY NAME(%s) = type[]\n", name);
        this.name = name;
        this.type = type;
    }

    public void PrintMe() {
        System.out.format("AST ARRAY TYPEDEF ID(%s) = TYPE[]\n", name);
        if (type!=null) type.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Array Declaration NAME(%s)", name));
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
    }
}
