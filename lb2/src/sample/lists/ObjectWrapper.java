package sample.lists;

import sample.model.Employee;

import java.util.List;

public class ObjectWrapper {
    private List<Employee> allEditions;

    public ObjectWrapper() {
    }

    public List<Employee> getAllEditions() {
        return allEditions;
    }

    public void setAllEditions(List<Employee> allEditions) {
        this.allEditions = allEditions;
    }
}
