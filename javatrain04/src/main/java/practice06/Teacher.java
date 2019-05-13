package practice06;

public class Teacher extends Person {
    public Teacher(String name, int age) {
        super(name, age);
        setKlass(-1);
    }

    public Teacher(String name, int age, int klass) {
        super(name, age);
        setKlass(klass);
    }

    public Teacher() {

    }

    public int getKlass() {
        return klass;
    }

    public void setKlass(int klass) {
        this.klass = klass;
    }

    private int klass;

    @Override
    public String introduce() {
        String line = super.introduce()+" " + "I am a Teacher. I teach Class **.";
        String linenull = super.introduce() +" "+ "I am a Teacher. I teach No Class.";
        if (this.getKlass() == -1) {
            return linenull;
        } else {
            line=line.replace("**", this.getKlass()+"");
            return line;
        }
    }
}
