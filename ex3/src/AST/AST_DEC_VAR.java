package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_TYPE;
import AST.AST_EXP;
import AST.AST_EXP_INT;
import AST.AST_EXP_NIL;
import AST.AST_EXP_STRING;
import AST.AST_STMT_ASSIGN;
import Helpers.FunctionHelpers;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.*;

public class AST_DEC_VAR extends AST_DEC {

    AST_TYPE typeNode;
    String id;
    AST_EXP exp;

    public AST_DEC_VAR(AST_TYPE typeNode, String id, AST_EXP exp) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        if (exp != null)
            System.out.format("====================== varDec -> TYPE ID(%s) := EXP;\n", id);
        else
            System.out.format("====================== varDec -> TYPE ID(%s);\n", id);


        this.typeNode = typeNode;
        this.id = id;
        this.exp = exp;
    }

    public void PrintMe() {

        if (typeNode != null) typeNode.PrintMe();
        if (exp != null) exp.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("VarDec ID(%s)", id));

        if (typeNode != null)
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typeNode.SerialNumber);
        if (exp != null)
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }



    public TYPE SemantMe() {

        TYPE t = typeNode.SemantMe();

        if (t instanceof TYPE_VOID) {
            System.out.println("Error: variable cannot be type void\n");
            FunctionHelpers.printError(myLine);
        }

        TYPE nameType = FunctionHelpers.findTypeByString(this.id);
        if (nameType != null) {
            if (nameType.name.equals(this.id)) {
                System.out.println("Error: illegal name var\n");
                FunctionHelpers.printError(myLine);
            }
        }

        if (SYMBOL_TABLE.getInstance().findInCurrScope(id) != null) {
            System.out.format("ERROR variable %s already exists in scope\n", id);
            FunctionHelpers.printError(this.myLine);
        }

        if (exp != null) {
            TYPE expType = exp.SemantMe();
            if (!AST_STMT_ASSIGN.assignmentChecker(t, expType)) {
                System.out.format("ERROR type mismatch for var %s := exp\n", id);
                FunctionHelpers.printError(exp.myLine);
            }
        }

        SYMBOL_TABLE.getInstance().enter(id, t);

        return t;
    }

    public TYPE cSemantMe(String containingClassName) {

        TYPE t = typeNode.SemantMe();
        TYPE_CLASS_VAR_DEC newDec = null;


        if (t instanceof TYPE_VOID) {
            System.out.println("Error: variable cannot be type void\n");
            FunctionHelpers.printError(myLine);
        }

        if (SYMBOL_TABLE.getInstance().findInCurrScope(this.id) != null) {
            System.out.format("ERROR variable %s already exists in scope\n", this.id);
            FunctionHelpers.printError(this.myLine);

        }


        TYPE_CLASS containingClass = (TYPE_CLASS) SYMBOL_TABLE.getInstance().find(containingClassName);

        for (TYPE_LIST typeList = containingClass.data_members; typeList != null; typeList = typeList.tail) {
            TYPE_CLASS_VAR_DEC varDec = (TYPE_CLASS_VAR_DEC) typeList.head;
            if (varDec != null) {
                if (varDec.name.equals(this.id)) {
                    System.out.println("Error: variable shadowing is not allowed\n");
                    FunctionHelpers.printError(this.myLine);
                }
            }
        }

        if (exp instanceof AST_EXP_INT) {
            if (!(t instanceof TYPE_INT)) { 
                System.out.format("ERROR assignment to variable %s is invalid type\n", this.id);
                FunctionHelpers.printError(exp.myLine);
            } else {
                newDec = new TYPE_CLASS_VAR_DEC(TYPE_INT.getInstance(), this.id);
            }
        } else if (exp instanceof AST_EXP_STRING) {
            if (!(t instanceof TYPE_STRING)) { 
                System.out.format("ERROR assignment to variable %s is invalid type\n", this.id);
                FunctionHelpers.printError(exp.myLine);
            } else {
                newDec = new TYPE_CLASS_VAR_DEC(TYPE_STRING.getInstance(), this.id);
            }
        } else if (exp instanceof AST_EXP_NIL) {
            if (FunctionHelpers.isIntOrString(t)) {
                System.out.format("ERROR assignment to variable %s is invalid type\n", this.id);
                FunctionHelpers.printError(exp.myLine);
            } else {
                newDec = new TYPE_CLASS_VAR_DEC(t, this.id);
            }
        } else if (exp == null) {
            newDec = new TYPE_CLASS_VAR_DEC(t, this.id);
        }
        containingClass.data_members.add(newDec);

        SYMBOL_TABLE.getInstance().enter(this.id, t);

        return newDec;
    }


}