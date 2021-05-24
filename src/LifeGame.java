import java.io.*;

public class LifeGame {
    char[][] past_board;
    char[][] cur_board;
    String filename=null;

    LifeGame(){
        cur_board=new char[20][20];
        initBoard();
    }

    LifeGame(String filename) throws FileNotFoundException {
        this.filename=filename;
        cur_board=new char[20][20];
        BufferedReader bufferedReader=null;
        try {
            bufferedReader=new BufferedReader(new FileReader(new File(filename+".txt")));
            System.out.println("Read Success");
            String line;
            int line_count=0;
            while((line=bufferedReader.readLine())!=null){
                for(int i=0;i<line.length() && i<20;i++)
                    cur_board[line_count][i]=line.charAt(i);
                line_count++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        this.past_board=this.cur_board.clone();
    }

    void initBoard(){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                cur_board[i][j]=' ';
            }
        }
        for(int i=1;i<15;i+=3){
            for(int j=i+1;j<20;j++){
                cur_board[i][j]='#';
            }
        }
        this.past_board=this.cur_board.clone();
    }

    void run_once(){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(checkGround(i,j))
                    this.cur_board[i][j]='#';
                else
                    this.cur_board[i][j]=' ';
            }
        }
        // reset past_board
        this.past_board=this.cur_board.clone();
    }

    boolean isValid(int x,int y){
        return x >= 0 && y >= 0 && x <= 19 && y <= 19;
    }

    /*
    For point alive:
        less than 3 alive around will be dead, more than 3 alive around will be dead;
        keep alive with 2-3 alive around
    For point dead:
        more than 3 alive around will be alive, or keep dead
     */
    boolean checkGround(int x, int y){
        int count=0;
        if(past_board[x][y]=='#'){
            for(int i=-1;i<2;i++){
                for(int j=-1;j<2;j++){
                    if(isValid(x+i,y+j)){
                        if(countAlive(past_board[x+i][y+j]))
                            count++;
                    }
                }
            }
            return count > 1 && count < 4;
        }else{
            for(int i=-1;i<2;i++){
                for(int j=-1;j<2;j++){
                    if(isValid(x+i,y+j)){
                        if(countAlive(past_board[x+i][y+j]))
                            count++;
                    }
                }
            }
            return count > 2;
        }
    }

    private boolean countAlive(char c) {
        return c == '#';
    }

    String printBorad(){
        String result="";
        for(int i=0;i<20;i++){
            result=result.concat("| ");
            for(int j=0;j<20;j++){
                result=result.concat(this.cur_board[i][j]+" ");
            }
            result=result.concat("|\n");
        }
        result=result.concat("\n");
        return result;
    }

    void record(){
        try {
            FileWriter fileWriter=new FileWriter(filename+"1.txt",true);
            fileWriter.write(printBorad());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
