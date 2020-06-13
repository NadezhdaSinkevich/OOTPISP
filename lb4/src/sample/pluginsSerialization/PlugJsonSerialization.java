package sample.pluginsSerialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.lists.ObjectWrapper;
import sample.model.Employee;
import sample.plugins.PluginOne;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PlugJsonSerialization implements PlugSerialization  {
    @Override
    public void serialize(ObservableList<Employee> list, String fileName, String key, Class plugClass, Method method) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        List<Employee> editions = new ArrayList<>(list);
        ObjectWrapper wrapper = new ObjectWrapper();
        wrapper.setAllEditions(editions);
        mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
        byte[] plainText = wrapper.toByteArray();
        byte[] cipherText = (byte[])method.invoke(plugClass.getDeclaredConstructor().newInstance(), plainText, key);
        mapper.writeValue(new File(fileName), cipherText);
    }

    @Override
    public ObservableList<Employee>  deserialize (String fileName, String key) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        byte[] cipherText = mapper.readValue(new File(fileName), byte[].class);
        String[] tokens = fileName.split("[.]");

        String [] Name = (tokens[tokens.length-2]).split("/");
        String str = "sample.plugins." + Name[Name.length-1];

        Class<?> plugClass =Class.forName(str);
        Method method = plugClass.getDeclaredMethod("decrypt", byte[].class, String.class);
        byte[] plainText = (byte[]) method.invoke(plugClass.getDeclaredConstructor().newInstance(), cipherText, key);
        ObjectWrapper wrapper = ObjectWrapper.getObject(plainText);
        ObservableList<Employee> list = FXCollections.observableArrayList();
        list.setAll(wrapper.getAllEditions());
        return list;
    }
}
