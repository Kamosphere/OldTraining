import com.tw.inputChecker;
import com.tw.Library;


public class main {
    public static void main(String[] args) throws Exception {
        inputChecker input=new inputChecker();
        Library library = new Library(input);
        library.mainMenu();
    }
}
