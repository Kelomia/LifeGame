import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        System.out.println("选择导入初始或默认生成:\n\t1.导入\t2.默认");
        LifeGame one = null;
        int choice;
        if((choice= scan.nextInt())==1){
            try {
                one=new LifeGame("tst");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            one=new LifeGame();
        }
        do{
            System.out.println(one.printBorad());
            System.out.println("请选择操作:\n\t1.继续\t2.结束");
            one.run_once();
            one.record();
        }while((choice= scan.nextInt())==1);
        System.out.println("END");
    }
}
