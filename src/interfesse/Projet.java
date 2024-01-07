package interfesse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleur.CInsideProject;
import controleur.CProjet;
import engine.EProjet;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Projet extends JFrame {

	private JPanel contentPane;
	private JList<String> factures;
	private CInsideProject ctrl;

	/**
	 * Launch the application.
	 */
	public static void main(EProjet e) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Projet frame = new Projet(e);
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
	public Projet(EProjet p) {
		this.ctrl = new CInsideProject(p, this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(20, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel(p.getNom());
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(5, 32767));
		scrollPane.setRowHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{89, 0};
		gbl_panel.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton_1 = new JButton("Nouvelle facture");
		btnNewButton_1.addActionListener(ctrl);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer facture");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 2;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Editer facture");
		btnNewButton_4.addActionListener(ctrl);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 3;
		panel.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton = new JButton("Générer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 5;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Fermer");
		btnNewButton_3.addActionListener(ctrl);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 7;
		panel.add(btnNewButton_3, gbc_btnNewButton_3);
		
		factures = new JList<String>();
		factures.addListSelectionListener(ctrl);
		scrollPane.setViewportView(factures);

		this.ctrl.updateList();
	}

	public JList<String> getFactures() {
		return factures;
	}

}
