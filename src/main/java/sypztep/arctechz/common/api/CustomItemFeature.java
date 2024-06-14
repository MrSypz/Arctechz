package sypztep.arctechz.common.api;

import net.minecraft.client.render.model.json.ModelTransformationMode;

public interface CustomItemFeature {
    float[] getRotation();
    void setRotation(float x, float y, float z);

    float[] getTranslation();
    void setTranslation(float x, float y, float z);

    float[] getScale();
    void setScale(float x, float y, float z);
    ModelTransformationMode getmodelTransform();
    void setModelTransform(ModelTransformationMode transform);
}
