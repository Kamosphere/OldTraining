package practice08;

public class Klass {
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
    public String getDisplayName(){
        String result="Class "+this.getNumber();
        return result;
    }
    private int number;
    private Student leader;

    public Student getLeader() {
        return leader;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public void assignLeader(Student stu){
        this.setLeader(stu);
    }


}