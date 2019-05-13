package practice09;

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
        }
    }
    public String getDisplayName(){
        String result="Class "+this.getNumber();
        return result;
    }
    public void appendMember(Student stu){
        stu.setKlass(this);
    }
}