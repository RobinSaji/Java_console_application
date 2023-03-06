import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
    

//admin password 9025
public class railway{
    static Scanner sc = new Scanner(System.in);
    static int[] station ={10,10,10,10,10};
    static int ticket_no=1;

    static ArrayList<String> reservation = new ArrayList<>();
    static ArrayList<String> waitingList = new ArrayList<>();

    public static void main(String[] args) {
        
        Main();

    }
    public static void Main(){
        System.out.println("Enter who you are :");
        System.out.println("\n1.Admin \n2.user \n3.Exit");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                admin();
                break;
            case 2:
                User();
            case 3:
                System.exit(1);
        }

    }

    public static void admin(){
        System.out.println("Enter the admin passsword:");
        int password = sc.nextInt();

        if(password==9025){
            System.out.println("*************Welcome**************");
            System.out.println("Available seats are");
            for(int i=0;i<station.length;i++){
                int n = i+1;
                System.out.println("the availble seats in station :"+n);
                System.out.println(station[i]);
                
            }
            System.out.println("Enter your choice \n1 to check bookings \n2  to see waiting lists:");
            int c = sc.nextInt();

            switch(c){
                case 1:
                System.out.println(reservation);  
                Main();
                case 2:
                System.out.println(waitingList);
                Main();
              
            }

        }
        else{
            System.out.println("************Invalid admin***************");
        }
    }
    

    public static void registration(){
        int count=0;int c=0;
        for (int i=0;i<4;i++){
            if(station[i]==0){
                count++;

            }
        }
        if(count>=4){
            System.out.println("*****************All seats are filleed******************** ");
            System.out.println("Enter your choice \n1.waiting \n2.Exit");
            int n = sc.nextInt();
            switch(n){
                case 1:
                    waiting();
                    break;
                case 2:
                    Main();
                    break;
            }
        }
        else{
            System.out.println("****************Welcome to rgistration**********************");
            System.out.println("Enter your name:");
            String name = sc.next();
            System.out.println("\n0.Cbe \n1.TUP \n2.ED \n3.SA \n4.MAS");
            System.out.println("Enetr your boarding id:");
            int boardpoint = sc.nextInt();
            if(boardpoint>6){
                System.out.println("Invalid Entry ");
                registration();
            }
            System.out.println("Enter the destination station id :");
            int destinationpoint= sc.nextInt();
            if((boardpoint<destinationpoint && boardpoint!=destinationpoint)&& (destinationpoint==1 ||destinationpoint==2||destinationpoint==3||destinationpoint==4)){
                for(int i=boardpoint;i<destinationpoint;i++){
                    if(station[i]==0){
                        c++;
                    }

                }
                if(c>0){
                    System.out.println("you dont have tickets right now" );
                    System.out.println("Enter 1 for Waiting list or 2 for exit" );
                    int w = sc.nextInt();
                    if(w==1){
                        waiting();

                    }
                    else{
                        Main();
                    }
                }
                else{
                    for(int i=boardpoint;i<=destinationpoint;i++){
                        station[i]=station[i]-1;
                    }
                    System.out.println();
                    System.out.println("Your Ticket is booked .\n ticket number "+ticket_no+" "+name+" "+boardpoint+" "+destinationpoint+" ...");
                    reservation.add(ticket_no+" "+name+" "+boardpoint+" "+destinationpoint);
                    ticket_no++;
                    Main();
                }
            }
            else{
                Main();
            }
        }

    
    }
    public static void cancellation(){
        System.out.println("*************Cancellation***************");
        System.out.println("Enter your ticket id:");
        int id = sc.nextInt();
        System.out.println("Enter your name:");
        String name= sc.next();
        System.out.println("Enter your Boarding point:");
        System.out.println("\n0.Cbe \n1.TUP \n2.ED \n3.SA \n4.MAS");
        int board= sc.nextInt();
        System.out.println("Enter your destination point: ");
        int dest = sc.nextInt();
        String cancel = id+" "+name+" "+board+" "+dest;
        if(reservation.contains(cancel)){
            System.out.println("Your Ticket is Canceled");
            reservation.remove(cancel);
            for(int i=board;i<=dest;i++){
                station[i]=station[i]+1;
            }

            if(!waitingList.isEmpty()){
                for(int i=0;i<waitingList.size();i++){
                    int a =0;
                    String new_waiter[]=waitingList.get(i).split(" ");
                    int waiters_boarding = Integer.parseInt(new_waiter[2]);
                    int waiters_destination = Integer.parseInt(new_waiter[3]);
                    String waiters_name= new_waiter[1];
                    for(int j = waiters_boarding;j<=waiters_destination;j++){
                        if(station[j]==0){
                            a++;
                        }
                    }
                    if(a==0){
                        for(int z=waiters_boarding;z<waiters_destination;z++){
                            station[z]=station[z]-1;
                        }
                        reservation.add(ticket_no+" "+waiters_name+" "+waiters_boarding+" "+" "+waiters_destination);
                        ticket_no++;
                        waitingList.remove(i);
                        Main();
                    }

                }
            }
            Main();

            
        }
        else{
            System.out.println("Invalid Sentence");
            Main();
        }
    }

    public static void waiting(){
        int w =1 ;
        if(waitingList.size()<5){
            System.out.println("Enter your name:");
            String name = sc.next();
            System.out.println("Enter your boarding point:");
            System.out.println("\n0.Cbe \n1.TUP \n2.ED \n3.SA \n4.MAS");
            int b = sc.nextInt();
            System.out.println("Enter your Destination Point");
            int d = sc.nextInt();
            
            waitingList.add(w+" "+name+" "+ " "+b+" "+d);
            System.out.println("You are added to the waiting list : \n waiting list id:"+w);
            w++;
            System.out.println("Thank you ");
            Main();
            
        }
        else{
            System.out.println("The waiting list filled");
        }
    }

    public static void User(){
        System.out.println("Enter your choice :");
        System.out.println("\n1.Registration  \n2.Cancellation \n3.Exit");
        int n = sc.nextInt();
        switch(n){
            case 1:
                registration();
                break;
            case 2:
                cancellation();
            case 3:
                Main();
        }
    }
}