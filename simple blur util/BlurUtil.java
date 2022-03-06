
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;

public class BlurUtil {

    /**
    * @author Oliver Williams (OlivesAreShit)
    * See README for example.
    */
	
	Minecraft mc = Minecraft.getMinecraft();
    
     public void blurBackground() {
 		IMetadataSerializer metadataSerializer = new IMetadataSerializer();
 		IReloadableResourceManager mcResourceManager = new SimpleReloadableResourceManager(metadataSerializer);
        EntityRenderer entityRenderer = new EntityRenderer(mc, mcResourceManager);
         
        this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
     }
     
     public void disableBlur() {
 		IMetadataSerializer metadataSerializer = new IMetadataSerializer();
 		IReloadableResourceManager mcResourceManager = new SimpleReloadableResourceManager(metadataSerializer);
        EntityRenderer entityRenderer = new EntityRenderer(mc, mcResourceManager);
         
        this.mc.entityRenderer.closeShaders();
     }
     
}
