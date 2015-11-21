
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Box extends JFrame implements ActionListener
{
	/*
	 The Box class houses the main container cont.
	 cont is set to BocLayout. 
	 cont holds a the fields label, a text field, text area, and buttons
	 methods generate an instance of a particular field before appending
	 them to the container

	*tfmaker creates a new textfield
	*afmaker creates a new text area
	*btnmaker creates new buttons
	*labelmaker creates new labels

	 actionPerformed handles user input

	 // OTHER LIBRARIES USED:

	 Dimensions allows for use of custom sizing of fields
	 Component allows for manipulation of AWT fields, here it is used to align various fields
	 Container holds other AWT fields

	

	**FIXES**

	added regex pattern and matching libraries to allow for regex filtering of non a-z chars in actionPerformed()
	 */
	
	
	JTextField in;
	JTextArea out;
	JScrollPane scroller; //instantiate a scrollpane for use in text area
	JButton btn1, btn2, btn3, btn4;
	JLabel labe;
	
	
	

	public Box() 
	{
		setSize(500, 280);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container cont = new Container();
		
		add(cont);
		addToContainer(cont); //container is part of box
		setVisible(true);
		
	}


	public void addToContainer(Container cont)
	{
		
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS)); //container will be displayed in BoxLayout for y-axis
		labe = labelMaker(cont, "Type in some input, get some output");
		in = tfMaker(cont, "user input goes here"); //user input
		btn1 = btnMaker(cont, "Find number of words");//buttons
		btn2 = btnMaker(cont, "Find number of characters");
		btn3 = btnMaker(cont, "Put words in alphabetical order");
		btn4 = btnMaker(cont, "Find words of < 4 chars");
		out = afMaker(cont, "output goes here"); //output text area
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

	public JTextArea afMaker(Container cont, String fText)
	{
		out= new JTextArea(fText);
		cont.add(out);
		scroller = new JScrollPane(out); //new scrollpane for use with text area out
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //scroller will always show vertical scroll bar
        cont.add(scroller);
        return out;
	}

	public JLabel labelMaker(Container cont, String lText)
	{
		labe = new JLabel(lText);
        labe.setAlignmentX(Component.CENTER_ALIGNMENT);
        cont.add(labe);
        return labe;

	}

	public  JButton btnMaker(Container cont, String btnTxt)
	{
		JButton button = new JButton(btnTxt); //make a new instance of a button (for use with our global buttons)
		button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        cont.add(button);
        return button;
	}
	
	public void actionPerformed(ActionEvent evt) 
	{
		//appologies for long method
		if(evt.getSource() == btn1) //find number of words
		{
			String usrtxt = in.getText();
			if( !(usrtxt.equals(""))) //checks for empty input and responds accordingly
			{
					 
					String reg = "[^a-zA-Z +]"; //regex checking for any presence of non a-z characters from start to finish 
					String[] words = usrtxt.replaceAll(reg, "").split(" +"); //replaces any invalid characters with empty character
					//splits string usrtext based on presence of spaces to form a word array
					out.setText("number of words: " + words.length);
					
			}
			else
			{
				out.setText("please enter a word");
			}
				
		}
		else if(evt.getSource() == btn2) //find number of chars
		{
			String usrtxt = in.getText();
			if( !(usrtxt.equals("")))
			{
				String reg = "[^a-zA-Z +]";
				String[] words = usrtxt.replaceAll(reg, "").split(" +");
				String sansSpace = ""; //  here, we use a loop to concact each element of word into a string, so as not to include spaces
			for (int i = 0; i<words.length; i++)
			{
				sansSpace += words[i];
			}
			char[] chars = sansSpace.toCharArray(); //send spaceless string to a character array
			out.setText("number of characters (excluding spaces and non a-z chars): " + chars.length); //reads the length of this char arrayy
			}else
			{
				out.setText("please enter at least 1 word");
			}
				
		}
		else if(evt.getSource() == btn3) //put words in alphabetical order. capital letters > lower case letters
		{
			String usrtxt = in.getText();
			if( !(usrtxt.equals("")))
			{
			String reg = "[^a-zA-Z +]";
				String[] words = usrtxt.replaceAll(reg, "").split(" +");
			Arrays.sort(words); //ta da. words are now in alphabetical order thanks to .sort method from Arrays library
			String ans = "";
			for (int i = 0; i<words.length; i++)
			{
				ans += (words[i] + "\n"); //as befrore, conact elements from user txt array into one answer (with spaces);
			}
			out.setText(ans);	
			}
			else
			{
				out.setText("please enter at least 1 word");
			}	
		}
		else if(evt.getSource() == btn4) // displays words less than 4 chars long
		{
			String usrtxt = in.getText();
			if( !(usrtxt.equals("")))
			{
				String reg = "[^a-zA-Z +]";
				String[] words = usrtxt.replaceAll(reg, "").split(" +");
				String ans = "";
			for (int i = 0; i<words.length; i++)
			{
				if(words[i].length() < 4)
				{
					ans += (words[i] + "\n"); 
				}
			}
			out.setText(ans);
			}
			else
			{
				out.setText("please enter at least 1 word");
			}
					
		}
	}	

}
