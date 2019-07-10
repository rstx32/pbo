import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class TA extends JFrame {
	// data member //
	// layouting frame 
	private GridBagConstraints grid;
	// meletakan objek
	private DrawCanvas canvas;
	// mengeset ukuran kanvas
	private static final int CANVAS_WIDTH = 800;	// lebar canvas
	private static final int CANVAS_HEIGHT = 480;	// tinggi canvas
	// mengeset ukuran objek
	private int PANJANG1 = 40;	// panjang objek satu
	private int LEBAR1 = 40;	// lebar objek satu
	private int PANJANG2 = 40;	// panjang objek dua
	private int LEBAR2 = 40;	// lebar objek dua
	// button
	private JButton keatas, kebawah, kekanan, kekiri, kiriAtas, kiriBawah, kananAtas, kananBawah, flipV, flipH, fadeIn,
			fadeOut, gantiWarnaObjek, gantiWarnaBackground, objek1, duplikatObjek, kotak, lingkaran, ppanjang;
	// label
	private JLabel labelX1, labelY1, labelX2, labelY2, infoPilihObjek;
	// panel
	private JPanel btnPanel1, btnPanel2, btnPanel3, btnPanel4, infopanel, panelWarna, pilihObjek, gantiShape, panelCanvas;
	// textfield
	private JTextField tfx1, tfy1, tfx2, tfy2, tfObjek;
	// mengeset posisi awal objek agar di tengah
	// mengeset point x&y tidak di tengah, namun geser ke kiri atas supaya objek
	// berada tepat di tengah
	// karena kalau point di tengah maka objek menjadi geser ke kanan bawah sesuai
	// dengan point yaitu titik tengah
	private int y1 = (CANVAS_HEIGHT / 2) - (LEBAR1 / 2);
	private int x1 = (CANVAS_WIDTH / 2) - (PANJANG1 / 2);
	private int y2 = y1;
	private int x2 = x1 + 100;	// +100 untuk duplikat objek
	// default value warna untuk objek dan background
	private Color warnaObjek1 = Color.RED, warnaObjek2 = Color.BLUE;
	private Color warnaBackground = Color.BLACK;
	// boolean untuk memilih objek dan memulai program
	private boolean objekSatu, objekDua, cekObjek, cekTabrak, cekKotak, cekLingkaran, cekPPanjang;

	// konstruktor
	public TA() {

		// inisiasi
		grid = new GridBagConstraints();	// inisiasi gridbag
		setLayout(new GridBagLayout());		// mengeset layout dengan gridbag 
		canvas = new DrawCanvas();			// inisiasi canvas
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));	// mengeset ukuran canvas dengan konstanta
		// inisiasi panel 
		btnPanel1 = new JPanel();
		btnPanel2 = new JPanel();
		btnPanel3 = new JPanel();
		btnPanel4 = new JPanel();
		pilihObjek = new JPanel();
		infopanel = new JPanel();
		panelWarna = new JPanel();
		gantiShape=new JPanel();
		panelCanvas = new JPanel();
		// inisiasi listener
		btnListener btnlistener = new btnListener();
		keyListener keylistener = new keyListener();
		MouseListener mouselistener = new mouselistener();
		// mengeset nilai default untuk keperluan objek
		objekSatu = new Boolean(true);
		objekDua = new Boolean(false);
		cekObjek = new Boolean(false);
		cekTabrak=new Boolean(false);
		cekKotak=new Boolean(true);
		cekLingkaran=new Boolean(false);
		cekPPanjang=new Boolean(false);
		
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
		fadeIn = new JButton("Fade In");
		fadeOut = new JButton("Fade Out");
		objek1 = new JButton("Pilih Objek 1");
		duplikatObjek = new JButton("Duplikat Objek");
		gantiWarnaObjek = new JButton("Ganti Warna Objek");
		gantiWarnaBackground = new JButton("Ganti Warna Background");
		kotak=new JButton("Kotak");
		lingkaran=new JButton("Lingkaran");
		ppanjang=new JButton("Persegi Panjang");
		
		// label
		labelX1 = new JLabel("X1 = ");
		labelY1 = new JLabel("Y1 = ");
		labelX2 = new JLabel("X2 = ");
		labelY2 = new JLabel("Y2 = ");
		infoPilihObjek = new JLabel("Objek saat ini : ");

		// textfield
		// mengeset texfield tidak dapat diubah nilainya 
		tfx1 = new JTextField("        ");
		tfx1.setEditable(false);
		tfy1 = new JTextField("        ");
		tfy1.setEditable(false);
		tfx2 = new JTextField("        ");
		tfx2.setEditable(false);
		tfy2 = new JTextField("        ");
		tfy2.setEditable(false);
		tfObjek = new JTextField("            ");
		// inisiasi textfield objek diset ke objek satu
		tfObjek.setText("Objek 1");
		tfObjek.setEditable(false);

		// menambah button ke dalam panel
		// panel arrow
		btnPanel1.add(keatas);
		btnPanel1.add(kebawah);
		btnPanel1.add(kekanan);
		btnPanel1.add(kekiri);
		// panel flip
		btnPanel2.add(flipV);
		btnPanel2.add(flipH);
		// panel diagonal
		btnPanel3.add(kiriAtas);
		btnPanel3.add(kiriBawah);
		btnPanel3.add(kananAtas);
		btnPanel3.add(kananBawah);
		// panel fade
		btnPanel4.add(fadeIn);
		btnPanel4.add(fadeOut);
		// panel objek
		pilihObjek.add(objek1);
		pilihObjek.add(duplikatObjek);
		// panel ganti warna
		panelWarna.add(gantiWarnaObjek);
		panelWarna.add(gantiWarnaBackground);
		// panel ganti bentuk objek
		gantiShape.add(kotak);
		gantiShape.add(lingkaran);
		gantiShape.add(ppanjang);

		// menambah label dan textfield ke dalam panel bawah untuk menampilkan koordinat
		infopanel.add(labelX1);
		infopanel.add(tfx1);
		infopanel.add(labelY1);
		infopanel.add(tfy1);
		infopanel.add(infoPilihObjek);
		infopanel.add(tfObjek);

		// menambah listener ke dalam button
		// button arrow
		keatas.addActionListener(btnlistener);
		kebawah.addActionListener(btnlistener);
		kekiri.addActionListener(btnlistener);
		kekanan.addActionListener(btnlistener);
		
		// manambah listener ke dalam tombol
		kebawah.addKeyListener(keylistener);
		kekanan.addKeyListener(keylistener);
		kekiri.addKeyListener(keylistener);
		keatas.addKeyListener(keylistener);
		
		
		// button diagonal
		kiriAtas.addActionListener(btnlistener);
		kananAtas.addActionListener(btnlistener);
		kiriBawah.addActionListener(btnlistener);
		kananBawah.addActionListener(btnlistener);

		// button flip
		flipV.addActionListener(btnlistener);
		flipH.addActionListener(btnlistener);

		// button zoom
		fadeIn.addActionListener(btnlistener);
		fadeOut.addActionListener(btnlistener);

		// button Duplikat Objek dan pilih objek
		objek1.addActionListener(btnlistener);
		duplikatObjek.addActionListener(btnlistener);

		// button gantiwarna
		gantiWarnaObjek.addActionListener(btnlistener);
		gantiWarnaBackground.addActionListener(btnlistener);
		
		//button ganti shape
		kotak.addActionListener(btnlistener);
		lingkaran.addActionListener(btnlistener);
		ppanjang.addActionListener(btnlistener);
		
		// menambahkan mouse listener menggunakan mouse adapter
		addMouseListener(mouselistener);

		// menambah panel ke dalam layout 
		// panel satu 
		grid.gridx = 0;
		grid.gridy = 1;
		add(btnPanel1, grid);

		// panel dua
		grid.gridx = 0;
		grid.gridy = 2;
		add(btnPanel2, grid);

		// panel tiga
		grid.gridx = 0;
		grid.gridy = 3;
		add(btnPanel3, grid);
	
		// panel empat 
		grid.gridx = 0;
		grid.gridy = 4;
		add(btnPanel4, grid);

		// posisi sebelah kanan 
		// pilih objek
		grid.gridx = 1;	
		grid.gridy = 1;
		add(pilihObjek, grid);

		// panel warna
		grid.gridx = 1;
		grid.gridy = 2;
		add(panelWarna, grid);
		
		// panel ganti bentuk objek
		grid.gridx = 1;
		grid.gridy = 3;
		add(gantiShape, grid);
		
		// panel info posisi objek
		grid.gridx = 1;
		grid.gridy = 4;
		add(infopanel, grid);
		
		// panel canvas berada di atas
		// span agar dua kolom
		panelCanvas.add(canvas);
		grid.gridx = 0;
		grid.gridy = 0;
		grid.gridwidth = 2;
		add(panelCanvas, grid);


		// mengeset JFrame
		pack();						// mengemas JFrame 
		setResizable(false);		// mengeset window tidak dapat di resize
		setTitle("Moving Object");	// mengeset judul program
		setLocationRelativeTo(null);
		setVisible(true);			// menampilkan konten
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// menambah default operasi exit 
	}

	// class listener untuk button dengan mengimplementasi actionlistener
	private class btnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// variabel string untuk menampung action command
			String baca = e.getActionCommand();
			// jika string adalah ke atas
			if (baca.equals("Keatas")) {
				// jika cek y atas bernilai true
				if(cekYatas()) {
					// jika objek satu terpilih
					if (objekSatu==true) {
						// y1 -=4
						y1 -= 4;
						// jika tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek 1 y bernilai lebih besar dari objek 2 y
							if(y1>y2) 
								y2-=4;
							else		// jika tidak maka false 
								cekTabrak=false;
						}
					}
					// jika objek dua terpilih
					else if(objekDua==true) {
						y2 -= 4;
						// jika tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek 1 y bernilai lebih kecil dari objek 2 y
							if(y1<y2) 
								y1-=4;
							else 		// jika tidak maka false
								cekTabrak=false;
						}
					}
				}
				// jika string adalah ke bawah
			} else if (baca.equals("Kebawah")) {
				if(cekYbawah()) {
					// jika objek satu terpilih
					if (objekSatu==true) {
						y1 += 4;
						// jika tabrak bernilai true 
						if(cekTabrak==true) {
							// jika objek 1 y  bernilai lebih besar dari objek 2 y
							if(y1<y2)
								y2+=4;
							else 		// jika tidak maka false
								cekTabrak=false;
						}
					}
					// jika objek dua terpilih
					else if(objekDua==true) {
						y2 += 4;
						// jika tabrak bernilai benar
						if(cekTabrak==true) {
							// jika objek 1 y bernilai lebih kecil dari objek 2 y
							if(y1>y2)
								y1+=4;
							else 		// jika tidak maka false
								cekTabrak=false;
						}
						
					}
				}
				// jika string adalah ke kanan 
			} else if (baca.equals("Kekanan")) {
				if(cekXkanan()) {
					// jika objek satu terpilih
					if (objekSatu==true) {
						x1 += 4;
						// jika tabrak bernilai benar
						if(cekTabrak==true) {
							// jika objek 1 x bernilai lebih besar dari objek 2 x
							if(x1<x2)
								x2+=4;
							else 		// jika tidak maka false
								cekTabrak=false;
						}
					}
					// jika objek dua terpilih
					else if(objekDua==true) {
						x2 += 4;
						// jika tabrak bernilai benar
						if(cekTabrak==true) {
							// jika objek 1 x bernilai lebih kecil dari objek 2 x
							if(x1>x2)
								x1+=4;
							else 		// jika tidak maka false
								cekTabrak=false;
						}
					}
				}
				// jika string adalah ke kiri
			} else if (baca.equals("Kekiri")) {
				if(cekXkiri()) {
					// jika objek satu terpilih
					if (objekSatu==true) {
						x1 -= 4;
						// jika tabrak bernilai benar
						if(cekTabrak==true) {
							// jika objek 1 x bernilai lebih besar dari objek 2 x
							if(x1>x2)
								x2-=4;
							else 		// jika tidak maka false
								cekTabrak=false;
						}
					}
					// jika objek dua terpilih
					else if(objekDua==true) {
						x2 -= 4;
						// jika tabrak bernilai benar
						if(cekTabrak==true) {
							// // jika objek 1 x bernilai lebih kecil dari objek 2 x
							if(x1<x2)
								x1-=4;
							else 		// jika tidak maka false
								cekTabrak=false;
						}
					}
				}
			} else if (baca.equals("Kiri Atas")) { // jika kiri atas
				// jika cek X kiri dan cek Y atas bernilai true
				if (cekXkiri() && cekYatas()) {
					// jika objek satu terpilih
					if (objekSatu == true) {
						x1 -= 4;
						y1 -= 4;
					} else if (objekDua == true) { // jika objek dua terpilih
						x2 -= 4;
						y2 -= 4;
					}
				}
			} else if (baca.equals("Kanan Atas")) { // jika kanan atas
				// jika cek Y atas dan cek X kanan bernilai true
				if (cekYatas() && cekXkanan()) {
					// jika objek satu terpilih
					if (objekSatu == true) {
						x1 += 4;
						y1 -= 4;
					} else if (objekDua == true) { // jika objek dua terpilih
						x2 += 4;
						y2 -= 4;
					}
				}
			} else if (baca.equals("Kiri Bawah")) {  // jika kiri bawah
				// jika cek X kiri dan cek Y bawah bernilai true
				if (cekXkiri() && cekYbawah()) {
					// jika objek satu terpilih
					if (objekSatu == true) {
						x1 -= 4;
						y1 += 4;
					} else if (objekDua == true) { // jika objek dua terpilih
						x2 -= 4;
						y2 += 4;
					}
				}
			} else if (baca.equals("Kanan Bawah")) { // jika kanan bawah
				// jika cek X kanan dan cek Y bawah bernilai true
				if (cekXkanan() && cekYbawah()) {
					// jika objek satu terpilih
					if (objekSatu == true) {
						x1 += 4;
						y1 += 4;
					} else if (objekDua == true) { // jika objek dua terpilih
						x2 += 4;
						y2 += 4;
					}
				}
			} else if (baca.equals("Flip Horizontal")) { // jika flip horizontal
				// jika objek satu bernilai true
				if (objekSatu == true) {
					int jarakX = CANVAS_WIDTH / 2 - x1;				// mengeset variabel integer yang berisi jarak ke sumbu X dikurangi X1
					x1 = (CANVAS_WIDTH / 2 + jarakX) - PANJANG1;	// mengeset objek dengan rumus di samping
				} else if (objekDua == true) { // jika objek dua bernilai true
					int jarakX = CANVAS_WIDTH / 2 - x2;				// mengeset variabel integer yang berisi jarak ke sumbu X dikurangi X2
					x2 = (CANVAS_WIDTH / 2 + jarakX) - PANJANG2;	// mengeset objek dengan rumus di samping
				}
			} else if (baca.equals("Flip Vertikal")) { // jika flip vertikal
				// jika objek satu bernilai true
				if (objekSatu == true) {
					int jarakY = CANVAS_HEIGHT / 2 - y1;			// mengeset variabel integer yang berisi jarak ke sumbu Y dikurangi Y1
					y1 = (CANVAS_HEIGHT / 2 + jarakY) - LEBAR1;		// mengeset objek dengan rumus di samping
				} else if (objekDua == true) { // jika objek dua bernilai true
					int jarakY = CANVAS_HEIGHT / 2 - y2;			// mengeset variabel integer yang berisi jarak ke sumbu Y dikurangi Y2
					y2 = (CANVAS_HEIGHT / 2 + jarakY) - LEBAR2;		// mengeset objek dengan rumus di samping
				}
			} else if (baca.equals("Fade In")) { // jika fade in
				// jika objek satu bernilai true
				if (objekSatu == true) {
					PANJANG1 += 4; 	// menambah nilai panjang
					LEBAR1 += 4;	// menambah nilai lebar
					x1 -= 2; 		// set agar fade bergerak dari semua sisi
					y1 -= 2; 		// -^
				} else if (objekDua == true) { // jika objek dua bernilai true
					PANJANG2 += 4;	// menambah nilai panjang
					LEBAR2 += 4;	// menambah nilai lebar
					x2 -= 2; 		// set agar fade bergerak dari semua sisi
					y2 -= 2; 		// -^
				}
			} else if (baca.equals("Fade Out")) { // jika fade out
				// jika objek satu bernilai true
				if (objekSatu == true) {
					PANJANG1 -= 4;	// menambah nilai panjang
					LEBAR1 -= 4;	// menambah nilai lebar
					x1 += 2; 		// set agar fade bergerak dari semua sisi
					y1 += 2; 		// -^
				} else if (objekDua == true) { // jika objek dua bernilai true
					PANJANG2 -= 4;	// menambah nilai panjang
					LEBAR2 -= 4;	// menambah nilai lebar
					x2 += 2; 		// set agar fade bergerak dari semua sisi
					y2 += 2; 		// -^
				}

			} else if (baca.equals("Ganti Warna Objek")) { // jika ganti warna objek
				// jika objek satu bernilai true
				if (objekSatu == true) {
					// mengeset variabel color lalu menampilkan dialog memilih warna objek 1
					Color color = JColorChooser.showDialog(TA.this, "Pilih Warna Objek 1", warnaObjek1);
					// jika warna tidak kosong
					if (color != null) {
						warnaObjek1 = color; // set warna objek sesuai variabel color
					}
				} else if (objekDua == true) { // jika objek 2 bernilai true
					// mengeset variabel color lalu menampilkan dialog memilih warna objek 2
					Color color = JColorChooser.showDialog(TA.this, "Pilih Warna Objek 2", warnaObjek2);
					// jika warna tidak kosong
					if (color != null) {
						warnaObjek2 = color; // set warna objek sesuai variabel color
					}
				}
			} else if (baca.equals("Ganti Warna Background")) { // jika ganti warna background
				// mengeset variabel color lalu menampilkan dialog memilih warna background
				Color color = JColorChooser.showDialog(TA.this, "Pilih Warna", warnaBackground);
				// jika warna tidak kosong
				if (color != null) {
					warnaBackground = color; // set warna objek sesuai variabel color
				}
			} else if (baca.equals("Pilih Objek 1")) {	// jika pilih objek 1
				objekSatu = true;			// objek 1 bernilai true
				objekDua = false;			// objek 2 bernilai false
				tfObjek.setText("Objek 1");	// mengeset text field dengan string objek 1
			} else if (baca.equals("Pilih Objek 2")) { // jika pilih objek 2
				objekDua = true;			// objek dua bernilai true
				objekSatu = false;			// objek satu bernilai false
				tfObjek.setText("Objek 2");	// mengeset text field dengan string objek 2
			} else if (baca.equals("Duplikat Objek")) { // jika duplikat objek
				objekSatu = false; 			// objek satu bernilai false
				objekDua = true;			// objek dua bernilai true
				cekObjek = true;			// cek objek bernilai true
				duplikatObjek.setText("Pilih Objek 2");	// menganti string duplikat objek dengan string pilih objek 2
				tfObjek.setText("Objek 2");				// mengeset text field dengan string objek 2
				// menambah informasi label dan textfield objek ke-2 ketika button di klik
				infopanel.add(labelX2);
				infopanel.add(tfx2);
				infopanel.add(labelY2);
				infopanel.add(tfy2);
			}else if(baca.equals("Kotak")) { // jika memilih kotak
				cekKotak=true;			// cek kotak bernilai true
				cekLingkaran=false;		// cek lingkaran bernilai false
				cekPPanjang=false;		// cek persegi panjang bernilai false
			}else if(baca.equals("Lingkaran")) { // jika memilih lingkaran
				cekLingkaran=true;		// cek lingkaran bernilai true
				cekKotak=false;			// cek kotak bernilai false
				cekPPanjang=false;		// cek persegi panjang bernilai false
			}else if(baca.equals("Persegi Panjang")) { // jika memilih persegi panjang
				cekPPanjang=true;		// cek persegi panjang bernilai true
				cekKotak=false;			// cek kotak bernilai false
				cekLingkaran=false;		// cek lingkarang bernilai false
			}
			execute(); // memanggil method execute
		}
	}

	// class keyListener sebagai listener keyboard
	private class keyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}
		// mengoverride method tombol keyboard ditekan
		@Override
		public void keyPressed(KeyEvent e) {
			// memasukkan kode tombol keyboard yang bernilai integer key dalam variabel
			int key = e.getKeyCode();
			// jika key adalah tombol angka 1
			if(key==KeyEvent.VK_1) {
				objekSatu=true;		// objek satu bernilai true
				objekDua=false;		// objek dua bernilai false
				cekTabrak=false;	// cek tabrak bernilai false
			} else if(key ==KeyEvent.VK_2) { // jika key adalah tombol angka 2
				objekDua=true;		// objek dua bernilai true
				objekSatu=false;	// objek satu bernilai false
				cekTabrak=false;	// cek tabrak bernilai false
			} else if (key == KeyEvent.VK_UP) { // jika key adalah arrow up
				// jika cek Y atas bernilai true
				if (cekYatas()) {
					// jika objek satu terpilih
					if (objekSatu==true) {
						y1 -= 4;
						// jika cek tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek satu Y lebih besar dari objek dua Y
							if(y1>y2) 
								y2-=4;
							else // jika tidak cek tabrak bernilai false
								cekTabrak=false;
						}
					}
					else if(objekDua==true) { // jika objek dua terpilih
						y2 -= 4;
						// jika cek tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek satu Y lebih kecil dari objek dua Y
							if(y1<y2) 
								y1-=4;
							else // jika tidak cek tabrak bernilai false
								cekTabrak=false;
						}
					}
				}
			} else if (key == KeyEvent.VK_DOWN) { // jika key adalah arrow down
				// jika cek Y bawah bernilai true
				if (cekYbawah()) {
					// jika objek satu terpilih
					if (objekSatu==true) {
						y1 += 4;
						// jika cek tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek satu Y lebih kecil dari objek dua Y
							if(y1<y2) 
								y2+=4;
							else // jika tidak cek tabrak bernilai false
								cekTabrak=false;
						}
					}
					else if(objekDua==true) { // jika objek dua terpilih
						y2 += 4;
						// jika cek tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek satu Y lebih besar dari objek dua Y
							if(y1>y2)
								y1+=4;
							else // jika tidak cek tabrak bernilai false
								cekTabrak=false;
						}
					}
				}
			} else if (key == KeyEvent.VK_RIGHT) { // jika key adalah arrow kanan
				// jika cek X kanan bernilai true
				if (cekXkanan()) {	
					// jika objek satu terpilih
					if (objekSatu==true) {
						x1 += 4;
						// jika cek tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek satu X lebih kecil dari objek dua X
							if(x1<x2)
								x2+=4;
							else // jika tidak cek tabrak bernilai false
								cekTabrak=false;
						}
					}
					else if(objekDua==true) { // jika objek dua terpilih
						x2 += 4;
						// jika cek tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek satu X lebih besar dari objek dua X
							if(x1>x2)
								x1+=4;
							else // jika tidak cek tabrak bernilai false
								cekTabrak=false;
						}
					}
				}
			} else if (key == KeyEvent.VK_LEFT) { // jika key adalah arrow kiri
				// jika cek X kiri bernilai true
				if (cekXkiri()) {
					// jika objek satu terpilih
					if (objekSatu==true) {
						x1 -= 4;
						// jika cek tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek satu X lebih besar dari objek dua X
							if(x1>x2)
								x2-=4;
							else // jika tidak cek tabrak bernilai false
								cekTabrak=false;
						}
					}
					else if(objekDua==true) { // jika objek dua terpilih
						x2 -= 4;
						// jika cek tabrak bernilai true
						if(cekTabrak==true) {
							// jika objek satu X lebih kecil dari objek dua X
							if(x1<x2)
								x1-=4;
							else // jika tidak cek tabrak bernilai false
								cekTabrak=false;
						}
					}
				} 
			} else if (key == KeyEvent.VK_Q) { // key listener diagonal kiri atas
				// cek Y atas dan cek X kiri bernilai true
				if (cekYatas() && cekXkiri()) {
					// jika objek satu terpilih
					if (objekSatu == true) {
						x1 -= 4;
						y1 -= 4;
					} else if (objekDua == true) { // jika objek dua terpilih
						x2 -= 4;
						y2 -= 4;
					}
				}
			} else if (key == KeyEvent.VK_W) { // key listener diagonal kanan atas
				// cek Y atas dan cek X kiri bernilai true
				if (cekYatas() && cekXkanan()) {
					// jika objek satu terpilih
					if (objekSatu == true) {
						x1 += 4;
						y1 -= 4;
					} else if (objekDua == true) { // jika objek dua terpilih
						x2 += 4;
						y2 -= 4;
					}
				}
			} else if (key == KeyEvent.VK_A) { // key listener diagonal kiri bawah
				// jika objek satu terpilih
				if (cekYbawah() && cekXkiri()) {
					// jika objek satu terpilih
					if (objekSatu == true) {
						x1 -= 4;
						y1 += 4;
					} else if (objekDua == true) { // jika objek dua terpilih
						x2 -= 4;
						y2 += 4;
					}
				}
			} else if (key == KeyEvent.VK_S) { // key listener diagonal kanan bawah
				// jika objek satu terpilih
				if (cekYbawah() && cekXkanan()) {
					// jika objek satu terpilih
					if (objekSatu == true) {
						x1 += 4;
						y1 += 4;
					} else if (objekDua == true) { // jika objek dua terpilih
						x2 += 4;
						y2 += 4;
					}
				}
			}
			execute(); // memanggil method execute
		}

		@Override
		public void keyReleased(KeyEvent e) {}

	}

	// class mouselistener
	private class mouselistener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			// variabel point untuk menampung point X dan Y pada mouse
			Point posisi = e.getPoint();
			// memberi kondisi jika posisi kursor tetap berada di dalam kanvas
			if(posisi.x<CANVAS_WIDTH-PANJANG1 && posisi.y<CANVAS_HEIGHT-LEBAR1) {
				// jika objek satu terpilih
				if (objekSatu == true) {
					x1 = e.getX() - 25;
					y1 = e.getY() - 50;
				} else if (objekDua == true) { // jika objek dua terpilih
					x2 = e.getX() - 25;
					y2 = e.getY() - 50;
				}
			}else {
				
			}
			execute(); // memanggil method execute
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
	}

	// class canvas sebagai tempat menaruh objek
	public class DrawCanvas extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// mengeset warna background 
			setBackground(warnaBackground);
			//shape akan dijadikan objek dahulu karena kedepannya akan menggunakan fungsi intersect
			//intersect digunakan untuk mendeteksi collision (pergesekan antar objek)
			if(cekKotak==true) {
				// inisiasi objek
				Rectangle kotak1=new Rectangle(x1, y1, LEBAR1,PANJANG1);
				g.setColor(warnaObjek1);
				g.fillRect(kotak1.x, kotak1.y, kotak1.width, kotak1.height);
				if(cekObjek==true) {
					Rectangle kotak2=new Rectangle(x2, y2, LEBAR2, PANJANG2);
					g.setColor(warnaObjek2);
					g.fillRect(kotak2.x, kotak2.y, kotak2.width, kotak2.height);
					if(kotak1.intersects(kotak2)) {
						cekTabrak=true;
					}
				}
			}else if(cekLingkaran==true) {
				Graphics2D g2d=(Graphics2D)g;
				Ellipse2D.Double lingkaran1=new Ellipse2D.Double(x1,y1,LEBAR1,PANJANG1);
				g2d.setColor(warnaObjek1);
				g2d.fill(lingkaran1);
				if(cekObjek==true) {
					Ellipse2D.Double lingkaran2=new Ellipse2D.Double(x2,y2,LEBAR2,PANJANG2);
					g2d.setColor(warnaObjek2);
					g2d.fill(lingkaran2);
					if(lingkaran2.intersects(lingkaran1.x, lingkaran1.y, lingkaran1.width, lingkaran1.height)) {
						cekTabrak=true;
					}
				}
			}else if(cekPPanjang==true) {
				Rectangle ppanjang1=new Rectangle(x1-15, y1, LEBAR1+30,PANJANG1);
				g.setColor(warnaObjek1);
				g.fillRect(ppanjang1.x, ppanjang1.y, ppanjang1.width, ppanjang1.height);
				if(cekObjek==true) {
					Rectangle ppanjang2=new Rectangle(x2-15, y2, LEBAR2+30, PANJANG2);
					g.setColor(warnaObjek2);
					g.fillRect(ppanjang2.x, ppanjang2.y, ppanjang2.width, ppanjang2.height);
					if(ppanjang1.intersects(ppanjang2)) {
						cekTabrak=true;
					}
				}
			}
			
			g.setColor(Color.GREEN);
			g.drawLine(CANVAS_WIDTH/2, 0, CANVAS_WIDTH/2, CANVAS_HEIGHT);
			g.drawLine(0, CANVAS_HEIGHT/2, CANVAS_WIDTH, CANVAS_HEIGHT/2);
			
		}
	}

	// program utama
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				TA apps = new TA();
			}
		});
	}

	// mengecek supaya tidak keluar dari canvas
	public boolean cekYatas() {
		return (y1 > 0 && y2 > 0);
	}

	public boolean cekYbawah() {
		return (y1 < CANVAS_HEIGHT - PANJANG1 && y2 < CANVAS_HEIGHT - PANJANG2);
	}

	public boolean cekXkiri() {
		return (x1 > 0 && x2 > 0);
	}

	public boolean cekXkanan() {
		return (x1 < CANVAS_WIDTH - LEBAR1 && x2 < CANVAS_WIDTH - LEBAR2);
	}

	// mengeset textfield x & y dan repaint
	public void execute() {
		tfx1.setText(x1 + "");
		tfy1.setText(y1 + "");
		tfx2.setText(x2 + "");
		tfy2.setText(y2 + "");
		repaint();
	}
}