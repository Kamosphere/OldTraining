package practice04;

public class Worker extends Person{

    public Worker(String name,int age) {
        super(name,age);
    }

    public Worker() {
    }
    public String introduce(){
        String line=basicintroduce()+" "+"I am a Worker. I have a job.";
        return line;
    }
}