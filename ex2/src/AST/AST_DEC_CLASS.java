package AST;

public class AST_DEC_CLASS extends AST_DEC {
    String name;
    String ext;
    public AST_C_FIELD_LIST cfieldList;

    public AST_DEC_CLASS(String name, String ext, AST_C_FIELD_LIST cfieldList) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if(ext == null){
            System.out.format("====================== classDec -> NAME(%s)\n", name);
        }
        else{
            System.out.format("====================== classDec -> NAME(%s) extends NAME(%s)\n", name, ext);
        }

        this.name = name;
        this.cfieldList = cfieldList;
        this.ext = ext;
    }

    public void PrintMe() {
        if (ext != null){
            System.out.format("AST DEC CLASS ( %s ) extends %s\n", name, ext);
        }
        else{
            System.out.format("AST_DEC_CLASS ( %s )\n", name);
        }
        if (cfieldList != null) cfieldList.PrintMe();

        if (ext != null)
            AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Class Declaration NAME(%s) EXTENDS %s", name, ext));
        else
            AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Class Declaration NAME(%s)", name));
        if (cfieldList != null)
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cfieldList.SerialNumber);

    }
}
