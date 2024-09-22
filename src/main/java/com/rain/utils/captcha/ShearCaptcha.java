package com.rain.utils.captcha;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 扭曲干扰验证码
 *
 * @author rain
 * @date 2024/09/22
 */
public class ShearCaptcha extends AbstractCaptcha {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public ShearCaptcha(int width, int height, int codeCount, int interfereCount, Color color, Font font) {
        super(width, height, codeCount, interfereCount);
        if (color != null) {
            this.setBackground(color);
        }
        if (font != null) {
            this.setFont(font);
        }
        generateCode();
    }

    @Override
    public void generateCode() {
        this.code = generateRandomText(this.codeCount);
        BufferedImage image = generateCaptchaImage(this.code);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            this.imageBytes = baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verify(String inputCode) {
        return this.code != null && this.code.equalsIgnoreCase(inputCode);
    }

    private String generateRandomText(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    private BufferedImage generateCaptchaImage(String text) {
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(this.background);
        g.fillRect(0, 0, this.width, this.height);

        int fontSize = (int) (this.height * 0.75);
        g.setFont(new Font("Arial", Font.BOLD, fontSize));

        FontMetrics fontMetrics = g.getFontMetrics();
        int charWidth = this.width / this.codeCount;
        int charHeight = fontMetrics.getAscent();
        int charX = (this.width - charWidth * this.codeCount) / 2;
        int charY = (this.height + charHeight) / 2 - fontMetrics.getDescent();

        int maxCharHeight = this.height - 2 * (this.height - charY);
        if (charHeight > maxCharHeight) {
            fontSize = (int) (fontSize * ((double) maxCharHeight / charHeight));
            g.setFont(new Font("Arial", Font.BOLD, fontSize));
            fontMetrics = g.getFontMetrics();
            charHeight = fontMetrics.getAscent();
            charY = (this.height + charHeight) / 2 - fontMetrics.getDescent();
        }

        Random random = new Random();
        for (int i = 0; i < text.length(); i++) {
            g.setColor(generateBrightColor(random));
            AffineTransform originalTransform = g.getTransform();
            AffineTransform transform = new AffineTransform();
            transform.rotate(random.nextDouble() * 0.4 - 0.2, charX + i * charWidth + charWidth / 2, charY - charHeight / 2);
            transform.shear(random.nextDouble() * 0.3 - 0.15, random.nextDouble() * 0.3 - 0.15);
            g.setTransform(transform);
            g.drawString(String.valueOf(text.charAt(i)), charX + i * charWidth, charY);
            g.setTransform(originalTransform);
        }

        for (int i = 0; i < this.interfereCount; i++) {
            int x1 = random.nextInt(this.width);
            int y1 = random.nextInt(this.height);
            int x2 = random.nextInt(this.width);
            int y2 = random.nextInt(this.height);
            g.setColor(generateBrightColor(random));
            g.drawLine(x1, y1, x2, y2);
        }

        addNoise(image);

        g.dispose();
        return image;
    }

    private void addNoise(BufferedImage image) {
        Random random = new Random();
        int area = (int) (0.05 * this.width * this.height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(this.width);
            int y = random.nextInt(this.height);
            int rgb = generateBrightColor(random).getRGB();
            image.setRGB(x, y, rgb);
        }
    }

    private Color generateBrightColor(Random random) {
        float hue = random.nextFloat();
        float saturation = 0.9f;
        float brightness = 1.0f;
        return Color.getHSBColor(hue, saturation, brightness);
    }
}