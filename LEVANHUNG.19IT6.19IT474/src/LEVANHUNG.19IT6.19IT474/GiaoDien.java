package baithigiuaki;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
import java.awt.TextComponent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class GiaoDien extends JFrame implements ActionListener{
	private JPanel contentPane;
	JFileChooser f1 = new JFileChooser();
	 File f=null;
	 TextArea textArea;
	 JLabel lblaCh;
	 JButton btnNewButton_4;
	 JButton btnNewButton_5;
	 private JTextField textField2;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDien frame = new GiaoDien("File Mannage");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public GiaoDien(String s) {
		super(s);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea = new TextArea();
		textArea.setBounds(10, 10, 341, 120);
		contentPane.add(textArea);
		lblaCh = new JLabel("");
		lblaCh.setBounds(284, 28, 420, 22);
		contentPane.add(lblaCh);
		JButton btnNewButton_1 = new JButton("Open file");
		btnNewButton_1.setBounds(353, 11, 110, 21);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		btnNewButton_4 = new JButton("Rename");
		btnNewButton_4.setBounds(353, 61, 110, 21);
		btnNewButton_4.addActionListener(this);
		contentPane.add(btnNewButton_4);
		btnNewButton_4.setEnabled(false);
		btnNewButton_5 = new JButton("Close");
		btnNewButton_5.setBounds(353, 109, 114, 21);
		btnNewButton_5.addActionListener(this);
		contentPane.add(btnNewButton_5);
		btnNewButton_5.setEnabled(false);		
		textField2 = new JTextField();
		textField2.setBounds(26, 155, 299, 20);
		contentPane.add(textField2);
		textField2.setColumns(10);
		JButton btnNewButton_S = new JButton("Search");
		btnNewButton_S.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				File dir = new File(textField2.getText());
			      FilenameFilter filter = new FilenameFilter() {
			         public boolean accept
			         (File dir, String name) {
			            return name.startsWith(textArea.getText());
			        }
			      };
			      String[] children = dir.list(filter);
			      if (children == null) {
			         System.out.println("Either dir does not exist or is not a directory");
			      } 
			      else {
			         for (int i=0; i<children.length; i++) {
			            String filename = children[i];
			            textArea.append(filename+"\n");
			         }
			      }
			}
		});
		btnNewButton_S.setBounds(353, 154, 114, 23);
		contentPane.add(btnNewButton_S);
	}
	public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Open file")) {
				f1  = new JFileChooser("D:\\"); 
				f1.setDialogTitle("Open File");
				int returnVal = f1.showDialog(f1,"Open");
				if(returnVal== JFileChooser.APPROVE_OPTION) { 
				f = f1.getSelectedFile();
				textArea.setText(""); 
				lblaCh.setText(f.getAbsolutePath());
				System.out.println(f.getAbsolutePath());
					try {
						FileReader fis = new FileReader(f);
						int data = fis.read();
						while (data!=-1) {
							textArea.append(Character.toString((char)data));
							data = fis.read();
						}
						fis.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					btnNewButton_4.setEnabled(true);// hien thi cac nut bi an? 
					btnNewButton_5.setEnabled(true);	
				}
			}
			if(e.getActionCommand().equals("Rename")) {
				JFileChooser fr = new JFileChooser("D:\\");
				fr.setSelectedFile(f);
				int val = fr.showDialog(fr, "Rename");
				if(val == JFileChooser.APPROVE_OPTION) {
					File frgs = fr.getSelectedFile();
					if(f.renameTo(frgs)) {
						JOptionPane.showMessageDialog(null,"Rename success");
						f = fr.getSelectedFile();
					}else JOptionPane.showMessageDialog(null, "Fail");
				}
			}
			if(e.getActionCommand().equals("Close")) {
				f = null;
				textArea.setText("");
				lblaCh.setText(" ");
				btnNewButton_4.setEnabled(false);
			}
	}
}