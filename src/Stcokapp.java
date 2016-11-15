import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.CardLayout;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import yahoofinance.*;

import javax.swing.JTextArea;

import org.jfree.ui.RefineryUtilities;

//import org.jfree.ui.RefineryUtilities;

public class Stcokapp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String user, pass, selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stcokapp frame = new Stcokapp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Stcokapp() {
		JPanel panel_1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(450,300));
		pack();
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		CardLayout layout = new CardLayout(0,0);
		contentPane.setLayout(layout);
		
		JPanel panel = new JPanel();
		//panel.setBounds(100, 100, 450, 300);
		//panel.setSize(1000,300);
		contentPane.add(panel, "login");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblUserName = new JLabel("User Name:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 4;
		gbc_lblUserName.gridy = 3;
		panel.add(lblUserName, gbc_lblUserName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 5;
		gbc_textField.gridy = 3;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 4;
		gbc_lblPassword.gridy = 5;
		panel.add(lblPassword, gbc_lblPassword);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 5;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
			
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 5;
		gbc_btnLogin.gridy = 7;
		panel.add(btnLogin, gbc_btnLogin);
		
		
		panel_1 = new JPanel();
		//panel_1.setBounds(100, 100, 450, 300);
		contentPane.add(panel_1, "mainMenu");
		panel_1.setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(),"login");
				user = "";
				pass = "";
			}
		});
		btnLogout.setBounds(352, 8, 88, 29);
		panel_1.add(btnLogout);
		
		JButton btnChooseStock = new JButton("choose stock");
		btnChooseStock.setBounds(129, 72, 128, 29);
		panel_1.add(btnChooseStock);
		
		JButton btnViewLog = new JButton("view log");
		btnViewLog.setBounds(145, 166, 96, 29);
		panel_1.add(btnViewLog);
		
		JPanel panel_2 = new JPanel();
		//panel_2.setBounds(100, 100, 450, 300);
		contentPane.add(panel_2, "stockList");
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JList list = new JList();
	
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"AAPL", "AXP", "BA", "CAT", "CSCO", "CVX", "KO", "DD"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 8;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		panel_2.add(list, gbc_list);
		
		JButton btnLogout_2 = new JButton("Logout");
		btnLogout_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "login");
				user = "";
				pass = "";
			}
		});
		GridBagConstraints gbc_btnLogout_2 = new GridBagConstraints();
		gbc_btnLogout_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogout_2.gridx = 1;
		gbc_btnLogout_2.gridy = 0;
		panel_2.add(btnLogout_2, gbc_btnLogout_2);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "mainMenu");
			}
		});
		GridBagConstraints gbc_btnHome = new GridBagConstraints();
		gbc_btnHome.insets = new Insets(0, 0, 5, 0);
		gbc_btnHome.gridx = 1;
		gbc_btnHome.gridy = 1;
		panel_2.add(btnHome, gbc_btnHome);
		JButton btnSelect = new JButton("select");
		
		
		
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.gridx = 1;
		gbc_btnSelect.gridy = 7;
		panel_2.add(btnSelect, gbc_btnSelect);
		
		JPanel panel_3 = new JPanel();
		//panel_3.setBounds(100, 100, 450, 300);
		contentPane.add(panel_3, "log");
		panel_3.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setBounds(0, 0, 341, 268);
		panel_3.add(list_1);
		
		JButton btnChoose_1 = new JButton("choose");
		btnChoose_1.setBounds(346, 119, 89, 29);
		panel_3.add(btnChoose_1);
		
		JButton btnChoose = new JButton("Logout");
		btnChoose.setBounds(353, 6, 82, 29);
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "login");
				user = "";
				pass = "";
			}
		});
		panel_3.add(btnChoose);
		
		JButton btnHome_1 = new JButton("Home");
		btnHome_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "mainMenu");
			}
		});
		btnHome_1.setBounds(353, 47, 82, 29);
		panel_3.add(btnHome_1);
		
		JPanel panel_4 = new JPanel();
		//panel_4.setSize(800, 300);
		contentPane.add(panel_4, "stockdata");
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{167, 143, 25, 112, 0};
		gbl_panel_4.rowHeights = new int[]{29, 209, 29, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JButton btnNewButton = new JButton("choose history");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historyGraph chart;
				try {
					chart = new historyGraph("History Data Graph", "History Data for certain interval", selected);
					chart.pack( );          
				      RefineryUtilities.centerFrameOnScreen( chart );          
				      chart.setVisible( true ); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			}
		});
		
		JButton btnLogout_1 = new JButton("Logout");
		btnLogout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "login");
				user = "";
				pass = "";
			}
		});
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridheight = 2;
		gbc_textArea.gridwidth = 3;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		panel_4.add(textArea, gbc_textArea);
		GridBagConstraints gbc_btnLogout_1 = new GridBagConstraints();
		gbc_btnLogout_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnLogout_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogout_1.gridx = 3;
		gbc_btnLogout_1.gridy = 0;
		panel_4.add(btnLogout_1, gbc_btnLogout_1);
		
		JButton btnHome_2 = new JButton("Home");
		GridBagConstraints gbc_btnHome_2 = new GridBagConstraints();
		gbc_btnHome_2.anchor = GridBagConstraints.NORTH;
		gbc_btnHome_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnHome_2.gridx = 3;
		gbc_btnHome_2.gridy = 1;
		panel_4.add(btnHome_2, gbc_btnHome_2);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel_4.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnMovingAverage = new JButton("moving average");
		GridBagConstraints gbc_btnMovingAverage = new GridBagConstraints();
		gbc_btnMovingAverage.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnMovingAverage.insets = new Insets(0, 0, 0, 5);
		gbc_btnMovingAverage.gridx = 1;
		gbc_btnMovingAverage.gridy = 2;
		panel_4.add(btnMovingAverage, gbc_btnMovingAverage);
		
		JButton btnRecommedation = new JButton("recommedation");
		btnRecommedation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphGUI chart = new graphGUI("Recommedation", "Recommedation for "+selected);
			      chart.pack( );          
			      RefineryUtilities.centerFrameOnScreen( chart );          
			      chart.setVisible( true );
			}
		});
		GridBagConstraints gbc_btnRecommedation = new GridBagConstraints();
		gbc_btnRecommedation.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRecommedation.gridwidth = 2;
		gbc_btnRecommedation.gridx = 2;
		gbc_btnRecommedation.gridy = 2;
		panel_4.add(btnRecommedation, gbc_btnRecommedation);
		btnLogin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//String user, pass;
			user = textField.getText();
			pass = textField_1.getText();
			textField.setText("");
			textField_1.setText("");
			
			if(new login(user,pass).verify())
				layout.show(getContentPane(), "mainMenu");
		}
	});
		
		btnChooseStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "stockList");
			}
		});
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selected = (String)list.getSelectedValue();
				
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						layout.show(getContentPane(), "stockdata");
						try {
							Stock select = YahooFinance.get(selected);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
					    PrintStream ps = new PrintStream(baos);
					    // IMPORTANT: Save the old System.out!
					    PrintStream old = System.out;
					    // Tell Java to use your special stream
					    System.setOut(ps);
					    // Print some output: goes to your special stream
					    select.print();
					    // Put things back
					    System.out.flush();
					    System.setOut(old);
					    // Show what happened
					    //System.out.println("Here: " + baos.toString());
					    textArea.setText(baos.toString());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						log.add(user, selected);
					}
				});
			}
		});
		
	}
}
