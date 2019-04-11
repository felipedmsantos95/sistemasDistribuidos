import java.awt.Container;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
		final JTextField field = new JTextField();
        field.setSize(200, 50);
        field.setText("              ");
	Container panelMain = getContentPane();
	Container panel = getContentPane();
	final JTextField consultar = new JTextField(5);
	final JTextField adicionar = new JTextField(5);
	final JTextField alterar = new JTextField(5);	
	final JTextField dolarText = new JTextField(5);
	final JTextField euroText = new JTextField(5);
	JButton add = new JButton("Adicionar");
	final JLabel consultaLabel = new JLabel("Consultar:");
	final JLabel addLabel = new JLabel("Adicionar Cotação:");
	final JLabel dolarLabel = new JLabel("Dolar:");
	final JLabel euroLabel = new JLabel("Euro:");
	final JLabel alteraLabel = new JLabel("Alterar:");
	final JLabel listaLabel = new JLabel("Lista:");
	final JLabel okLabel = new JLabel("OK:");
	DefaultListModel<String>listModel = new DefaultListModel();
	listModel.addElement("Dolar: R$4,00");
	listModel.addElement("Euro: 000");
	listModel.addElement("Libra: 000");
	
	//JComboBox
	
	 JComboBox comboBox = new JComboBox();
     comboBox.setEditable(true);
     comboBox.addItem("Dolar");
     comboBox.addItem("Euro");
     comboBox.addItem("Libra");
	comboBox.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			 JComboBox comboBox = (JComboBox) event.getSource();
			 Object selected = comboBox.getSelectedItem();
             if(selected.toString().equals("Dolar"))
             field.setText("dolar");
             else if(selected.toString().equals("Euro"))
             field.setText("euro");
             else if(selected.toString().equals("Libra"))
                 field.setText("libra");
		}
	});
	

	
	JList list = new JList(listModel);
	panelMain.setLayout(new FlowLayout());
	panel.setLayout(new FlowLayout());
	panel.add(comboBox);
	panel.add(field);
	panel.add(add);
	panel.add(consultaLabel);
	panel.add(list);
	
	//panel.add(consultar);
//	panel.add(addLabel);
//	panel.add(adicionar);
//	panel.add(alteraLabel);
//	panel.add(alterar);
	//panel.add(listaLabel);
	
	}
	
	public static void main(String[] args) {
		 Tela frame = new Tela();
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} 
		});
		

	}
	
	
}

