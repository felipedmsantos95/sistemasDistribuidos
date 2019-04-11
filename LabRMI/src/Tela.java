import java.awt.Container;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Tela extends JFrame{
	
	public Tela(){
	Container panel = getContentPane();
	final JTextField consultar = new JTextField(5);
	final JTextField adicionar = new JTextField(5);
	final JTextField alterar = new JTextField(5);	
	JButton ok = new JButton("OK");
	final JLabel consultaLabel = new JLabel("Consultar:");
	final JLabel addLabel = new JLabel("Adicionar:");
	final JLabel alteraLabel = new JLabel("Alterar:");
	final JLabel listaLabel = new JLabel("Lista:");
	final JLabel okLabel = new JLabel("OK:");
	DefaultListModel<String>listModel = new DefaultListModel();
	listModel.addElement("GEO");
	listModel.addElement("Lipe");
	listModel.addElement("Beacon");
	
	JList list = new JList(listModel);
	panel.setLayout(new FlowLayout());
	panel.add(consultaLabel);
	panel.add(list);
	//panel.add(consultar);
	panel.add(addLabel);
	panel.add(adicionar);
	panel.add(alteraLabel);
	panel.add(alterar);
	//panel.add(listaLabel);
	
	}
	
	public static void main(String[] args) {
		 Tela frame = new Tela();
		frame.setSize(600, 300);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} 
		});
		

	}
}
