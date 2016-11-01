package WeatherReader;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;

import javax.swing.*;
public class WeatherFraming extends JFrame
{
    private ImageIcon image;
    private String location;
    private String conditions;
    
    JFrame frame = new JFrame();    
    JPanel mainPanel = new JPanel();
    JPanel east = new JPanel();
    JPanel west = new JPanel();
    JPanel south = new JPanel();
    JPanel north = new JPanel(new BorderLayout());
    
    JLabel tempLabel = new JLabel();
    JLabel resultLabel = new JLabel();
    JLabel error = new JLabel();
    JLabel conditionLabel = new JLabel("Images Loading...");
    JLabel searchLabel = new JLabel("  Search");
    JLabel infoLabel = new JLabel("");
    JButton btn = new JButton("Search");
    JTextField inputField = new JTextField();
    ActionListener al;
    NJWeatherReader w = new NJWeatherReader();
	private Scanner in;
         
    public String getConditions()
    {
        return conditions;        
    }
    public String setConditions()
    {
        return conditions;
    }
    public ImageIcon getImage()
    {
        return image;
    }
    public ImageIcon setImageIcon(String file)
    {
        image = new ImageIcon("WeatherReader/image/" + file);
        return image;
    }
    public WeatherFraming()
    {
        super();
        setPreferredSize(new Dimension(520, 380));
        setTitle("Weather Application");
            getContentPane().add(mainPanel, BorderLayout.CENTER);  
            getContentPane().add(east, BorderLayout.EAST);
            getContentPane().add(north, BorderLayout.NORTH);
            getContentPane().add(south, BorderLayout.SOUTH); 
            getContentPane().add(west, BorderLayout.WEST);       
            north.add(searchLabel, BorderLayout.WEST);
            north.add(inputField, BorderLayout.CENTER);
            north.add(btn, BorderLayout.EAST);
            east.add(resultLabel);
            east.add(tempLabel);
            south.add(infoLabel, BorderLayout.WEST);              
            al = new BtnAction();
            btn.addActionListener(al);
    }
    
    public static void main(String args[])
    {
        
        WeatherFraming f = new WeatherFraming();
        f.setSize(520, 380);
        f.setPreferredSize(new Dimension(520, 380));
        WeatherFraming.setDefaultLookAndFeelDecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);      
    }
    class BtnAction implements ActionListener
    {

		public void actionPerformed(ActionEvent ae) {
			String cloudy = "cloudy";
         String mocldy = "mocldy";
         String ptcldy = "ptcldy";
			String sunny = "sunny";
         String ptsunny = "ptsunny";
         String clear = "clear";
			String windy = "windy";
			String rain = "rain";
			resultLabel.setText("Loading...");
			
			String fieldText = inputField.getText().toUpperCase();
			System.out.println(fieldText);
			String weatherResult = NJWeatherReader.getWeatherStatus(fieldText);
			resultLabel.setText("<html>" + weatherResult + "<br>Temperature: " + weatherResult.substring(25, 27) + " Degrees F</html>");
			infoLabel.setText("");
			infoLabel.setIcon(null);
			
			if(weatherResult.contains(cloudy.toUpperCase()))
			{
				infoLabel.setIcon(setImageIcon(cloudy+".png"));
			}
         else if(weatherResult.contains(ptcldy.toUpperCase()))
			{
				infoLabel.setIcon(setImageIcon(ptcldy+".png"));
			}
         else if(weatherResult.contains(mocldy.toUpperCase()))
			{
				infoLabel.setIcon(setImageIcon(mocldy+".png"));
			}
			else if(weatherResult.contains(sunny.toUpperCase()))
			{
				infoLabel.setIcon(setImageIcon(sunny+".png"));
			}
         else if(weatherResult.contains(ptsunny.toUpperCase()))
			{
				infoLabel.setIcon(setImageIcon(ptsunny+".png"));
			}
         else if(weatherResult.contains(clear.toUpperCase()))
			{
				infoLabel.setIcon(setImageIcon(clear+".png"));
			}
			else if(weatherResult.contains(windy.toUpperCase()))
			{
				infoLabel.setIcon(setImageIcon(windy+".png"));
			}
			else if(weatherResult.contains(rain.toUpperCase()))
			{
				infoLabel.setIcon(setImageIcon(rain+".png"));
			}
         else 
			{
				infoLabel.setText("No other images available...");
			}
		}
    } 
}    
    

    

