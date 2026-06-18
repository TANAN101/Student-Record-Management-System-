import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        String file_pa = "student_info.txt";

        Scanner input = new Scanner(System.in);
        System.out.println("1:Add student");
        System.out.println("2:Srearch for student");
        int a = input.nextInt();
       ///add file
        if (a == 1) {

            System.out.println("Enter Student Id : ");
            int id = input.nextInt();
            System.out.println("Enter Student Name : ");
            String name = input.next();
            System.out.println("Enter Student Department : ");
            String Dep = input.next();
            System.out.println("Enter Student GPA : ");
            double gpa = input.nextDouble();
            student add = new student(id, name, Dep, gpa);


            try {

                PrintWriter pw = new PrintWriter(new FileWriter(file_pa, true));

                pw.println(add.student_ID + " " + add.Name + " " + add.Department + " " + add.GPA);
                pw.close();
            } catch (IOException e) {
                System.out.println(e);
            }

        }
///search file
        if(a==2){
            System.out.println("Enter the individuals name: ");
            String who =input.next();

            try {

                Scanner look = new Scanner(new File(file_pa));

            while(look.hasNext()){
                String line=look.nextLine();
                if (line.contains(who)){
                    System.out.println("available: "+line);
                }

            }




            }catch (IOException e){System.out.println(e);}

        }

        ///update file

        if (a==3){
            try {
                System.out.println("Enter the name you want to update:  ");
                String whom =input.next();
                System.out.println("Enter the name you want to update it to:  ");
                String To =input.next();
                Scanner update = new Scanner(new File(file_pa));
                ArrayList<String> up_name=new ArrayList<>();
                while (update.hasNext()){
                    up_name.add(update.nextLine());
                }
                update.close();
                for(int i=0;i<up_name.size();i++){
                    if (up_name.get(i).contains(whom)){
                        up_name.set(i,up_name.get(i).replace(whom,To));

                    }
                    PrintWriter pupdate = new PrintWriter(new FileWriter(file_pa));
                   for (String s:up_name){
                       pupdate.println(s);
                   }
                    pupdate.close();
                }




            }catch (IOException e){System.out.println(e);}

        }
///Remove student
        if (a==4){
            System.out.println("Who do you want to delete :  ");
            String delete = input.next();
           try{  Scanner remove = new Scanner(new File(file_pa));
            ArrayList<String> rm_name=new ArrayList<>();
            while (remove.hasNext()){
                rm_name.add(remove.nextLine());}
            for (int i =0;i<rm_name.size();i++){
                if (rm_name.get(i).contains(delete)){
                    rm_name.remove(rm_name.get(i));
                }
            }
               PrintWriter pupdate = new PrintWriter(new FileWriter(file_pa));
            for (String l:rm_name){
                pupdate.println(l);
            }
            pupdate.close();




           }catch (IOException e){System.out.println(e.getMessage());}



        }
    }
}