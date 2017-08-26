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
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JTextArea;

public class Home {

	private JFrame frmHaiPerso;

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
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmHaiPerso = new JFrame();
		frmHaiPerso.setPreferredSize(new Dimension(900, 500));
		frmHaiPerso.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ricki\\Desktop\\Football_icon.png"));
		frmHaiPerso.setTitle("Hai perso");
		frmHaiPerso.setBounds(100, 100, 900, 495);
		frmHaiPerso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Welcom to Java app for fantacalcio", SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 880, 40);
		lblNewLabel.setForeground(new Color(102, 0, 0));
		lblNewLabel.setFont(new Font("Armalite Rifle", Font.PLAIN, 20));
		lblNewLabel.setIcon(null);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setPreferredSize(new Dimension(500, 50));
		
		JButton btnNewButton = new JButton("Open file");
		btnNewButton.setBounds(142, 103, 200, 50);
		btnNewButton.setPreferredSize(new Dimension(100, 50));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(153, 0, 51));
		btnNewButton.setFont(new Font("Armalite Rifle", Font.PLAIN, 13));
		
		JButton btnExtractOnePlayer = new JButton("Extract One Player");
		btnExtractOnePlayer.setBounds(523, 103, 200, 50);
		btnExtractOnePlayer.setPreferredSize(new Dimension(100, 50));
		btnExtractOnePlayer.setForeground(new Color(153, 0, 0));
		btnExtractOnePlayer.setBackground(new Color(0, 0, 0));
		btnExtractOnePlayer.setFont(new Font("Armalite Rifle", Font.PLAIN, 13));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new CloseListener());
		btnExit.setBounds(791, 425, 79, 23);
		btnExit.setPreferredSize(new Dimension(100, 50));
		btnExit.setForeground(new Color(153, 0, 0));
		btnExit.setBackground(new Color(0, 0, 0));
		btnExit.setFont(new Font("Armalite Rifle", Font.PLAIN, 13));
		
		JTextArea txtrProva = new JTextArea();
		txtrProva.setToolTipText("prova");
		txtrProva.setBounds(139, 228, 608, 129);
		
		frmHaiPerso.getContentPane().setLayout(null);
		frmHaiPerso.getContentPane().add(lblNewLabel);
		frmHaiPerso.getContentPane().add(btnNewButton);
		frmHaiPerso.getContentPane().add(btnExtractOnePlayer);
		frmHaiPerso.getContentPane().add(btnExit);
		frmHaiPerso.getContentPane().add(txtrProva);
		
		btnExtractOnePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrProva.setText("prova");
			}
		});
	}
}
