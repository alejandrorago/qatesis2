package entities;

public class EarlyAspect implements EarlyAspectInterface {
    private String verb;
    private String noun;

    public EarlyAspect(String noun, String verb) {
        this.noun = noun;
        this.verb = verb;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }
}
