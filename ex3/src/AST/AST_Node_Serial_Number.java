package AST;

public class AST_Node_Serial_Number {
    public int SerialNumber;

    /**************************************/
    /* USUAL SINGLETON IMPLEMENTATION ... */
    /**************************************/
    private static AST_Node_Serial_Number instance = null;

    /*****************************/
    /* PREVENT INSTANTIATION ... */

    /*****************************/
    protected AST_Node_Serial_Number() {
    }

    /******************************/
    /* GET SINGLETON INSTANCE ... */

    /******************************/
    private static AST_Node_Serial_Number getInstance() {
        if (instance == null) {
            instance = new AST_Node_Serial_Number();
            instance.SerialNumber = 0;

        }
        return instance;
    }

    /**********************************/
    /* GET A UNIQUE SERIAL NUMBER ... */

    /**********************************/
    public int get() {
        return SerialNumber++;
    }

    /**********************************/
    /* GET A UNIQUE SERIAL NUMBER ... */

    /**********************************/
    public static int getFresh() {
        return AST_Node_Serial_Number.getInstance().get();
    }
}
