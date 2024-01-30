package interfesse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controleur.CProjet;
import controleur.CProjetInit;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ProjectInit extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomProjet;
	private JTextField entrepriseBase;
	private JTextField date;
	private JSpinner salaires;
	private JSpinner capital;
	private CProjetInit ctrl;
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int screenWidth = (int) screenSize.getWidth();
    private final int screenHeight = (int) screenSize.getHeight();

	public JTextField getNomProjet() {
		return nomProjet;
	}

	public JTextField getEntrepriseBase() {
		return entrepriseBase;
	}

	/**
	 * Launch the application.
	 */
	public static void main(CProjet c) {
		try {
			ProjectInit dialog = new ProjectInit(c);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public ProjectInit(CProjet c) throws ParseException {
		this.ctrl = new CProjetInit(this, c);
		setBounds(this.screenWidth/6, this.screenHeight/6, 275, 199);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Nom  du projet");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			nomProjet = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(nomProjet, gbc_textField);
			nomProjet.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Entreprise base");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			entrepriseBase = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			contentPanel.add(entrepriseBase, gbc_textField_1);
			entrepriseBase.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Capital");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			capital = new JSpinner();
			capital.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
			GridBagConstraints gbc_capital = new GridBagConstraints();
			gbc_capital.fill = GridBagConstraints.HORIZONTAL;
			gbc_capital.insets = new Insets(0, 0, 5, 0);
			gbc_capital.gridx = 1;
			gbc_capital.gridy = 2;
			contentPanel.add(capital, gbc_capital);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Salaires");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 3;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			salaires = new JSpinner();
			salaires.setModel(new SpinnerNumberModel(Float.valueOf(0), Float.valueOf(0), null, Float.valueOf(1)));
			GridBagConstraints gbc_salaires = new GridBagConstraints();
			gbc_salaires.fill = GridBagConstraints.HORIZONTAL;
			gbc_salaires.insets = new Insets(0, 0, 5, 0);
			gbc_salaires.gridx = 1;
			gbc_salaires.gridy = 3;
			contentPanel.add(salaires, gbc_salaires);
		}
		{
			JLabel labelDate = new JLabel("Date paiement salaire");
			labelDate.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_labelDate = new GridBagConstraints();
			gbc_labelDate.insets = new Insets(0, 0, 0, 5);
			gbc_labelDate.gridx = 0;
			gbc_labelDate.gridy = 4;
			contentPanel.add(labelDate, gbc_labelDate);
		}
		{
			date = new JFormattedTextField(new MaskFormatter("##/##/####"));
			date.setHorizontalAlignment(SwingConstants.CENTER);
			date.setColumns(23);
			GridBagConstraints gbc_date = new GridBagConstraints();
			gbc_date.fill = GridBagConstraints.HORIZONTAL;
			gbc_date.gridx = 1;
			gbc_date.gridy = 4;
			contentPanel.add(date, gbc_date);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(ctrl);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.addActionListener(ctrl);
			
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getDate() {
		return date;
	}

	public JSpinner getSalaires() {
		return salaires;
	}

	public JSpinner getCapital() {
		return capital;
	}

}
