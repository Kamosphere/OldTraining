package practice07;

public class Klass {
    public Klass(int klass) {
        this.setNumber(klass);
    }

    public Klass() {
        this.setNumber(-1);
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
}
