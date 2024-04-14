package ru.aloyenz.effectsapi.mixins;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.aloyenz.effectsapi.ShaderManager;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Unique
    private boolean abberationEffectAPI$isShaderManagerInitialized = false;

    @Inject(method = "resize", at = @At("TAIL"))
    public void resizeMixin(int p_147704_1_, int p_147704_2_, CallbackInfo ci) {
        if (abberationEffectAPI$isShaderManagerInitialized) {
            ShaderManager.getInstance().resize(p_147704_1_, p_147704_2_);
        }
    }

    @Inject(method = "renderLevel", at = @At("TAIL"))
    public void renderLevelMixin(float p_228378_1_, long p_228378_2_, MatrixStack p_228378_4_, CallbackInfo ci) {
        ShaderManager.getInstance().drawAllEffects(p_228378_1_);
        abberationEffectAPI$isShaderManagerInitialized = true;
    }
}
