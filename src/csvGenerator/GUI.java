package csvGenerator;
import java.awt.*;

import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

import java.io.File;



class GUI extends Frame implements ActionListener
{
TextField text1,text2,text3;
Button button_BrowseFile, button_BrowseDir,button_GenerateFiles;
TextField prefix;
static JLabel label, message;


final JFileChooser fc = new JFileChooser();

public GUI()
{
	//Exit when X is clicked
	addWindowListener(new WindowAdapter()
	{
		  public void windowClosing(WindowEvent we)
		  {	System.exit(0);  }
	});
	
	//Window label
    label = new JLabel("CSV Files Generator",JLabel.CENTER);
    label.setVerticalAlignment(JLabel.TOP);
    label.setFont(new Font("Verdana", Font.PLAIN, 15));
	add(label);
	
    message = new JLabel("s",JLabel.CENTER);


    button_BrowseFile =new Button("1. Browse CSV File");
	add(button_BrowseFile);
	
	button_BrowseDir = new Button("2. Open Files Directory");
	button_GenerateFiles = new Button("3. Generate Files And Directories");
	
	setSize(200,250);
	
	setTitle("CSV Files and Directories");
	setLayout(new FlowLayout());

	button_BrowseFile.addActionListener(this);
	button_BrowseDir.addActionListener(this);
	button_GenerateFiles.addActionListener(this);
	
	
	
}


void updateGUI()
{
	add(message);
	setSize(200,251);
	setSize(250,250);
}



public void browseFile()
{
	JFileChooser fc=new JFileChooser();
    FileNameExtensionFilter extFilter = new FileNameExtensionFilter("csv","csv");
    fc.addChoosableFileFilter(extFilter);
    fc.setDialogTitle("Choose a .csv file");
    fc.setAcceptAllFileFilterUsed(false);
    
    if(fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
    {    
    	
        File f=fc.getSelectedFile();
        
        //Get file name and display it on the message box
        csvReader.FILE=f.getPath();
        message.setText("File: "+f.getName());
        System.out.print(csvReader.FILE);
        add(button_BrowseDir);
        updateGUI();

    }
	


}

void browseDir()
{
    JFileChooser dc = new JFileChooser(); 
    dc.setDialogTitle("Choose a Directory");
    dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    dc.setAcceptAllFileFilterUsed(false);
    
    if (dc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
    {
    	csvReader.DIR=dc.getSelectedFile().getPath();
    	
    	//get directory name and display one the message box. 
    	message.setText("Dir: "+ dc.getSelectedFile().getName());
    	add(button_GenerateFiles);
    	updateGUI();

    	
     }
    


}
public void actionPerformed(ActionEvent action) 
{
	if(action.getSource()==button_BrowseFile)
	{
		browseFile();    
	}
	
	if(action.getSource()==button_BrowseDir)
	{
		browseDir(); 
	}
	
	if(action.getSource()==button_GenerateFiles)
	{
		csvReader.generateFiles();
		message.setText("Generating!");
	}
}
	
	

}