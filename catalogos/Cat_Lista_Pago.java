package catalogos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import SQL.Connexion;

@SuppressWarnings("serial")
public class Cat_Lista_Pago extends JDialog{
	
	Container cont = getContentPane();
	JLayeredPane campo = new JLayeredPane();
	
	//DECLARACION PARA CREAR UNA TABLA
	DefaultTableModel model = new DefaultTableModel(0,3){
		public boolean isCellEditable(int fila, int columna){
			if(columna < 0)
				return true;
			return false;
		}
	};
	
	JTable tabla = new JTable(model);
	
	String busqueda[] = {"Folio","Nombre Completo","Establecimiento"};
	@SuppressWarnings("unchecked")
	JComboBox cmbBuscar = new JComboBox(busqueda);
	
	JLabel lblImprimir = new JLabel(new ImageIcon("imagen//imprimir-32.png"));
	
	public Cat_Lista_Pago()	{
		this.setTitle("..:: Lista de pago por establecimiento ::..");
		tabla.setFont(new java.awt.Font("Algerian",0,140));

		campo.add(lblImprimir).setBounds(30, 15, 33, 33);
		campo.add(getPanelTabla()).setBounds(30,50,460,GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height-120);
	
		lblImprimir.addMouseListener(OpImprimir);
		
		cont.add(campo);
		this.setModal(true);
		this.setSize(520,GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
//		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
 
	private JScrollPane getPanelTabla()	{	
		Connection conn = new Connexion().conexion();
		
//		cont.setBackground(new java.awt.Color(214,214,214));
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		tabla.getColumnModel().getColumn(1).setCellRenderer(tcr);
		

		// Creamos las columnas.
		tabla.getColumnModel().getColumn(0).setHeaderValue("Nombre Completo");
		tabla.getColumnModel().getColumn(0).setMaxWidth(245);
		tabla.getColumnModel().getColumn(0).setMinWidth(245);
		tabla.getColumnModel().getColumn(1).setHeaderValue("Estab");
		tabla.getColumnModel().getColumn(1).setMaxWidth(55);
		tabla.getColumnModel().getColumn(1).setMinWidth(55);
		tabla.getColumnModel().getColumn(2).setHeaderValue("Firma");
		tabla.getColumnModel().getColumn(2).setMaxWidth(150);
		tabla.getColumnModel().getColumn(2).setMinWidth(150);
		
		TableCellRenderer render = new TableCellRenderer() 
		{ 
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int row, int column) { 
				JLabel lbl = new JLabel(value == null? "": value.toString());
				
				lbl.setFont(new java.awt.Font("",0,9));
		
				if(row%2!=0){
						lbl.setOpaque(true); 
						lbl.setBackground(new java.awt.Color(214,214,214));
				} 
			return lbl; 
			} 
		}; 
						tabla.getColumnModel().getColumn(0).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(1).setCellRenderer(render); 
						tabla.getColumnModel().getColumn(2).setCellRenderer(render);
		Statement s;
		ResultSet rs;
		try {
			s = conn.createStatement();
			rs = s.executeQuery("select tb_empleado.nombre as [Nombre], "+
								 "  tb_empleado.ap_paterno as [Paterno], "+
								 "  tb_empleado.ap_materno as [Materno], "+ 
								 "  tb_establecimiento.nombre as [Establecimiento] "+
								 
								"  from tb_empleado, tb_establecimiento "+

								"  where "+
									"  tb_empleado.establecimiento_id = tb_establecimiento.folio and "+
									"  tb_empleado.status < 3 "+
								" order by Establecimiento asc");
			
			while (rs.next())
			{ 
			   String [] fila = new String[3];
			   fila[0] ="  "+rs.getString(1).trim()+" "+rs.getString(2).trim()+" "+rs.getString(3).trim();
			   fila[1] ="  "+rs.getString(4).trim();
			   fila[2] = "";
 
			   model.addRow(fila); 
			}	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 JScrollPane scrol = new JScrollPane(tabla);
		   
	    return scrol; 
	}
	
	MouseListener OpImprimir = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			MessageFormat encabezado = new MessageFormat("Lista de pago {0,number,integer}");
			try {
//			tabla.print(JTable.PrintMode.FIT_WIDTH, encabezado, null);
			tabla.print(JTable.PrintMode.NORMAL, encabezado, null);
			
			} catch (java.awt.print.PrinterException e1) {
				JOptionPane.showMessageDialog(null, "No se encontro la impresora!","Aviso",JOptionPane.WARNING_MESSAGE);
//				System.err.format("No se puede imprimir %s%n", e1.getMessage());
			}
		}
		public void mouseReleased(MouseEvent e) {}		
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	};
	
	KeyListener validaCantidad = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e){
			char caracter = e.getKeyChar();				
			if(((caracter < '0') ||	
			    	(caracter > '9')) && 
			    	(caracter != '.' )){
			    	e.consume();
			    	}
		}
		@Override
		public void keyReleased(KeyEvent e) {	
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
		}	
	};
	
	KeyListener validaNumericoConPunto = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			char caracter = e.getKeyChar();
			
		    // VERIFICAR SI LA TECLA PULSADA NO ES UN DIGITO
		    if(((caracter < '0') ||	
		    	(caracter > '9')) && 
		    	(caracter != '.')){
		    	e.consume();
		    	}
		    		    		       	
		}
		@Override
		public void keyPressed(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}
								
	};
	
	public static void main(String [] arg){
		new Cat_Lista_Pago().setVisible(true);
	}
}