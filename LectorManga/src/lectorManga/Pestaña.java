package lectorManga;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Pestaña extends JTabbedPane {
	    
	    private transient MouseMotionListener hoverHandler;
	    public Pestaña() {
	        super(TOP, SCROLL_TAB_LAYOUT);
	    }

	    public Pestaña(int tabPlacement) {
	        super(tabPlacement, SCROLL_TAB_LAYOUT);
	    }

	    @Override public void updateUI() {
	        removeMouseMotionListener(hoverHandler);
	        super.updateUI();
	        //setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	        if (hoverHandler == null) {
	            hoverHandler = new MouseMotionAdapter() {
	                private int prev = -1;
	                @Override public void mouseMoved(MouseEvent e) {
	                    JTabbedPane source = (JTabbedPane) e.getComponent();
	                    int focussed = source.indexAtLocation(e.getX(), e.getY());
	                    if (focussed == prev) {
	                        return;
	                    }
	                    for (int i = 0; i < source.getTabCount(); i++) {
	                        TabPanel tab = (TabPanel) source.getTabComponentAt(i);
	                        tab.setButtonVisible(i == focussed);
	                    }
	                    prev = focussed;
	                }
	            };
	        }
	        addMouseMotionListener(hoverHandler);
	    }
	    @Override public void addTab(String title, final Component content) {
	        super.addTab(title, content);
	        setTabComponentAt(getTabCount() - 1, new TabPanel(this, title, content));
	    }
	

	class TabPanel extends JPanel {
	    private final JButton button = new JButton(new CloseTabIcon()) {
	        @Override public void updateUI() {
	            super.updateUI();
	            setBorder(BorderFactory.createEmptyBorder());
	            setBorderPainted(false);
	            setFocusPainted(false);
	            setContentAreaFilled(false);
	            setFocusable(false);
	            setVisible(false);
	        }
	    };
	    private final JLabel label = new JLabel() {
	        @Override public Dimension getPreferredSize() {
	            Dimension dim = super.getPreferredSize();
	            int w = button.isVisible() ? 80 - button.getPreferredSize().width : 80;
	            return new Dimension(w, dim.height);
	        }
	    };
	    public TabPanel(final JTabbedPane pane, String title, final Component content) {
	        super(new BorderLayout(0, 0));
	        setOpaque(false);

	        label.setText(title);
	        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));

	        button.addActionListener(new ActionListener() {
	            @Override public void actionPerformed(ActionEvent e) {
	                int idx = pane.indexOfComponent(content);
	                pane.removeTabAt(idx);
	                int count = pane.getTabCount();
	                if (count > idx) {
	                    TabPanel tab = (TabPanel) pane.getTabComponentAt(idx);
	                    tab.setButtonVisible(true);
	                }
	            }
	        });
	        add(label);
	        add(button, BorderLayout.EAST);
	    }
	    public void setButtonVisible(boolean flag) {
	        button.setVisible(flag);
	    }
	}

	class CloseTabIcon implements Icon {
	    @Override public void paintIcon(Component c, Graphics g, int x, int y) {
	        g.translate(x, y);
	        g.setColor(Color.ORANGE);
	        g.drawLine(2, 3, 9, 10);
	        g.drawLine(2, 4, 8, 10);
	        g.drawLine(3, 3, 9, 9);
	        g.drawLine(9, 3, 2, 10);
	        g.drawLine(9, 4, 3, 10);
	        g.drawLine(8, 3, 2, 9);
	        g.translate(-x, -y);
	    }
	    @Override public int getIconWidth() {
	        return 12;
	    }
	    @Override public int getIconHeight() {
	        return 12;
	    }
	}


}
