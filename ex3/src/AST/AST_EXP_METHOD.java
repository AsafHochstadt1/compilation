package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_VAR;
import Helpers.FunctionHelpers;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.*;

public class AST_EXP_METHOD extends AST_EXP {

    public String id;
    public AST_VAR var;
    public AST_EXP_LIST args;

    public AST_EXP_METHOD(AST_VAR var, String id, AST_EXP_LIST args) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if ((var != null) && (args != null))
            System.out.printf("====================== exp -> var. %s (expList)\n", id);
        else if (var != null)
            System.out.printf("====================== exp -> var. %s ()\n", id);
        else if (args != null)
            System.out.printf("====================== exp -> %s (expList)\n", id);
        else
            System.out.printf("====================== exp -> %s ()\n", id);

        this.var = var;
        this.id = id;
        this.args = args;
        left = var;
        right = args;

    }

    public void PrintMe() {

        if (var != null) var.PrintMe();
        if (args != null) args.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Call for method: NAME(%s)", id));
        if (var != null) {
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        }
        if (args != null) {
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, args.SerialNumber);
        }
    }


    @Override
    public TYPE SemantMe() {
        TYPE_FUNCTION func;
        if (var != null) { 
            TYPE c = var.SemantMe();
            if (!c.isClass()) {
                System.out.println("Error: calling a method from non-class\n");
                FunctionHelpers.printError(this.myLine);
                return null;
            }
            TYPE_CLASS classType = (TYPE_CLASS) c;
            func = (TYPE_FUNCTION) classType.function_list.findInList(this.id);

            if (func == null) {
                System.out.format("ERROR: no such function %s in class %s\n", id, classType.name);
                FunctionHelpers.printError(this.myLine);
                return null;
            }
        } else { 
            func = (TYPE_FUNCTION) SYMBOL_TABLE.getInstance().find(this.id);
            if (func == null) {
                System.out.format("ERROR: no such func %s\n", id);
                FunctionHelpers.printError(this.myLine);
                return null;
            }
        }

        if (args == null) {
            if (func.arguments != null) {
                System.out.println("Error: not enough arguments sent to function\n");
                FunctionHelpers.printError(myLine);
            }
        }
        if (func.arguments == null) {
            if (args != null) {
                System.out.println("Error: too many arguments sent to function\n");
                FunctionHelpers.printError(myLine);
            }
        }

        if (!(func.arguments == null && args == null))
            funcCallSemanter(args, func); 

        return func.returnType;

    }

    public static void funcCallSemanter(AST_EXP_LIST args, TYPE_FUNCTION func) {
        AST_EXP_LIST argRunner = args;
        TYPE_LIST funcRunner = func.arguments;
        while (argRunner != null && funcRunner != null) {
            TYPE currArgType = argRunner.head.SemantMe();
            TYPE currFuncType = funcRunner.head;
            if (currArgType.isArray() && currFuncType.isArray()) {
                currArgType = ((TYPE_ARRAY) currArgType).type;
                currFuncType = ((TYPE_ARRAY) currFuncType).type;
            }
            if (currArgType != currFuncType) {
                if (!FunctionHelpers.isSameOrInheritorOrValidNil(currArgType, currFuncType)) { 
                    System.out.println("Error: invalid parameter type sent to function\n");
                    FunctionHelpers.printError(argRunner.head.myLine);
                }

            }
            /*get next arg*/
            argRunner = argRunner.tail;
            funcRunner = funcRunner.tail;
        }

        if (argRunner != null) {
            System.out.println("Error: too many arguments sent to function");
            FunctionHelpers.printError(args.myLine);
        }
        if (funcRunner != null) {
            System.out.println("Error: too few arguments sent to function");
            FunctionHelpers.printError(args.myLine);
        }

    }

    @Override
    public TYPE getExpType() {
        return null;
    }
}