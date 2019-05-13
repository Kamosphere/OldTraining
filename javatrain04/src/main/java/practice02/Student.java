package practice02;

public class Student extends Person{
    public Student(String name,int age,int klass) {
        super(name,age);
        this.klass = klass;
    }

    public int getKlass() {
        return klass;
    }

    public void setKlass(int klass) {
        this.klass = klass;
    }

    private int klass;
    public String introduce(){
        String line="I am a Student. I am at Class **.";
        line=line.replace("**",getKlass()+"");
        return line;
    }
}
