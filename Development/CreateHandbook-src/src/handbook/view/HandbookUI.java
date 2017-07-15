package handbook.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class HandbookUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7858053643214099545L;
	private JPanel contentPane;
	public static JTextPane txtpnHandbookFormaterLok;

	/**
	 * Launch the application.
	 */
	public static void addMessage(String text) {
		String previousMessages = txtpnHandbookFormaterLok.getText();
		previousMessages += "\n";
		previousMessages += "\n";
		previousMessages += text;

		txtpnHandbookFormaterLok.setText(previousMessages);
	}

	/**
	 * Create the frame.
	 */
	public HandbookUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		txtpnHandbookFormaterLok = new JTextPane();
		txtpnHandbookFormaterLok.setText("Handbook Formater log\n===============");
		contentPane.add(txtpnHandbookFormaterLok, BorderLayout.CENTER);
	}

}
