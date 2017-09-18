package stocksbro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Gui {

	private JFrame frame;
	private JTextField txtSymbol;
	public static JLabel lblConfirmation;
	public static JTextField Link;
	public static JLabel Output;
	public static JLabel SR;
	public static JLabel lblWarning;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 336, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(221, 411, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StockProcessor processor = 
						new StockProcessor(txtSymbol.getText());
			}
		});
		btnRun.setBounds(221, 36, 89, 23);
		frame.getContentPane().add(btnRun);
		
		txtSymbol = new JTextField();
		txtSymbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StockProcessor processor = 
						new StockProcessor(txtSymbol.getText());
			}
		});
		txtSymbol.setBounds(10, 36, 165, 23);
		frame.getContentPane().add(txtSymbol);
		txtSymbol.setColumns(10);
		
		JLabel lblInputSymbolBelow = new JLabel("Input symbol below: ");
		lblInputSymbolBelow.setBounds(10, 11, 122, 14);
		frame.getContentPane().add(lblInputSymbolBelow);
		
		lblConfirmation = new JLabel("");
		lblConfirmation.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirmation.setVerticalAlignment(SwingConstants.TOP);
		lblConfirmation.setBounds(10, 70, 300, 35);
		frame.getContentPane().add(lblConfirmation);
		
		JLabel lblLinkToChart = new JLabel("Link to chart: ");
		lblLinkToChart.setBounds(10, 355, 89, 14);
		frame.getContentPane().add(lblLinkToChart);
		
		Link = new JTextField();
		Link.setBounds(10, 380, 300, 20);
		frame.getContentPane().add(Link);
		Link.setColumns(10);
		
		Output = new JLabel("");
		Output.setVerticalAlignment(SwingConstants.TOP);
		Output.setHorizontalAlignment(SwingConstants.LEFT);
		Output.setBounds(10, 104, 300, 35);
		frame.getContentPane().add(Output);
		
		SR = new JLabel("");
		SR.setVerticalAlignment(SwingConstants.TOP);
		SR.setHorizontalAlignment(SwingConstants.LEFT);
		SR.setBounds(10, 150, 300, 135);
		frame.getContentPane().add(SR);
		
		lblWarning = new JLabel("");
		lblWarning.setVerticalAlignment(SwingConstants.TOP);
		lblWarning.setHorizontalAlignment(SwingConstants.LEFT);
		lblWarning.setBounds(10, 296, 300, 48);
		frame.getContentPane().add(lblWarning);
	}
}
