package me.luna.fastmc.mixin.patch;

import me.luna.fastmc.FastMcMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.glu.GLU;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Shadow @Final private static Logger LOGGER;

    @Redirect(method = "runGameLoop", at = @At(value = "INVOKE", target = "Ljava/lang/Thread;yield()V", remap = false))
    public void runGameLoop$Redirect$INVOKE$yield() {

    }

    /**
     * @author Luna
     * @reason Optimization
     */
    @Overwrite
    private void checkGLError(String message) {
        if (FastMcMod.INSTANCE.getConfig().getGlErrorDebug()) {
            int i = GlStateManager.glGetError();

            if (i != 0) {
                String s = GLU.gluErrorString(i);
                LOGGER.error("########## GL ERROR ##########");
                LOGGER.error("@ {}", message);
                LOGGER.error("{}: {}", i, s);
            }
        }
    }
}
