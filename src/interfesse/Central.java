package interfesse;

import java.awt.EventQueue;
import engine.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controleur.CProjet;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JList;
import java.awt.CardLayout;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Central extends JFrame {

	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int screenWidth = (int) screenSize.getWidth();
    private final int screenHeight = (int) screenSize.getHeight();
    
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField CA;
	private JTextField CV;
	private JTextField CF;
	private JTextField MCV;
	private JTextField SR;
	private JTextField IDS;
	private JTextField TR;
	private JTextField R;
	public void setListProjet(JList<String> listProjet) {
		this.listProjet = listProjet;
	}
	private JTextField montantpret;
	private JTable table;
	private JTextField c11;
	private JTextField c12;
	private JTextField c2;
	private JTextField c1;
	private JTextField c13;
	private JTextField c3;
	private JTable table_1;
	private JTable table_2;
	private JList<String> listProjet;
	private CProjet ctrl;

	public JList<String> getListProjet() {
		return listProjet;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Central frame = new Central();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 
	 */
	
	
	private void addToTable(JTable table, String s,int col) {
		Text t = new Text(s);
		float f;
		if (t.isNumeric()) {
			if (!t.isNull()) {
				if (!t.checkMultiplication()) {
					if (t.isDot()) {
						f = t.notEntier();
						table.setValueAt(f, searchEmptyCell(table), col);
					} else {
						f = t.stringToFloat();
						table.setValueAt(f, searchEmptyCell(table), col);
					}	
				} else {
					f = t.multiplication();
					table.setValueAt(f, searchEmptyCell(table), col);
				}
			}else {
				f = 0f;
				table.setValueAt(f, searchEmptyCell(table), 3);
			}
		}
		
	}
	
	private int searchEmptyCell(JTable table) {
		for(int i = 0 ; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 3)==null) {
				return i;
			}
		}
		throw new IllegalArgumentException("Plus de place");
	}

	/**
	 * Create the frame.
	 */
	
	private int convertMonthToInt(String s) {
		
		if (s == "Janvier") {
			return 1;
		}
		System.out.println(s == "Février");
		if (s == "Février") {
			System.out.println("là");
			return 2;
		}
		else if (s == "Mars") {
			return 3;
		}
		else if (s == "Avril") {
			return 4;
		}
		else if (s == "Mai") {
			return 5;
		}
		else if (s == "Juin") {
			return 6;
		}
		else if (s == "Juillet") {
			return 7;
		}
		else if (s == "Août") {
			return 8;
		}
		else if (s == "Septembre") {
			return 9;
		}
		else if (s == "Octobre") {
			return 10;
		}
		else if (s == "Novembre") {
			return 11;
		}
		else if (s == "Décembre") {
			return 12;
		}
		else {
			System.out.println("ici");
			return 13;
		}
	}
	
	
	
	private void desactive(JRadioButton r1,JRadioButton r2,JRadioButton r3) {
		
		if (r1!=null) {
			if (r1.isSelected()) {
				r1.setSelected(false);
			}
			
		}
		if (r2!=null) {
			if (r2.isSelected()) {
				r2.setSelected(false);
			}
			
		}
		if (r3!=null) {
			if (r3.isSelected()) {
				r3.setSelected(false);
			}
			
		}
	}
	
	private int getEtat(JRadioButton r1,JRadioButton r2,JRadioButton r3,JRadioButton r4) {
		if(r1.isSelected()) {
			return 0;
		}
		else if(r2.isSelected()) {
			return 1;
		}
		else if(r3.isSelected()) {
			return 2;
		}
		else if(r4.isSelected()) {
			return 3;
		} 
		else {
			throw new IllegalArgumentException("Selectionnez un bouton");
		}
		
	}
	
	private void valueAt(int index,int col,JTextField f,JTable table) {
		if (table.getValueAt(index, col)!=null) {
			f.setText(""+table.getValueAt(index, col));
		} else {
			f.setText("");
		}
		
	}
	
	private void addToTableAtRow(JTable table, String s,int col,int row) {
		Text t = new Text(s);
		float f;
		if (!t.isNull()) {
			if (t.isNumeric()) {
				if (!t.checkMultiplication()) {
					if (t.isDot()) {
						f = t.notEntier();
						table.setValueAt(f, row, col);
					} else {
						f = t.stringToFloat();
						table.setValueAt(f, row, col);
					}	
				} else {
					f = t.multiplication();
					table.setValueAt(f, row, col);
				}
			}
		
		} else {
			f = 0f;
			table.setValueAt(f, row, col);
		}
	
	}
	
	private int countNotNull(JTable table, int col) {
		int count = 0;
		for(int row=0;row<table.getRowCount();row++) {
			if (table.getValueAt(row, col)!=null) {
				count++;
			}
		}
		return count;
	}
	
	private boolean isTableReady(JTable table, int col,int ech) {
		if (countNotNull(table,col)==ech) {
			return true;
		} else {
			return false;
		}
	}
	
	private void clearTable(JTable table, JTable table2) {
		for(int row=0;row<table.getRowCount();row++) {
			table.setValueAt(null, row, 3);
			table2.setValueAt(null, row, 3);
		
		}
	}
	public Central() throws Exception {
		ctrl = new CProjet(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(this.screenWidth/6, this.screenHeight/6, 907, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Project Calculator", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JButton reset1 = new JButton("Reset");
		
		panel_1.add(reset1);
		
		JButton calculer1 = new JButton("Calculer");
		panel_1.add(calculer1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 0, 10, 20));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(panel_1_1);
		panel_1_1.setLayout(new GridLayout(0, 2, 10, 50));
		
		JLabel lblNewLabel_10 = new JLabel("");
		panel_1_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		panel_1_1.add(lblNewLabel_11);
		
		JLabel lblNewLabel_5 = new JLabel("CA :");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		panel_1_1.add(lblNewLabel_5);
		
		CA = new JTextField();
		CA.setColumns(10);
		panel_1_1.add(CA);
		
		JLabel lblNewLabel_6 = new JLabel("CV :");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		panel_1_1.add(lblNewLabel_6);
		
		CV = new JTextField();
		CV.setColumns(10);
		panel_1_1.add(CV);
		
		JLabel lblNewLabel_7 = new JLabel("CF :");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		panel_1_1.add(lblNewLabel_7);
		
		CF = new JTextField();
		CF.setColumns(10);
		panel_1_1.add(CF);
		
		JLabel lblNewLabel_8 = new JLabel("");
		panel_1_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		panel_1_1.add(lblNewLabel_9);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(0, 2, 0, 50));
		
		JLabel lblNewLabel = new JLabel("MCV :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		panel_2_1.add(lblNewLabel);
		
		MCV = new JTextField();
		MCV.setEditable(false);
		MCV.setColumns(10);
		panel_2_1.add(MCV);
		
		JLabel lblNewLabel_1 = new JLabel("SR :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		panel_2_1.add(lblNewLabel_1);
		
		SR = new JTextField();
		SR.setEditable(false);
		SR.setColumns(10);
		panel_2_1.add(SR);
		
		JLabel lblNewLabel_2 = new JLabel("IDS :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		panel_2_1.add(lblNewLabel_2);
		
		IDS = new JTextField();
		IDS.setEditable(false);
		IDS.setColumns(10);
		panel_2_1.add(IDS);
		
		JLabel lblNewLabel_3 = new JLabel("TR :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		panel_2_1.add(lblNewLabel_3);
		
		TR = new JTextField();
		TR.setEditable(false);
		TR.setColumns(10);
		panel_2_1.add(TR);
		
		JLabel lblNewLabel_4 = new JLabel("Résultat :");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("OCR A Extended", Font.PLAIN, 26));
		panel_2_1.add(lblNewLabel_4);
		
		R = new JTextField();
		R.setEditable(false);
		R.setColumns(10);
		panel_2_1.add(R);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Prêts", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.SOUTH);
		
		JButton reset2 = new JButton("Reset");
		
		panel_4.add(reset2);
		
		JButton calculer2 = new JButton("Calculer");
		
		panel_4.add(calculer2);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2_2 = new JPanel();
		panel_5.add(panel_2_2);
		panel_2_2.setLayout(new BorderLayout(5, 0));
		
		JPanel panel_1_2 = new JPanel();
		panel_2_2.add(panel_1_2, BorderLayout.WEST);
		panel_1_2.setLayout(new GridLayout(0, 1, 20, 5));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_2.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel montant = new JLabel("Montant initial :");
		panel_8.add(montant);
		montant.setForeground(new Color(0, 0, 0));
		montant.setHorizontalAlignment(SwingConstants.CENTER);
		montant.setFont(new Font("Dialog", Font.BOLD, 14));
		
		montantpret = new JTextField();
		panel_8.add(montantpret);
		montantpret.setColumns(10);
		
		JLabel taux = new JLabel("Taux % :");
		panel_8.add(taux);
		taux.setForeground(new Color(0, 0, 0));
		taux.setHorizontalAlignment(SwingConstants.CENTER);
		taux.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JSpinner tauxpret = new JSpinner();
		tauxpret.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), Float.valueOf(100), Float.valueOf(1)));
		panel_8.add(tauxpret);
		tauxpret.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel duree = new JLabel("Nombre d'échéances :");
		panel_8.add(duree);
		duree.setForeground(new Color(0, 0, 0));
		duree.setHorizontalAlignment(SwingConstants.CENTER);
		duree.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JSpinner nbpaiements = new JSpinner();
		nbpaiements.setModel(new SpinnerNumberModel(1, 1, 24, 1));
		panel_8.add(nbpaiements);
		nbpaiements.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_2.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, -5));
		
		JPanel panel_12 = new JPanel();
		panel_7.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_15 = new JLabel("Constante : ");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_12.add(lblNewLabel_15);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_12.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 1, 0, 0));
		
		JRadioButton amorcst = new JRadioButton("Amortissement");
		panel_11.add(amorcst);
		
		JRadioButton anncst = new JRadioButton("N-litée");
		panel_11.add(anncst);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_2.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_16 = new JLabel("Paiement");
		lblNewLabel_16.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel_16, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_9.add(panel_10, BorderLayout.SOUTH);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_17 = new JLabel("Date Premier Paiement");
		lblNewLabel_17.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		panel_10.add(lblNewLabel_17);
		
		JPanel panel_13 = new JPanel();
		panel_10.add(panel_13);
		panel_13.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JComboBox mois = new JComboBox();
		mois.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "Février", "Mars", "Avril", "Mai ", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"}));
		panel_13.add(mois);
		
		
		JComboBox annee = new JComboBox();
		annee.setModel(new DefaultComboBoxModel(new String[] {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099"}));
		panel_13.add(annee);
		
		
		
		
		JPanel panel_10_1 = new JPanel();
		panel_10_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_9.add(panel_10_1, BorderLayout.CENTER);
		panel_10_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton mensuel = new JRadioButton("Mensuel");
		panel_10_1.add(mensuel);
		
		JRadioButton trimestriel = new JRadioButton("Trimestriel");
		panel_10_1.add(trimestriel);
		
		JRadioButton semestriel = new JRadioButton("Semestriel");
		panel_10_1.add(semestriel);
		
		JRadioButton annuel = new JRadioButton("Annuel");
		panel_10_1.add(annuel);
		
		amorcst.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				desactive(anncst,null,null);
				
			}
		});
		amorcst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					amorcst.setSelected(true);
				
			}
		});
		
		anncst.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				desactive(amorcst,null,null);
				
			}
		});
		anncst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					anncst.setSelected(true);
				
			}
		});
		
		mensuel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				desactive(semestriel,trimestriel,annuel);
				
			}
		});
		mensuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
					mensuel.setSelected(true);
				
			}
		});
		
		trimestriel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				desactive(semestriel,annuel,mensuel);
				
			}
		});
		trimestriel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					trimestriel.setSelected(true);
				
			}
		});
		
		semestriel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				desactive(annuel,trimestriel,mensuel);
				
			}
		});
		semestriel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					semestriel.setSelected(true);
				
			}
		});
		
		annuel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				desactive(semestriel,trimestriel,mensuel);
				
				
			}
		});
		annuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					annuel.setSelected(true);
				
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2_2.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Ech\u00E9ance", "Restant", "Int\u00E9r\u00EAts", "N-lit\u00E9e", "Amortissment"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(184);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(15);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Actualisation", null, panel_6, null);
		panel_6.setLayout(new BorderLayout(10, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.add(panel_14, BorderLayout.WEST);
		panel_14.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_14.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JRadioButton vu = new JRadioButton("Valeur unique");
		vu.setSelected(true);
		vu.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		panel_16.add(vu, BorderLayout.NORTH);
		
		JPanel pvu = new JPanel();
		pvu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_16.add(pvu, BorderLayout.CENTER);
		pvu.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_18 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_18.getLayout();
		flowLayout.setVgap(10);
		pvu.add(panel_18, BorderLayout.NORTH);
		
		JLabel t11 = new JLabel("Nombre \r\nd'échéances");
		t11.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		panel_18.add(t11);
		
		JSpinner s11 = new JSpinner();
		s11.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		panel_18.add(s11);
		
		JPanel panel_19 = new JPanel();
		pvu.add(panel_19, BorderLayout.CENTER);
		panel_19.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_22 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_22.getLayout();
		flowLayout_2.setVgap(20);
		panel_19.add(panel_22);
		
		JLabel t12 = new JLabel("Valeur à actualiser");
		t12.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		t12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_22.add(t12);
		
		c11 = new JTextField();
		c11.setColumns(10);
		panel_22.add(c11);
		
		JPanel panel_23 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_23.getLayout();
		flowLayout_1.setVgap(20);
		panel_19.add(panel_23);
		
		JLabel t13 = new JLabel("Valeurs extérieures");
		t13.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		t13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_23.add(t13);
		
		c12 = new JTextField();
		c12.setColumns(10);
		panel_23.add(c12);
		
		JButton b12 = new JButton("+");
		
		panel_23.add(b12);
		
		JPanel panel_20 = new JPanel();
		pvu.add(panel_20, BorderLayout.SOUTH);
		
		JLabel tauT = new JLabel("Taux : ");
		tauT.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		panel_20.add(tauT);
		
		JSpinner tauxact = new JSpinner();
		panel_20.add(tauxact);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_14.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JRadioButton vm = new JRadioButton("Valeurs multiples ");
		
		vm.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		panel_15.add(vm, BorderLayout.NORTH);
		
		JPanel pvm = new JPanel();
		pvm.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pvm.setEnabled(false);
		panel_15.add(pvm, BorderLayout.CENTER);
		pvm.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		pvm.add(panel_21, BorderLayout.NORTH);
		
		JLabel t1 = new JLabel("Nombre d'échéances");
		t1.setEnabled(false);
		t1.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		panel_21.add(t1);
		
		JSpinner s1 = new JSpinner();
		
		s1.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		s1.setEnabled(false);
		panel_21.add(s1);
		
		JPanel panel_19_1 = new JPanel();
		pvm.add(panel_19_1, BorderLayout.CENTER);
		panel_19_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_22_1 = new JPanel();
		panel_19_1.add(panel_22_1);
		panel_22_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_25 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_25.getLayout();
		flowLayout_3.setVgap(2);
		panel_22_1.add(panel_25);
		
		JLabel t2 = new JLabel("Valeur à actualiser");
		t2.setEnabled(false);
		t2.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		t2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_25.add(t2);
		
		c1 = new JTextField();
		c1.setEnabled(false);
		c1.setColumns(10);
		panel_25.add(c1);
		
		JButton b1 = new JButton("+");
		
		b1.setEnabled(false);
		panel_25.add(b1);
		
		JPanel panel_24 = new JPanel();
		panel_22_1.add(panel_24);
		panel_24.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel t3 = new JLabel("Valeur à actualiser à la position :");
		t3.setEnabled(false);
		t3.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		panel_24.add(t3);
		
		JSpinner s2 = new JSpinner();
		
		s2.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		s2.setEnabled(false);
		panel_24.add(s2);
		
		JPanel panel_23_1 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_23_1.getLayout();
		flowLayout_4.setVgap(20);
		panel_19_1.add(panel_23_1);
		
		JLabel t4 = new JLabel("Valeurs extérieures");
		t4.setEnabled(false);
		t4.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		t4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_23_1.add(t4);
		
		c2 = new JTextField();
		c2.setEnabled(false);
		c2.setColumns(10);
		panel_23_1.add(c2);
		
		JButton b2 = new JButton("+");
		
		b2.setEnabled(false);
		panel_23_1.add(b2);
		
		JPanel panel_20_1 = new JPanel();
		pvm.add(panel_20_1, BorderLayout.SOUTH);
		
		JLabel tauT_1 = new JLabel("Taux : ");
		tauT_1.setEnabled(false);
		tauT_1.setFont(new Font("OCR A Extended", Font.PLAIN, 12));
		panel_20_1.add(tauT_1);
		
		JSpinner tauxact_1 = new JSpinner();
		tauxact_1.setEnabled(false);
		panel_20_1.add(tauxact_1);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_6.add(panel_26, BorderLayout.CENTER);
		panel_26.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel pvut = new JPanel();
		pvut.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_26.add(pvut);
		pvut.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_29 = new JPanel();
		panel_29.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pvut.add(panel_29);
		panel_29.setLayout(new BorderLayout(0, 0));
		
		JScrollPane sp1 = new JScrollPane();
		panel_29.add(sp1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Ech\u00E9ance", "Valeur \u00E0 Actualiser", "Valeur Actualis\u00E9e", "Valeur Ext\u00E9rieure"
			}
		));
		sp1.setViewportView(table_1);
		table_1.setRowHeight(25);
		
		JPanel panel_30 = new JPanel();
		pvut.add(panel_30);
		panel_30.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel t14 = new JLabel("Valeur actualisée");
		t14.setFont(new Font("OCR A Extended", Font.PLAIN, 32));
		t14.setHorizontalAlignment(SwingConstants.CENTER);
		panel_30.add(t14);
		
		c13 = new JTextField();
		c13.setEditable(false);
		c13.setFont(new Font("OCR A Extended", Font.PLAIN, 33));
		c13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_30.add(c13);
		c13.setColumns(10);
		
		JPanel pvmt = new JPanel();
		pvmt.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pvmt.setEnabled(false);
		panel_26.add(pvmt);
		pvmt.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_31 = new JPanel();
		pvmt.add(panel_31);
		panel_31.setLayout(new BorderLayout(0, 0));
		
		JScrollPane sp2 = new JScrollPane();
		panel_31.add(sp2, BorderLayout.CENTER);
		
		table_2 = new JTable();
		table_2.setEnabled(false);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Ech\u00E9ance", "Valeur \u00E0 actualiser", "Valeur Actualis\u00E9e", "Valeur Ext\u00E9rieure"
			}
		));
		sp2.setViewportView(table_2);
		table_2.setRowHeight(25);
		
		JPanel panel_32 = new JPanel();
		pvmt.add(panel_32);
		panel_32.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel t5 = new JLabel("Valeur actualisée");
		t5.setEnabled(false);
		t5.setHorizontalAlignment(SwingConstants.CENTER);
		t5.setFont(new Font("OCR A Extended", Font.PLAIN, 32));
		panel_32.add(t5);
		
		c3 = new JTextField();
		c3.setEditable(false);
		c3.setFont(new Font("OCR A Extended", Font.PLAIN, 33));
		c3.setHorizontalAlignment(SwingConstants.CENTER);
		c3.setEnabled(false);
		panel_32.add(c3);
		c3.setColumns(10);
		
		JPanel panel_17 = new JPanel();
		panel_6.add(panel_17, BorderLayout.SOUTH);
		
		JButton valexterne = new JButton("Reset valeurs ext.");
		
		panel_17.add(valexterne);
		
		JButton reset3 = new JButton("Reset");
		
		panel_17.add(reset3);
		
		JButton calculer3 = new JButton("Calculer");
		
		panel_17.add(calculer3);
		
		JPanel panel_27 = new JPanel();
		tabbedPane.addTab("Balance et bilan", null, panel_27, null);
		panel_27.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_28 = new JPanel();
		panel_27.add(panel_28, BorderLayout.SOUTH);
		
		JButton btnNewButton_2 = new JButton("Nouveau projet");
		btnNewButton_2.addActionListener(ctrl);
		panel_28.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Supprimer projet");
		btnNewButton_1.addActionListener(ctrl);
		panel_28.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Editer projet");
		btnNewButton.addActionListener(ctrl);
		panel_28.add(btnNewButton);
		
		JPanel panel_33 = new JPanel();
		panel_27.add(panel_33, BorderLayout.NORTH);
		
		JLabel lblNewLabel_13 = new JLabel("Projets");
		panel_33.add(lblNewLabel_13);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_27.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel_34 = new JPanel();
		scrollPane_1.setViewportView(panel_34);
		panel_34.setLayout(new CardLayout(0, 0));
		
		
		listProjet = new JList<String>();
		listProjet.addListSelectionListener(ctrl);
		panel_34.add(listProjet, "name_804636888798500");
		try {
			ctrl.updateList();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JLabel lblNewLabel_12 = new JLabel("M Calculator");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setFont(new Font("OCR A Extended", Font.BOLD, 35));
		contentPane.add(lblNewLabel_12, BorderLayout.NORTH);
		
		vm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vm.setSelected(true);
				t1.setEnabled(true);
				t2.setEnabled(true);
				t3.setEnabled(true);
				t4.setEnabled(true);
				t5.setEnabled(true);
				
				s1.setEnabled(true);
				s2.setEnabled(true);
				
				table_2.setEnabled(true);
				
				b1.setEnabled(true);
				b2.setEnabled(true);
				
				c1.setEnabled(true);
				c2.setEnabled(true);
				c3.setEnabled(true);
				sp1.setEnabled(true);
				
				t11.setEnabled(false);
				t12.setEnabled(false);
				t13.setEnabled(false);
				t14.setEnabled(false);
				tauT_1.setEnabled(true);
				tauxact_1.setEnabled(true);
				tauT.setEnabled(false);
				tauxact.setEnabled(false);
				s11.setEnabled(false);
				
				
				table_1.setEnabled(false);
				
			
				b12.setEnabled(false);
				
				c11.setEnabled(false);
				c12.setEnabled(false);
				c13.setEnabled(false);
				sp2.setEnabled(false);
			}
		});
		vu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vu.setSelected(true);
				t1.setEnabled(false);
				t2.setEnabled(false);
				t3.setEnabled(false);
				t4.setEnabled(false);
				t5.setEnabled(false);
				tauT_1.setEnabled(false);
				tauxact_1.setEnabled(false);
				tauT.setEnabled(true);
				tauxact.setEnabled(true);
				s1.setEnabled(false);
				s2.setEnabled(false);
				
				table_2.setEnabled(false);
				
				b1.setEnabled(false);
				b2.setEnabled(false);
				
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setEnabled(false);
				sp1.setEnabled(true);
				t11.setEnabled(true);
				t12.setEnabled(true);
				t13.setEnabled(true);
				t14.setEnabled(true);
				
				s11.setEnabled(true);
				
				
				table_1.setEnabled(true);
				
				
				b12.setEnabled(true);
				
				c11.setEnabled(true);
				c12.setEnabled(true);
				c13.setEnabled(true);
				sp2.setEnabled(false);
			}
		});
		vm.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				desactive(vu,null,null);
			}
		});
		vu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				desactive(vm,null,null);
			}
		});
		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToTable(table_1,c12.getText(),3);
				c12.setText(null);
			}
		});
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = (Integer)s2.getValue();
				addToTableAtRow(table_2,c1.getText(),1,value-1);
				
				if ((Integer)s2.getValue()==(Integer)s1.getValue()) {
					s2.setValue((Integer)s1.getValue());
				} else {
					s2.setValue((Integer)s2.getValue()+1);
				}
				
				
				
			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToTable(table_2,c2.getText(),3);
				c2.setText(null);
			}
		});
		s2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if ((Integer)s2.getValue()>=(Integer)s1.getValue()) {
					s2.setValue((Integer)s1.getValue());	
				}
				valueAt((Integer)s2.getValue()-1,1,c1,table_2);
			}
		});
		s1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if ((Integer)s2.getValue()>=(Integer)s1.getValue()) {
					s2.setValue((Integer)s1.getValue());
				}
				table_2.setValueAt(null,(Integer)s2.getValue() , 1);
				valueAt((Integer)s2.getValue()-1,1,c1,table_2);
			}
		});
		
		
		
		
		reset1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CA.setText("");
				CV.setText("");
				CF.setText("");
				R.setText("");
				IDS.setText("");
				TR.setText("");
				SR.setText("");
				MCV.setText("");
				calculer1.setEnabled(true);
			}
		});
		
		
		calculer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Indice i = new Indice(new Text(CA.getText()),new Text(CV.getText()),new Text(CF.getText()));
				CA.setText(""+i.getCa()+'€');
				CV.setText(""+i.getCv()+'€');
				CF.setText(""+i.getCf()+'€');
				R.setText(""+i.resultat()+'€');
				IDS.setText(""+i.Ids()+'%');
				TR.setText(""+i.TR()+'%');
				SR.setText(""+i.SR()+'€');
				MCV.setText(""+i.MCV()+'€');
				calculer1.setEnabled(false);
			}
		});
		
		reset2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pret p = new Pret(new Text(montantpret.getText()),
						Float.parseFloat(tauxpret.getValue().toString()),
						Float.parseFloat(nbpaiements.getValue().toString()),
						table,
						getEtat(mensuel,trimestriel,semestriel,annuel),
						Integer.parseInt(annee.getSelectedItem().toString()),
						convertMonthToInt(mois.getSelectedItem().toString()));
				montantpret.setText(null);
				tauxpret.setValue(0);
				nbpaiements.setValue(1);
				p.reset();
				
			}
		});
		
		calculer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pret p = new Pret(new Text(montantpret.getText()),
						Float.parseFloat(tauxpret.getValue().toString()),
						Float.parseFloat(nbpaiements.getValue().toString()),
						table,
						getEtat(mensuel,trimestriel,semestriel,annuel),
						Integer.parseInt(annee.getSelectedItem().toString()),
						convertMonthToInt(mois.getSelectedItem().toString()));
				p.reset();
				switch (getEtat(anncst,amorcst,null,null)) {
					case 0 : 
						System.out.println("bite");
						p.fillTableAnn();
						break;
					case 1 :
						p.fillTableAmor();
						break;
					default :
						throw new IllegalArgumentException("Faut choisir à un moment donné svp");
						
				}
			}
		});
		
		calculer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Text t; 
				Text t2;
				switch (getEtat(vu,vm,null,null)) {
				case 0:
					t = new Text(c11.getText());
					t2 = new Text(""+tauxact.getValue());
					Actu a = new Actu((Integer)s11.getValue(),table_1,t.toFloat(),t2.toFloat());
					a.fillTableVU();
					c13.setText(""+a.somme());
					break;
				case 1 :
					if (isTableReady(table_2,1,(Integer)s1.getValue())) {
						t2 = new Text(""+tauxact_1.getValue());
						Actu a2 = new Actu((Integer)s1.getValue(),table_2,0,t2.toFloat());
						a2.fillTableVM();
						c3.setText(""+a2.somme());
					}
				}
			}
		});
		
		reset3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Text t = new Text(c11.getText());
				Text t2 = new Text(""+tauxact.getValue());
				Actu a2 = new Actu((Integer)s1.getValue(),table_2,0,t2.toFloat());
				Actu a = new Actu((Integer)s11.getValue(),table_1,t.toFloat(),t2.toFloat());
				c11.setText(null);
				c13.setText(null);
				c3.setText(null);
				c1.setText(null);
				a.reset();
				a2.reset();
				
			}
		});
		valexterne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable(table_1,table_2);
			}
		});
	}

}
