package ru.aloyenz.effectsapi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.aloyenz.effectsapi.mixins.IShaderGetter;

import java.io.IOException;

@OnlyIn(Dist.CLIENT)
public class ShaderManager {

    private static ShaderManager INSTANCE;

    private static ResourceLocation CHROMATIC_ABERRATION;
    private static ResourceLocation LINEAR_CHROMATIC_ABERRATION;
    private static ResourceLocation WHITE_AND_BLACK;
    private static ResourceLocation COLOR_MULTIPLIER;
    private static ResourceLocation COLOR_ADD;

    private final ShaderGroup aberration;
    private final ShaderGroup linearAberration;
    private final ShaderGroup whiteAndBlack;
    private final ShaderGroup colorMultiplier;
    private final ShaderGroup colorAdd;

    private final SettingsManager settingsManager = EffectsAPI.getInstance().getSettingsManager();

    public static ShaderManager getInstance() {
        if (INSTANCE == null) {
            CHROMATIC_ABERRATION = new ResourceLocation(EffectsAPI.MOD_ID, "shaders/post/aberration.json");
            LINEAR_CHROMATIC_ABERRATION = new ResourceLocation(EffectsAPI.MOD_ID, "shaders/post/linear_aberration.json");
            WHITE_AND_BLACK = new ResourceLocation(EffectsAPI.MOD_ID, "shaders/post/white_and_black.json");
            COLOR_MULTIPLIER = new ResourceLocation(EffectsAPI.MOD_ID, "shaders/post/color_mul.json");
            COLOR_ADD = new ResourceLocation(EffectsAPI.MOD_ID, "shaders/post/color_add.json");

            try {
                INSTANCE = new ShaderManager(Minecraft.getInstance());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return INSTANCE;
    }

    public ShaderManager(Minecraft minecraft) throws IOException {
        aberration = new ShaderGroup(minecraft.getTextureManager(),
                minecraft.getResourceManager(), minecraft.getMainRenderTarget(), CHROMATIC_ABERRATION);
        linearAberration = new ShaderGroup(minecraft.getTextureManager(),
                minecraft.getResourceManager(), minecraft.getMainRenderTarget(), LINEAR_CHROMATIC_ABERRATION);
        whiteAndBlack = new ShaderGroup(minecraft.getTextureManager(),
                minecraft.getResourceManager(), minecraft.getMainRenderTarget(), WHITE_AND_BLACK);
        colorMultiplier = new ShaderGroup(minecraft.getTextureManager(),
                minecraft.getResourceManager(), minecraft.getMainRenderTarget(), COLOR_MULTIPLIER);
        colorAdd = new ShaderGroup(minecraft.getTextureManager(),
                minecraft.getResourceManager(), minecraft.getMainRenderTarget(), COLOR_ADD);

        resize(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
    }

    public void resize(int width, int height) {
        aberration.resize(width, height);
        linearAberration.resize(width, height);
        whiteAndBlack.resize(width, height);
        colorMultiplier.resize(width, height);
        colorAdd.resize(width, height);
    }

    public void drawAllEffects(float partialTicks) {
        drawAberration(partialTicks);
        drawLinearAberration(partialTicks);
        drawWhiteAndBlack(partialTicks);
        drawColorMultiplies(partialTicks);
        drawColorAdd(partialTicks);
    }

    private void drawColorAdd(float partialTicks) {
        Vector3f rgb = settingsManager.getColorAdd();
        for (Shader shader : ((IShaderGetter) colorAdd).getPasses()) {
            shader.getEffect().safeGetUniform("RAdd").set(rgb.x());
            shader.getEffect().safeGetUniform("GAdd").set(rgb.y());
            shader.getEffect().safeGetUniform("BAdd").set(rgb.z());
        }
        colorAdd.process(partialTicks);
    }

    private void drawColorMultiplies(float partialTicks) {
        Vector3f rgb = settingsManager.getColorMultiplier();
        for (Shader shader : ((IShaderGetter) colorMultiplier).getPasses()) {
            shader.getEffect().safeGetUniform("RMultiplier").set(rgb.x());
            shader.getEffect().safeGetUniform("GMultiplier").set(rgb.y());
            shader.getEffect().safeGetUniform("BMultiplier").set(rgb.z());
        }
        colorMultiplier.process(partialTicks);
    }

    private void drawLinearAberration(float partialTicks) {
        for (Shader shader : ((IShaderGetter) linearAberration).getPasses()) {
            shader.getEffect().safeGetUniform("Multiplier").set(settingsManager.getLinearAberrationMultiplier());
        }
        linearAberration.process(partialTicks);
    }

    private void drawAberration(float partialTicks) {
        for (Shader shader : ((IShaderGetter) aberration).getPasses()) {
            shader.getEffect().safeGetUniform("Multiplier").set(settingsManager.getAberrationMultiplier());
        }
        aberration.process(partialTicks);
    }

    private void drawWhiteAndBlack(float partialTicks) {
        for (Shader shader : ((IShaderGetter) whiteAndBlack).getPasses()) {
            shader.getEffect().safeGetUniform("Multiplier").set(settingsManager.getWhiteAndBlackMultiplier());
        }
        whiteAndBlack.process(partialTicks);
    }
}
