import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PesoImagen {
    public static void main(String[] args) {
        
        String input = JOptionPane.showInputDialog("Ingrese su peso:");
        float peso = Float.parseFloat(input);

        if (peso > 80) {
            BufferedImage imagenOriginal = null;
            try {
                imagenOriginal = ImageIO.read(new File("C:\\Users\\Montenegro\\Documents\\Proyectos-Java\\gorda.png"));
            } catch (IOException e) {
                System.out.println("No se pudo cargar la imagen.");
                e.printStackTrace();
                System.exit(1);
            }

            JFrame ventana = new JFrame("Peso Excedido");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);

            PanelImagenRota panelImagen = new PanelImagenRota(imagenOriginal);
            ventana.add(panelImagen);
            ventana.setSize(imagenOriginal.getWidth(), imagenOriginal.getHeight());
            ventana.setVisible(true);

            Timer timer = new Timer(16, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelImagen.incrementarAngulo();
                    panelImagen.repaint();
                }
            });
            timer.start();
        } else {
            System.out.println("Tu peso no supera los 100 kg.");
        }
    }
}

