package com.uitaudio;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by danilko on 9/7/16.
 */
public class Execution {
    public static void main(String [] args) throws IOException, WriterException {
		 LocalDateTime date = LocalDateTime.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddkkss");
         String text = date.format(formatter);
  
		String qrCodeData = args[0] + "-" + text;
        String filePath = "QRCode.png";
        String charset = "UTF-8";
        Map hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        createBarcodeCode(qrCodeData,filePath,charset,hintMap, 40, 350);
    }
	
    public static void createBarcodeCode(String qrCodeData, String filePath, String charset, Map hintMap, int qrCodeHeight, int qrCodeWidth) throws IOException, WriterException {
        BitMatrix matrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.CODE_128, qrCodeWidth, qrCodeHeight, hintMap);

        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);

        BufferedImage combined = new BufferedImage(1800, 1000, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = (Graphics2D)combined.getGraphics();

        g.setFont(new Font("default", Font.BOLD, 25));
        g.setColor(Color.white);
		
        g.fillRect(0, 0, 1800, 1000);
        g.setColor(Color.black);
        g.drawImage(bufferedImage, 1380, 350,null );
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g.drawString("S/N: "+ qrCodeData, 1415, 430);

        ImageIO.write(combined, "PNG", new File(filePath));
    }
}
