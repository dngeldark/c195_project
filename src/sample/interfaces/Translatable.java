package sample.interfaces;
import java.util.ResourceBundle;

/** Functional interface for the method Translatable. */
public interface Translatable {
    /** Stub for the function resource
     *  Using this lamba expression makes code easier to read and reuse.
     * @param file path of the file where the resources are located
     * @return ResourceBundle
     */
    ResourceBundle resource(String file);
}
