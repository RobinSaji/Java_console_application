import java.util.Scanner;

public class newbank {
    Scanner sc = new Scanner(System.in);
    public static String[] state= new String[3];
    public static int[][] amount = new int[2][4];
    public static int[][] arr = { { 001,1111,1000 }, {002, 2222,20000 },{003,3333,10000} };
    public static int balance= arr[0][2] +arr[1][2]+arr[2][2];

    
    
    public static void admin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter what you want do:");
        System.out.println("\n1.Load Amount \n2.Check Balance \n3.Go Back \n4.Exit");
        int a= sc.nextInt();
        do{switch(a){
            case 1 : Load_Amount();
            break;

            case 2:Balance();
            break;

            case 3: 
            Main();

            case 4:
            System.exit(a);

        }}while(true);


    
    }
    public static void Load_Amount(){
        denomination('A',0);
    }

    //Admin And User Denomination..
    public static void denomination(char w,int i){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount :");
        int a=sc.nextInt();
        System.out.println("Enter the denomination value : ");
        System.out.print("2000 :");
        int a1=sc.nextInt();
        if(a1+amount[1][0]<=100){
            System.out.println("Valid value");
            amount[1][0]+=a1;
            balance += 2000*a1;
        }
        else{
            System.out.println("***************System only accepts "+ (100-amount[1][0])+" notes*****************");
            admin();
        }
        System.out.println("500 :");
        int a2=sc.nextInt();
        if(a2+amount[1][1]<=100){
            System.out.println("Valid value");
            amount[1][1]+=a2;
            balance+=500*a2;
        }
        else{
            if(a1 !=0 ){
                amount[1][0]-=a1;
                balance -= 2000*a1;
            }
            System.out.println("****************System only accepts "+ (100-amount[1][1])+" notes****************");
            admin();
        }
        System.out.println("200 :");
        int a3=sc.nextInt();
        if(a3+amount[1][2]<=100){
            System.out.println("Valid value");
            amount[1][2]+=a3;
            balance+=200*a3;
        }
        else{
            if(a1 !=0 ){
                amount[1][0]-=a1;
                balance -= 2000*a1;
            }
            if(a2 !=0 ){
                amount[1][1]-=a2;
                balance -= 500*a2;
            }
            System.out.println("***************System only accepts "+ (100-amount[1][2])+" notes*****************");
            admin();
        }
        System.out.println("100 :");
        int a4=sc.nextInt();
        if(a4+amount[1][3]<=100){
            System.out.println("Valid");
            amount[1][3]+=a4;
            balance+=100*a4;
        }
        else{
            if(a1 !=0 ){
                amount[1][0]-=a1;
                balance -= 2000*a1;
            }
            if(a2 !=0 ){
                amount[1][1]-=a2;
                balance -= 500*a2;
            }
            if(a3!=0){
                amount[1][2]-=a3;
                balance -= 200*a3;
            }
            System.out.println("******************System only accepts "+ (100-amount[1][3])+" notes*****************");
            admin();
        }
        if(a1*2000+a2*500+a3*200+a4*100 == a){
            //System.out.println(a1*2000+a2*500+a3*200+a4*100);
            System.out.println("*************Amount Loaded Successfully***************");
        }
        else{
            System.out.println("***************Amount and the denomination value does not match*****************");
            balance-=a1*2000+a2*500+a3*200+a4*100;
            amount[1][0]-=a1;
            amount[1][1]-=a2;
            amount[1][2]-=a3;
            amount[1][3]-=a4;
            if (w=='A'){
                denomination(w, i);
            }
            else{
                User(i);
            }
        }
        if (w=='A'){
            System.out.println("Select any one : ");
            System.out.println("To go to admin : 1");
            System.out.println("To go to main menu : 2");
            int n3 = sc.nextInt();
            if(n3==1){
                admin();
            }
            else if(n3==2){
                Main();
            }
        }
        
        else if (w=='U'){
            arr[i][2]+=a;
            state[i] = state[i]+ '\n'+"Amount deposited of "+ a +" at ";
            System.out.println("Your available balance is" + arr[i][2]);
            User(i);}
        }

    //Admin Balance
    public static void Balance(){
        System.out.println("the balance amount in the atm is "+balance);
        admin();
    }

    

    // User Deposit
    public static void Deposit(int i){
            denomination('U', i);
    }

    //User Check Balance
    public static void checkBalance(int i){
        System.out.println("The available balance is "+arr[i][2]);
        User(i);
    }

    //User Withdrawl
    public static void withdraw(int i ){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount to be withdrawn :");
        int with = sc.nextInt();
        if (with > arr[i][2] || arr[i][2] < 500) {
            System.out.println("****************Insufficient balance******************");
        }
        else if (with%100 != 0){
            System.out.println("***************Plese enter value in 100's*****************");
            User(i);
       }
        else{
            arr[i][2]=arr[i][2]-with;
            balance=balance-with;
            System.out.println("***************Succesfully withdrawn....***************");
        }
        User(i);


    }

    //User Pinchange
    public static void pinchange(int i ){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the old pin:");
        int p=sc.nextInt();
        if(p==arr[i][1]){
            System.out.println("Please enter the new pin:");
            int p1=sc.nextInt();
            System.out.println("Please re-enter the pin:");
            int p2=sc.nextInt();
            if(p1==p2){
                arr[i][1]=p1;
                System.out.println("**************Pin changed successfully*****************");
                // String timeStamp = df.format(new Date());
                state[i] = state[i]+ '\n'+"New pin changed ";
                user();
            }
            else{
                System.out.println("**************Please renter the pin correctly****************");
                pinchange(i);
            }
        }
    }


    //User
    public static void user(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your  id");
        int id = sc.nextInt();
        int a =0;
        for(int i=0;i<3;i++){
            if(id == arr[i][0]){
                a=1;
                System.out.println("Enter your pin :");
                int pin =sc.nextInt();
                if(pin==arr[i][1]){
                    User(i);
                }
                else{
                    System.out.println("*******password incorect*******");
                    user();
                }

            }
        }
        if(a==0){
            System.out.println("Enter proper id :");
            user();

        }
    }

    // user choice
    public static void User(int i){
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n1. Deposit \n2.Withdraw \n3.Check Balance \n4.GoBack \n5.Pinchange \n6.transfer \n7.Exit");
            System.out.println("Enter your choice : ");
            int choice =sc.nextInt();
            switch (choice) {
                case 1:
                    Deposit(i);
                    break;
                case 2:
                    withdraw(i);
                    break;
                case 3:
                    checkBalance(i);
                    break;
                case 4:
                    Main();
                    break;
                case 5 :
                        pinchange(i);
                        break;
                case 6:
                    transfer(i);
                case 7:
                    System.exit(1);
            }
        } while (true);
    }

    //Money Transfer
    public static void transfer(int i){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the user id to whom you want to transfer:");
        int u=sc.nextInt();
        int v=0;
        for(int k=0;k<arr.length;k++){
            if(u==arr[k][0]){
                v++;
                System.out.println("Please enter the amount to be transferred :");
                int h1= sc.nextInt();
                if(arr[i][2] >= h1){
                        arr[k][2]+=h1;
                        arr[i][2]-=h1;
                        System.out.println("****************Succesfull*****************");
                        state[i] = state[i]+ '\n'+"Amount transferred of "+ h1 +" at "+ " to "+ arr[k][1];
                        System.out.println("Your available balance is" + arr[i][2]);
                        User(i);
                }
                }}}

    // Admin or User 
    public static void Main(){
        Scanner sc = new Scanner(System.in);
        System.out.println( "Enter what role you want to choose :");
        System.out.println("\n1. Admin \n2.User ");
        int od = sc.nextInt();
        switch (od) {
            case 1:
                admin();
                break;
            case 2:
                user();
                break;
        }
    }
    public static void main(String[] args) {
        amount[0][0]=2000;
        amount[0][1]=500;
        amount[0][2]=200;
        amount[0][3]=100;
        Main();
    }
    
}
