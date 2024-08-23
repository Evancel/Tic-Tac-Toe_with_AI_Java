package tictactoe;

public enum UserParameters {
    START ("start"),
    EXIT ("exit"),
    USER ("user"),
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private String value;
    private UserParameters(String value){
        this.value = value;
    }

    public static UserParameters fromString(String value){
        if(value!=null){
            for(UserParameters p:UserParameters.values()){
                if(value.equalsIgnoreCase(p.value)){
                    return p;
                }
            }
        }
        throw new IllegalArgumentException("Bad parameters!");
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return "UserParameter {value = " + value + "}";
    }
}
