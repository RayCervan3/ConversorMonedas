package mx.edu.igu;


import javax.swing.JFrame;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import mx.edu.logica.ConversorAPI;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;


public class Display extends JFrame {
	
	JLabel titulo;
	JButton btnClose;
	JComboBox comboBox;
	ConversorAPI cAPI;
	private JTextField cantidad;
	private JLabel lblAdvertencia;
	private JLabel lblResultado;
	private JLabel lblConvertirA;
	private JButton btnMXN;
	private JButton btnEUR;
	private JButton btnUsd;
	private JButton btnJPY;
	private JButton btnKRW;
	private JButton btnRUB;
	private JButton btnGBP;
	private JLabel lblMoneda;
	private JLabel lblPeso;
	private JLabel lblDolar;
	private JLabel lblEuro;
	private JLabel lblYen;
	private JLabel lblWon;
	private JLabel lblRublo;
	private JLabel lblLibra;
	private JLabel lblPrecio;
	private JLabel lblPreciomex;
	private JLabel lblPreciousd;
	private JLabel lblPrecioeur;
	private JLabel lblPreciojpy;
	private JLabel lblPreciokrw;
	private JLabel lblPreciorub;
	private JLabel lblPreciogbp;
	
	public Display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		titulo = new JLabel("Conversor de Monedas");
		getContentPane().add(titulo);
		titulo.setVerticalAlignment(SwingConstants.TOP);
		titulo.setFont(new Font("Roboto", Font.PLAIN, 16));
		titulo.setBounds(40, 11, 169, 19);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setForeground(new Color(0, 0, 0));
		btnClose.setBackground(new Color(204, 153, 204));
		btnClose.setFont(new Font("Roboto", Font.PLAIN, 10));
		btnClose.setBounds(187, 375, 63, 25);
		getContentPane().add(btnClose);
		
		String[] currencies = {"","MXN","EUR","USD","JPY","KRW","RUB","GBP"};
		comboBox = new JComboBox(currencies);
		comboBox.setFont(new Font("Roboto", Font.PLAIN, 12));
		comboBox.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox.setBounds(156, 55, 74, 22);
		getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==comboBox) {
					System.out.println(comboBox.getSelectedItem().toString());
					cAPI = new ConversorAPI(comboBox.getSelectedItem().toString());
					lblPreciomex.setText(cAPI.getMexPeso().toString());
					lblPreciousd.setText(cAPI.getUsDolar().toString());
					lblPrecioeur.setText(cAPI.getEuro().toString());
					lblPreciojpy.setText(cAPI.getYenJapan().toString());
					lblPreciokrw.setText(cAPI.getWonKorea().toString());
					lblPreciorub.setText(cAPI.getRussianRuble().toPlainString());
					lblPreciogbp.setText(cAPI.getPoundBritish().toString());
				}
			}
		});
		
		getContentPane().setBackground(SystemColor.inactiveCaption);
		getContentPane().setLayout(null);
		
		lblAdvertencia = new JLabel("");
		lblAdvertencia.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblAdvertencia.setForeground(new Color(255, 51, 51));
		lblAdvertencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdvertencia.setBounds(40, 78, 156, 14);
		getContentPane().add(lblAdvertencia);
		cantidad = new JTextField();
		cantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
								
				if(Character.isLetter(c)) {
					cantidad.setEditable(false);
					
					lblAdvertencia.setText("Sólo se permiten numeros");
				} else {
					cantidad.setEditable(true);
					lblAdvertencia.setText("");
				}
			}
		});
		cantidad.setFont(new Font("Roboto", Font.PLAIN, 12));
		cantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		cantidad.setBackground(SystemColor.inactiveCaptionBorder);
		cantidad.setBounds(20, 56, 126, 20);
		getContentPane().add(cantidad);
		cantidad.setColumns(10);
		
		JLabel lblElegirCantidad = new JLabel("Escribe cantidad a convertir");
		lblElegirCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblElegirCantidad.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblElegirCantidad.setBounds(20, 41, 119, 14);
		getContentPane().add(lblElegirCantidad);
		
		JLabel lblElegirMoneda = new JLabel("Moneda Base:");
		lblElegirMoneda.setHorizontalAlignment(SwingConstants.LEFT);
		lblElegirMoneda.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblElegirMoneda.setBounds(156, 41, 73, 14);
		
		getContentPane().add(lblElegirMoneda);
		
		lblResultado = new JLabel("----------TOTAL----------");
		lblResultado.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(20, 319, 207, 55);
		getContentPane().add(lblResultado);
		
		lblConvertirA = new JLabel("Convertir A:");
		lblConvertirA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConvertirA.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblConvertirA.setBounds(156, 98, 74, 14);
		getContentPane().add(lblConvertirA);
		
		btnMXN = new JButton("MXN");
		btnMXN.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnMXN.setBackground(new Color(240, 255, 240));
		btnMXN.setBounds(156, 115, 74, 23);
		getContentPane().add(btnMXN);
		btnMXN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText(cAPI.multiplication(new BigDecimal(cantidad.getText().toString()),"MXN").toString());
			}
		});
		
		btnEUR = new JButton("EUR");
		btnEUR.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnEUR.setBackground(new Color(240, 255, 240));
		btnEUR.setBounds(156, 172, 74, 23);
		getContentPane().add(btnEUR);
		btnEUR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText(cAPI.multiplication(new BigDecimal(cantidad.getText().toString()),"EUR").toString());
			}
		});
		
		btnUsd = new JButton("USD");
		btnUsd.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnUsd.setBackground(new Color(240, 255, 240));
		btnUsd.setBounds(156, 143, 74, 23);
		getContentPane().add(btnUsd);
		btnUsd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText(cAPI.multiplication(new BigDecimal(cantidad.getText().toString()),"USD").toString());
			}
		});
		
		btnJPY = new JButton("JPY");
		btnJPY.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnJPY.setBackground(new Color(240, 255, 240));
		btnJPY.setBounds(156, 201, 74, 23);
		getContentPane().add(btnJPY);
		btnJPY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText(cAPI.multiplication(new BigDecimal(cantidad.getText().toString()),"JPY").toString());
			}
		});
		
		btnKRW = new JButton("KRW");
		btnKRW.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnKRW.setBackground(new Color(240, 255, 240));
		btnKRW.setBounds(156, 229, 74, 23);
		getContentPane().add(btnKRW);
		btnKRW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText(cAPI.multiplication(new BigDecimal(cantidad.getText().toString()),"KRW").toString());
			}
		});
		
		btnRUB = new JButton("RUB");
		btnRUB.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnRUB.setBackground(new Color(240, 255, 240));
		btnRUB.setBounds(156, 257, 74, 23);
		getContentPane().add(btnRUB);
		btnRUB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText(cAPI.multiplication(new BigDecimal(cantidad.getText().toString()),"RUB").toString());
			}
		});
		
		btnGBP = new JButton("GBP");
		btnGBP.setFont(new Font("Roboto", Font.PLAIN, 12));
		btnGBP.setBackground(new Color(240, 255, 240));
		btnGBP.setBounds(156, 285, 74, 23);
		getContentPane().add(btnGBP);
		btnGBP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResultado.setText(cAPI.multiplication(new BigDecimal(cantidad.getText().toString()),"GBP").toString());
			}
		});
		
		lblMoneda = new JLabel("Moneda:");
		lblMoneda.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblMoneda.setBounds(20, 98, 51, 14);
		getContentPane().add(lblMoneda);
		
		lblPeso = new JLabel("PESOmx");
		lblPeso.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblPeso.setBounds(20, 119, 51, 14);
		getContentPane().add(lblPeso);
		
		lblDolar = new JLabel("DOLAR");
		lblDolar.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblDolar.setBounds(20, 148, 51, 14);
		getContentPane().add(lblDolar);
		
		lblEuro = new JLabel("EURO");
		lblEuro.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblEuro.setBounds(20, 177, 51, 14);
		getContentPane().add(lblEuro);
		
		lblYen = new JLabel("YEN");
		lblYen.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblYen.setBounds(20, 206, 51, 14);
		getContentPane().add(lblYen);
		
		lblWon = new JLabel("WON");
		lblWon.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblWon.setBounds(20, 234, 51, 14);
		getContentPane().add(lblWon);
		
		lblRublo = new JLabel("RUBLO");
		lblRublo.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblRublo.setBounds(20, 262, 51, 14);
		getContentPane().add(lblRublo);
		
		lblLibra = new JLabel("LIBRA");
		lblLibra.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblLibra.setBounds(20, 290, 51, 14);
		getContentPane().add(lblLibra);
		
		lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblPrecio.setBounds(95, 98, 51, 14);
		getContentPane().add(lblPrecio);
		
		lblPreciomex = new JLabel("PrecioMEX");
		lblPreciomex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreciomex.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblPreciomex.setBounds(81, 120, 65, 14);
		getContentPane().add(lblPreciomex);
		
		lblPreciousd = new JLabel("PrecioUSD");
		lblPreciousd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreciousd.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblPreciousd.setBounds(81, 148, 65, 14);
		getContentPane().add(lblPreciousd);
		
		lblPrecioeur = new JLabel("PrecioEUR");
		lblPrecioeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioeur.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblPrecioeur.setBounds(81, 177, 65, 14);
		getContentPane().add(lblPrecioeur);
		
		lblPreciojpy = new JLabel("PrecioJPY");
		lblPreciojpy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreciojpy.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblPreciojpy.setBounds(81, 206, 65, 14);
		getContentPane().add(lblPreciojpy);
		
		lblPreciokrw = new JLabel("PrecioKRW");
		lblPreciokrw.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreciokrw.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblPreciokrw.setBounds(81, 234, 65, 14);
		getContentPane().add(lblPreciokrw);
		
		lblPreciorub = new JLabel("PrecioRUB");
		lblPreciorub.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreciorub.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblPreciorub.setBounds(81, 262, 65, 14);
		getContentPane().add(lblPreciorub);
		
		lblPreciogbp = new JLabel("PrecioGBP");
		lblPreciogbp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreciogbp.setFont(new Font("Roboto", Font.PLAIN, 10));
		lblPreciogbp.setBounds(81, 290, 65, 14);
		getContentPane().add(lblPreciogbp);
		
		
		setUndecorated(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setResizable(false);
		setSize(250,400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
