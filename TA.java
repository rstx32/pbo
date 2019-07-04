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
	private int PANJANG1 = 40;
	private int LEBAR1 = 40;
	private int PANJANG2 = 40;
	private int LEBAR2 = 40;
	// button
	private JButton keatas, kebawah, kekanan, kekiri, kiriAtas, kiriBawah, kananAtas, kananBawah, flipV, flipH, fadeIn,
			fadeOut, gantiWarnaObjek, gantiWarnaBackground, objek1, duplikatObjek;
	// label
	private JLabel labelX1, labelY1, labelX2, labelY2, infoPilihObjek;
	// panel
	private JPanel btnPanel1, btnPanel2, btnPanel3, btnPanel4, infopanel, panelWarna, pilihObjek;
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
	private int x2 = x1 + 100;
	// default value warna untuk objek dan background
	private Color warnaObjek1 = Color.RED, warnaObjek2 = Color.BLUE;
	private Color warnaBackground = Color.BLACK;
	// boolean untuk memilih objek dan memulai program
	private boolean objekSatu, objekDua, cekObjek, cekTabrak;

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
		pilihObjek = new JPanel();
		infopanel = new JPanel();
		panelWarna = new JPanel();
		btnListener btnlistener = new btnListener();
		keyListener keylistener = new keyListener();
		MouseListener mouselistener = new mouselistener();
		objekSatu = new Boolean(true);
		objekDua = new Boolean(false);
		cekObjek = new Boolean(false);
		cekTabrak=new Boolean(false);
		
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

		// label
		labelX1 = new JLabel("X1 = ");
		labelY1 = new JLabel("Y1 = ");
		labelX2 = new JLabel("X2 = ");
		labelY2 = new JLabel("Y2 = ");
		infoPilihObjek = new JLabel("Objek saat ini : ");

		// textfield
		tfx1 = new JTextField("        ");
		tfx1.setEditable(false);
		tfy1 = new JTextField("        ");
		tfy1.setEditable(false);
		tfx2 = new JTextField("        ");
		tfx2.setEditable(false);
		tfy2 = new JTextField("        ");
		tfy2.setEditable(false);
		tfObjek = new JTextField("            ");
		tfObjek.setText("Objek 1");
		tfObjek.setEditable(false);

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
		btnPanel4.add(fadeIn);
		btnPanel4.add(fadeOut);
		pilihObjek.add(objek1);
		pilihObjek.add(duplikatObjek);
		panelWarna.add(gantiWarnaObjek);
		panelWarna.add(gantiWarnaBackground);

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
		fadeIn.addActionListener(btnlistener);
		fadeOut.addActionListener(btnlistener);

		// button Duplikat Objek dan pilih objek
		objek1.addActionListener(btnlistener);
		duplikatObjek.addActionListener(btnlistener);

		// button gantiwarna
		gantiWarnaObjek.addActionListener(btnlistener);
		gantiWarnaBackground.addActionListener(btnlistener);

		// menambahkan mouse listener menggunakan mouse adapter
		addMouseListener(mouselistener);

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
		add(pilihObjek, grid);

		grid.gridx = 0;
		grid.gridy = 6;
		add(panelWarna, grid);

		grid.gridx = 0;
		grid.gridy = 7;
		add(infopanel, grid);

		grid.gridx = 0;
		grid.gridy = 7;
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

			String baca = e.getActionCommand();
			if (baca.equals("Keatas")) {
				if(cekYatas()) {
					if (objekSatu==true) {
						y1 -= 5;
						if(cekTabrak==true) {
							if(y1>y2) {
								y2-=5;
							}
							else
								cekTabrak=false;
						}
					}
					else if(objekDua==true) {
						y2 -= 5;
						if(cekTabrak==true) {
							if(y1<y2) {
								y1-=5;
							}
							else 
								cekTabrak=false;
						}
					}
				}
			} else if (baca.equals("Kebawah")) {
				if(cekYbawah()) {
					if (objekSatu==true) {
						y1 += 5;
						if(cekTabrak==true) {
							if(y1<y2) {
								y2+=5;
							}
							else 
								cekTabrak=false;
						}
					}
					else if(objekDua==true) {
						y2 += 5;
						if(cekTabrak==true) {
							if(y1>y2) {
								y1+=5;
							}
							else 
								cekTabrak=false;
						}
						
					}
				}
			} else if (baca.equals("Kekanan")) {
				if(cekXkanan()) {
					if (objekSatu==true) {
						x1 += 5;
						if(cekTabrak==true) {
							if(x1<x2) {
								x2+=5;
							}
							else 
								cekTabrak=false;
						}
					}
					else if(objekDua==true) {
						x2 += 5;
						if(cekTabrak==true) {
							if(x1>x2) {
								x1+=5;
							}
							else 
								cekTabrak=false;
						}
					}
				}
			} else if (baca.equals("Kekiri")) {
				if(cekXkiri()) {
					if (objekSatu==true) {
						x1 -= 5;
						if(cekTabrak==true) {
							if(x1>x2) {
								x2-=5;
							}
							else 
								cekTabrak=false;
						}
					}
					else if(objekDua==true) {
						x2 -= 5;
						if(cekTabrak==true) {
							if(x1<x2) {
								x1-=5;
							}
							else 
								cekTabrak=false;
						}
					}
				}
			} else if (baca.equals("Kiri Atas")) {
				if (cekXkiri() && cekYatas()) {
					if (objekSatu == true) {
						x1 -= 5;
						y1 -= 5;
					} else if (objekDua == true) {
						x2 -= 5;
						y2 -= 5;
					}
				}
			} else if (baca.equals("Kanan Atas")) {
				if (cekYatas() && cekXkanan()) {
					if (objekSatu == true) {
						x1 += 5;
						y1 -= 5;
					} else if (objekDua == true) {
						x2 += 5;
						y2 -= 5;
					}
				}
			} else if (baca.equals("Kiri Bawah")) {
				if (cekXkiri() && cekYbawah()) {
					if (objekSatu == true) {
						x1 -= 5;
						y1 += 5;
					} else if (objekDua == true) {
						x2 -= 5;
						y2 += 5;
					}
				}
			} else if (baca.equals("Kanan Bawah")) {
				if (cekXkanan() && cekYbawah()) {
					if (objekSatu == true) {
						x1 += 5;
						y1 += 5;
					} else if (objekDua == true) {
						x2 += 5;
						y2 += 5;
					}
				}
			} else if (baca.equals("Flip Horizontal")) {
				if (objekSatu == true) {
					int jarakX = CANVAS_WIDTH / 2 - x1;
					x1 = (CANVAS_WIDTH / 2 + jarakX) - PANJANG1;
				} else if (objekDua == true) {
					int jarakX = CANVAS_WIDTH / 2 - x2;
					x2 = (CANVAS_WIDTH / 2 + jarakX) - PANJANG2;
				}
			} else if (baca.equals("Flip Vertikal")) {
				if (objekSatu == true) {
					int jarakY = CANVAS_HEIGHT / 2 - y1;
					y1 = (CANVAS_HEIGHT / 2 + jarakY) - LEBAR1;
				} else if (objekDua == true) {
					int jarakY = CANVAS_HEIGHT / 2 - y2;
					y2 = (CANVAS_HEIGHT / 2 + jarakY) - LEBAR2;
				}
			} else if (baca.equals("Fade In")) {
				if (objekSatu == true) {
					PANJANG1 += 4;
					LEBAR1 += 4;
					x1 -= 2; // set agar fade bergerak dari semua sisi
					y1 -= 2; // -^
				} else if (objekDua == true) {
					PANJANG2 += 4;
					LEBAR2 += 4;
					x2 -= 2; // set agar fade bergerak dari semua sisi
					y2 -= 2; // -^
				}
			} else if (baca.equals("Fade Out")) {
				if (objekSatu == true) {
					PANJANG1 -= 4;
					LEBAR1 -= 4;
					x1 += 2; // set agar fade bergerak dari semua sisi
					y1 += 2; // -^
				} else if (objekDua == true) {
					PANJANG2 -= 4;
					LEBAR2 -= 4;
					x2 += 2; // set agar fade bergerak dari semua sisi
					y2 += 2; // -^
				}

			} else if (baca.equals("Ganti Warna Objek")) {
				if (objekSatu == true) {
					Color color = JColorChooser.showDialog(TA.this, "Pilih Warna Objek 1", warnaObjek1);
					if (color != null) {
						warnaObjek1 = color;
					}
				} else if (objekDua == true) {
					Color color = JColorChooser.showDialog(TA.this, "Pilih Warna Objek 2", warnaObjek2);
					if (color != null) {
						warnaObjek2 = color;
					}
				}
			} else if (baca.equals("Ganti Warna Background")) {
				Color color = JColorChooser.showDialog(TA.this, "Pilih Warna", warnaBackground);
				if (color != null) {
					warnaBackground = color;
				}
			} else if (baca.equals("Pilih Objek 1")) {
				objekSatu = true;
				objekDua = false;
				tfObjek.setText("Objek 1");
			} else if (baca.equals("Pilih Objek 2")) {
				objekDua = true;
				objekSatu = false;
				tfObjek.setText("Objek 2");
			} else if (baca.equals("Duplikat Objek")) {
				objekSatu = false;
				objekDua = true;
				cekObjek = true;
				duplikatObjek.setText("Pilih Objek 2");
				tfObjek.setText("Objek 2");
				// menambah informasi label dan textfield objek ke-2 ketika button di klik
				infopanel.add(labelX2);
				infopanel.add(tfx2);
				infopanel.add(labelY2);
				infopanel.add(tfy2);
			}
			execute();
		}
	}

	// class keyListener sebagai listener keyboard
	private class keyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key==KeyEvent.VK_1) {
				objekSatu=true;
				objekDua=false;
				cekTabrak=false;
			}
			else if(key ==KeyEvent.VK_2) {
				objekDua=true;
				objekSatu=false;
				cekTabrak=false;
			}
			else if (key == KeyEvent.VK_UP) {
				if (cekYatas()) {
					if (objekSatu==true) {
						y1 -= 5;
						if(cekTabrak==true) {
							if(y1>y2) {
								y2-=5;
							}
							else
								cekTabrak=false;
						}
					}
					else if(objekDua==true) {
						y2 -= 5;
						if(cekTabrak==true) {
							if(y1<y2) {
								y1-=5;
							}
							else
								cekTabrak=false;
						}
					}
				}
			} else if (key == KeyEvent.VK_DOWN) {
				if (cekYbawah()) {
					if (objekSatu==true) {
						y1 += 5;
						if(cekTabrak==true) {
							if(y1<y2) {
								y2+=5;
							}
							else
								cekTabrak=false;
						}
					}
					else if(objekDua==true) {
						y2 += 5;
						if(cekTabrak==true) {
							if(y1>y2) {
								y1+=5;
							}
							else
								cekTabrak=false;
						}
					}
				}
			} else if (key == KeyEvent.VK_RIGHT) {
				if (cekXkanan()) {			
					if (objekSatu==true) {
						x1 += 5;
						if(cekTabrak==true) {
							if(x1<x2) {
								x2+=5;
							}
							else
								cekTabrak=false;
						}
					}
					else if(objekDua==true) {
						x2 += 5;
						if(cekTabrak==true) {
							if(x1>x2) {
								x1+=5;
							}
							else
								cekTabrak=false;
						}
					}
				}
			} else if (key == KeyEvent.VK_LEFT) {
				if (cekXkiri()) {
					if (objekSatu==true) {
						x1 -= 5;
						if(cekTabrak==true) {
							if(x1>x2) {
								x2-=5;
							}
							else
								cekTabrak=false;
						}
					}
					else if(objekDua==true) {
						x2 -= 5;
						if(cekTabrak==true) {
							if(x1<x2) {
								x1-=5;
							}
							else
								cekTabrak=false;
						}
					}
				} 
			} else if (key == KeyEvent.VK_Q) { // key listener diagonal kiri atas
				if (cekYatas() && cekXkiri()) {
					if (objekSatu == true) {
						x1 -= 5;
						y1 -= 5;
					} else if (objekDua == true) {
						x2 -= 5;
						y2 -= 5;
					}
				}
			} else if (key == KeyEvent.VK_W) { // key listener diagonal kanan atas
				if (cekYatas() && cekXkanan()) {
					if (objekSatu == true) {
						x1 += 5;
						y1 -= 5;
					} else if (objekDua == true) {
						x2 += 5;
						y2 -= 5;
					}
				}
			} else if (key == KeyEvent.VK_A) { // key listener diagonal kiri bawah
				if (cekYbawah() && cekXkiri()) {
					if (objekSatu == true) {
						x1 -= 5;
						y1 += 5;
					} else if (objekDua == true) {
						x2 -= 5;
						y2 += 5;
					}
				}
			} else if (key == KeyEvent.VK_S) { // key listener diagonal kanan bawah
				if (cekYbawah() && cekXkanan()) {
					if (objekSatu == true) {
						x1 += 5;
						y1 += 5;
					} else if (objekDua == true) {
						x2 += 5;
						y2 += 5;
					}
				}
			}
			execute();
		}

		@Override
		public void keyReleased(KeyEvent e) {}

	}

	// class mouselistener
	private class mouselistener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			Point posisi = e.getPoint();
			// memberi kondisi jika posisi kursor tetap berada di dalam kanvas
			if(posisi.x<CANVAS_WIDTH-PANJANG1 && posisi.y<CANVAS_HEIGHT-LEBAR1) {
				if (objekSatu == true) {
					x1 = e.getX() - 25;
					y1 = e.getY() - 50;
				} else if (objekDua == true) {
					x2 = e.getX() - 25;
					y2 = e.getY() - 50;
				}
			}else {
				
			}
			execute();
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
			setBackground(warnaBackground);
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