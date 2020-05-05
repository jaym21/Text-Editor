package projects;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame {
	
	private JTextArea textArea = new JTextArea(20,60);
	private JFileChooser fc = new JFileChooser();

	//CONSTRUCTOR FOR WINDOW
	public TextEditor() {
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//FILTER FOR TXT FILES
		FileFilter txtFilter = new FileNameExtensionFilter("Plane text","txt");
		fc.setFileFilter(txtFilter);
		
		//MENU
		add(scrollPane);	 
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		//ACTIONS ON FILES
		file.add(Open);
		file.add(Save);
		file.addSeparator();
		file.add(Exit);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	//DEFINING ACTIONS
	
	Action Open = new AbstractAction("Open File") {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(fc.showOpenDialog(null)== JFileChooser.APPROVE_OPTION){
				openFile(fc.getSelectedFile().getAbsolutePath());	
			}
		}
	};
	
	Action Save = new AbstractAction("Save File") {

		@Override
		public void actionPerformed(ActionEvent e) {
			saveFile(null);
		}
	};
	
	Action Exit = new AbstractAction("Exit") {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	};
	
	public void openFile(String fileName) {
		FileReader fr = null;
		try {
		fr = new FileReader(fileName);
		textArea.read(fr, null);
		fr.close();
		setTitle(fileName);
	}catch(IOException e) {
		e.printStackTrace();
	}
	}
	
	public void saveFile(String fileName) {
		if(fc.showSaveDialog(null)== JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				fw = new FileWriter(fc.getSelectedFile().getAbsolutePath());
				textArea.write(fw);
				fw.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args) {

		new TextEditor();
	}

}
 