package TYPES;

public class TYPE_STRING extends TYPE {
    private static TYPE_STRING instance = null;

    protected TYPE_STRING() {
        this.name = "string";
    }

    public static TYPE_STRING getInstance() {
        if (instance == null) {
            instance = new TYPE_STRING();
        }
        return instance;
    }

    public String getType() {
        return "TYPE_STRING";
    }
}
