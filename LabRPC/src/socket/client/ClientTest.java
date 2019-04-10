package socket.client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class ClientTest extends JFrame {

	/**
	 * 
	 */
	
	public ClientTest() {
		Container panel = getContentPane();
		
		final JTextField q0 = new JTextField(5);
		final JTextField qf = new JTextField(5);
		final JTextField t = new JTextField(5);		
		JButton ok = new JButton("Segredo de Robertina");
		//Labels
		final JLabel q0Label = new JLabel("Q0:");
		final JLabel qfLabel = new JLabel("QF:");
		final JLabel timeLabel = new JLabel("Tempo:");
		final JLabel ipLabel = new JLabel("IP:");
		
		final JTextArea area = new JTextArea(10,30);
		
		final JTextField ip = new JTextField(7);
		
		
		panel.setLayout(new FlowLayout());
		panel.add(q0Label);
		panel.add(q0);
		panel.add(qfLabel);
		panel.add(qf);
		panel.add(timeLabel);
		panel.add(t);
		panel.add(ok);
		panel.add(area);
		panel.add(ipLabel);
		panel.add(ip);
		ip.setText("192.168.43.47");
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String qtdInicial = q0.getText();
					qtdInicial = qtdInicial.replace(',', '.'); //Para tratar vírgulas
					String qtdFinal = qf.getText();
					qtdFinal = qtdFinal.replace(',', '.');
					String tempo = t.getText();
					tempo = tempo.replace(',', '.');
					
					double i;
					i = new ClientSocket().segredoDoSucesso(Double.parseDouble(qtdInicial), Double.parseDouble(qtdFinal), Integer.parseInt(tempo), ip.getText());
					
					//Arredondar valor					
					BigDecimal bd = new BigDecimal(i).setScale(5,RoundingMode.HALF_EVEN);					
					if(i != -1){
						area.append("\n\nTaxa de juros do Segredo de Robertina: \nMontante Inicial: " + qtdInicial + "\nMontante Final: " + qtdFinal + "\nTempo de Aplicação: " + tempo + " anos\nTaxa de Juros: " + bd.doubleValue() + "%");
					}
					
					else {
						area.append("\n\nAlgo deu errado :/");
					}
				} catch (NumberFormatException | IOException e1) {
					
					area.append("\n\n Por favor, digite esse negocio direito");
					e1.printStackTrace();
				}
				
				
			}
		});
	}
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		ClientTest frame = new ClientTest();
		frame.setSize(400, 300);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		

	}

}
