package ru.aloyenz.effectsapi;

import net.minecraft.util.math.vector.Vector3f;

public class SettingsManager {

    private float aberrationMultiplier = 0F;
    private float linearAberrationMultiplier = 0F;
    private float whiteAndBlackMultiplier = 0F;
    private Vector3f colorMultiplier = new Vector3f(1F, 1F, 1F);
    private Vector3f colorAdd = new Vector3f(0F, 0F, 0F);

    public void setAberrationMultiplier(float aberrationMultiplier) {
        this.aberrationMultiplier = aberrationMultiplier;
    }

    public float getAberrationMultiplier() {
        return aberrationMultiplier;
    }

    public void setLinearAberrationMultiplier(float linearAberrationMultiplier) {
        this.linearAberrationMultiplier = linearAberrationMultiplier;
    }

    public float getLinearAberrationMultiplier() {
        return linearAberrationMultiplier;
    }

    public void setWhiteAndBlackMultiplier(float whiteAndBlackMultiplier) {
        this.whiteAndBlackMultiplier = whiteAndBlackMultiplier;
    }

    public float getWhiteAndBlackMultiplier() {
        return whiteAndBlackMultiplier;
    }

    public Vector3f getColorMultiplier() {
        return colorMultiplier;
    }

    public void setColorMultiplier(Vector3f colorMultiplier) {
        this.colorMultiplier = colorMultiplier;
    }

    public Vector3f getColorAdd() {
        return colorAdd;
    }

    public void setColorAdd(Vector3f colorAdd) {
        this.colorAdd = colorAdd;
    }
}
