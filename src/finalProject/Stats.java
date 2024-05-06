package finalProject;

import javax.swing.*;
import java.awt.*;

public class Stats extends JFrame {

    
	 private int[] data; // Sample data
	    private int[] NumberOfGuesses;
	    private int numGuesses;
	    private int numPlayed;
	    private int numWins;
	    private int streak;
	    private int maxStreak;
	    public Stats(int[] data, int[] NumberOfGuesses, int numGuesses, int numPlayed, int numWins, int streak, int maxStreak) {
	        this.data = data;
	        this.NumberOfGuesses = NumberOfGuesses;
	        this.numPlayed = numPlayed;
	        this.numGuesses = numGuesses;
	        this.numWins = numWins;
	        this.streak = streak;
	        this.maxStreak = maxStreak;
	        
        setTitle("Statistics");
        setSize(400, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawLabels(g);
                drawHistogram(g);
            }
        };
        panel.setBackground(Color.BLACK);
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void drawLabels(Graphics g) {
        Font font = new Font("Franklin Gothic", Font.BOLD, 30);
        
        g.setFont(font);
        g.setColor(Color.WHITE);
        
        
        // Centers the number numPlayed over the label "Played" (done with calculation to work with any number)
        int labelWidth = g.getFontMetrics().stringWidth("Played");
        int numWidth = g.getFontMetrics().stringWidth(String.valueOf(numPlayed));
        int xCoordinate = 23 + labelWidth / 2 - numWidth / 2;
        g.drawString(String.valueOf(numPlayed), xCoordinate, 95); // Adjusted Y coordinate

        // Centering the win percentage number     
        int winPerc = (int) Math.ceil(((double) numWins / numPlayed) * 100);
        numWidth = g.getFontMetrics().stringWidth(String.valueOf(winPerc));
        labelWidth = g.getFontMetrics().stringWidth("Win %");
        xCoordinate = 99 + labelWidth / 2 - numWidth / 2;
        g.drawString(String.valueOf(winPerc), xCoordinate, 95); // Adjusted Y coordinate

        // Centering current streak number
        numWidth = g.getFontMetrics().stringWidth(String.valueOf(streak));
        labelWidth = g.getFontMetrics().stringWidth("Current");
        xCoordinate = 168 + labelWidth / 2 - numWidth / 2;
        g.drawString(String.valueOf(streak), xCoordinate, 95); // Adjusted Y coordinate

        // Centering MaxStreak number
        numWidth = g.getFontMetrics().stringWidth(String.valueOf(maxStreak));
        labelWidth = g.getFontMetrics().stringWidth("Max Streak");
        xCoordinate = 227 + labelWidth / 2 - numWidth / 2;
        g.drawString(String.valueOf(maxStreak), xCoordinate, 95); // Adjusted Y coordinate

        // drawing the words onto the dialog box       
        g.setFont(new Font("Franklin Gothic", Font.BOLD, 13));
        g.drawString("Played", 50, 110); // Adjusted Y coordinate
        g.drawString("Win %", 50+75, 110); // Adjusted Y coordinate
        g.drawString("GUESS DISTRIBUTION", 28, 140); // Adjusted Y coordinate
        g.drawString("Current", 50+75+75, 110); // Adjusted Y coordinate
        g.drawString(" Streak", 50+75+75, 123); // Adjusted Y coordinate
        g.drawString("Max Streak", 50+75+75+75, 110); // Adjusted Y coordinate
        g.setFont(new Font("Franklin Gothic", Font.BOLD, 25));
        g.drawString("Statistics", 28, 50);
    }

    private void drawHistogram(Graphics g) {
        g.setFont(new Font("Franklin Gothic", Font.BOLD, 12));
        int maxValue = findMaxValue();
        int barHeight = 13;
        int gap = 10;
        int baseX = 50;
        int baseY = 152; // Adjusted Y coordinate

        // Calculate maximum label width
        int maxLabelWidth = 0;
        for (int i = 0; i < data.length; i++) {
            String label = String.valueOf(data[i]);
            int labelWidth = g.getFontMetrics().stringWidth(label);
            
            if (labelWidth > maxLabelWidth) {
                maxLabelWidth = labelWidth;
            }
        }

        for (int i = 0; i < data.length; i++) {
            int barWidth;
            if (data[i] == 0) {
                // Set a fixed minimum length for zero
                barWidth = 10; 
            } 
            else {
                barWidth = (int) (((double) data[i] / maxValue) * 250) + 10;
            }

            int x = baseX + 10 ; // Adjust x position to accommodate the label
            int y = baseY - (barHeight + gap) * -i;
            
            if (numGuesses == NumberOfGuesses[i]) {
                g.setColor(Color.decode("#538d4e"));
                g.fillRect(x - 20, y, barWidth, barHeight);

                g.setColor(Color.decode("#538d4e"));
                g.drawRect(x - 20, y, barWidth, barHeight);
                g.setColor(Color.WHITE);
            } 
            
            else {
                g.setColor(Color.GRAY);
                g.fillRect(x - 20, y, barWidth, barHeight);

                g.setColor(Color.GRAY);
                g.drawRect(x - 20, y, barWidth, barHeight);
                g.setColor(Color.WHITE);
            }
           
            // Display the label inside the bar at the end
            int labelX = x + barWidth - g.getFontMetrics().stringWidth(String.valueOf(data[i]))- 2; // Adjusted x position
            g.drawString(String.valueOf(data[i]), labelX - 20, y + barHeight / 2 + 5);
            // Display the label to the left of each bar
            g.drawString(String.valueOf(NumberOfGuesses[i]), baseX - 20, y + barHeight / 2 + 5);
        }
    }

    
    private int findMaxValue() {
        int max = Integer.MIN_VALUE;
        for (int value : data) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void main(String[] args) {
    	
    }
    }
