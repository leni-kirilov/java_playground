package v8.model;

public class OverwritingInterface implements WithDefaultMethod {
    @Override
    public String getType() {
        return "I'm ovewriting this!";
    }
}
