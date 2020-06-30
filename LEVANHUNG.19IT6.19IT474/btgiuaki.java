package javagiuaki;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class btgiuaki extends JFrame implements ActionListener {
	private JPanel contentPane;
	JFileChooser f1 = new JFileChooser();
	File f = null;
	TextArea textArea;
	JLabel lblaCh;
	JButton btnNewButton_4;
	JButton btnNewButton_5;
	private JTextField textField2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					btgiuaki frame = new btgiuaki("File Mannage");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public btgiuaki(String s) {
		super(s);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		textArea = new TextArea();
		lblaCh = new JLabel("");
		JButton btnNewButton_1 = new JButton("Open file");
		btnNewButton_1.addActionListener(this);
		btnNewButton_4 = new JButton("Sort");
		btnNewButton_4.addActionListener(this);
		btnNewButton_5 = new JButton("Close");
		btnNewButton_5.addActionListener(this);
		btnNewButton_5.setEnabled(false);
		textField2 = new JTextField();
		textField2.setColumns(10);
		JButton btnNewButton_S = new JButton("Search");
		btnNewButton_S.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				File dir = new File(textField2.getText());
				FilenameFilter filter = new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.startsWith(textArea.getText());
					}
				};
				String[] children = dir.list(filter);
				if (children == null) {
					System.out.println("Either dir does not exist or is not a directory");
				} else {
					for (int i = 0; i < children.length; i++) {
						String filename = children[i];
						textArea.append(filename + "\n");
					}
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(343)
							.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(274)
							.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(textField2, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnNewButton_S, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(77)
							.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_S)))
		);
		contentPane.setLayout(gl_contentPane);
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
		if(e.getActionCommand().equals("Sort")) {
			 File dir = new File("D:\\Learn\\FontEnd");

		      File[] files = dir.listFiles();

		      Arrays.sort(files, (f1, f2) -> {
		         return new Long(f1.length()).compareTo(new Long(f2.length()));
		      });

		      for (File file : files) {
		         if (!file.isHidden()) {
		            if (!file.isDirectory()) {
		               System.out.println("FILE\t" + " " + file.length() + " bytes\t\t" + file.getName());
		            }
		         }
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