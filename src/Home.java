import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JTextArea;

public class Home {

	private JFrame frmHaiPerso, frmVendor;
	public Sqlaccess db;
	private PreparedStatement results;
	private Connection connection;
	private FileReader file;
	ArrayList<Player> Current;
	String curr;
	int Lottery;
	RandomInteger random=new RandomInteger();
	Player p;
	Vendor v;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmHaiPerso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Home() throws SQLException {
		Current= new ArrayList<>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		db= new Sqlaccess();
		file=new FileReader();
		frmHaiPerso = new JFrame();
		frmHaiPerso.getContentPane().setForeground(new Color(255, 204, 0));
		frmHaiPerso.getContentPane().setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		frmHaiPerso.setPreferredSize(new Dimension(900, 500));
		frmHaiPerso.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ricki\\Desktop\\Football_icon.png"));
		frmHaiPerso.setTitle("Hai perso");
		frmHaiPerso.setBounds(100, 100, 900, 500);
		frmHaiPerso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		connection=db.get_access();
		JLabel lblNewLabel = new JLabel("Welcom to Java app for fantacalcio", SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 880, 40);
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Armalite Rifle", Font.PLAIN, 20));
		lblNewLabel.setIcon(null);
		lblNewLabel.setBackground(new Color(204, 204, 204));
		lblNewLabel.setPreferredSize(new Dimension(500, 50));
		
		JButton openfile = new JButton("Open file");
		openfile.setBounds(23, 65, 200, 50);
		openfile.setPreferredSize(new Dimension(100, 50));
		openfile.setBackground(new Color(204, 204, 204));
		openfile.setForeground(new Color(153, 0, 51));
		openfile.setFont(new Font("Armalite Rifle", Font.PLAIN, 13));
		
		JButton btnExtractOnePlayer = new JButton("Extract One Player");
		btnExtractOnePlayer.setBounds(213, 243, 200, 50);
		btnExtractOnePlayer.setPreferredSize(new Dimension(100, 50));
		btnExtractOnePlayer.setForeground(new Color(153, 0, 0));
		btnExtractOnePlayer.setBackground(new Color(204, 204, 204));
		btnExtractOnePlayer.setFont(new Font("Armalite Rifle", Font.PLAIN, 13));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new CloseListener());
		btnExit.setBounds(801, 425, 79, 23);
		btnExit.setPreferredSize(new Dimension(100, 50));
		btnExit.setForeground(new Color(153, 0, 0));
		btnExit.setBackground(new Color(204, 204, 204));
		btnExit.setFont(new Font("Armalite Rifle", Font.PLAIN, 13));
		
		JTextArea displayresult = new JTextArea();
		displayresult.setBounds(23, 306, 516, 129);
		displayresult.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 25));
		
		frmHaiPerso.getContentPane().setLayout(null);
		frmHaiPerso.getContentPane().add(lblNewLabel);
		frmHaiPerso.getContentPane().add(openfile);
		frmHaiPerso.getContentPane().add(btnExtractOnePlayer);
		frmHaiPerso.getContentPane().add(btnExit);
		frmHaiPerso.getContentPane().add(displayresult);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(102, 51, 51));
		lblNewLabel_1.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(260, 53, 537, 40);
		frmHaiPerso.getContentPane().add(lblNewLabel_1);
		
		JButton btnAttaccanti = new JButton("Attaccanti");
		
		btnAttaccanti.setForeground(Color.RED);
		btnAttaccanti.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		btnAttaccanti.setBounds(23, 148, 97, 25);
		btnAttaccanti.setVisible(false);
		frmHaiPerso.getContentPane().add(btnAttaccanti);
		
		JButton btnPortieri = new JButton("Portieri");
		
		btnPortieri.setForeground(new Color(255, 204, 0));
		btnPortieri.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		btnPortieri.setBounds(126, 146, 97, 25);
		btnPortieri.setVisible(false);
		frmHaiPerso.getContentPane().add(btnPortieri);
		
		JButton btnCentrocamp = new JButton("Centroca.");
		
		btnCentrocamp.setForeground(new Color(102, 204, 102));
		btnCentrocamp.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		btnCentrocamp.setBounds(23, 186, 97, 25);
		btnCentrocamp.setVisible(false);
		frmHaiPerso.getContentPane().add(btnCentrocamp);
		
		JButton btnDifensori = new JButton("Difensori");
		
		btnDifensori.setForeground(new Color(51, 102, 204));
		btnDifensori.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		btnDifensori.setBounds(130, 184, 97, 25);
		btnDifensori.setVisible(false);
		frmHaiPerso.getContentPane().add(btnDifensori);
		
		JButton btnSvincola = new JButton("Svincola");
		btnSvincola.setForeground(new Color(153, 0, 0));
		btnSvincola.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 18));
		btnSvincola.setVisible(false);
		btnSvincola.setBounds(561, 330, 135, 25);
		frmHaiPerso.getContentPane().add(btnSvincola);
		
		JButton btncompra = new JButton("Compra");
		btncompra.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 18));
		btncompra.setBounds(561, 391, 135, 25);
		btncompra.setForeground(new Color(153, 0, 0));
		btncompra.setVisible(false);
		frmHaiPerso.getContentPane().add(btncompra);
		
		openfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					file.fileread();
				} catch (IOException e) {
					e.printStackTrace();
				}
				lblNewLabel_1.setText("File aperto--->>>>>Quotazioni_Fantacalcio_Ruoli_Fantagazzetta.xlsx");
				btnAttaccanti.setVisible(true);
				btnPortieri.setVisible(true);
				btnDifensori.setVisible(true);
				btnCentrocamp.setVisible(true);
			}
		});
		btnAttaccanti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Current=file.getAttaccanti();
				curr="A";
				btnAttaccanti.setBackground(new Color(0, 0, 0));
				btnPortieri.setBackground(new Color(240, 240, 240));
				btnCentrocamp.setBackground(new Color(240, 240, 240));
				btnDifensori.setBackground(new Color(240, 240, 240));
			}
		});
		btnPortieri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Current=file.getPortieri();
				curr="P";
				btnPortieri.setBackground(new Color(0, 0, 0));
				btnAttaccanti.setBackground(new Color(240, 240, 240));
				btnCentrocamp.setBackground(new Color(240, 240, 240));
				btnDifensori.setBackground(new Color(240, 240, 240));
			}
		});
		btnCentrocamp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Current=file.getCentrocampisti();
				curr="C";
				btnCentrocamp.setBackground(new Color(0, 0, 0));
				btnAttaccanti.setBackground(new Color(240, 240, 240));
				btnPortieri.setBackground(new Color(240, 240, 240));
				btnDifensori.setBackground(new Color(240, 240, 240));
			}
		});
		btnDifensori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Current=file.getDifensori();
				curr="D";
				btnDifensori.setBackground(new Color(0, 0, 0));
				btnAttaccanti.setBackground(new Color(240, 240, 240));
				btnCentrocamp.setBackground(new Color(240, 240, 240));
				btnPortieri.setBackground(new Color(240, 240, 240));
			}
		});
		
		btnExtractOnePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Current.size()==0){
					displayresult.setText("Seleziona un Array");
					btnSvincola.setVisible(false);
					btncompra.setVisible(false);
				}
				else{
					Lottery=random.getNum(Current.size());
					p=Current.get(Lottery);
					Current.remove(Lottery);
					displayresult.setText("Giocatore:  "+p.getNome()+"\nSquadra:  "+p.getTeam()+"\nGiocatori rimanenti nell'array: "+Current.size());
					btnSvincola.setVisible(true);
					btncompra.setVisible(true);
				}
			}
		});
		
		btnSvincola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("INSERT INTO scartati (`nome`, `squadra`, `ruolo`) VALUES ('"+p.getNome()+"', '"+p.getTeam()+"', '"+curr+"')");
					results =connection.prepareStatement("INSERT INTO scartati (`nome`, `squadra`, `ruolo`) VALUES ('"+p.getNome()+"', '"+p.getTeam()+"', '"+curr+"')");
		        	results.executeUpdate();
				} catch (SQLException p) {
					p.printStackTrace();
				}
			}
		});
		
		btncompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Vendor(p, curr);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
