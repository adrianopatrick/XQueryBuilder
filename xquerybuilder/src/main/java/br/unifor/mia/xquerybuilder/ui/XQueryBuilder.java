package br.unifor.mia.xquerybuilder.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.xml.xquery.XQException;

import br.unifor.mia.xquerybuilder.dao.XQExpressionSample;

/**
 * Classe que tem por resposabilidade interagir com o usuário
 * 
 * @author patrick cunha
 * @since 15/08/2014 08:30
 */
public class XQueryBuilder extends JFrame {

	private static final long serialVersionUID = 1414824045988777937L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JButton btnLimpar;

	/**
	 * Create the frame.
	 */
	public XQueryBuilder() {
		
		setTitle("XQuery Builder 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 30, 880, 95);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F5){
					executaConsulta();
				}
			}
		});
		scrollPane.setViewportView(textArea);

		JButton btnExecutar = new JButton("Executar");
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executaConsulta();
			}
		});
		btnExecutar
				.setIcon(new ImageIcon(getClass().getResource("run-icon.png")));
		btnExecutar.setBounds(21, 137, 104, 29);
		contentPane.add(btnExecutar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 178, 880, 249);
		contentPane.add(scrollPane_1);

		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_1.setText("");
			}
		});
		btnLimpar.setBounds(131, 137, 84, 29);
		btnLimpar.setIcon(new ImageIcon(getClass().getResource("icon-clean.png")));
		contentPane.add(btnLimpar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(21, 459, 880, 12);
		contentPane.add(separator);
		
		JLabel lbladrianopatrick = new JLabel("#mia-unifor @adrianopatrickc - 15/08/2014");
		lbladrianopatrick.setBounds(21, 459, 282, 16);
		contentPane.add(lbladrianopatrick);
		
	}
	
	/**
	 * Método que executa a lógica de execucao
	 * da instrucao em tela.
	 * 
	 * @author patrick cunha
	 * */
	private void executaConsulta() {
		try {
			XQExpressionSample xqex = new XQExpressionSample();
			long inicio = System.nanoTime();
			List<String> resultado = null;
			if(textArea.getSelectedText() != null) {
				resultado = xqex.consultar(textArea.getSelectedText());
			} else {
				resultado = xqex.consultar(textArea.getText());
			}
			long fim = System.nanoTime();
			if (!resultado.isEmpty()) {
				for (String string : resultado) {
					textArea_1.append(string + "\n");
				}
				textArea_1.append("Consulta retornada em: "
						+ TimeUnit.MICROSECONDS.convert((fim - inicio),
								TimeUnit.NANOSECONDS) + "ms.\n\n");
			} else {
				textArea_1.append("Nenhum resultado!\n\n");
			}
		} catch (XQException e) {
			textArea_1
					.append("Erro ao realizar consulta, verifique a xquery informada!\n");
			textArea_1.append(e.getMessage() + "\n\n");
		} catch (Exception e) {
			textArea_1
					.append("Erro ao realizar consulta, verifique a xquery informada!\n");
			textArea_1.append(e.getMessage() + e.getCause() + "\n\n");
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					XQueryBuilder frame = new XQueryBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
