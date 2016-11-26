
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

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JPasswordField;
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

public class Stcokapp extends JFrame /*implements ActionListener*/{

	private JPanel contentPane;
	private JTextField textField;	//Username textfield.
	private String username = "";
	private String selectedStock = "AAPL";	//Default value for the selected stock. 
	private JPasswordField passwordField;
	
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
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
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
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 5;
		gbc_passwordField.gridy = 5;
		panel.add(passwordField, gbc_passwordField);
		
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
	
		btnLogout.setBounds(331, 11, 88, 29);
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
		
		JButton btnLogout_2 = new JButton("Logout");
		
		
		JComboBox comboBox = new JComboBox(new String[] {"AAPL", "AXP", "BA", "CAT", "CSCO", "CVX", "KO", "DD", "XOM", "GE", "GS","HD","IBM", "INTC",
				"JNJ", "JPM", "MCD", "MMM", "MRK", "MSFT", "NKE", "PFE", "PG", "TRV", "UNH", "UTX", "V", "VZ", "WMT", "DIS"} );
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedStock = (String) comboBox.getSelectedItem();
				System.out.println(selectedStock);
			}
		});
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel_2.add(comboBox, gbc_comboBox);
		
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
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{346, 0, 89, 0};
		gbl_panel_3.rowHeights = new int[]{268, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JList list_1 = new JList();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.insets = new Insets(0, 0, 0, 5);
		gbc_list_1.gridx = 0;
		gbc_list_1.gridy = 0;
		panel_3.add(list_1, gbc_list_1);
		
		JButton btnChoose_1 = new JButton("choose");
		GridBagConstraints gbc_btnChoose_1 = new GridBagConstraints();
		gbc_btnChoose_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnChoose_1.gridx = 1;
		gbc_btnChoose_1.gridy = 0;
		panel_3.add(btnChoose_1, gbc_btnChoose_1);
		
		JButton btnChoose = new JButton("Logout");
		
		
		JButton btnHome_1 = new JButton("Home");
		btnHome_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "mainMenu");
			}
		});
		btnHome_1.setBounds(353, 47, 82, 29);
		panel_3.add(btnHome_1);
		
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.anchor = GridBagConstraints.NORTH;
		gbc_btnChoose.gridx = 2;
		gbc_btnChoose.gridy = 0;
		panel_3.add(btnChoose, gbc_btnChoose);
		
		JPanel panel_4 = new JPanel();
		//panel_4.setSize(800, 300);
		contentPane.add(panel_4, "stockdata");
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JButton btnNewButton = new JButton("choose history");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historyGraph chart;
				try {
					chart = new historyGraph("History Data Graph", "History Data for certain interval", selectedStock);
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
		
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		panel_4.add(textArea, gbc_textArea);
		
		GridBagConstraints gbc_btnLogout_1 = new GridBagConstraints();
		gbc_btnLogout_1.anchor = GridBagConstraints.NORTH;
		gbc_btnLogout_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogout_1.gridx = 2;
		gbc_btnLogout_1.gridy = 0;
		panel_4.add(btnLogout_1, gbc_btnLogout_1);
		
		JButton btnHome_2 = new JButton("Home");
		GridBagConstraints gbc_btnHome_2 = new GridBagConstraints();
		gbc_btnHome_2.anchor = GridBagConstraints.NORTH;
		gbc_btnHome_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnHome_2.gridx = 2;
		gbc_btnHome_2.gridy = 1;
		panel_4.add(btnHome_2, gbc_btnHome_2);
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel_4.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5, "viewLog");
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{340, 89, 55, 65, 0};
		gbl_panel_5.rowHeights = new int[]{23, 99, 23, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblNewLabel = new JLabel(" search history:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_5.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnLogout_3 = new JButton("Logout");
		
		GridBagConstraints gbc_btnLogout_3 = new GridBagConstraints();
		gbc_btnLogout_3.anchor = GridBagConstraints.NORTH;
		gbc_btnLogout_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogout_3.gridx = 3;
		gbc_btnLogout_3.gridy = 0;
		panel_5.add(btnLogout_3, gbc_btnLogout_3);
		DefaultListModel model = new DefaultListModel();
		//JList logList = new JList();
		JList logList = new JList(model);
		GridBagConstraints gbc_logList = new GridBagConstraints();
		gbc_logList.insets = new Insets(0, 0, 5, 5);
		gbc_logList.fill = GridBagConstraints.BOTH;
		gbc_logList.gridx = 0;
		gbc_logList.gridy = 1;
		panel_5.add(logList, gbc_logList);
		
		JButton btnViewStock = new JButton("View stock");
		GridBagConstraints gbc_btnViewStock = new GridBagConstraints();
		gbc_btnViewStock.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewStock.gridx = 1;
		gbc_btnViewStock.gridy = 1;
		panel_5.add(btnViewStock, gbc_btnViewStock);
		
		JButton btnBackButton = new JButton("Back");
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(),"mainMenu");
			}
		});
		GridBagConstraints gbc_btnBackButton = new GridBagConstraints();
		gbc_btnBackButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnBackButton.gridx = 2;
		gbc_btnBackButton.gridy = 1;
		panel_5.add(btnBackButton, gbc_btnBackButton);
	   
		
		/*	
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"AAPL", "AXP", "BA", "CAT", "CSCO", "CVX", "KO", "DD", "XOM", "GE", "GS","HD","IBM", "INTC",
					"JNJ", "JPM", "MCD", "MMM", "MRK", "MSFT", "NKE", "PFE", "PG", "TRV", "UNH", "UTX", "V", "VZ", "WMT", "DIS"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		*/
	
		
		//Setting button functionality.
		
		//Login button.
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
			String user, pass;
			user = textField.getText();
			pass = passwordField.getText();
			textField.setText("");
			passwordField.setText("");
			
			if(new login(user,pass).verify()){
				layout.show(getContentPane(), "mainMenu");
				username = user;
				lblNewLabel.setText(username + " search history");
				try {
					Log2.setLog(user);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		
		//Button which brings the user to the list of stocks.
		btnChooseStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "stockList");
			}
		});
		
		//Select stock button.
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						layout.show(getContentPane(), "stockdata");
						try { 
							Log2.setLogEmpty();
							Log2.writeToFile(selectedStock, username);	//Adding selected stock into the user's search log.
							Log2.displayLogInfo(username);
							Stock select = YahooFinance.get(selectedStock);
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
					    textArea.setText(baos.toString());
					  
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					}
				});
				


			
				//View log button
				btnViewLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Log2.displayLogInfo(username);
					model.clear();
					logList.setModel(new AbstractListModel() {
						String[] values = new String[] {Log2.getIndexElement(10),Log2.getIndexElement(9),Log2.getIndexElement(8),Log2.getIndexElement(7),Log2.getIndexElement(6),
					Log2.getIndexElement(5),Log2.getIndexElement(4),Log2.getIndexElement(3),Log2.getIndexElement(2),Log2.getIndexElement(1)};

						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
					
					layout.show(getContentPane(), "viewLog");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});	
		
		//Search history list.
		logList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedStock = (String)logList.getSelectedValue();
			}
		});
		
		//View stock from the log.
		btnViewStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(), "stockdata");
				try {
					Stock select = YahooFinance.get(selectedStock);
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
			    textArea.setText(baos.toString());	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		
		//Logout button from menu.
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(),"login");
				username = "";
			//	Log2.setLogEmpty();
				model.clear();
			/*	try {
					Log2.displayLogInfo(username);
					logList.setModel(new AbstractListModel() {
						String[] values = new String[] {"","","","","","","","","",""};
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		});
		//Logout button from the basic stock information panel.
				btnLogout_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						layout.show(getContentPane(), "login");
						username = "";
				//		Log2.setLogEmpty();
						model.clear();
					}
				});
				
		//Logout button from the choose stock menu.
		btnLogout_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(),"login");
				username = "";
				Log2.setLogEmpty();
				model.clear();
			}
		});
		
		//Logout button from log history menu.
				btnLogout_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						layout.show(getContentPane(),"login");
						username = "";
				//		Log2.setLogEmpty();
						model.clear();
					}
				});
				
		//Logout button from the choose stock data menu.
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(getContentPane(),"login");
				username = "";
			//	Log2.setLogEmpty();
				model.clear();
		/*		try {
					Log2.displayLogInfo(username);
					logList.setModel(new AbstractListModel() {
						String[] values = new String[] {"","","","","","","","","",""};
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		});
	}
}