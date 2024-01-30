package interfesse;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controleur.CJBB;
import engine.EFacture;
import engine.EProjet;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class JBB extends JFrame {

	private JPanel contentPane;
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int screenWidth = (int) screenSize.getWidth();
    private final int screenHeight = (int) screenSize.getHeight();
    private JTable tableJournal;
    private CJBB ctrl;

	/**
	 * Launch the application.
	 */
	public static void main(List<EFacture> factures) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JBB frame = new JBB(factures);
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
	public JBB(List<EFacture> factures) {
		tableJournal = new JTable();
		tableJournal.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		this.ctrl = new CJBB(factures,this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(this.screenWidth/6, this.screenHeight/6, 950, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Journal", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		
		scrollPane.setViewportView(tableJournal);
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane_1, null);
	}

	public JTable getTableJournal() {
		return tableJournal;
	}

	public void setTableJournal(JTable tableJournal) {
		this.tableJournal = tableJournal;
	}

}
