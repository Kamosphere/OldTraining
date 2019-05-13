package practice08;

public class Teacher extends Person {
    public Teacher(int id,String name, int age) {
        super(id,name, age);
        klass=new Klass(-1);
    }

    public Teacher(int id,String name, int age, Klass klass) {
        super(id,name, age);
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
    public boolean isTeaching(Student stu){
        boolean ifExist=false;
        if(stu.isIn(klass)){
            ifExist=true;
        }
        return ifExist;
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
    public String introduceWith(Student stu){
        String line = super.introduce() +" "+ "I am a Teacher. I teach ";
        String linenull = super.introduce() +" "+ "I am a Teacher. I don't teach ";
        if(isTeaching(stu)){
            line=line+stu.getName()+".";
            return line;
        }
        else{
            linenull=linenull+stu.getName()+".";
            return linenull;
        }
    }
}