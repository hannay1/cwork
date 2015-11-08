import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Interface 
{

static byte[] encrypted;
static ArrayList<byte []> lst = new ArrayList<byte []>();
static Scanner input = new Scanner(System.in);
// we need to figure out which fields are static, what isnt
   
	public void mainNonStatic(String [] args) 
    {
        boolean done = false;
        System.out.println("Password Bank\n");
        do
        {
           int choice =  menu(input);
            switch(choice)
                {  
                case 1: get_AES_password(input);
                break;
                case 2: System.out.println( "filler");
                break;
                case 3: System.out.println("goodbye");
                        System.exit(1);
                break;
                }

        }while(!done);
    }

    int menu(Scanner input)
    {
    	String message = 
    	"Please select from the below\n" +
        "1. Enter new password\n" +
        "2. Select & decrypt saved passwords\n" +
        "3. Quit\n";
        System.out.println(message);
        //3. Edit password list?   
        return get_menu_input(1, 3, "Please enter # from 1-3", input); 
    }


    int get_menu_input(int low, int high, String message, Scanner input)
    {
        System.out.println(message);
        try { 
                String text = input.nextLine();
                int num = Integer.parseInt( text );
                if(num < low || num > high)
                {
                    System.out.println(message);
                }else
                {
                    return num;
                }
            }
            catch(Exception e) {
                System.out.println("error input");
            }
            return 0;
    }

    String get_AES_password(Scanner input)
    {
        //encryption key generator
        Random rand = new Random();
        String chars ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
        char[] e_char =  new char[16];
        for(int i = 0; i< 16; i++)
        {
            e_char[i] = chars.charAt(rand.nextInt(chars.length()));
        }
        String e_key = new String(e_char);
        

        //checks for valid password
        System.out.println("Please enter a password of 8-16 chars long");
        try{

            String usrpass = input.nextLine();
       
            if (usrpass.length() > 16 || usrpass.length() < 8)
            {
                System.out.println("Please enter a valid password, only 8-16 chars long");
                return get_AES_password(input);

            }else if(usrpass.length() < 16)
            {
                int span = 16 - usrpass.length();
                for (int i = 0; i< span; i++)
                {
                    usrpass = usrpass + "\0";
                }

                System.out.println(e_key);
                encrypted = Cryptfunc.encrypt(usrpass,e_key); //send this to an array
                //
                System.out.print("encrypted:  ");
                for (int i=0; i<encrypted.length; i++)
                    {
                    System.out.print(new Integer(encrypted[i])+" ");
                    }
                    System.out.println("");

                    lst.add(encrypted);
                    System.out.println(lst);
                    
                
            }
            System.out.println(usrpass);
            }
            catch(Exception e) {}
            return "";

    }

    String get_AES_decrypt(Scanner input)
    {
        //PRESENT ARRAY somehow, prompt user to  search for password BY INDEX,
        //select password, prompt user for key, decrypt. 

    }

  



  
 public static void main( String [] args )
    {
        (new Interface()).mainNonStatic( args );
    }

    
}