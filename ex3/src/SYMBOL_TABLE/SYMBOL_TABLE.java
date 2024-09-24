package SYMBOL_TABLE;


import TYPES.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SYMBOL_TABLE {

    private final List<Map<String, TYPE>> tableList;
    private int scope_index = 0;
    public static int n = 0;
    private static SYMBOL_TABLE instance = null;


    /*push <String,TYPE> to current scope*/
    public void enter(String name, TYPE t) {
        Map<String, TYPE> currScope = tableList.get(scope_index);
        currScope.put(name, t);
        PrintMe();
    }

    /***********************************************/
    /* Find the inner-most scope element with name */
    /***********************************************/
    public TYPE find(String name) {
        /*start from innermost*/
        for (int i = scope_index; i >= 0; i--) {
            Map<String, TYPE> currScope = tableList.get(i);
            if (currScope.containsKey(name)) return currScope.get(name);

        }

        return null;
    }

    public TYPE findInCurrScope(String name) {
        Map<String, TYPE> currScope = tableList.get(scope_index);
        return currScope.getOrDefault(name, null);
    }

    /*add a hashmap and enter it*/
    public void beginScope() {
        tableList.add(new HashMap<>());
        scope_index++;
        PrintMe();
    }


    /*delete the hashmap and exit it*/
    public void endScope() {
        tableList.remove(scope_index);
        scope_index--;
        PrintMe();
    }

    public void PrintMe() {

    }


    protected SYMBOL_TABLE() {
        this.tableList = new ArrayList<>();
        tableList.add(new HashMap<>());


    }

    /*create the symbol table if doesnt exist, and return it.*/
    public static SYMBOL_TABLE getInstance() {
        if (instance == null) {
            instance = new SYMBOL_TABLE();
            instance.enter("int", TYPE_INT.getInstance());
            instance.enter("string", TYPE_STRING.getInstance());
            instance.enter("PrintInt", new TYPE_FUNCTION(TYPE_VOID.getInstance(), "PrintInt", new TYPE_LIST(TYPE_INT.getInstance(), null)));
            instance.enter("PrintString", new TYPE_FUNCTION(TYPE_VOID.getInstance(), "PrintString", new TYPE_LIST(TYPE_STRING.getInstance(), null)));
        }
        return instance;
    }
}