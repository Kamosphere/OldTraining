package practice11;
import java.util.Observable;
import java.util.Observer;
import java.util.*;
public class Teacher extends Person implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
            System.out.print("I am "+this.getName()+". I know "+((String)arg)+" become Leader of Class "+((Klass)o).getNumber()+".\n");
        }
        if(arg instanceof Student){
            System.out.print("I am "+this.getName()+". I know "+((String)((Person) arg).getName())+" has joined Class "+((Klass)o).getNumber()+".\n");
        }
    }

    public Teacher(int id,String name, int age) {
        super(id,name, age);
    }

    public Teacher(int id,String name, int age, Set<Klass> klass) {
        super(id,name, age);
        for(Klass klasses:klass){
            this.classes.add(klasses);
            klasses.addObserver(this);
        }
    }
    public Set getClasses() {
        return classes;
    }

    public void setClasses(Set klass) {
        this.classes = klass;
    }

    private Set<Klass> classes=new HashSet<Klass>();

    public boolean isTeaching(Student stu){
        boolean ifExist=false;
        for(Klass klasses:classes){
            if(stu.isIn(klasses)){
                ifExist=true;
                break;
            }
        }
        return ifExist;
    }
    @Override
    public String introduce() {
        String line = super.introduce()+" " + "I am a Teacher. I teach Class";
        String linenull = super.introduce() +" "+ "I am a Teacher. I teach No Class.";
        int start=0;
        if (classes.size()==0) {
            return linenull;
        } else {
            String temp="";
            for(Klass klasses:classes){
                if(start==0){
                    temp=temp+" "+klasses.getNumber();
                    start++;
                }
                else{
                    temp=temp+", "+klasses.getNumber();
                }
            }
            line=line+temp+".";
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