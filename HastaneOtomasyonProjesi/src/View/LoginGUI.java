package View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;

import java.sql.*;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fldHastaTc;
	private JTextField fldHastaSifre;
	private JTextField fldDoktorTc;
	private JPasswordField fldDoktorSifre;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("hLogo.png")));
		lblLogo.setBounds(211, 20, 50, 50);
		w_pane.add(lblLogo);
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("hLogo.png"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(scaledImage);
		lblLogo.setIcon(resizedIcon);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz.");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel.setBounds(30, 80, 412, 30);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbedPane.setBounds(10, 120, 466, 233);
		w_pane.add(w_tabbedPane);
		
		JPanel hastaLogin = new JPanel();
		hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabbedPane.addTab("Hasta Girişi", null, hastaLogin, null);
		hastaLogin.setLayout(null);
		
		JLabel lblTcNumaras = new JLabel("T.C. Numaranız:");
		lblTcNumaras.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblTcNumaras.setBounds(10, 25, 160, 30);
		hastaLogin.add(lblTcNumaras);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblifre.setBounds(10, 65, 160, 30);
		hastaLogin.add(lblifre);
		
		fldHastaTc = new JTextField();
		fldHastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		fldHastaTc.setBounds(180, 25, 270, 30);
		hastaLogin.add(fldHastaTc);
		fldHastaTc.setColumns(10);
		
		fldHastaSifre = new JTextField();
		fldHastaSifre.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		fldHastaSifre.setColumns(10);
		fldHastaSifre.setBounds(180, 65, 270, 30);
		hastaLogin.add(fldHastaSifre);
		
		JButton btnHastaKayit = new JButton("Kayıt Ol");
		btnHastaKayit.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		btnHastaKayit.setBounds(25, 135, 190, 40);
		hastaLogin.add(btnHastaKayit);
		
		JButton btnHastaGiris = new JButton("Giriş Yap");
		btnHastaGiris.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		btnHastaGiris.setBounds(245, 135, 190, 40);
		hastaLogin.add(btnHastaGiris);
		
		JPanel doctorLogin = new JPanel();
		doctorLogin.setBackground(new Color(255, 255, 255));
		w_tabbedPane.addTab("Doktor Girişi", null, doctorLogin, null);
		doctorLogin.setLayout(null);
		
		JLabel lblTcNumaras_1 = new JLabel("T.C. Numaranız:");
		lblTcNumaras_1.setBounds(10, 25, 160, 30);
		lblTcNumaras_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		doctorLogin.add(lblTcNumaras_1);
		
		fldDoktorTc = new JTextField();
		fldDoktorTc.setBounds(180, 25, 270, 30);
		fldDoktorTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		fldDoktorTc.setColumns(10);
		doctorLogin.add(fldDoktorTc);
		
		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setBounds(10, 65, 54, 30);
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		doctorLogin.add(lblifre_1);
		
		JButton btnDoktorGiris = new JButton("Giriş Yap");
		btnDoktorGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fldDoktorTc.getText().length() ==0 || fldDoktorSifre.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						Connection con = conn.connDB();
						Statement st= con.createStatement();
						ResultSet rs= st.executeQuery("SELECT * FROM user");
					while(rs.next()) {
						if(fldDoktorTc.getText().equals(rs.getString("tcno")) && fldDoktorSifre.getText().equals(rs.getString("password"))) {
							Bashekim bashekim = new Bashekim();
							bashekim.setId(rs.getInt("id"));
							bashekim.setName(rs.getString("name"));
							bashekim.setPassword(rs.getString("password"));
							bashekim.setType(rs.getString("type"));
							bashekim.setTcno(rs.getString("tcno"));
							BashekimGUI bGUI = new BashekimGUI(bashekim);
							bGUI.setVisible(true);
							dispose();
						}
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				}
				}
		});
		btnDoktorGiris.setBounds(20, 135, 415, 40);
		btnDoktorGiris.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		doctorLogin.add(btnDoktorGiris);
		
		fldDoktorSifre = new JPasswordField();
		fldDoktorSifre.setBounds(180, 65, 270, 30);
		doctorLogin.add(fldDoktorSifre);
	}
}
