package es.alop;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        PDDocument pdf = PDDocument.load(new File("01.pdf"));
        PDFRenderer render = new PDFRenderer(pdf);
        BufferedImage pdfImage = render.renderImageWithDPI(0, 300);
//        ImageIO.write(pdfImage, "jpg", new File("pdfImage.jpg"));

        PDDocument pdf2 = PDDocument.load(new File("03.pdf"));
        PDFRenderer render2 = new PDFRenderer(pdf2);
        BufferedImage pdfImage2 = render2.renderImageWithDPI(0, 300);
//        ImageIO.write(pdfImage2, "jpg", new File("pdfImage2.jpg"));

        BufferedImage fase1_1 = ImageUtils.Threshold(pdfImage, 160);
//        ImageIO.write(fase1_1, "jpg", new File("fase1_1.jpg"));

        BufferedImage fase1_2 = ImageUtils.Threshold(pdfImage2, 160);
//        ImageIO.write(fase1_2, "jpg", new File("fase1_2.jpg"));

        BufferedImage fase2_1 = ImageUtils.resizeImage(fase1_1, 100, 100);
//        ImageIO.write(fase2_1, "jpg", new File("fase2_1.jpg"));

        BufferedImage fase2_2 = ImageUtils.resizeImage(fase1_2, 100, 100);
        //ImageIO.write(fase2_2, "jpg", new File("fase2_2.jpg"));

        ImageUtils.compare(fase2_1, fase2_2);

    }
}