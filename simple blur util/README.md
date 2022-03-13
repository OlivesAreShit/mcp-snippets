# Example:

```
import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import xyz.oliverwilliams.cacti.Client;
import xyz.oliverwilliams.cacti.gui.clickgui.SectionButton;
import xyz.oliverwilliams.cacti.gui.clickgui.SelectedSectionButton;
import xyz.oliverwilliams.cacti.utils.GUIUtils;

public class ClickGUICosmeticsScreen extends GuiScreen {
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		ScaledResolution sr = new ScaledResolution(mc);
		
        BlurUtil blurUtil = new BlurUtil();
        blurUtil.blurBackground();
		
		buttonList.add(new SelectedSectionButton(1, sr.getScaledWidth()/2 - 275, sr.getScaledHeight()/2 - 125, 124, 20, "Modules"));
		buttonList.add(new SectionButton(2, sr.getScaledWidth()/2 - 275, sr.getScaledHeight()/2 - 100, 124, 20, "HUD Editor"));
		buttonList.add(new SectionButton(3, sr.getScaledWidth()/2 - 275, sr.getScaledHeight()/2 - 75, 124, 20, "Quick Chats"));
		buttonList.add(new SectionButton(4, sr.getScaledWidth()/2 - 275, sr.getScaledHeight()/2 - 50, 124, 20, "Cosmetics"));
		buttonList.add(new SectionButton(5, sr.getScaledWidth()/2 - 275, sr.getScaledHeight()/2 - 25, 124, 20, "Settings"));
		
		super.initGui();
	}
	
	@Override
	public void onGuiClosed() {
        BlurUtil blurUtil = new BlurUtil();
        blurUtil.disableBlur();
		    super.onGuiClosed();
	}
	
}
```

# Error fixes


If you're using Optifine and get an error with the lines that have closeShaders() in them than replace closeShaders() with func_181022_b().

```  
     public void disableBlur() {
 		IMetadataSerializer metadataSerializer = new IMetadataSerializer();
 		IReloadableResourceManager mcResourceManager = new SimpleReloadableResourceManager(metadataSerializer);
        EntityRenderer entityRenderer = new EntityRenderer(mc, mcResourceManager);
         
        this.mc.entityRenderer.func_181022_b();
     }
```
