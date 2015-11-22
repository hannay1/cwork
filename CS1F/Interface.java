import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Base64;
import java.io.*;
import java.util.Date;

public class Interface 
{

static Scanner input = new Scanner(System.in);
static String file = "enc.txt";
static String tagfile = "tags.txt";

 
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
                case 2: dec_menu(input);
                break;
                case 3: System.out.println("goodbye");
                        System.exit(1);
                
                }

        }while(!done);
    }

    int menu(Scanner input)
    {
    	String message = 
    	"Please select from the below\n" +
        "1. Enter/save new password\n" +
        "2. Select & decrypt saved passwords\n" +
        "3. Quit";
        System.out.println(message);
        return get_menu_input(1, 3, "Please enter # from 1-3", input); 
    }

       


    int get_menu_input(int low, int high, String message, Scanner input)
    {
        
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

    void get_AES_password(Scanner input)
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
        System.out.println("\n" + "Please enter a password of 8-16 chars long, no spaces." + "\n" +
         "type 'm' to exit to main menu");
        try{

            String usrpass = input.nextLine();
            if(usrpass.equals("m"))
            {
                return; //quits to main menu
            }
       
            if (usrpass.length() > 16 || usrpass.length() < 8 || usrpass.indexOf(" ") != -1)
            {

                System.out.println("length must be 8-12 chars and must not have spaces");
                get_AES_password(input);

            }else if(usrpass.length() < 16)
            {
                int span = 16 - usrpass.length();
                for (int i = 0; i< span; i++)
                {
                    usrpass = usrpass + "\0";
                }

                System.out.println("\n" + "encryption key: " + e_key);

                byte [] encrypted = Cryptfunc.encrypt(usrpass,e_key);
                System.out.println(encrypted);

                System.out.println("Please enter a breif description for this password, ie 'facebook'");
                String usr_tag = input.nextLine();
                
                /*
                two files, one storing encoded passwords, one storing time + tags,
                instead of reading through one chracter by character
                not the most efficient way of storing user tags, but o well. we didn't do regex so I won't here.
                */

                Date now = new Date(); //time password was added
                BufferedWriter tr = new BufferedWriter(new FileWriter("tags.txt", true));
                tr.write("'" + usr_tag +  "', added on " + now);
                tr.newLine();
                tr.flush();

                String enc64 = Base64.getEncoder().encodeToString(encrypted);
                BufferedWriter br = new BufferedWriter(new FileWriter("enc.txt", true));
                br.write(enc64);
                br.newLine();
                br.flush();

                System.out.println("a password for " + usr_tag + " has been added" + "\n");   
            }
            
            }
            catch(Exception e) {}
            
    }

    
    int dec_menu(Scanner input) 
    {

       //decryption menu
        String message = "\nPlease select from the passwords below below,\n" +
        "type 'm' to go back to the main menu\n";
        System.out.println(message);
        boolean dec_done = false;

        //opens files for reading
        FileReader fr = null;
        FileReader ur = null;
        try {
            fr = new FileReader( file );
            ur = new FileReader(tagfile);
        }
        catch( FileNotFoundException e ) {
            System.out.println( "Can't read file: "+file );
            System.exit( 1 );
        }
        
        BufferedReader br = new BufferedReader(fr);//reads password file
        BufferedReader tr = new BufferedReader(ur);//reads tag file
        ArrayList< String > lines = new ArrayList< String >(); //saves encrypted passwords temporarily
        ArrayList< String > tags = new ArrayList< String >();//saves tags and timestamp

        boolean done = false;
        while( !done )
        {
            try {
                String line = br.readLine();
                String taag = tr.readLine();
                if( line == null && taag == null ) 
                {
                    done = true;
                }
                else {
                    lines.add(line);
                    tags.add(taag);
                }
            }
            catch( IOException e ) {
                System.out.println( "Couldn't read file "+file );
                System.exit( 1 );
            }
        }

      
         if(lines.size() != 0)
            {
                for (int i = 0; i<lines.size(); i++)
                {
                    System.out.println( (i + 1) +  ". " + "password for " + tags.get(i));
                }
            }
            else
            {
                System.out.println("please enter at least one password first\n");
                return -1;
            }

        try
        {
            String dech = input.nextLine();

            if(dech.equals("m"))
            {
                return -1;
            }

            int num = Integer.parseInt(dech);
            if (! (dech == lines.get(num -1)) )
            {
               
                do
                {
                    System.out.println("Please input key for " + "password " + num + "\n");
                    String usr_enc = input.nextLine();
                  

                    byte[] d64 = Base64.getDecoder().decode(lines.get((num - 1))); 
                    String decd = Cryptfunc.decrypt(d64,usr_enc); 

                    if(!isValid(decd))
                    {
                        System.out.println("Could not decode password " + num + ", key did not match");
                        return dec_menu(input);

                    }
                    System.out.println("DECODED PASSWORD IS: "  +  decd);
                    
                    return dec_menu(input);    
                }
                while(!dec_done);

            }
        }catch(Exception e){}
        return dec_menu(input);
    }


    //checks for valid decryption. invalid decryption will yield non-ASCII characters...
    boolean isValid(String dex) 
    {   //enhanced for loop
        for ( char c: dex.toCharArray() ) //for each character in the character array of string dex..
        {
            if (((int)c)>127) //if value of some byte derived from dex is above 127 aka not standard ASCII ..
            {
                return false; //password did not decrypt properly
            } 
        }
        return true;//otherwise it did. 

    }


 public static void main( String [] args )
    {
        (new Interface()).mainNonStatic( args );
    }
   
}