package practice10;
import java.util.*;
public class Person {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
    Person(){
        id=-1;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public int hashCode(){
        Person p=(Person) this;
        Integer result=p.getId();
        return result.hashCode();
    }
    public boolean equals(Object object){
        if(object instanceof Person){
            Person p = (Person) object;
            if(p.getId() == -1 || id == -1){
                return false;
            }
            else{
                if(id==p.getId()){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
    Person(int id,String name,int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }
    public String introduce(){
        String line="My name is **. I am && years old.";
        line=line.replace("**",getName());
        line=line.replace("&&",getAge()+"");
        return line;
    }
}