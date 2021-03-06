package sample.serialization;

import javafx.collections.ObservableList;
import sample.model.Employee;

public interface Serialization {
    public String ext = "";

    void serialize(ObservableList<Employee> list, String fileName) throws Exception;
    ObservableList<Employee> deserialize(String fileName) throws Exception;
}
