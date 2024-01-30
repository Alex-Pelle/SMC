package interfesse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controleur.CFacture;
import controleur.CInsideProject;
import engine.Classe;
import engine.Compte;
import engine.Paiement;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Optional;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;

public class Facture extends JFrame {

	private JPanel contentPane;
	private JTextField entreprise;
	private CFacture ctrl;
	private JFormattedTextField date;
	private JSpinner qte;
	private JSpinner prix;
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int screenWidth = (int) screenSize.getWidth();
    private final int screenHeight = (int) screenSize.getHeight();
    
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
	public static void main(CInsideProject c,Optional<String> facture) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Facture frame = new Facture(c,facture);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Facture(CInsideProject c, Optional<String> facture) {
		entreprise = new JTextField();
		comptes = new JComboBox<String>();
		this.ctrl = new CFacture(c, this, facture);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(this.screenWidth/6, this.screenHeight/6, 450, 417);
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
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_8 = new JLabel("Nom");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 1;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		nomFacture = new JTextField();
		GridBagConstraints gbc_nomFacture = new GridBagConstraints();
		gbc_nomFacture.insets = new Insets(0, 0, 5, 0);
		gbc_nomFacture.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomFacture.gridx = 1;
		gbc_nomFacture.gridy = 1;
		panel.add(nomFacture, gbc_nomFacture);
		nomFacture.setColumns(10);
		
		
		JLabel lblNewLabel_10 = new JLabel("Doit");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 2;
		panel.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		panel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 10, 0));
		
		doit = new JComboBox<String>();
		panel_2.add(doit);
		doit.setName("doit");
		doit.addActionListener(ctrl);
		doit.addItem(c.getP().getEntrepriseBase());
		doit.addItem("Autre");
		
		panel_2.add(entreprise);
		entreprise.setColumns(10);
		
		
		JLabel lblNewLabel_9 = new JLabel("Compte Associé");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 3;
		panel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		
		GridBagConstraints gbc_comptes = new GridBagConstraints();
		gbc_comptes.insets = new Insets(0, 0, 5, 0);
		gbc_comptes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comptes.gridx = 1;
		gbc_comptes.gridy = 3;
		panel.add(comptes, gbc_comptes);
		
		
		try {
			JLabel lblNewLabel_1 = new JLabel("Date");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 4;
			panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			date = new JFormattedTextField(new MaskFormatter("##/##/####"));
			date.setHorizontalAlignment(SwingConstants.CENTER);
			date.setColumns(23);
			GridBagConstraints gbc_date = new GridBagConstraints();
			gbc_date.insets = new Insets(0, 0, 5, 0);
			gbc_date.fill = GridBagConstraints.HORIZONTAL;
			gbc_date.gridx = 1;
			gbc_date.gridy = 4;
			panel.add(date, gbc_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Quantité");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		qte = new JSpinner();
		qte.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		GridBagConstraints gbc_qte = new GridBagConstraints();
		gbc_qte.insets = new Insets(0, 0, 5, 0);
		gbc_qte.gridx = 1;
		gbc_qte.gridy = 5;
		panel.add(qte, gbc_qte);
		
		
		JLabel lblNewLabel_3 = new JLabel("Prix Unitaire");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 6;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		prix = new JSpinner();
		prix.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
		GridBagConstraints gbc_prix = new GridBagConstraints();
		gbc_prix.insets = new Insets(0, 0, 5, 0);
		gbc_prix.gridx = 1;
		gbc_prix.gridy = 6;
		panel.add(prix, gbc_prix);
		
		JLabel lblNewLabel_4 = new JLabel("Remise");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 7;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		remise = new JSpinner();
		remise.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(100), Float.valueOf(1)));
		GridBagConstraints gbc_remise = new GridBagConstraints();
		gbc_remise.insets = new Insets(0, 0, 5, 0);
		gbc_remise.gridx = 1;
		gbc_remise.gridy = 7;
		panel.add(remise, gbc_remise);
		
		JLabel lblNewLabel_5 = new JLabel("Rabais");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 8;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		rabais = new JSpinner();
		rabais.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(100), Float.valueOf(1)));
		GridBagConstraints gbc_rabais = new GridBagConstraints();
		gbc_rabais.insets = new Insets(0, 0, 5, 0);
		gbc_rabais.gridx = 1;
		gbc_rabais.gridy = 8;
		panel.add(rabais, gbc_rabais);
		
		JLabel lblNewLabel_6 = new JLabel("Ristourne");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 9;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		ristourne = new JSpinner();
		ristourne.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(100), Float.valueOf(1)));
		GridBagConstraints gbc_ristourne = new GridBagConstraints();
		gbc_ristourne.insets = new Insets(0, 0, 5, 0);
		gbc_ristourne.gridx = 1;
		gbc_ristourne.gridy = 9;
		panel.add(ristourne, gbc_ristourne);
		
		JLabel lblNewLabel_7 = new JLabel("Paimement");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 10;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		paiements = new JComboBox<String>();
		GridBagConstraints gbc_paiements = new GridBagConstraints();
		gbc_paiements.insets = new Insets(0, 0, 5, 0);
		gbc_paiements.fill = GridBagConstraints.HORIZONTAL;
		gbc_paiements.gridx = 1;
		gbc_paiements.gridy = 10;
		panel.add(paiements, gbc_paiements);
		for(Paiement pm : Paiement.values()) {
			paiements.addItem(pm.name());
		}
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.addActionListener(ctrl);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.addActionListener(ctrl);
		panel_1.add(btnNewButton_1);
		
		ctrl.editer();
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
	
	public void fillComboBox(String selectedValue) {
		if (comptes!=null) {
			comptes.removeAllItems();
		}
		
		if (selectedValue=="Autre") {
			for(Compte cpt : Compte.values()) {
				if(cpt.getClasse().equals(Classe.CLASSE_7)) {
					comptes.addItem(cpt.name());
				}
			}
		} else {
			for(Compte cpt : Compte.values()) {
				if(!cpt.getClasse().equals(Classe.CLASSE_7) && !Compte.getNonSelectionnable().contains(cpt)) {
					comptes.addItem(cpt.name());
				}
			}
		}
		
	}

}
