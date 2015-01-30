package lectorManga;

/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.InputMap;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class Imagen
/*     */ {
/*     */   private JFrame frame;
/*     */   private JLabel labelImagen;
/*     */   private JLabel labelCantidad;
/*     */   private JButton botonAtras;
/*     */   private JButton botonAdelante;
/*     */   private JButton botonbhInicio;
/*     */   private JButton botonbhCargar;
/*     */   private JButton botonbhAFavorito;
/*     */   private JButton botonbhVFavorito;
/*     */   private JButton botonbhAyuda;
/*     */   private JButton botonbhZoomG;
/*     */   private JButton botonbhZoomC;
/*     */   private JPanel panelNorte;
/*     */   private JPanel panelComponentes;
/*     */   private JPanel panelImagen;
/*     */   private JPanel panelContenidoDirectorio;
/*     */   private JScrollPane scroll;
/*     */   private int contZoom;
/*     */   private int x;
/*     */   private int y;
/*     */   private int contador;
/*     */   private int cantImagen;
/*     */   private String url;
/*     */   private JComboBox cbGenero;
/*     */   private JComboBox cbManga;
/*     */   private JComboBox cbTomo;
/*     */   private JMenu menuArchivo;
/*     */   private JMenu menuAyuda;
/*     */   private JMenuBar barraMenu;
/*     */   private JMenuItem itemArSalir;
/*     */   private JMenuItem itemArCargaManual;
/*     */   private JMenuItem itemArCargaAuto;
/*     */   private JMenuItem itemAyAyuda;
/*     */   private JMenuItem itemAyAcerca;
/*  50 */   private static Varios varios = new Varios();
/*     */   private ImageIcon[] verImagen;
/*     */   private JViewport view;
/*     */   private Graphics g;
/*     */   private JToolBar barraHerramientas;
/*     */   private FocusAdapter focusCombo;
/*     */   private ActionListener actionCombo;
/*     */   private static final int INCREASE = 0;
/*     */   private static final int DECREASE = 1;
/*     */   private static final int X_AXIS = 0;
/*     */   private static final int Y_AXIS = 1;
/*     */   private static final int UNIT = 0;
/*     */   private static final int BLOCK = 1;
/*     */ 
/*     */   public Imagen()
/*     */   {
/*  36 */     this.contador = 9999;
/*     */ 
/*  56 */     this.focusCombo = new FocusAdapter() {
/*     */       public void focusLost(FocusEvent evt) {
/*  58 */         Imagen.this.elegirAccion(evt.getSource());
/*     */       }
/*     */     };
/*  61 */     this.actionCombo = new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  63 */         if (e.getModifiers() == 16)
/*  64 */           ((JComboBox)e.getSource()).transferFocus();
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/* 152 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*     */     } catch (ClassNotFoundException e) {
/* 154 */       JOptionPane.showMessageDialog(null, "Clase no encotrada.", "Error", 0);
/*     */     } catch (UnsupportedLookAndFeelException e) {
/* 156 */       JOptionPane.showMessageDialog(null, "Sin soporte grafico.", "Error", 0);
/*     */     } catch (InstantiationException e) {
/* 158 */       JOptionPane.showMessageDialog(null, "Error de Instanciacion.", "Error", 0);
/*     */     } catch (IllegalAccessException e) {
/* 160 */       JOptionPane.showMessageDialog(null, "Acceso Ilegal.", "Error", 0);
/*     */     }
/*     */ 
/* 164 */     new Imagen().run();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/* 173 */     this.frame = new JFrame("Visor de Manga by Rocker13 - Paraguay");
/* 174 */     this.frame.setResizable(false);
/* 175 */     this.frame.getContentPane().setLayout(new BorderLayout());
/*     */ 
/* 180 */     this.barraMenu = new JMenuBar();
/* 181 */     this.barraHerramientas = new JToolBar("Herramientas");
/*     */ 
/* 183 */     this.frame.setJMenuBar(this.barraMenu);
/* 184 */     this.botonbhInicio = new JButton(new ImageIcon(getClass().getResource("/imagenes/inicio.png")));
/* 185 */     this.botonbhInicio.setToolTipText("Inicio");
/* 186 */     this.botonbhCargar = new JButton(new ImageIcon(getClass().getResource("/imagenes/cargar.png")));
/* 187 */     this.botonbhCargar.setToolTipText("Cargar Manualmente");
/* 188 */     this.botonbhVFavorito = new JButton(new ImageIcon(getClass().getResource("/imagenes/favoritos.jpg")));
/* 189 */     this.botonbhVFavorito.setToolTipText("Ver Favoritos");
/* 190 */     this.botonbhAFavorito = new JButton(new ImageIcon(getClass().getResource("/imagenes/favoritos.png")));
/* 191 */     this.botonbhAFavorito.setToolTipText("Agregar a Favoritos");
/* 192 */     this.botonbhZoomG = new JButton(new ImageIcon(getClass().getResource("/imagenes/zoomIn.png")));
/* 193 */     this.botonbhZoomG.setToolTipText("Zoom +");
/* 194 */     this.botonbhZoomC = new JButton(new ImageIcon(getClass().getResource("/imagenes/zoomOut.png")));
/* 195 */     this.botonbhZoomC.setToolTipText("Zoom -");
/* 196 */     this.botonbhAyuda = new JButton(new ImageIcon(getClass().getResource("/imagenes/ayuda.png")));
/* 197 */     this.botonbhAyuda.setToolTipText("Ayuda(Pendiente)");
/*     */ 
/* 199 */     this.menuArchivo = new JMenu("Archivo");
/* 200 */     this.itemArCargaAuto = new JMenuItem("Buscar por genero");
/* 201 */     this.itemArCargaManual = new JMenuItem("Cargar Manualmente");
/* 202 */     this.itemArSalir = new JMenuItem("Salir");
/* 203 */     this.menuArchivo.add(this.itemArCargaManual);
/* 204 */     this.menuArchivo.add(this.itemArCargaAuto);
/* 205 */     this.menuArchivo.add(this.itemArSalir);
/* 206 */     this.menuAyuda = new JMenu("Ayuda");
/* 207 */     this.itemAyAyuda = new JMenuItem("Manual de usuario");
/* 208 */     this.itemAyAcerca = new JMenuItem("Acerca de");
/* 209 */     this.menuAyuda.add(this.itemAyAyuda);
/* 210 */     this.menuAyuda.add(this.itemAyAcerca);
/* 211 */     this.barraMenu.add(this.menuArchivo);
/* 212 */     this.barraMenu.add(this.menuAyuda);
/*     */ 
/* 214 */     this.barraHerramientas.add(this.botonbhInicio);
/* 215 */     this.barraHerramientas.add(this.botonbhCargar);
/* 216 */     this.barraHerramientas.add(this.botonbhAFavorito);
/* 217 */     this.barraHerramientas.add(this.botonbhVFavorito);
/* 218 */     this.barraHerramientas.add(this.botonbhZoomG);
/* 219 */     this.barraHerramientas.add(this.botonbhZoomC);
/* 220 */     this.barraHerramientas.add(this.botonbhAyuda);
/*     */ 
/* 224 */     this.labelCantidad = new JLabel("00/00");
/* 225 */     this.labelCantidad.setHorizontalAlignment(0);
/* 226 */     this.labelImagen = new JLabel();
/* 227 */     this.labelImagen.setHorizontalAlignment(0);
/* 228 */     this.scroll = new JScrollPane(this.labelImagen, 22, 32);
/*     */ 
/* 232 */     this.botonAdelante = new JButton("Siguiente");
/* 233 */     this.botonAtras = new JButton("Anterior");
/*     */ 
/* 237 */     this.cbGenero = new ComboStandar();
/* 238 */     this.cbGenero.addItem("<Genero>");
/* 239 */     this.cbGenero.setPreferredSize(new Dimension(150, 25));
/* 240 */     varios.cargarGenero(this.cbGenero);
/* 241 */     this.cbManga = new ComboStandar();
/* 242 */     this.cbManga.setPreferredSize(new Dimension(227, 25));
/* 243 */     this.cbManga.addItem("<Manga>");
/* 244 */     this.cbTomo = new ComboStandar();
/* 245 */     this.cbTomo.setPreferredSize(new Dimension(100, 25));
/* 246 */     this.cbTomo.addItem("<Tomo>");
/*     */ 
/* 249 */     this.panelNorte = new JPanel();
/* 250 */     this.panelNorte.setLayout(new BorderLayout());
/* 251 */     this.panelComponentes = new JPanel();
/* 252 */     this.panelComponentes.add(this.cbGenero);
/* 253 */     this.panelComponentes.add(this.cbManga);
/* 254 */     this.panelComponentes.add(this.cbTomo);
/* 255 */     this.panelComponentes.add(this.botonAtras);
/* 256 */     this.panelComponentes.add(this.labelCantidad);
/* 257 */     this.panelComponentes.add(this.botonAdelante);
/* 258 */     this.panelComponentes.setBorder(BorderFactory.createTitledBorder("Controles de imagen"));
/* 259 */     this.panelNorte.add(this.barraHerramientas, "North");
/* 260 */     this.panelImagen = new JPanel();
/* 261 */     this.panelImagen.setLayout(new BorderLayout());
/* 262 */     this.panelImagen.setBorder(BorderFactory.createTitledBorder("Visualizacion de la imagen"));
/* 263 */     this.panelImagen.add(this.scroll, "Center");
/* 264 */     this.frame.add(this.panelImagen, "Center");
/* 265 */     this.panelNorte.add(this.panelComponentes, "South");
/* 266 */     this.frame.add(this.panelNorte, "North");
/*     */ 
/* 270 */     this.panelContenidoDirectorio = new JPanel();
/* 271 */     this.panelContenidoDirectorio.setLayout(new BorderLayout());
/*     */ 
/* 275 */     this.frame.setVisible(true);
/* 276 */     this.frame.setExtendedState(6);
/* 277 */     this.frame.setDefaultCloseOperation(3);
/*     */ 
/* 279 */     this.view = this.scroll.getViewport();
/* 280 */     InputMap inputMap = this.view.getInputMap(2);
/* 281 */     ActionMap actionMap = this.view.getActionMap();
/*     */ 
/* 284 */     Action upKeyAction = new Imagen.MoveAction(this.view, 1, 1, 0, this.labelImagen);
/* 285 */     KeyStroke upKey = KeyStroke.getKeyStroke("UP");
/* 286 */     inputMap.put(upKey, "up");
/* 287 */     actionMap.put("up", upKeyAction);
/*     */ 
/* 290 */     Action downKeyAction = new Imagen.MoveAction(this.view, 0, 1, 0, this.labelImagen);
/* 291 */     KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
/* 292 */     inputMap.put(downKey, "down");
/* 293 */     actionMap.put("down", downKeyAction);
/*     */ 
/* 296 */     Action leftKeyAction = new Imagen.MoveAction(this.view, 1, 0, 0, this.labelImagen, this.botonAtras, this.botonAdelante);
/* 297 */     KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
/* 298 */     inputMap.put(leftKey, "left");
/* 299 */     actionMap.put("left", leftKeyAction);
/*     */ 
/* 302 */     Action rightKeyAction = new Imagen.MoveAction(this.view, 0, 0, 0, this.labelImagen, this.botonAtras, this.botonAdelante);
/* 303 */     KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
/* 304 */     inputMap.put(rightKey, "right");
/* 305 */     actionMap.put("right", rightKeyAction);
/*     */ 
/* 308 */     Action pgUpKeyAction = new Imagen.MoveAction(this.view, 1, 1, 1, this.labelImagen);
/* 309 */     KeyStroke pgUpKey = KeyStroke.getKeyStroke("PAGE_UP");
/* 310 */     inputMap.put(pgUpKey, "pgUp");
/* 311 */     actionMap.put("pgUp", pgUpKeyAction);
/*     */ 
/* 314 */     Action pgDnKeyAction = new Imagen.MoveAction(this.view, 0, 1, 1, this.labelImagen);
/* 315 */     KeyStroke pgDnKey = KeyStroke.getKeyStroke("PAGE_DOWN");
/* 316 */     inputMap.put(pgDnKey, "pgDn");
/* 317 */     actionMap.put("pgDn", pgDnKeyAction);
/*     */ 
/* 320 */     this.botonAtras.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 323 */         if (Imagen.this.contador != 9999) {
/* 324 */           Imagen.access$110(Imagen.this);
/* 325 */           if ((Imagen.this.contador == -1) || (Imagen.this.contador == -2)) {
/* 326 */             Imagen.this.contador = (Imagen.this.cantImagen - 1);
/*     */           }
/* 328 */           Imagen.this.labelImagen.setIcon(Imagen.this.verImagen[Imagen.this.contador]);
/* 329 */           if (Imagen.this.contador + 1 < 10)
/* 330 */             Imagen.this.labelCantidad.setText("0" + (Imagen.this.contador + 1) + "/" + Imagen.this.cantImagen);
/*     */           else {
/* 332 */             Imagen.this.labelCantidad.setText(Imagen.this.contador + 1 + "/" + Imagen.this.cantImagen);
/*     */           }
/* 334 */           Imagen.this.contZoom = 0;
/*     */         }
/*     */       }
/*     */     });
/* 340 */     this.botonAdelante.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 343 */         if (Imagen.this.contador != 9999) {
/* 344 */           Imagen.access$108(Imagen.this);
/* 345 */           if (Imagen.this.contador == Imagen.this.cantImagen) {
/* 346 */             Imagen.this.contador = 0;
/*     */           }
/* 348 */           Imagen.this.labelImagen.setIcon(Imagen.this.verImagen[Imagen.this.contador]);
/* 349 */           Imagen.this.view.setViewPosition(new Point(0, 0));
/* 350 */           if (Imagen.this.contador + 1 < 10)
/* 351 */             Imagen.this.labelCantidad.setText("0" + (Imagen.this.contador + 1) + "/" + Imagen.this.cantImagen);
/*     */           else {
/* 353 */             Imagen.this.labelCantidad.setText(Imagen.this.contador + 1 + "/" + Imagen.this.cantImagen);
/*     */           }
/* 355 */           Imagen.this.contZoom = 0;
/*     */         }
/*     */       }
/*     */     });
/* 361 */     this.botonbhZoomG.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 363 */         Imagen.this.zoomAction('+');
/*     */       }
/*     */     });
/* 368 */     this.botonbhZoomC.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 370 */         Imagen.this.zoomAction('-');
/*     */       }
/*     */     });
/* 374 */     this.cbGenero.addActionListener(this.actionCombo);
/* 375 */     this.cbGenero.addFocusListener(this.focusCombo);
/*     */ 
/* 378 */     this.cbManga.addActionListener(this.actionCombo);
/* 379 */     this.cbManga.addFocusListener(this.focusCombo);
/*     */ 
/* 382 */     this.cbTomo.addActionListener(this.actionCombo);
/* 383 */     this.cbTomo.addFocusListener(this.focusCombo);
/*     */ 
/* 386 */     this.itemArCargaManual.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 388 */         CargaManual Cmanual = new CargaManual(Imagen.this.frame, true);
/* 389 */         Cmanual.setVisible(true);
/*     */         try {
/* 391 */           if (!CargaManual.direccion.equalsIgnoreCase(""))
/* 392 */             Imagen.this.verMangaIndependiente(Imagen.this.quitarEspacios(CargaManual.manga, 2), "<Tomo>");
/*     */         } catch (Exception ee) {
/*     */         }
/* 395 */         Imagen.this.botonAdelante.requestFocus();
/*     */       }
/*     */     });
/* 400 */     this.botonbhVFavorito.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evento) {
/* 402 */         Favoritos favorito = new Favoritos(Imagen.this.frame, true);
/* 403 */         favorito.setVisible(true);
/* 404 */         if (favorito.getVerFavorito()) {
/* 405 */           Imagen.this.verMangaIndependiente(favorito.getManga(), favorito.getTomo());
/*     */         }
/* 407 */         Imagen.this.botonAdelante.requestFocus();
/*     */       }
/*     */     });
/* 412 */     this.botonbhAFavorito.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evento) {
/* 414 */         if (!Imagen.this.cbManga.getSelectedItem().equals("<Manga>")) {
/* 415 */           Favoritos favorito = new Favoritos(Imagen.this.frame, false);
/* 416 */           favorito.setDatos(Imagen.this.cbManga.getSelectedItem(), Imagen.this.cbTomo.getSelectedItem(), Imagen.this.cbTomo.getItemAt(1));
/* 417 */           favorito.serializar("listado.dat");
/*     */         } else {
/* 419 */           JOptionPane.showMessageDialog(null, "Debe seleccionar un manga para añadirla a Favoritos", "Error", 0);
/*     */         }
/* 421 */         Imagen.this.botonAdelante.requestFocus();
/*     */       }
/*     */     });
/* 426 */     this.itemArCargaAuto.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent evento) {
/* 428 */         Imagen.this.verMangaIndependiente(null, null);
/* 429 */         Imagen.this.botonAdelante.requestFocus();
/*     */       }
/*     */     });
/* 434 */     this.itemAyAcerca.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 436 */         AcercaDe acercaDe = new AcercaDe(Imagen.this.frame, true);
/* 437 */         acercaDe.setVisible(true);
/*     */       }
/*     */     });
/* 442 */     this.botonbhInicio.addActionListener(this.itemArCargaAuto.getActionListeners()[0]);
/*     */ 
/* 445 */     this.botonbhCargar.addActionListener(this.itemArCargaManual.getActionListeners()[0]);
/*     */ 
/* 448 */     this.botonbhAyuda.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent ee) {
/* 450 */         JOptionPane.showMessageDialog(null, "Este boton aun no funciona", "Ayuda", -1);
/* 451 */         Imagen.this.botonAdelante.requestFocus();
/*     */       }
/*     */     });
/* 456 */     this.itemAyAyuda.addActionListener(this.botonbhAyuda.getActionListeners()[0]);
/*     */   }
/*     */ 
/*     */   private void limpiar() {
/* 460 */     this.labelImagen.setIcon(null);
/* 461 */     this.contador = 9999;
/* 462 */     this.labelCantidad.setText("00/00");
/*     */   }
/*     */ 
/*     */   private void zoomAction(char zoomImagen)
/*     */   {
/* 468 */     if ((this.contador != 9999) && (this.contador > -1)) {
/* 469 */       ImageIcon img = this.verImagen[this.contador];
/* 470 */       if (img != null) {
/* 471 */         if (this.contZoom == 0) {
/* 472 */           this.x = img.getIconWidth();
/* 473 */           this.y = img.getIconHeight();
/*     */         }
/* 475 */         if ((zoomImagen == '+') && (this.contZoom <= 4)) {
/* 476 */           this.x = ((int)(this.x + this.x * 0.1D));
/* 477 */           this.y = ((int)(this.y + this.y * 0.1D));
/* 478 */           this.contZoom += 1;
/*     */         }
/* 480 */         if ((zoomImagen == '-') && (this.contZoom > 0)) {
/* 481 */           this.x -= this.x / 11;
/* 482 */           this.y -= this.y / 11;
/* 483 */           this.contZoom -= 1;
/*     */         }
/* 485 */         BufferedImage bi = new BufferedImage(this.x, this.y, 2);
/* 486 */         this.g = bi.createGraphics();
/* 487 */         this.g.drawImage(img.getImage(), 0, 0, this.x, this.y, null);
/* 488 */         ImageIcon newIcon = new ImageIcon(bi);
/* 489 */         this.labelImagen.setIcon(newIcon);
/*     */       }
/*     */     }
/* 492 */     this.botonAdelante.requestFocus();
/*     */   }
/*     */ 
/*     */   public void verMangaIndependiente(Object manga, Object tomo) {
/* 496 */     this.cbGenero.setSelectedItem("<Genero>");
/* 497 */     this.cbGenero.setEnabled(false);
/* 498 */     this.cbManga.removeAllItems();
/* 499 */     this.cbManga.addItem(tomo == null ? "<Manga>" : manga);
/* 500 */     if (manga != null) {
/* 501 */       this.cbManga.setSelectedIndex(0);
/* 502 */       elegirAccion(this.cbManga);
/* 503 */       this.cbTomo.setSelectedItem(tomo);
/* 504 */       elegirAccion(this.cbTomo);
/*     */     } else {
/* 506 */       this.cbGenero.setEnabled(true);
/*     */     }
/*     */   }
/*     */ 
/* 510 */   public String quitarEspacios(Object objeto, int opc) { String mse = (String)objeto;
/* 511 */     if (opc == 1)
/* 512 */       mse = mse.replaceAll(" ", "_");
/*     */     else {
/* 514 */       mse = mse.replaceAll("_", " ");
/*     */     }
/* 516 */     return mse; }
/*     */ 
/*     */   private void elegirAccion(Object combo)
/*     */   {
/* 520 */     if (combo == this.cbGenero) {
/* 521 */       this.url = ("http://submanga.com/genero/" + quitarEspacios(this.cbGenero.getSelectedItem(), 1));
/* 522 */       varios.cargarManga(this.cbManga, this.url);
/* 523 */     } else if (combo == this.cbManga) {
/* 524 */       if (!this.cbManga.getSelectedItem().toString().equalsIgnoreCase("<Manga>")) {
/* 525 */         this.url = ("http://submanga.com/" + quitarEspacios(this.cbManga.getSelectedItem(), 1) + "/completa");
/* 526 */         varios.cargarTomo(this.cbTomo, this.url);
/*     */       } else {
/* 528 */         this.cbTomo.removeAllItems();
/* 529 */         this.cbTomo.addItem("<Tomo>");
/* 530 */         limpiar();
/*     */       }
/* 532 */     } else if (combo == this.cbTomo) {
/* 533 */       if (!this.cbTomo.getSelectedItem().toString().equalsIgnoreCase("<Tomo>"))
/*     */         try {
/* 535 */           String direccion = varios.obtenerCapitulo(this.url, quitarEspacios(this.cbTomo.getSelectedItem(), 1));
/* 536 */           this.cantImagen = varios.cantidadImagen(direccion);
/* 537 */           this.labelCantidad.setText("00/" + this.cantImagen);
/* 538 */           String direcImagen = varios.obtenerImagen(direccion);
/* 539 */           ImageIcon[] imagen = new ImageIcon[this.cantImagen];
/* 540 */           Hilo hilo1 = new Hilo(this.cantImagen, imagen, direcImagen);
/* 541 */           this.contador = -1;
/* 542 */           this.verImagen = imagen;
/*     */         }
/*     */         catch (Exception ee) {
/*     */         }
/* 546 */       else limpiar();
/*     */ 
/* 548 */       this.labelImagen.setIcon(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class MoveAction extends AbstractAction
/*     */   {
/*     */     JViewport viewport;
/*     */     int direction;
/*     */     int axis;
/*     */     int type;
/*     */     int label;
/*     */     JButton at;
/*     */     JButton ad;
/*     */ 
/*     */     public MoveAction(JViewport viewport, int direction, int axis, int type, JLabel lbl, JButton atr, JButton adel)
/*     */     {
/*  85 */       if (viewport == null) {
/*  86 */         throw new IllegalArgumentException("null viewport not permitted");
/*     */       }
/*     */ 
/*  89 */       this.viewport = viewport;
/*  90 */       this.direction = direction;
/*  91 */       this.axis = axis;
/*  92 */       this.type = type;
/*  93 */       this.label = lbl.getWidth();
/*  94 */       this.at = atr;
/*  95 */       this.ad = adel;
/*     */     }
/*     */ 
/*     */     public MoveAction(JViewport viewport, int direction, int axis, int type, JLabel lbl) {
/*  99 */       if (viewport == null) {
/* 100 */         throw new IllegalArgumentException("null viewport not permitted");
/*     */       }
/*     */ 
/* 103 */       this.viewport = viewport;
/* 104 */       this.direction = direction;
/* 105 */       this.axis = axis;
/* 106 */       this.type = type;
/* 107 */       this.label = lbl.getWidth();
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent actionEvent) {
/* 111 */       Dimension extentSize = this.viewport.getExtentSize();
/* 112 */       int horizontalMoveSize = 0;
/* 113 */       int verticalMoveSize = 0;
/* 114 */       if (this.axis == 0) {
/* 115 */         if (this.type == 0) {
/* 116 */           horizontalMoveSize = 10;
/* 117 */           if (extentSize.width > this.label)
/* 118 */             if (this.direction == 1)
/* 119 */               this.at.doClick();
/*     */             else
/* 121 */               this.ad.doClick();
/*     */         }
/*     */         else
/*     */         {
/* 125 */           horizontalMoveSize = extentSize.width;
/*     */         }
/*     */       }
/* 128 */       else if (this.type == 0)
/* 129 */         verticalMoveSize = 10;
/*     */       else {
/* 131 */         verticalMoveSize = extentSize.height;
/*     */       }
/*     */ 
/* 134 */       if (this.direction == 1) {
/* 135 */         horizontalMoveSize = -horizontalMoveSize;
/* 136 */         verticalMoveSize = -verticalMoveSize;
/*     */       }
/*     */ 
/* 139 */       Point origin = this.viewport.getViewPosition();
/* 140 */       origin.x += horizontalMoveSize;
/* 141 */       origin.y += verticalMoveSize;
/* 142 */       if (origin.x < 0) origin.x = 0;
/* 143 */       if (origin.y < 0) origin.y = 0;
/*     */ 
/* 145 */       this.viewport.setViewPosition(origin);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /home/rodrigo/Descargas/LectorManga4.jar
 * Qualified Name:     Imagen
 * JD-Core Version:    0.6.2
 */