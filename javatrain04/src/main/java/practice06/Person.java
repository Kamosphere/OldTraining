package practice06;

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

    }
    Person(String name,int age){
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