package jsonOperations;
import java.io.File;

public interface JsonManipulator {

    public Object readFromJson(File fileToRead);
    public void writeToJson(Object objectToWrite, File file) ;

}
