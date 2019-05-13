package practice07;

public class Teacher extends Person {
    public Teacher(String name, int age) {
        super(name, age);
        klass=new Klass(-1);
    }

    public Teacher(String name, int age, Klass klass) {
        super(name, age);
        setKlass(klass);
    }

    public Teacher() {

    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    private Klass klass;

    public String introduceWith(Student stu){
        String line = super.introduce() +" "+ "I am a Teacher. I teach **.";
        String linenull = super.introduce() +" "+ "I am a Teacher. I don't teach **.";
        if (this.getKlass().getNumber() != stu.getKlass().getNumber()) {
            linenull=linenull.replace("**", stu.getName());
            return linenull;
        } else {
            line=line.replace("**", stu.getName());
            return line;
        }
    }

    @Override
    public String introduce() {
        String line = super.introduce() +" "+ "I am a Teacher. I teach Class **.";
        String linenull = super.introduce() +" "+ "I am a Teacher. I teach No Class.";
        if (this.getKlass().getNumber() == -1) {
            return linenull;
        } else {
            line=line.replace("**", this.getKlass().getNumber()+"");
            return line;
        }
    }
}