
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class ReadF extends JFrame implements ActionListener
{
	
	/* 
	ReadF constructor sets default GUI/file chooser parameters
	
	addToContainer creates and adds all new GUI elements to main container

	various methods (tfmaker, afmaker, etc) produce individual gui elements

	in ActionListener, btn_opn opens filechooser browser
	btn_fnd adds a word to a file if found, or reports line(s) in file that
	contains the word (if a file has been chosen first).
	*/



	//instance variables
	JTextField in;
	JTextArea openout, wordarea;
	JScrollPane scroller; 
	JButton btn_opn, btn_fnd;

	JFileChooser fc;
	Container cont;
	File file;
	FileReader fr;
	JLabel labe;

	public ReadF() 
	{
		setSize(700, 280);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		cont = new Container();
		fc = new JFileChooser();
		add(cont);
		addToContainer(cont); 
		setVisible(true);	
	}


	public void addToContainer(Container cont)
	{
		//adds parts of GUI to container
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS)); //container will be displayed in BoxLayout for y-axis
		labe = l_maker(cont, "Open a file for reading");
		btn_opn = btnMaker(cont, "Open File");
		openout = afMaker(cont);
		labe = l_maker(cont, "Type in a word to search for. If word is not found, it will be added to the file");
		in = tfMaker(cont, "Boolean"); //user input
		btn_fnd = btnMaker(cont, "Find/Add word");
		wordarea = afMaker(cont);

		
	}

	public JTextField tfMaker(Container cont, String fText)
	{
		in= new JTextField(fText);
        in.setHorizontalAlignment(JTextField.CENTER); //sets allignment of in to center
		in.addActionListener(this); //actionlistener for Box
		in.setMaximumSize(new Dimension(400, 200)); //sets max size of in using Dimensions
        cont.add(in);
        return in; //method spits out a new text field
	}

	public JTextArea afMaker(Container cont)
	{
		JTextArea tixt = new JTextArea();
		tixt.setEditable(false);
		cont.add(tixt);
		scroller = new JScrollPane(tixt); //new scrollpane for use with text area out
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //scroller will always show vertical scroll bar
        cont.add(scroller);
        return tixt;
	}

	public JLabel l_maker(Container cont, String lbl)
	{
		labe = new JLabel(lbl);
		labe.setAlignmentX(Component.CENTER_ALIGNMENT);
        cont.add(labe);
        return labe;

	}

	public  JButton btnMaker(Container cont, String btnTxt)
	{
		JButton button = new JButton(btnTxt); //make a new a button (for use with our global buttons)
		button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        cont.add(button);
        return button; 
	}

	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource() == btn_opn) //open file browser
		{
			int browserVal = fc.showOpenDialog(ReadF.this); //browser value will be the file selected for reading

			if(browserVal == JFileChooser.APPROVE_OPTION) //if the file option is chosen...
			{
				try{

				file = fc.getSelectedFile(); //get the file
				openout.append(file + " selected...\n"); //shows file to be opened
				fr = new FileReader(file); //filereader set to read file
				}catch(IOException e) //else if no file was chosen this error will be shown in the terminal
				{
					System.err.println("error- no file selected " + e.getMessage());
				}
			}
		}
		
		else if(evt.getSource() == btn_fnd) //find or add word to file
		{
			
		ArrayList< String > lines = new ArrayList< String >(); //arraylist for each line of file
		String usrtxtb4 = in.getText(); //takes input from JTextField
		String usrtxt = usrtxtb4.replaceAll(" +", ""); //removes all spaces

		if(usrtxt.equals("")) //if input is null, please enter valid input
		{
			wordarea.append("Please supply at most one word/select file from browser first\n");
			return;
		}

		try{
  			FileInputStream fs = new FileInputStream(file); //put file in new inputstream
  			DataInputStream file_in = new DataInputStream(fs); //takes data from reader
  			BufferedReader br = new BufferedReader(new InputStreamReader(file_in)); //bufferedreader, takes inputstreamreader that reads data
  			String each_line; //instance string for bufferedreader
  			while (( each_line = br.readLine() ) != null)   //while each line in file is not null
  			{	
  				lines.add(each_line);
  			}
  			
  			file_in.close(); //close datainputstream
    		}
    		catch (Exception e)
    		{
  				System.err.println("error - file not read: " + e.getMessage());
  			}

  			int c = 0; //counter to see how many times word does not turn up in file
  			for(String elem : lines) //for each element of lines...
  			{
  				String a = elem.toLowerCase();
  				String b = usrtxt.toLowerCase(); //put everything to lower case and compare once.
  				if( a.indexOf(b) != -1 && !a.equals("") ) //if there is an index of user's text in each line and it isn't empty...
  				{
					wordarea.append("word " + usrtxt + "\nwas found in file " + file + "\nin line " + (lines.indexOf(elem)+1) + "\n");
  				}
  				else
  				{
					c++; //increment counter
  				}
  			}
  			if(c == lines.size()) //if the counter is the same size as the array of lines (i.e if there were no matches)
  			{
  				try{
  					BufferedWriter br = new BufferedWriter(new FileWriter(file, true)); //new bufferedwriter
  					br.write(usrtxt); //add usertext to file
  					br.newLine(); //set new line
  					br.flush(); //flush bufferedwriter stream, data will be visible before file is closed
  					wordarea.append(usrtxt + " was added to file " + file + "\n");
  				}catch (Exception e)
    			{
  					System.err.println("error - word not written to file:" + e.getMessage());
  				}
  			}
	
		}
		

	}


}
