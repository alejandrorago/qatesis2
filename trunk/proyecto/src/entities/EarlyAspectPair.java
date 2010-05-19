package entities;

public class EarlyAspectPair implements EarlyAspectPairInterface  {
    private String verb;
    private String object;

    public EarlyAspectPair() {
     }
    
    public EarlyAspectPair(String object, String verb) {
        this.object = object;
        this.verb = verb;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object= object;
    }
}
