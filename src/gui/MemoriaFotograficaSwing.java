package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MemoriaFotograficaSwing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 503113875790453286L;
	private JPanel contentPane;
	private JButton btnIniciar;
	private JLabel lblImagem;
	private JTextField txtLocal;
	
	private static String diretorio = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemoriaFotograficaSwing frame = new MemoriaFotograficaSwing();
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
	public MemoriaFotograficaSwing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		txtLocal = new JTextField();
		txtLocal.setText("Local");
		contentPane.add(txtLocal, BorderLayout.NORTH);
		txtLocal.setColumns(10);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {

					@Override
					protected Void doInBackground() throws Exception {
						List<String> listaDeNomes = new ArrayList<>();
						diretorio = txtLocal.getText();
						File file = new File(diretorio);
						listaDeNomes.addAll(Arrays.asList(file.list()));
						listaDeNomes.forEach(
							nomeDaImagem -> {
								try {
										Thread.sleep( (1 * 1000) ); // 1 segundos.
										{
											for (int i = 0; i < 400; i++) { // Pisca 400 vezes.
												Thread.sleep(70); // Adiciona um fundo branco a cada 7/100 de segundo.
												lblImagem.setIcon(new ImageIcon(MemoriaFotograficaSwing.class.getResource("/assets/fundo_branco.png")));
												Thread.sleep(70); // Adiciona uma imagem a cada 7/100 de segundo.
												//lblImagem.setIcon(new ImageIcon(MemoriaFotograficaSwing.class.getResource(RAIZ+nomeDaImagem)));
												lblImagem.setIcon(new ImageIcon(diretorio+"/"+nomeDaImagem));
												//System.out.println("Caminho: " + diretorio+"/"+nomeDaImagem);
											}
											
										}
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						);
						return null;
					}
					
				};
				worker.execute();
			}
		});
		contentPane.add(btnIniciar, BorderLayout.SOUTH);
		
		lblImagem = new JLabel();
		lblImagem.setText("Imagens");
		contentPane.add(lblImagem, BorderLayout.CENTER);
	}

}
