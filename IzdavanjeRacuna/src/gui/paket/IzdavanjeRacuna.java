package gui.paket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import javax.swing.JRadioButton;

public class IzdavanjeRacuna {
	
	private static String strTrenutno;
	private static boolean mozeteIspisati = true;
	private static boolean mozeteDodati = true;
	private static double ukupno = 0;
	private static double ukupnoTrenutno = 0;
	private static int brLinija = 0;
	private static boolean vecSteNarucili = false;
	private static DecimalFormat df = new DecimalFormat("#.##");
	private String nacinPlacanja = "";
	
	private JFrame izdavanjeRacuna;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzdavanjeRacuna window = new IzdavanjeRacuna();
					window.izdavanjeRacuna.setVisible(true);
					window.izdavanjeRacuna.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzdavanjeRacuna() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		izdavanjeRacuna = new JFrame();
		izdavanjeRacuna.setAlwaysOnTop(true);
		izdavanjeRacuna.setTitle("Izdavanje Racuna");
		izdavanjeRacuna.setBounds(0, -25, 399, 722);
		izdavanjeRacuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		izdavanjeRacuna.getContentPane().setLayout(null);
		
		JLabel lblVoce = new JLabel("Voce");
		lblVoce.setBounds(35, 31, 46, 14);
		izdavanjeRacuna.getContentPane().add(lblVoce);
		
		JLabel lblPovrce = new JLabel("Povrce");
		lblPovrce.setBounds(199, 31, 46, 14);
		izdavanjeRacuna.getContentPane().add(lblPovrce);
		
		JLabel lblHemija = new JLabel("Hemija");
		lblHemija.setBounds(310, 31, 46, 14);
		izdavanjeRacuna.getContentPane().add(lblHemija);
		
		JComboBox comboBoxVoce = new JComboBox();
		comboBoxVoce.setBounds(35, 56, 96, 22);
		double[] nizCenaVoce = new double[] {3.5,1.5,2.0,4.5,3.5};
		String[] nizVoce = new String[] {"Malina", "Kruska", "Sljiva", "Kajsija", "Ribizla"};
		comboBoxVoce.setModel(new DefaultComboBoxModel(nizVoce));
		izdavanjeRacuna.getContentPane().add(comboBoxVoce);
		
		JComboBox comboBoxPovrce = new JComboBox();
		comboBoxPovrce.setBounds(154, 56, 96, 22);
		double[] nizCenaPovrce = new double[] {4.5,2.5,3.0,3.5,1.5};
		String[] nizPovrce = new String[] {"Paradajz", "Krastavac", "Krompir", "Brokoli", "Celer"};
		comboBoxPovrce.setModel(new DefaultComboBoxModel(nizPovrce));
		izdavanjeRacuna.getContentPane().add(comboBoxPovrce);
		
		
		JComboBox comboBoxHemija = new JComboBox();
		comboBoxHemija.setBounds(277, 56, 96, 22);
		double[] nizCenaHemija = new double[] {2.5,3.5,4.0,2.5,2.5};
		String[] nizHemija = new String[] {"Sapun", "Kaladont", "Deterdzent", "Sampon", "Kupka"};
		comboBoxHemija.setModel(new DefaultComboBoxModel(nizHemija));
		izdavanjeRacuna.getContentPane().add(comboBoxHemija);
		
		JSpinner voceSpinner = new JSpinner();
		voceSpinner.setBounds(84, 106, 47, 20);
		izdavanjeRacuna.getContentPane().add(voceSpinner);
		voceSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		ArrayList<Double> nizUkupnoTrenutno = new ArrayList<Double>();
		ArrayList<String> nizStrTrenutno = new ArrayList<String>();
		
		JTextArea textAreaRacun = new JTextArea();
		textAreaRacun.setBounds(35, 203, 338, 330);
		textAreaRacun.setEditable(false);
		textAreaRacun.setText(pocetnoStanje());
		izdavanjeRacuna.getContentPane().add(textAreaRacun);
		
		JButton btnVoce = new JButton("Dodaj");
		btnVoce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mozeteDodati) {
					if ((int) voceSpinner.getValue() > 0) {
						strTrenutno = textAreaRacun.getText();
						ukupnoTrenutno=ukupno;
						textAreaRacun.append(comboBoxVoce.getSelectedItem().toString()+"  "+nizCenaVoce[comboBoxVoce.getSelectedIndex()]+ "\t        " + voceSpinner.getValue()+ "\t    " + df.format((int) voceSpinner.getValue()*nizCenaVoce[comboBoxVoce.getSelectedIndex()]) +"\n");
						ukupno+=(int) voceSpinner.getValue()*nizCenaVoce[comboBoxVoce.getSelectedIndex()];
						nizUkupnoTrenutno.add(brLinija, ukupnoTrenutno);
						nizStrTrenutno.add(brLinija, strTrenutno);
						brLinija++;
					}
					else {
						JOptionPane.showMessageDialog(izdavanjeRacuna, "Podesite kolicinu da biste dodali! Kolicina ne moze biti negativna ni 0.");
					}
				}
				else {
					JOptionPane.showMessageDialog(izdavanjeRacuna, "Ako zelite dodati stavku, morate obrisati postojece stanje racuna!");
				}
			}
		});
		btnVoce.setBounds(35, 153, 96, 23);
		izdavanjeRacuna.getContentPane().add(btnVoce);
		
		
		
		JSpinner povrceSpinner = new JSpinner();
		povrceSpinner.setBounds(204, 106, 46, 20);
		izdavanjeRacuna.getContentPane().add(povrceSpinner);
		povrceSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		JButton btnPovrce = new JButton("Dodaj");
		btnPovrce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mozeteDodati) {
					if ((int) povrceSpinner.getValue() > 0) {
						strTrenutno = textAreaRacun.getText();
						ukupnoTrenutno=ukupno; 
						textAreaRacun.append(comboBoxPovrce.getSelectedItem().toString()+ "  " +nizCenaPovrce[comboBoxPovrce.getSelectedIndex()]+ "\t        " + povrceSpinner.getValue()+ "\t    " + df.format((int) povrceSpinner.getValue()*nizCenaPovrce[comboBoxPovrce.getSelectedIndex()]) +"\n");
						ukupno+=(int) povrceSpinner.getValue()*nizCenaPovrce[comboBoxPovrce.getSelectedIndex()];
						nizUkupnoTrenutno.add(brLinija, ukupnoTrenutno);
						nizStrTrenutno.add(brLinija, strTrenutno);
						brLinija++;
					}
					else {
						JOptionPane.showMessageDialog(izdavanjeRacuna, "Podesite kolicinu da biste dodali! Kolicina ne moze biti negativna ni 0.");
					}
				}
				else {
					JOptionPane.showMessageDialog(izdavanjeRacuna, "Ako zelite dodati stavku, morate obrisati postojece stanje racuna!");
				}
			}
		});
		btnPovrce.setBounds(154, 153, 96, 23);
		izdavanjeRacuna.getContentPane().add(btnPovrce);
		
		JSpinner hemijaSpinner = new JSpinner();
		hemijaSpinner.setBounds(319, 106, 54, 20);
		izdavanjeRacuna.getContentPane().add(hemijaSpinner);
		
		JButton btnHemija = new JButton("Dodaj");
		btnHemija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mozeteDodati) {
					if ((int) hemijaSpinner.getValue() > 0) {
						strTrenutno = textAreaRacun.getText();
						ukupnoTrenutno=ukupno;
						textAreaRacun.append(comboBoxHemija.getSelectedItem().toString()+"  "+nizCenaHemija[comboBoxVoce.getSelectedIndex()] + "\t        " + hemijaSpinner.getValue()+ "\t    " + df.format((int) hemijaSpinner.getValue()*nizCenaHemija[comboBoxHemija.getSelectedIndex()]) +"\n");
						ukupno+=(int) hemijaSpinner.getValue()*nizCenaHemija[comboBoxHemija.getSelectedIndex()];
						nizUkupnoTrenutno.add(brLinija, ukupnoTrenutno);
						nizStrTrenutno.add(brLinija, strTrenutno);
						brLinija++;
					}
					else {
						JOptionPane.showMessageDialog(izdavanjeRacuna, "Podesite kolicinu da biste dodali! Kolicina ne moze biti negativna ni 0.");
					}
				}
				else {
					JOptionPane.showMessageDialog(izdavanjeRacuna, "Ako zelite dodati stavku, morate obrisati postojece stanje racuna!");
				}
			}
		});
		btnHemija.setBounds(277, 153, 96, 23);
		izdavanjeRacuna.getContentPane().add(btnHemija);
		
		JLabel lblKolicina = new JLabel("Kolicina");
		lblKolicina.setBounds(35, 109, 53, 14);
		izdavanjeRacuna.getContentPane().add(lblKolicina);
		
		JLabel lblKolicina_1 = new JLabel("Kolicina");
		lblKolicina_1.setBounds(154, 109, 40, 14);
		izdavanjeRacuna.getContentPane().add(lblKolicina_1);
		
		JLabel lblKolicina_2 = new JLabel("Kolicina");
		lblKolicina_2.setBounds(277, 109, 40, 14);
		izdavanjeRacuna.getContentPane().add(lblKolicina_2);
		
		JRadioButton rdbtnGotovina = new JRadioButton("Gotovina");
		rdbtnGotovina.setBounds(154, 540, 109, 23);
		izdavanjeRacuna.getContentPane().add(rdbtnGotovina);
		
		JRadioButton rdbtnKartica = new JRadioButton("Kartica");
		rdbtnKartica.setBounds(265, 540, 109, 23);
		izdavanjeRacuna.getContentPane().add(rdbtnKartica);	
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnKartica);
		radioGroup.add(rdbtnGotovina);
		
		
		
		JButton btnIspisi = new JButton("Ispisi stanje");
		btnIspisi.setBounds(35, 572, 152, 33);
		izdavanjeRacuna.getContentPane().add(btnIspisi);
		btnIspisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (brLinija==0) {
					JOptionPane.showMessageDialog(izdavanjeRacuna, "Niste nista dodali na racun, molimo unesite odredjenu kolicinu!");
				}
				
				else if (brLinija !=0 && !rdbtnGotovina.isSelected() && !rdbtnKartica.isSelected()) {
					JOptionPane.showMessageDialog(izdavanjeRacuna, "Niste odabrali nacin placanja!");
				}
				else {
					if (mozeteIspisati) {
						textAreaRacun.append("-------------------------------------------------------------------------------------------\n" + 
											"UKUPAN IZNOS RACUNA: "+df.format(ukupno)+"\n");
						
						if(rdbtnGotovina.isSelected()) {
							textAreaRacun.append("-------------------------------------------------------------------------------------------\n" + 
									"NACIN PLACANJA: "+ rdbtnGotovina.getText());
						}
						if(rdbtnKartica.isSelected()) {
							textAreaRacun.append("-------------------------------------------------------------------------------------------\n" +
									"NACIN PLACANJA: "+ rdbtnKartica.getText());
						}
						
						
						mozeteIspisati=false;
						mozeteDodati=false;
					}
					else {
						JOptionPane.showMessageDialog(izdavanjeRacuna, "Ako zelite ponovo ispisati konacan iznos,\n morate obrisati postojece stanje racuna!");
					}
				}				
			}	
		});
		
		JButton btnObrisi = new JButton("Obrisi stanje");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaRacun.setText(pocetnoStanje());
				mozeteIspisati=true;
				mozeteDodati=true;
				nizStrTrenutno.clear();
				nizUkupnoTrenutno.clear();
				ukupno = 0;
				brLinija=0;
				vecSteNarucili=false;
			}
		});
		btnObrisi.setBounds(221, 572, 152, 33);
		izdavanjeRacuna.getContentPane().add(btnObrisi);
		
		JButton btnNaruci = new JButton("Naruci");
		btnNaruci.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNaruci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!vecSteNarucili) {
					if (!mozeteIspisati) {
						JOptionPane.showMessageDialog(izdavanjeRacuna, "USPESNA KUPOVINA! UKUPAN IZNOS: " +  df.format(ukupno));
						mozeteDodati=false;
						vecSteNarucili=true;
					}
					else {
						JOptionPane.showMessageDialog(izdavanjeRacuna, "ISPISITE KONACAN IZNOS, PA ONDA NARUCITE!");
					}
				}
				else {
					JOptionPane.showMessageDialog(izdavanjeRacuna, "VEC STE NARUCILI STAVKE SA RACUNA,\nAKO ZELITE PONOVO NESTO NARUCITI OBRISITE STANJE I ISPISITE NOVI RACUN!");
				}
			}
		});
		btnNaruci.setBounds(35, 616, 338, 32);
		izdavanjeRacuna.getContentPane().add(btnNaruci);
		
		JLabel lblNacinPlacanja = new JLabel("Nacin placanja");
		lblNacinPlacanja.setBounds(51, 544, 113, 14);
		izdavanjeRacuna.getContentPane().add(lblNacinPlacanja);
		
	}
	
	public static String pocetnoStanje() { 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String str = ("~~~~~~~~~~~~~~Dobro dosli!~~~~~~~~~~~~~~~~~~~~~~~~\n"+
					"Firma: Moja Prodavnica\n"+
					"Datum: "+dateFormat.format(cal.getTime())+"\n"+
					"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+"Format:\n"+
					"PROIZVOD:\t"+"KOLICINA:\t"+"IZNOS:\n"+
					"----------------------------------------------------------------------------------\n");
		return str;
	}
}
