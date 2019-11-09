package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import comunicaciones.AltaJugador;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ClienteReg extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNick;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	JComboBox comboBox;
	protected Socket miSocket;

	/**
	 * Create the frame.
	 */
	public ClienteReg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNick = new JTextField();
		textFieldNick.setBounds(114, 86, 160, 20);
		contentPane.add(textFieldNick);
		textFieldNick.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar al server");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 miSocket=new Socket("127.0.0.1",5000);

					out = new ObjectOutputStream(miSocket.getOutputStream());
					in = new ObjectInputStream( miSocket.getInputStream() );
					
					darAltaJugador();
					
					btnEntrar.setEnabled(false);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEntrar.setBounds(144, 166, 130, 23);
		contentPane.add(btnEntrar);
		
		comboBox = new JComboBox();
		comboBox.setBounds(293, 86, 103, 20);
		comboBox.addItem("Rojo");
		comboBox.addItem("Negro");
		comboBox.addItem("Azul");
		
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Ingrese nick");
		lblNewLabel.setBounds(10, 89, 94, 14);
		contentPane.add(lblNewLabel);
		setVisible(true);
	}

	protected void darAltaJugador() throws IOException {
		Color color=null;
		
		switch(comboBox.getSelectedItem().toString()){
			case "Rojo":
				color=Color.RED;
				break;
			case "Negro":
				color=Color.BLACK;
				break;	
			case "Azul":
				color=Color.BLUE;
				break;
		}
		
		out.writeObject(new AltaJugador(textFieldNick.getText(), color));
		
	}
}
