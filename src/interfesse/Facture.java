package interfesse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controleur.CFacture;
import controleur.CInsideProject;
import engine.Compte;
import engine.Paiement;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;

public class Facture extends JFrame {

	private JPanel contentPane;
	private JTextField entreprise;
	private CFacture ctrl;
	private JFormattedTextField date;
	private JSpinner qte;
	private JSpinner prix;
	public JSpinner getQte() {
		return qte;
	}

	private JSpinner remise;
	private JSpinner rabais;
	private JSpinner ristourne;
	
	public CFacture getCtrl() {
		return ctrl;
	}

	public JSpinner getPrix() {
		return prix;
	}

	public JSpinner getRemise() {
		return remise;
	}

	public JSpinner getRabais() {
		return rabais;
	}

	public JSpinner getRistourne() {
		return ristourne;
	}

	public JComboBox<String> getComptes() {
		return comptes;
	}

	public JComboBox<String> getPaiements() {
		return paiements;
	}

	private JComboBox<String> comptes;
	private JComboBox<String> paiements;
	
	public JTextField getEntreprise() {
		return entreprise;
	}

	private JTextField nomFacture;
	private JComboBox<String> doit;

	/**
	 * Launch the application.
	 */
	public static void main(CInsideProject c) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Facture frame = new Facture(c);
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
	public Facture(CInsideProject c) {
		entreprise = new JTextField();
		this.ctrl = new CFacture(c, this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Facture");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 2};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_8 = new JLabel("Nom");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 0;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		nomFacture = new JTextField();
		GridBagConstraints gbc_nomFacture = new GridBagConstraints();
		gbc_nomFacture.insets = new Insets(0, 0, 5, 0);
		gbc_nomFacture.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomFacture.gridx = 1;
		gbc_nomFacture.gridy = 0;
		panel.add(nomFacture, gbc_nomFacture);
		nomFacture.setColumns(10);
		
		doit = new JComboBox<String>();
		doit.setName("doit");
		System.out.println(doit.getName());
		doit.addActionListener(ctrl);
		doit.addItem(c.getP().getEntrepriseBase());
		doit.addItem("Autre");
		
		GridBagConstraints gbc_doit = new GridBagConstraints();
		gbc_doit.insets = new Insets(0, 0, 5, 5);
		gbc_doit.fill = GridBagConstraints.HORIZONTAL;
		gbc_doit.gridx = 0;
		gbc_doit.gridy = 1;
		panel.add(doit, gbc_doit);
		
		
		GridBagConstraints gbc_entreprise = new GridBagConstraints();
		gbc_entreprise.insets = new Insets(0, 0, 5, 0);
		gbc_entreprise.fill = GridBagConstraints.HORIZONTAL;
		gbc_entreprise.gridx = 1;
		gbc_entreprise.gridy = 1;
		panel.add(entreprise, gbc_entreprise);
		entreprise.setColumns(10);
		
		
		JLabel lblNewLabel_9 = new JLabel("Compte Associé");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 2;
		panel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		comptes = new JComboBox<String>();
		for(Compte cpt : Compte.values()) {
			comptes.addItem(cpt.name());
		}
		GridBagConstraints gbc_comptes = new GridBagConstraints();
		gbc_comptes.insets = new Insets(0, 0, 5, 0);
		gbc_comptes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comptes.gridx = 1;
		gbc_comptes.gridy = 2;
		panel.add(comptes, gbc_comptes);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		try {
			date = new JFormattedTextField(new MaskFormatter("##/##/####"));
			date.setHorizontalAlignment(SwingConstants.CENTER);
			date.setColumns(23);
			GridBagConstraints gbc_date = new GridBagConstraints();
			gbc_date.insets = new Insets(0, 0, 5, 0);
			gbc_date.fill = GridBagConstraints.HORIZONTAL;
			gbc_date.gridx = 1;
			gbc_date.gridy = 3;
			panel.add(date, gbc_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_2 = new JLabel("Quantité");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		qte = new JSpinner();
		qte.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		GridBagConstraints gbc_qte = new GridBagConstraints();
		gbc_qte.insets = new Insets(0, 0, 5, 0);
		gbc_qte.gridx = 1;
		gbc_qte.gridy = 4;
		panel.add(qte, gbc_qte);
		
		JLabel lblNewLabel_3 = new JLabel("Prix Unitaire");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 5;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		prix = new JSpinner();
		prix.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
		GridBagConstraints gbc_prix = new GridBagConstraints();
		gbc_prix.insets = new Insets(0, 0, 5, 0);
		gbc_prix.gridx = 1;
		gbc_prix.gridy = 5;
		panel.add(prix, gbc_prix);
		
		JLabel lblNewLabel_4 = new JLabel("Remise");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 6;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		remise = new JSpinner();
		remise.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(100), Float.valueOf(1)));
		GridBagConstraints gbc_remise = new GridBagConstraints();
		gbc_remise.insets = new Insets(0, 0, 5, 0);
		gbc_remise.gridx = 1;
		gbc_remise.gridy = 6;
		panel.add(remise, gbc_remise);
		
		JLabel lblNewLabel_5 = new JLabel("Rabais");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 7;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		rabais = new JSpinner();
		rabais.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(100), Float.valueOf(1)));
		GridBagConstraints gbc_rabais = new GridBagConstraints();
		gbc_rabais.insets = new Insets(0, 0, 5, 0);
		gbc_rabais.gridx = 1;
		gbc_rabais.gridy = 7;
		panel.add(rabais, gbc_rabais);
		
		JLabel lblNewLabel_6 = new JLabel("Ristourne");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 8;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		ristourne = new JSpinner();
		ristourne.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(100), Float.valueOf(1)));
		GridBagConstraints gbc_ristourne = new GridBagConstraints();
		gbc_ristourne.insets = new Insets(0, 0, 5, 0);
		gbc_ristourne.gridx = 1;
		gbc_ristourne.gridy = 8;
		panel.add(ristourne, gbc_ristourne);
		
		JLabel lblNewLabel_7 = new JLabel("Paimement");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 9;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		paiements = new JComboBox<String>();
		for(Paiement pm : Paiement.values()) {
			paiements.addItem(pm.name());
		}
		GridBagConstraints gbc_paiements = new GridBagConstraints();
		gbc_paiements.fill = GridBagConstraints.HORIZONTAL;
		gbc_paiements.gridx = 1;
		gbc_paiements.gridy = 9;
		panel.add(paiements, gbc_paiements);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Annuler");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(ctrl);
		panel_1.add(btnNewButton_1);
	}

	public JFormattedTextField getDate() {
		return date;
	}

	public JTextField getNomFacture() {
		return nomFacture;
	}

	public JComboBox<String> getDoit() {
		return doit;
	}

	public void setEntreprise(JTextField entreprise) {
		this.entreprise = entreprise;
	}

}
