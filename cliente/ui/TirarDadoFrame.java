package ui;

import javax.swing.JButton;
import javax.swing.JFrame;

import entities.Dado;
import entities.Personaje;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class TirarDadoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private int valor = -1;

	public TirarDadoFrame(Personaje pj, Dado dado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(Textos.TITULO_DADO);
		setSize(250, 250);
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().setLayout(null);
		JButton botonDado = new JButton();
		botonDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valor = dado.tirarDado();
				removeAll();
			}
		});
		botonDado.setBounds(49, 66, 131, 81);
		botonDado.setText("Tirar Dado");
		getContentPane().add(botonDado);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(pj.getNombre() + " tira el dado");
		lblNewLabel.setBounds(38, 26, 143, 14);
		getContentPane().add(lblNewLabel);
	}

	public int getValor() {
		return valor;
	}
}
