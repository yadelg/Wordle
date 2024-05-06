package finalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setForeground(Color.WHITE);
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(50, 50));
        setFont(new Font("Franklin Gothic", Font.BOLD, 20));
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Rounded rectangle
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }

}



