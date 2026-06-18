import java.io.FileReader;
import java.io.IOException;


public class displayStudents {
    String path = "student_info.txt";
    void display(){
        try{FileReader read = new FileReader(path);
      int chara ;
       while ((chara=read.read())!=-1){
           System.out.print((char) chara);
       }
       read.close();


        }catch (IOException e){System.out.println(e.getMessage());}
    }
}
