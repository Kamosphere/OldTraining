package practice07;

public class Student extends Person{
    public Student(String name,int age,Klass klass) {
        super(name,age);
        this.setKlass(klass);
    }

    public Klass getKlass() {
        return this.klass;
    }

    public void setKlass(Klass klass) {
        this.klass=klass;
    }

    private Klass klass;

    @Override
    public String introduce(){
        String line=super.introduce()+" "+"I am a Student. I am at Class **.";
        line=line.replace("**",getKlass().getNumber()+"");
        return line;
    }
}