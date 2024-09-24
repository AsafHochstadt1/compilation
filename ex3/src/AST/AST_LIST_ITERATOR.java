package AST;

import java.util.Iterator;

public class AST_LIST_ITERATOR implements Iterator<AST_Node>{

    private AST_LIST localList;

    public AST_LIST_ITERATOR(AST_LIST list){
        this.localList = list;
    }

    @Override
    public boolean hasNext() {
        return localList != null;
    }

    @Override
    public AST_Node next() {
        AST_Node res = localList.getHead();
        localList = localList.getTail();
        return res;
    }
}