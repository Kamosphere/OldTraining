public class Prime {
    void isPrime(){
        for(int i=101;i<=200;i++){
            boolean b=false;
            for(int j=2;j<Math.sqrt(i);j++){
                if(i%j==0) {
                    b = false;
                    break;
                }
                else{
                    b=true;
                }
            }
            if(b){
                System.out.println(i);
            }
        }
    }
}
