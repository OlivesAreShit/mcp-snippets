

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;

public class CustomFont {
	
	public int size = 4096;
	 public static Font font;
	public DynamicTexture texture;
	int height;	
	protected CharData[] charData = new CharData[512];
	public int charOffset = 0;
	
	public CustomFont(Font font) {
		this.font = font;
		texture = setupTexture(font, this.charData);
	}
	
	protected BufferedImage createFontImage(Font font, CharData[] chars) {
		
		BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = (Graphics2D) image.getGraphics();
        g.setFont(font);
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fontMetrics = g.getFontMetrics();
        
        int charHeight = 0;
        int positionX = 0;
        int positionY = 1;
        for (int i = 0; i < chars.length; i++) {
            char ch = (char) i;
            CharData charData = new CharData();
            Rectangle2D dimensions = fontMetrics.getStringBounds(String.valueOf(ch), g);
            charData.width = (dimensions.getBounds().width + 8);
            charData.height = dimensions.getBounds().height;
            if (positionX + charData.width >= size) {
                positionX = 0;
                positionY += charHeight;
                charHeight = 0;
            }
            if (charData.height > charHeight) {
                charHeight = charData.height;
            }
            charData.storedX = positionX;
            charData.storedY = positionY;
            if (charData.height > this.height) {
            	this.height = charData.height;
            }
            chars[i] = charData;
            g.drawString(String.valueOf(ch), positionX + 2, positionY + fontMetrics.getAscent());
            positionX += charData.width;
        }
        return image;
		
	}
	
	public DynamicTexture setupTexture(Font font, CharData[] chars) {
		BufferedImage img = createFontImage(font, chars);
        try {
            return new DynamicTexture(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	 public void drawChar(CharData[] chars, char c, float x, float y) throws ArrayIndexOutOfBoundsException {
	        try {
	            drawQuad(x, y, chars[c].width, chars[c].height, chars[c].storedX, chars[c].storedY, chars[c].width, chars[c].height);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    protected void drawQuad(float x, float y, float width, float height, float srcX, float srcY, float srcWidth, float srcHeight) {
	        float renderSRCX = srcX / size;
	        float renderSRCY = srcY / size;
	        float renderSRCWidth = srcWidth / size;
	        float renderSRCHeight = srcHeight / size;
	        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
	        GL11.glVertex2d(x + width, y);
	        GL11.glTexCoord2f(renderSRCX, renderSRCY);
	        GL11.glVertex2d(x, y);
	        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
	        GL11.glVertex2d(x, y + height);
	        GL11.glTexCoord2f(renderSRCX, renderSRCY + renderSRCHeight);
	        GL11.glVertex2d(x, y + height);
	        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY + renderSRCHeight);
	        GL11.glVertex2d(x + width, y + height);
	        GL11.glTexCoord2f(renderSRCX + renderSRCWidth, renderSRCY);
	        GL11.glVertex2d(x + width, y);
	    }

	    public int getStringHeight(String text) {
	        return getHeight();
	    }

	    public int getHeight() {
	        return (this.height - 8) / 2;
	    }

	    public int getStringWidth(String text) {
	        int width = 0;
	        for (char c : text.toCharArray()) {
	            if ((c < this.charData.length) && (c >= 0)) width += this.charData[c].width - 8 + this.charOffset;
	        }
	        return width / 2;
	    }
	    
	    public Font getFont() {
	        return this.font;
	    }

	    public void setFont(Font font) {
	        this.font = font;
	        texture = setupTexture(font, this.charData);
	    }
	
	 protected class CharData {
	        public int width;
	        public int height;
	        public int storedX;
	        public int storedY;

	        protected CharData() {
	        }
	    }
	
}