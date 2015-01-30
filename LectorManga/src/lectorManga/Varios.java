package lectorManga;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class Varios
/*     */ {
/*     */   private URL url;
/*     */   private BufferedReader br;
/*     */ 
/*     */   public void cargarGenero(JComboBox combo)
/*     */   {
/*     */     try
/*     */     {
/*  24 */       this.url = new URL("http://submanga.com/series/g");
/*  25 */       this.br = new BufferedReader(new InputStreamReader(this.url.openStream()));
/*     */ 
/*  28 */       String cadena = this.br.readLine();
/*  29 */       while (cadena != null) {
/*  30 */         while (cadena.contains("http://submanga.com/series/g#")) {
/*  31 */           int i1 = cadena.indexOf("http://submanga.com/series/g#");
/*  32 */           cadena = cadena.substring(i1);
/*  33 */           int i2 = cadena.indexOf(">") + 1;
/*  34 */           int i3 = cadena.indexOf("<");
/*  35 */           String c2 = cadena.substring(i2, i3);
/*  36 */           combo.addItem(c2);
/*  37 */           cadena = cadena.substring(i3);
/*     */         }
/*  39 */         cadena = this.br.readLine();
/*     */       }
/*     */     } catch (Exception e) {
/*  42 */       JOptionPane.showMessageDialog(null, "Error en Cargar Genero", "Error", 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cargarManga(JComboBox combo, String direccion) {
/*     */     try {
/*  48 */       combo.removeAllItems();
/*  49 */       combo.addItem("<Manga>");
/*  50 */       this.url = new URL(direccion);
/*  51 */       this.br = new BufferedReader(new InputStreamReader(this.url.openStream()));
/*     */ 
/*  53 */       for (int i = 0; i < 8; i++) this.br.readLine();
/*  54 */       String cadena = this.br.readLine();
/*     */ 
/*  56 */       while (cadena.contains("http://submanga.com/")) {
/*  57 */         int i1 = cadena.indexOf("http://submanga.com/");
/*  58 */         cadena = cadena.substring(i1);
/*  59 */         int i2 = cadena.indexOf(">") + 1;
/*  60 */         int i3 = cadena.indexOf("<");
/*  61 */         String c2 = cadena.substring(i2, i3);
/*  62 */         combo.addItem(c2);
/*  63 */         cadena = cadena.substring(i3);
/*     */       }
/*     */     } catch (Exception ex) {
/*  66 */       JOptionPane.showMessageDialog(null, "Error en Cargar Manga", "Error", 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cargarTomo(JComboBox combo, String direccion) {
/*     */     try {
/*  72 */       combo.removeAllItems();
/*  73 */       combo.addItem("<Tomo>");
/*  74 */       this.url = new URL(direccion);
/*  75 */       this.br = new BufferedReader(new InputStreamReader(this.url.openStream()));
/*     */ 
/*  77 */       for (int i = 0; i < 4; i++) this.br.readLine();
/*  78 */       String cadena = this.br.readLine();
/*     */ 
/*  80 */       while (cadena.contains("<strong>")) {
/*  81 */         int i1 = cadena.indexOf("http://submanga.com/");
/*  82 */         cadena = cadena.substring(i1);
/*  83 */         int i2 = cadena.indexOf("<strong>") + 8;
/*  84 */         int i3 = cadena.indexOf("</strong>");
/*  85 */         String c2 = cadena.substring(i2, i3);
/*  86 */         combo.addItem(c2);
/*  87 */         cadena = cadena.substring(i3);
/*     */       }
/*     */     } catch (Exception ex) {
/*  90 */       JOptionPane.showMessageDialog(null, "Error en Cargar Tomo", "Error", 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String obtenerCapitulo(String direccion, String tomo)
/*     */   {
/*     */     try {
/*  97 */       this.url = new URL(direccion);
/*  98 */       direccion = direccion.substring(0, direccion.indexOf("completa")) + tomo + "/";
/*  99 */       this.br = new BufferedReader(new InputStreamReader(this.url.openStream()));
/* 100 */       for (int i = 0; i < 4; i++) this.br.readLine();
/* 101 */       String cadena = this.br.readLine();
/*     */ 
/* 103 */       cadena = cadena.toLowerCase();
/* 104 */       direccion = direccion.toLowerCase();
/* 105 */       if ((direccion.contains("%")) && 
/* 106 */         (direccion.length() > direccion.lastIndexOf("%") + 3)) {
/* 107 */         direccion = direccion.substring(direccion.lastIndexOf("%") + 3);
/*     */       }
/*     */ 
/* 110 */       int i1 = cadena.indexOf(direccion);
/* 111 */       cadena = cadena.substring(i1);
/* 112 */       int i2 = cadena.indexOf(">") - 1;
/* 113 */       int i3 = cadena.indexOf(direccion) + direccion.length() - 1;
/* 114 */       direccion = "http://submanga.com/c" + cadena.substring(i3, i2);
/*     */     } catch (Exception ex) {
/* 116 */       JOptionPane.showMessageDialog(null, "Error en Obtener Capitulo", "Error", 0);
/*     */     }
/* 118 */     return direccion;
/*     */   }
/*     */ 
/*     */   public int cantidadImagen(String direccion) {
/* 122 */     int cantImagen = 0;
/*     */     try {
/* 124 */       this.url = new URL(direccion);
/* 125 */       this.br = new BufferedReader(new InputStreamReader(this.url.openStream()));
/* 126 */       String c2 = "";
/* 127 */       String cadena = this.br.readLine();
/*     */ 
/* 129 */       while (cadena.contains("option value")) {
/* 130 */         int i1 = cadena.indexOf("option ");
/* 131 */         cadena = cadena.substring(i1);
/* 132 */         int i2 = cadena.indexOf(">") + 1;
/* 133 */         int i3 = cadena.indexOf("<");
/* 134 */         c2 = cadena.substring(i2, i3);
/* 135 */         cadena = cadena.substring(i3);
/*     */       }
/* 137 */       cantImagen = Integer.parseInt(c2);
/*     */     } catch (Exception ex) {
/* 139 */       JOptionPane.showMessageDialog(null, "Error en Cargar Capitulo", "Error", 0);
/*     */     }
/* 141 */     return cantImagen;
/*     */   }
/*     */ 
/*     */   public String obtenerImagen(String direccion) {
/*     */     try {
/* 146 */       this.url = new URL(direccion);
/* 147 */       this.br = new BufferedReader(new InputStreamReader(this.url.openStream()));
/*     */ 
/* 149 */       String cadena = this.br.readLine();
/*     */ 
/* 151 */       while (cadena.contains("jpg")) {
/* 152 */         int i1 = cadena.indexOf("jpg") - 2;
/* 153 */         cadena = cadena.substring(0, i1);
/* 154 */         int i2 = cadena.lastIndexOf("img");
/* 155 */         direccion = cadena.substring(i2);
/*     */       }
/*     */     } catch (Exception ex) {
/* 158 */       JOptionPane.showMessageDialog(null, "Error en Cargar Capitulo", "Error", 0);
/*     */     }
/* 160 */     return direccion;
/*     */   }
/*     */ 
/*     */   public boolean revisarConexion(String direccion) {
/* 164 */     if (direccion.contains("submanga")) {
/* 165 */       if (!direccion.contains("http://"))
/* 166 */         direccion = "http://" + direccion;
/*     */       try
/*     */       {
/* 169 */         URL ruta = new URL(direccion);
/* 170 */         URLConnection rutaC = ruta.openConnection();
/* 171 */         rutaC.connect();
/* 172 */         return true;
/*     */       } catch (Exception e) {
/* 174 */         return false;
/*     */       }
/*     */     }
/* 177 */     return false;
/*     */   }
/*     */ }

/* Location:           /home/rodrigo/Descargas/LectorManga4.jar
 * Qualified Name:     Varios
 * JD-Core Version:    0.6.2
 */