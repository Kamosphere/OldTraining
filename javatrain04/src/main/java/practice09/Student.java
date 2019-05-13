package practice09;

public class Student extends Person{
    public Student(int id,String name,int age,Klass klass) {
        super(id,name,age);
        this.setKlass(klass);
    }

    public Student() {
    }

    public Klass getKlass() {
        return this.klass;
    }

    public void setKlass(Klass klass) {
        this.klass=klass;
    }

    private Klass klass;
    public boolean isIn(Klass klass){
        if(this.getKlass().getNumber()==klass.getNumber()){
            return true;
        }
        else return false;
    }
    @Override
    public String introduce(){
        String line=super.introduce()+" "+"I am a Student. I am at Class **.";
        String lineleader=super.introduce()+" "+"I am a Student. I am Leader of Class **.";
        if(this.klass.getLeader().getName()==null){
            line=line.replace("**", getKlass().getNumber()+"");
            return line;
        }
        else if(!this.klass.getLeader().getName().equals(this.getName())){
            line=line.replace("**", getKlass().getNumber()+"");
            return line;
        }
        else {
            lineleader=lineleader.replace("**",getKlass().getNumber()+"");
            return lineleader;
        }
    }
}