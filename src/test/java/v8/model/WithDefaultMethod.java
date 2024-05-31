package v8.model;

public interface WithDefaultMethod {

    String DEFAULT_IMPLEMENTATION = "no type defined";

    default String getType() {
        return DEFAULT_IMPLEMENTATION;
    }
}
