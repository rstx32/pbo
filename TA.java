import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TA extends JFrame {
	// data member //
	private GridBagConstraints grid;
	private DrawCanvas canvas;
	// mengeset ukuran kanvas
	private static final int CANVAS_WIDTH = 640;
	private static final int CANVAS_HEIGHT = 480;
	// mengeset ukuran objek
	private int PANJANG = 40;
	private int LEBAR = 40;
	// button
	private JButton keatas, kebawah, kekanan, kekiri, kiriAtas, kiriBawah, kananAtas, kananBawah,
					flipV, flipH, zoomIn, zoomOut, gantiWarnaObjek, gantiWarnaBackground;
	// label
	private JLabel labelX, labelY;
	// panel
	private JPanel btnPanel1, btnPanel2, btnPanel3, btnPanel4, infopanel, panelWarna;
	// textfield
	private JTextField tfx, tfy;
	// mengeset posisi awal objek agar di tengah
	// mengeset point x&y tidak di tengah, namun geser ke kiri atas supaya objek berada tepat di tengah
	// karena kalau point di tengah maka objek menjadi geser ke kanan bawah sesuai dengan point yaitu titik tengah 
	private int y = (CANVAS_HEIGHT / 2) - (LEBAR / 2);		
	private int x = (CANVAS_WIDTH / 2) - (PANJANG / 2);
	// default value warna untuk objek dan background
	private Color warnaObjek = Color.RED;
	private Color warnaBackground = Color.BLACK;

	// konstruktor
	public TA() {
		// inisiasi
		grid = new GridBagConstraints();
		setLayout(new GridBagLayout());
		canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		btnPanel1 = new JPanel();
		btnPanel2 = new JPanel();
		btnPanel3 = new JPanel();
		btnPanel4 = new JPanel();
		infopanel = new JPanel();
		panelWarna = new JPanel();
		btnListener btnlistener = new btnListener();
		keyListener keylistener = new keyListener();

		// inisiasi nama button
		keatas = new JButton("Keatas");
		kebawah = new JButton("Kebawah");
		kekanan = new JButton("Kekanan");
		kekiri = new JButton("Kekiri");
		kiriAtas = new JButton("Kiri Atas");
		kiriBawah = new JButton("Kiri Bawah");
		kananAtas = new JButton("Kanan Atas");
		kananBawah = new JButton("Kanan Bawah");
		gantiWarnaObjek = new JButton("Ganti Warna");
		flipV = new JButton("Flip Vertikal");
		flipH = new JButton("Flip Horizontal");
		zoomIn = new JButton("Zoom In");
		zoomOut = new JButton("Zoom Out");
		gantiWarnaObjek = new JButton("Ganti Warna Objek");
		gantiWarnaBackground= new JButton("Ganti Warna Background");
		
		// label
		labelX = new JLabel("X = ");
		labelY = new JLabel("Y = ");
		
		// textfield
		tfx = new JTextField("        ");
		tfx.setEditable(false);
		tfy = new JTextField("        ");
		tfy.setEditable(false);
 
		// menambah button ke dalam panel
		btnPanel1.add(keatas);
		btnPanel1.add(kebawah);
		btnPanel1.add(kekanan);
		btnPanel1.add(kekiri);
		btnPanel2.add(flipV);
		btnPanel2.add(flipH);
		btnPanel3.add(kiriAtas);
		btnPanel3.add(kiriBawah);
		btnPanel3.add(kananAtas);
		btnPanel3.add(kananBawah);	
		btnPanel4.add(zoomIn);
		btnPanel4.add(zoomOut);
		panelWarna.add(gantiWarnaObjek);
		panelWarna.add(gantiWarnaBackground);

		// menambah label dan textfield ke dalam panel bawah untuk menampilkan koordinat
		infopanel.add(labelX);
		infopanel.add(tfx);
		infopanel.add(labelY);
		infopanel.add(tfy);

		// menambah listener ke dalam button
			// button arrow
			keatas.addActionListener(btnlistener);
			keatas.addKeyListener(keylistener);
			kebawah.addActionListener(btnlistener);
			kebawah.addKeyListener(keylistener);
			kekanan.addActionListener(btnlistener);
			kekanan.addKeyListener(keylistener);
			kekiri.addActionListener(btnlistener);
			kekiri.addKeyListener(keylistener);
			
			// button diagonal
			kiriAtas.addActionListener(btnlistener);
			kananAtas.addActionListener(btnlistener);
			kiriBawah.addActionListener(btnlistener);
			kananBawah.addActionListener(btnlistener);
			
			// button flip
			flipV.addActionListener(btnlistener);
			flipH.addActionListener(btnlistener);
			
			// button zoom
			zoomIn.addActionListener(btnlistener);
			zoomOut.addActionListener(btnlistener);
			
			// button gantiwarna
			gantiWarnaObjek.addActionListener(btnlistener);
			gantiWarnaBackground.addActionListener(btnlistener);
		
		// menambahkan panel dan canvas ke dalam gridbaglayout
        grid.gridx = 0;
        grid.gridy = 0;
        add(canvas, grid);
        
        grid.gridx = 0;
        grid.gridy = 1;
        add(btnPanel1, grid);
        
        grid.gridx = 0;
        grid.gridy = 2;
        add(btnPanel2, grid);
        
        grid.gridx = 0;
        grid.gridy = 3;
        add(btnPanel3, grid);
        
        grid.gridx = 0;
        grid.gridy = 4;
        add(btnPanel4, grid);  
        
        grid.gridx = 0;
        grid.gridy = 5;
        add(panelWarna, grid); 
        
        grid.gridx = 0;
        grid.gridy = 6;
        add(infopanel, grid);
        
		// mengeset JFrame
		pack();
		setResizable(false);
		setTitle("Moving Object");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// class listener untuk button dengan mengimplementasi actionlistener
	private class btnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String baca = e.getActionCommand();
			if (baca.equals("Keatas")) {
				y -= 5;
			} else if (baca.equals("Kebawah")) {
				y += 5;
			} else if (baca.equals("Kekanan")) {
				x += 5;
			} else if (baca.equals("Kekiri")) {
				x -= 5;
			} else if (baca.equals("Kiri Atas")) {
				x -= 5;
				y -= 5;
			} else if (baca.equals("Kanan Atas")) {
				x += 5;
				y -= 5;
			} else if (baca.equals("Kiri Bawah")) {
				x -= 5;
				y += 5;
			} else if (baca.equals("Kanan Bawah")) {
				x += 5;
				y += 5;
			} else if (baca.equals("Flip Horizontal")) {
				int jarakX = CANVAS_WIDTH / 2 - x;
				x = (CANVAS_WIDTH / 2 + jarakX) - PANJANG;
			} else if (baca.equals("Flip Vertikal")) {
				int jarakY = CANVAS_HEIGHT / 2 - y;
				y = (CANVAS_HEIGHT / 2 + jarakY) - LEBAR;
			} else if (baca.equals("Zoom In")) {
				PANJANG += 5;
				LEBAR += 5;
			} else if (baca.equals("Zoom Out")) {
				PANJANG -= 5;
				LEBAR -= 5;
			} else if (baca.equals("Ganti Warna Objek")) {
				Color color=JColorChooser.showDialog(TA.this, "Pilih Warna", warnaObjek);
				if(color!=null) {
					warnaObjek=color;
				}
			} else if (baca.equals("Ganti Warna Background")) {
				Color color=JColorChooser.showDialog(TA.this, "Pilih Warna", warnaBackground);
				if(color!=null) {
					warnaBackground=color;
				}
			}
			execute();
		}
	}
	
	// class keyListener sebagai listener keyboard
	private class keyListener implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_UP) {
				if (cekYatas()) {
					y -= 5;
				}
			} else if (key == KeyEvent.VK_DOWN) {
				if (cekYbawah()) {
					y += 5;
				}
			} else if (key == KeyEvent.VK_RIGHT) {
				if (cekXkanan()) {
					x += 5;
				}
			} else if (key == KeyEvent.VK_LEFT) {
				if (cekXkiri()) {
					x -= 5;
				}
			} else if (key == KeyEvent.VK_Q) {		// key listener diagonal menggunakan qwas
				if(cekYatas() && cekXkiri()) {
					x -= 5;
					y -= 5;
				}
			} else if (key == KeyEvent.VK_W) {
				if(cekYatas() && cekXkanan()) {
					x += 5;
					y -= 5;
				}
			} else if (key == KeyEvent.VK_A) {
				if(cekYbawah() && cekXkiri()) {
					x -= 5;
					y += 5;
				}
			} else if (key == KeyEvent.VK_S) {
				if(cekYbawah() && cekXkanan()) {
					x += 5;
					y += 5;
				}
			}
			execute();
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}

	// class canvas sebagai tempat menaruh objek
	private class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			setBackground(warnaBackground);
			g.setColor(warnaObjek);
			g.fillRect(x, y, PANJANG, LEBAR);
			g.drawLine(CANVAS_WIDTH/2, 0, CANVAS_WIDTH/2, CANVAS_HEIGHT);
			g.drawLine(0, CANVAS_HEIGHT/2, CANVAS_WIDTH, CANVAS_HEIGHT/2);
		}
	}

	// program utama
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				TA apps = new TA();
			}
		});
	}
	
	// mengecek supaya tidak keluar dari canvas
	public boolean cekYatas() {
		return (y > 0);
	}

	public boolean cekYbawah() {
		return (y < CANVAS_HEIGHT - 40);
	}

	public boolean cekXkiri() {
		return (x > 0);
	}

	public boolean cekXkanan() {
		return (x < CANVAS_WIDTH - 40);
	}

	// mengeset textfield x & y dan repaint
	public void execute() {
		tfx.setText(x + "");
		tfy.setText(y + "");
		repaint();
	}

}