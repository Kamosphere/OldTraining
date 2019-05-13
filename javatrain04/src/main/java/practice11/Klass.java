package practice11;
import java.util.*;
public class Klass extends Observable {
    public Klass(int klass) {
        this.setNumber(klass);
        leader=new Student();
    }

    public Klass() {
        this.setNumber(-1);
        leader=new Student();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;
    private Student leader;

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public void assignLeader(Student stu) {
        if (stu.getKlass().getNumber() != this.getNumber()) {
            System.out.print("It is not one of us.\n");
        } else {
            this.setLeader(stu);
            super.setChanged();
            super.notifyObservers(leader.getName());
        }

    }
    public String getDisplayName(){
        String result="Class "+this.getNumber();
        return result;
    }
    public void appendMember(Student stu){
        stu.setKlass(this);
        super.setChanged();
        super.notifyObservers(stu);
    }
    public int hashCode(){
        Klass p=(Klass) this;
        Integer result=p.getNumber();
        return result.hashCode();
    }

    public boolean equals(Object object){
        if(object instanceof Klass){
            Klass p = (Klass) object;
            if(p.getNumber() == -1 || number == -1){
                return false;
            }
            else{
                if(number==p.getNumber()){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
}