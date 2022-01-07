package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mundo.Carro;
import mundo.Empresa;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class InterfazPrincipal extends JFrame {

	private static final String BUSCAR_IMAGEN = "BuscarImagen";

	private JPanel contentPane;
	private JTextField tfModelo1;
	private JTextField tfCilindraje1;
	private JTextField tfPrecio1;
	private JTextField tfAceleracion1;
	private JTextField tfCaballosFuerza1;
	private JTextField tfImage;
	private JList list;
	private Empresa empresa;
	private File imagenFile;
	private JLabel lblImage;
	private JLabel lblbw1;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal frame = new InterfazPrincipal();
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
	public InterfazPrincipal() {

		empresa = new Empresa();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1037, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder("Carro Seleccionado"));
		panel2.setBounds(367, 61, 423, 243);
		contentPane.add(panel2);
		panel2.setLayout(null);

		lblImage = new JLabel("image");
		lblImage.setBounds(23, 26, 375, 194);
		lblImage.setBorder(new LineBorder(Color.BLACK, 1));
		lblImage.setMinimumSize(new Dimension(374, 194));
		// lblImage.setMaximumSize( new Dimension( 140, 100 ) );
		lblImage.setSize(new Dimension(374, 194));

		ImageIcon wallpaper = new ImageIcon("data/default.png");
		Icon icono = new ImageIcon(
				wallpaper.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_DEFAULT));
		lblImage.setIcon(icono);

		panel2.add(lblImage);

		JPanel panel1 = new JPanel();
		panel1.setBorder(new TitledBorder("Datos Carro"));
		panel1.setBounds(23, 61, 326, 243);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Modelo");
		lblNewLabel.setBounds(10, 24, 46, 14);
		panel1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cilindraje");
		lblNewLabel_1.setBounds(10, 61, 69, 14);
		panel1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(10, 97, 46, 14);
		panel1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Aceleraci\u00F3n 0 a 100");
		lblNewLabel_3.setBounds(10, 133, 128, 14);
		panel1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Caballos de fuerza");
		lblNewLabel_4.setBounds(10, 169, 128, 14);
		panel1.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Imagen");
		lblNewLabel_5.setBounds(10, 205, 46, 14);
		panel1.add(lblNewLabel_5);

		tfModelo1 = new JTextField();
		tfModelo1.setBounds(135, 24, 160, 20);
		tfModelo1.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(tfModelo1);
		tfModelo1.setColumns(10);

		tfCilindraje1 = new JTextField();
		tfCilindraje1.setColumns(10);
		tfCilindraje1.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCilindraje1.setBounds(135, 61, 93, 20);
		panel1.add(tfCilindraje1);

		tfPrecio1 = new JTextField();
		tfPrecio1.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPrecio1.setColumns(10);
		tfPrecio1.setBounds(135, 97, 93, 20);
		panel1.add(tfPrecio1);

		tfAceleracion1 = new JTextField();
		tfAceleracion1.setHorizontalAlignment(SwingConstants.RIGHT);
		tfAceleracion1.setColumns(10);
		tfAceleracion1.setBounds(135, 133, 93, 20);
		panel1.add(tfAceleracion1);

		tfCaballosFuerza1 = new JTextField();
		tfCaballosFuerza1.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCaballosFuerza1.setColumns(10);
		tfCaballosFuerza1.setBounds(135, 169, 93, 20);
		panel1.add(tfCaballosFuerza1);

		tfImage = new JTextField();
		tfImage.setEnabled(false);
		tfImage.setBounds(135, 202, 93, 20);
		panel1.add(tfImage);
		tfImage.setColumns(10);

		JButton btnExaminar = new JButton("Examinar");
		btnExaminar.setActionCommand(BUSCAR_IMAGEN);

		btnExaminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fc = new JFileChooser("./data") {

					@Override
					public void approveSelection() {
						if (getSelectedFile().exists()) {
							// System.out.print(getSelectedFile().getPath());

							ImageIcon wallpaper = new ImageIcon(getSelectedFile().getPath());
							Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblImage.getWidth(),
									lblImage.getHeight(), Image.SCALE_DEFAULT));
							lblImage.setIcon(icono);
						}
						super.approveSelection();
					}

				};
				fc.setDialogTitle("Buscar imagen del carro");
				fc.setMultiSelectionEnabled(false);

				int resultado = fc.showOpenDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					imagenFile = fc.getSelectedFile();
					String imagen = fc.getSelectedFile().getAbsolutePath();
					tfImage.setText(imagen);
				}
			}
		});
		btnExaminar.setHorizontalAlignment(SwingConstants.LEFT);
		btnExaminar.setBounds(230, 201, 90, 23);
		panel1.add(btnExaminar);

		panel1.add(lblNewLabel_5);

		JLabel lblNewLabel_4_1 = new JLabel("$");
		lblNewLabel_4_1.setBounds(233, 100, 31, 14);
		panel1.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("Cc");
		lblNewLabel_4_1_1.setBounds(233, 67, 31, 14);
		panel1.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Segundos");
		lblNewLabel_4_1_1_1.setBounds(233, 136, 87, 14);
		panel1.add(lblNewLabel_4_1_1_1);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("");
		lblNewLabel_4_1_1_1_1.setBounds(233, 172, 87, 14);
		panel1.add(lblNewLabel_4_1_1_1_1);

		JPanel panel3 = new JPanel();
		panel3.setBounds(805, 61, 206, 243);

		panel3.setLayout(new BorderLayout());
		panel3.setBorder(new TitledBorder("Carros Registrados"));

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new LineBorder(Color.BLACK, 1)));
		scroll.setViewportView(list);

		panel3.add(scroll, BorderLayout.CENTER);

		contentPane.add(panel3);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.print(tfImage.getText() +","+tfCilindraje1.getText() +","+tfPrecio1.getText() +","+tfAceleracion1.getText() +","+tfCaballosFuerza1.getText() +","+tfModelo1.getText());
				if (tfImage.getText().length() < 5 || tfModelo1.getText() == ""
						|| !tfCilindraje1.getText().matches("\\d+") || tfCilindraje1.getText().equals("")
						|| !tfPrecio1.getText().matches("\\d+(\\.\\d+)?") || tfPrecio1.getText().equals("")
						|| !tfAceleracion1.getText().matches("\\d+(\\.\\d+)?")
						|| tfAceleracion1.getText().equals("") || !tfCaballosFuerza1.getText().matches("\\d+")
						|| tfCaballosFuerza1.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Debes ingresar valores en todos los campos y solamente numeros en los campos que lo requieran",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String nombre = tfModelo1.getText();
					int cilindraje1 = Integer.valueOf(tfCilindraje1.getText());
					double precio1 = Double.valueOf(tfPrecio1.getText());
					double aceleracion1 = Double.valueOf(tfAceleracion1.getText());
					int caballosFuerza1 = Integer.valueOf(tfCaballosFuerza1.getText());
					String imagen = tfImage.getText();
					String path = System.getProperty("user.dir");
					path += "\\data\\";
					path += nombre + ".png";
					imagen = path;

					boolean agregado = empresa.agregarCarro(nombre, cilindraje1, precio1, aceleracion1, caballosFuerza1,
							imagen);
					if (agregado) {
						actualizarLista(empresa.darCarros());
						imagenguardar(nombre);
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar el carro", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					limpiar();
					tfImage.setText("");
				}

			}
		});
		btnAgregar.setBounds(178, 335, 115, 38);
		contentPane.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println(tfCilindraje1.getText());
				System.out.println(tfAceleracion1.getText());
				System.out.println(tfPrecio1.getText());
				System.out.println(tfCaballosFuerza1.getText());

				if (!tfCilindraje1.getText().matches("\\d+")|| tfModelo1.getText().equals("")
						|| !tfPrecio1.getText().matches("\\d+(\\.\\d+)?") || tfPrecio1.getText().equals("")
						|| !tfAceleracion1.getText().matches("\\d+(\\.\\d+)?")
						|| tfAceleracion1.getText().equals("") || !tfCaballosFuerza1.getText().matches("\\d+")
						|| tfCaballosFuerza1.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null,
							"Debes ingresar valores en todos los campos  y solamente numeros en los campos que lo requieran",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {

					String nombre = tfModelo1.getText();
					int cilindraje1 = Integer.valueOf(tfCilindraje1.getText());
					double precio1 = Double.valueOf(tfPrecio1.getText());
					double aceleracion1 = Double.valueOf(tfAceleracion1.getText());
					int caballosFuerza1 = Integer.valueOf(tfCaballosFuerza1.getText());
					String imagen = tfImage.getText();
					String path = System.getProperty("user.dir");
					path += "\\data\\";
					path += nombre + ".png";
					imagen = path;
					try {
						imagenguardar(nombre);
						empresa.ModificarCarro(nombre, cilindraje1, precio1, aceleracion1, caballosFuerza1, imagen);
						actualizarLista(empresa.darCarros());
						limpiar();
						tfImage.setText("");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "No se pudo modificar el carro" , "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnModificar.setBounds(331, 335, 115, 38);
		contentPane.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre = tfModelo1.getText();
				if (nombre.equals("")) {
					JOptionPane.showMessageDialog(null, "No hay un carro seleccionado para borrar", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						if (empresa.buscarCarro(nombre) == null) {
							JOptionPane.showMessageDialog(null, "No es un carro que se pueda borrar", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

						empresa.EliminarCarro(nombre);
						String path = System.getProperty("user.dir");
						path += "\\data\\";
						path += nombre + ".png";
						File myObj = new File(path);
						myObj.delete();

					} catch (Exception e1) {

						JOptionPane.showMessageDialog(null, "No se pudo eliminar el carro" + e, "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					limpiar();
					tfImage.setText("");
				}

			}

		});
		btnEliminar.setBounds(484, 335, 115, 38);
		contentPane.add(btnEliminar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreTemp = JOptionPane.showInputDialog("Escribe el nombre del carro a buscar");

				if (nombreTemp == null || nombreTemp == "") {

				} else {
					Carro carroTemp = empresa.buscarCarro(nombreTemp);
					if (carroTemp == null) {
						JOptionPane.showMessageDialog(null, "No se encontro el carro dado", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						tfModelo1.setText(carroTemp.darNombre());
						tfModelo1.setEnabled(false);
						tfCilindraje1.setText(String.valueOf(carroTemp.darcilindraje()));
						tfPrecio1.setText(String.valueOf(carroTemp.darprecio()));
						tfAceleracion1.setText(String.valueOf(carroTemp.daraceleracion()));
						tfCaballosFuerza1.setText(String.valueOf(carroTemp.darcaballosFuerza()));
						tfImage.setText(carroTemp.darImagen());

						String path1 = System.getProperty("user.dir");
						path1 += "\\data\\";
						path1 += carroTemp.darNombre() + ".png";
						imagenFile = new File(path1);

						System.out.println(path1);
						ImageIcon wallpaper = new ImageIcon(path1);
						Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(lblImage.getWidth(),
								lblImage.getHeight(), Image.SCALE_DEFAULT));
						lblImage.setIcon(icono);
					}

				}
				actualizarLista(empresa.darCarros());
			}
		});
		btnBuscar.setBounds(637, 335, 115, 38);
		contentPane.add(btnBuscar);

		JLabel lblAdministradorDeCarros = new JLabel("Administrador de autos");
		lblAdministradorDeCarros.setForeground(new Color(102, 51, 255));
		lblAdministradorDeCarros.setFont(new Font("Malgun Gothic", Font.BOLD, 28));
		lblAdministradorDeCarros.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministradorDeCarros.setBounds(301, 11, 417, 47);
		contentPane.add(lblAdministradorDeCarros);

		this.setIconImage(Toolkit.getDefaultToolkit().getImage(("data/logo.png")));
		this.setTitle("Administrador de carros");
		lblbw1 = new JLabel("");
		lblbw1.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
		lblbw1.setBounds(0, 0, 1021, 398);
		ImageIcon wallpaperB = new ImageIcon("data/bw.jpg");
		Icon iconoB = new ImageIcon(
				wallpaperB.getImage().getScaledInstance(lblbw1.getWidth(), lblbw1.getHeight(), Image.SCALE_DEFAULT));
		lblbw1.setIcon(iconoB);

		contentPane.add(lblbw1);

		actualizarLista(empresa.darCarros());
	}

	/**
	 * Actualiza la lista de aspirantes que se está mostrando
	 * 
	 * @param listaActualizada Es una lista con los aspirantes que deben mostrarse
	 */
	public void actualizarLista(ArrayList listaActualizada) {
		list.setListData(listaActualizada.toArray());
		
	}

	/**
	 * Limpia los campos
	 */
	public void limpiar() {
		actualizarLista(empresa.darCarros());
		tfModelo1.setText("");
		tfModelo1.setEnabled(true);
		tfCilindraje1.setText("");
		tfPrecio1.setText("");
		tfAceleracion1.setText("");
		tfCaballosFuerza1.setText("");


		System.out.println(Arrays.deepToString(empresa.darCarros().toArray()));
	}

	public void imagenguardar(String nombre) {

		try {

			BufferedImage bi = ImageIO.read(imagenFile);
			File outputfile = new File("data\\" + nombre + ".png");
			ImageIO.write(bi, "png", outputfile);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
