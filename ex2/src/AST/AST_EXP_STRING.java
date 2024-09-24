package AST;

public class AST_EXP_STRING extends AST_EXP {
    public String value;

    public AST_EXP_STRING(String str) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.format("====================== exp -> String(%s)\n", str);
        this.value = str;
    }

    public void PrintMe() {
        System.out.format("AST_EXP_STRING( %s )\n", value);

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("STRING(%s)", value));
    }
}
