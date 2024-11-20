import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;
class PanelImagenRota extends JPanel {
    private BufferedImage imagen;
    private int angulo = 0;

    public PanelImagenRota(BufferedImage imagen) {
        this.imagen = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = this.imagen.createGraphics();
        g2d.drawImage(imagen, 0, 0, null);
        g2d.dispose();
    }

    public void incrementarAngulo() {
        angulo += 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rotar y centrar la imagen
        double rotationRequired = Math.toRadians(angulo);
        double locationX = imagen.getWidth() / 2.0;
        double locationY = imagen.getHeight() / 2.0;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        BufferedImage rotatedImage = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_ARGB);
        op.filter(imagen, rotatedImage);

        g2d.drawImage(rotatedImage, (getWidth() - rotatedImage.getWidth()) / 2, (getHeight() - rotatedImage.getHeight()) / 2, null);
    }
}
