import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

class Slice {
  double value;
  Color color;

  public Slice(double value, Color color) {
    this.value = value;
    this.color = color;
  }
}

class MyComponent extends JComponent {
	Slice[] slices ;
  MyComponent(int x, int y) {
	  slices  = new Slice[]{ new Slice(x, Color.green), new Slice(y, Color.red)};

  }
  public void paint(Graphics g) {
    drawPie((Graphics2D) g, getBounds(), slices);
  }

  void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
    double total = 0.0D;
    for (int i = 0; i < slices.length; i++) {
      total += slices[i].value;
    }

    double curValue = 0.0D;
    int startAngle = 0;
    for (int i = 0; i < slices.length; i++) {
      startAngle = (int) (curValue * 360 / total);
      int arcAngle = (int) (slices[i].value * 360 / total);

      g.setColor(slices[i].color);
      g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
      curValue += slices[i].value;
    }
  }
}

public class PieChart {
  public static void start(int[] a) {
    JFrame frame = new JFrame();
    
    int x = Math.round(((float)a[0]/(a[1]+a[0]))*100);
    int y = Math.round(((float)a[1]/(a[1]+a[0]))*100);
    
  frame.getContentPane().add(new MyComponent(x,y));
  
  JLabel lblNewLabel = new JLabel("Pass : "+x+" %");
  frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
  
  JLabel lblNewLabel_1 = new JLabel("Fail : "+y +" %");
  frame.getContentPane().add(lblNewLabel_1, BorderLayout.SOUTH);
    frame.setSize(351, 325);
    frame.setVisible(true);

  }
}