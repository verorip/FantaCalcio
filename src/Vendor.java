import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vendor {

	private JFrame frame;
	public Sqlaccess db;
	private PreparedStatement results;
	private Connection connection;
	private JTextField txtInserirePrezzo;
	
	public Vendor(Player p, String curr) throws SQLException {
		db= new Sqlaccess();
		connection=db.get_access();
		initialize(p, curr);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize(Player p, String curr) throws SQLException {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 703, 352);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setForeground(new Color(255, 204, 0));
		frame.getContentPane().setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(10);
		comboBox.setForeground(new Color(153, 0, 0));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 13));
		comboBox.setBounds(435, 45, 224, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblCurrentply = new JLabel("");
		lblCurrentply.setForeground(new Color(153, 0, 0));
		lblCurrentply.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 18));
		lblCurrentply.setBounds(25, 0, 398, 63);
		lblCurrentply.setText("Giocatore in vendita: "+p.getNome());
		frame.getContentPane().add(lblCurrentply);
		
		JLabel label = new JLabel("");
		label.setForeground(new Color(153, 0, 0));
		label.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 18));
		label.setBounds(25, 45, 398, 63);
		frame.getContentPane().add(label);
		label.setText("Della squadra: "+p.getTeam());
		
		txtInserirePrezzo = new JTextField();
		txtInserirePrezzo.setForeground(new Color(153, 0, 0));
		txtInserirePrezzo.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 18));
		txtInserirePrezzo.setText("Inserire prezzo");
		txtInserirePrezzo.setBounds(430, 127, 229, 44);
		frame.getContentPane().add(txtInserirePrezzo);
		txtInserirePrezzo.setColumns(10);
		
		JLabel lblErrore = new JLabel("");
		lblErrore.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		lblErrore.setForeground(new Color(153, 0, 0));
		lblErrore.setBounds(35, 127, 304, 43);
		frame.getContentPane().add(lblErrore);
		
		JButton btnNewButton = new JButton("Conferma");
		btnNewButton.setForeground(new Color(153, 0, 0));
		btnNewButton.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));
		btnNewButton.setBounds(273, 222, 150, 57);
		frame.getContentPane().add(btnNewButton);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ricki\\Desktop\\Football_icon.png"));
		frame.setTitle("Vendita");
		results =connection.prepareStatement("SELECT * FROM giocatori_fanta");
    	ResultSet gioc=results.executeQuery();
    	while(gioc.next()){
    		comboBox.addItem(gioc.getString(2));
    	}
    	btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int prezzo=-1, i=0;
				try{
					prezzo=Integer.parseInt(txtInserirePrezzo.getText());
				}catch(Exception t){
					lblErrore.setText("Il prezzo non è un intero!");
				}
				if (prezzo==-1) System.out.println("prezzo non intero");
				else if(prezzo>300){
					lblErrore.setText("il prezzo non può essere così grande");
				}
				else{
					try {
						results =connection.prepareStatement("SELECT soldi FROM giocatori_fanta WHERE nome='"+comboBox.getSelectedItem().toString()+"'");
						ResultSet gioc=results.executeQuery();
				    	if(gioc.next()){
				    		i=gioc.getInt(1);
				    	}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					System.out.println("Prezzo: "+txtInserirePrezzo.getText()+" di: " +comboBox.getSelectedItem());
					if(i-prezzo<0)
						lblErrore.setText("Non ha abbastanza soldi, credito residuo: "+i);
					else
					{
						i=i-prezzo;
						try {
							results =connection.prepareStatement("UPDATE giocatori_fanta SET `soldi`="+i+" WHERE `nome`='"+comboBox.getSelectedItem().toString()+"'");
							results.executeUpdate();
							results =connection.prepareStatement("INSERT INTO compravendita (`nome_gioc`, `nome_comp`, `team`, `ruolo`, `prezzo`) VALUES ('"+p.getNome()+"', '"+comboBox.getSelectedItem().toString()+"', '"+p.getTeam()+"', '"+curr+"', "+prezzo+")");
							results.executeUpdate();
							frame.dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
			        	
					}
			    	
				}
				
				
			}
		});
		
		
	}
}
