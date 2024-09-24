package AST;

public class AST_TYPE extends AST_Node {
    public String type;
    public Boolean isID;

    public AST_TYPE(String type,Boolean isID) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        if(isID)
        {
            System.out.format("====================== type -> ID(%s)\n", type);
        }
        else
        {
            System.out.format("====================== type -> %s\n", type);
        }

        this.type = type;
        this.isID = isID;
    }

    public void PrintMe() {
        if(isID)
        {
            System.out.format("AST_TYPE ID(%s)\n", type);
        }
        else
        {
            System.out.format("AST_TYPE %s \n", type);
        }

        if(isID)
        {
            AST_GRAPHVIZ.getInstance().logNode(
                    SerialNumber,
                    String.format("TYPE ID(%s)", type));
        }
        else
        {
            AST_GRAPHVIZ.getInstance().logNode(
                    SerialNumber,
                    String.format("TYPE %s", type));
        }

    }
}
