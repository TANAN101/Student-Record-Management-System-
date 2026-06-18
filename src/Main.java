import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        String file_patxt = "student_info.txt";
        String file_padat="student_info.dat";
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

                PrintWriter pw = new PrintWriter(new FileWriter(file_patxt, true));

                pw.println(add.student_ID + " " + add.Name + " " + add.Department + " " + add.GPA);
                pw.close();
                DataOutputStream q = new DataOutputStream(new FileOutputStream(file_padat,true));

                q.writeInt(add.student_ID );
                q.writeUTF(add.Name );
                q.writeUTF(add.Department );
                q.writeDouble(add.GPA );
                q.close();

            } catch (IOException e) {
                System.out.println(e);
            }


        }
///search file
        if(a==2){
            System.out.println("Enter the individuals name: ");
            String who =input.next();

            try {

                Scanner look = new Scanner(new File(file_patxt));

            while(look.hasNext()){
                String line=look.nextLine();
                if (line.contains(who)){
                    System.out.println("available: "+line);
                }

            }




            }catch (IOException e){System.out.println(e);}

        }

        ///update file

        if (a == 3) {
            try {
                System.out.println("Enter the name you want to update: ");
                String whom = input.next();
                System.out.println("Enter the name you want to update it to: ");
                String To = input.next();

                // --- Update TXT ---
                Scanner update = new Scanner(new File(file_patxt));
                ArrayList<String> up_name = new ArrayList<>();
                while (update.hasNext()) {
                    up_name.add(update.nextLine());
                }
                update.close();

                for (int i = 0; i < up_name.size(); i++) {
                    if (up_name.get(i).contains(whom)) {
                        up_name.set(i, up_name.get(i).replace(whom, To));
                    }
                }
                // Move PrintWriter OUTSIDE the loop
                PrintWriter pupdate = new PrintWriter(new FileWriter(file_patxt));
                for (String s : up_name) {
                    pupdate.println(s);
                }
                pupdate.close();

                // --- Also update DAT ---
                DataInputStream in = new DataInputStream(new FileInputStream(file_padat));
                ArrayList<student> students = new ArrayList<>();

                try {
                    while (true) {
                        int id      = in.readInt();
                        String name = in.readUTF();
                        String dep  = in.readUTF();
                        double gpa  = in.readDouble();
                        if (name.equals(whom)) name = To; // apply update
                        students.add(new student(id, name, dep, gpa));
                    }
                } catch (EOFException e) { }
                in.close();

                DataOutputStream out = new DataOutputStream(new FileOutputStream(file_padat)); // overwrite
                for (student s : students) {
                    out.writeInt(s.student_ID);
                    out.writeUTF(s.Name);
                    out.writeUTF(s.Department);
                    out.writeDouble(s.GPA);
                }
                out.close();

            } catch (IOException e) { System.out.println(e); }
        }
///Remove student
        if (a==4){
            System.out.println("Who do you want to delete :  ");
            String delete = input.next();
           try{  Scanner remove = new Scanner(new File(file_patxt));
            ArrayList<String> rm_name=new ArrayList<>();
            while (remove.hasNext()){
                rm_name.add(remove.nextLine());}
            for (int i =0;i<rm_name.size();i++){
                if (rm_name.get(i).contains(delete)){
                    rm_name.remove(rm_name.get(i));
                }
            }
               PrintWriter pupdate = new PrintWriter(new FileWriter(file_patxt));
            for (String l:rm_name){
                pupdate.println(l);
            }
            pupdate.close();




           }catch (IOException e){System.out.println(e.getMessage());}



        }
        if (a==5){
            displayStudents show = new displayStudents();
            show.display();
        }
        if (a == 6) {
            double count =0;
            try{
                Scanner total = new Scanner(new File(file_patxt));

                while(total.hasNextLine()){
                    String[] liss=total.nextLine().split(" ");
                   Double sum =Double.parseDouble(liss[3]);
                   count=count+sum;

                }
                System.out.println(count);

            }catch (FileNotFoundException e){}

        }
        if (a==7){
            try{
                double finall=0;
                double set =0;
                Scanner higest = new Scanner(new File(file_patxt));
                while(higest.hasNextLine()){
                    String[] high=higest.nextLine().split(" ");
                    double highh =Double.parseDouble(high[3]);
                    if(set<=highh){
                         set=highh;
                    }
                }
                System.out.println(set);

            }catch (FileNotFoundException e){}
        }
    }
}