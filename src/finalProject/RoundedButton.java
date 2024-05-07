package finalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

 
	/**
	 * This class was overridden to better replicate how each button looks on wordle for the keyboard.
	 * Traditional Java buttons have spiky edges, have borders around the text when hovered over, etc.
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
        setPreferredSize(new Dimension(52, 52));
        setFont(new Font("Franklin Gothic", Font.BOLD, 16));
        
        
    }

    @Override
    //Makes the rectangle have round edges.
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }
    


    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }

}



