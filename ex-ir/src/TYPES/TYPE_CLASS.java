package TYPES;

public class TYPE_CLASS extends TYPE {
    public TYPE_CLASS father;
    public TYPE_LIST data_members;
    public TYPE_LIST function_list;
    public TYPE_LIST members;
    private TYPE_CLASS_VAR_DEC_LIST lastDataMember;
    public TYPE_LIST localFuncs;

    public TYPE_CLASS(String name, TYPE_CLASS father, TYPE_LIST members) {
        this.father = father;
        this.members = members;
        this.name = name;
        data_members = new TYPE_LIST(null, null);
        function_list = new TYPE_LIST(null, null);
        localFuncs = new TYPE_LIST(null, null);
    }

    @Override
    public boolean isClass() {
        return true;
    }

    public String getType() {
        return "TYPE_CLASS";
    }
}
