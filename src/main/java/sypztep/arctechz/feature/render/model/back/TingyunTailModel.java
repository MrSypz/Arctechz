package sypztep.arctechz.feature.render.model.back;

import net.minecraft.client.model.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import sypztep.arctechz.feature.ArctechzFeature;
import sypztep.arctechz.feature.render.feature.WiggleFeature;

public class TingyunTailModel extends BackCosmeticModel implements WiggleFeature {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(ArctechzFeature.id("tingyuntail"), "main");

	// Store previous yaw and rotation for each tail segment
	private float prevYaw = 0.0F;
	private float tailRotation = 0.0F;
	private float boneRotation = 0.0F;
	private float bone2Rotation = 0.0F;
	private float bone3Rotation = 0.0F;
	private float bone4Rotation = 0.0F;

	private float tailVelocity = 0f;
	private float boneVelocity = 0f;
	private float bone2Velocity = 0f;
	private float bone3Velocity = 0f;
	private float bone4Velocity = 0f;

	// เปลี่ยนชื่อจาก yawRot → pitchRot
	private float tailPitch = 0f;
	private float bonePitch = 0f;
	private float bone2Pitch = 0f;
	private float bone3Pitch = 0f;
	private float bone4Pitch = 0f;

	// ค่า default pitch ของ model
	private final float tailDefaultPitch;
	private final float boneDefaultPitch;
	private final float bone2DefaultPitch;
	private final float bone3DefaultPitch;
	private final float bone4DefaultPitch;


	// Cache the model parts
	private final ModelPart tail;
	private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart bone4;


	public TingyunTailModel(EntityRendererFactory.Context ctx) {
		super(ctx, MODEL_LAYER);

		this.tail = this.back.getChild("tail");
		this.bone = this.tail.getChild("bone");
		this.bone2 = this.bone.getChild("bone2");
		this.bone3 = this.bone2.getChild("bone3");
		this.bone4 = this.bone3.getChild("bone4");

		// เก็บ default yaw
		this.tailDefaultPitch = tail.pitch;
		this.boneDefaultPitch = bone.pitch;
		this.bone2DefaultPitch = bone2.pitch;
		this.bone3DefaultPitch = bone3.pitch;
		this.bone4DefaultPitch = bone4.pitch;
	}


	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData back = modelPartData.addChild("back", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData backthing = back.addChild("backthing", ModelPartBuilder.create(), ModelTransform.of(2.5774F, 15.0995F, 3.0F, 0.1725F, 0.0427F, 0.0226F));

		backthing.addChild("bone5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tail = back.addChild("tail", ModelPartBuilder.create().uv(12, 0).cuboid(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.6742F, 2.2761F, 0.7854F, 0.0F, 0.0F));

		ModelPartData bone = tail.addChild("bone", ModelPartBuilder.create().uv(0, 19).cuboid(-2.5F, 0.0F, -1.5F, 5.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create(), ModelTransform.of(0.0F, 1.75F, 0.0F, -0.1309F, 0.0F, 0.0F));

		ModelPartData bone3 = bone2.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -0.5935F, -0.2274F));

		ModelPartData bone4 = bone3.addChild("bone4", ModelPartBuilder.create().uv(29, 0).cuboid(-2.5F, 5.25F, -2.0F, 5.0F, 3.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 13).cuboid(-1.0F, 6.25F, -1.5F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
				.uv(12, 5).cuboid(-3.0F, -0.75F, -2.0F, 6.0F, 6.0F, 4.0F, new Dilation(0.0F))
				.uv(18, 8).cuboid(0.0F, -3.75F, -3.0F, 0.0F, 15.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5935F, 0.2274F, -0.3927F, 0.0F, 0.0F));

		bone4.addChild("flap_r1", ModelPartBuilder.create().uv(32, -2).cuboid(-0.5F, -3.75F, -4.0F, 0.0F, 15.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		back.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public void applyTailPhysics(AbstractClientPlayerEntity entity, float tickDelta) {
		// -----------------------
		// Roll physics (เหมือนเดิม)
		// -----------------------
		float currentYaw = MathHelper.lerp(tickDelta, entity.prevBodyYaw, entity.bodyYaw);
		float yawDelta = currentYaw - prevYaw;
		while (yawDelta > 180F) yawDelta -= 360F;
		while (yawDelta < -180F) yawDelta += 360F;

		float targetRoll = MathHelper.clamp(yawDelta * 2F, -20F, 20F);
		float targetRollRad = (float) Math.toRadians(targetRoll);

		// Spring/damping constants
		float stiffness = 0.025f;
		float damping = 0.2f;

		// Tail roll
		tailVelocity += (targetRollRad - tailRotation) * stiffness - tailVelocity * damping;
		tailRotation += tailVelocity;
		tail.roll = tailRotation;

		// Cascade roll
		boneVelocity += (tailRotation - boneRotation) * stiffness - boneVelocity * damping;
		boneRotation += boneVelocity;
		bone.roll = boneRotation;

		bone2Velocity += (boneRotation - bone2Rotation) * stiffness - bone2Velocity * damping;
		bone2Rotation += bone2Velocity;
		bone2.roll = bone2Rotation;

		bone3Velocity += (bone2Rotation - bone3Rotation) * stiffness - bone3Velocity * damping;
		bone3Rotation += bone3Velocity;
		bone3.roll = bone3Rotation;

		bone4Velocity += (bone3Rotation - bone4Rotation) * stiffness - bone4Velocity * damping;
		bone4Rotation += bone4Velocity;
		bone4.roll = bone4Rotation;

		prevYaw = currentYaw;

		float dx = (float)(entity.getX() - entity.prevX);
		float dz = (float)(entity.getZ() - entity.prevZ);
		float horizontalSpeed = MathHelper.sqrt(dx * dx + dz * dz);

		if (horizontalSpeed > 0.001f) {
			// หางยกขึ้นตาม speed
			float pitchOffset = MathHelper.clamp(horizontalSpeed * 2f, 0f, 0.2f); // 0.5 rad ~ 28°

			// Lerp cascade
			tailPitch = MathHelper.lerp(0.1f, tailPitch, pitchOffset);
			bonePitch = MathHelper.lerp(0.1f, bonePitch, tailPitch);
			bone2Pitch = MathHelper.lerp(0.1f, bone2Pitch, bonePitch);
			bone3Pitch = MathHelper.lerp(0.1f, bone3Pitch, bone2Pitch);
			bone4Pitch = MathHelper.lerp(0.1f, bone4Pitch, bone3Pitch);
		} else {
			// idle → กลับไป default pitch ของ model
			tailPitch = MathHelper.lerp(0.05f, tailPitch, tailDefaultPitch);
			bonePitch = MathHelper.lerp(0.05f, bonePitch, boneDefaultPitch);
			bone2Pitch = MathHelper.lerp(0.05f, bone2Pitch, bone2DefaultPitch);
			bone3Pitch = MathHelper.lerp(0.05f, bone3Pitch, bone3DefaultPitch);
			bone4Pitch = MathHelper.lerp(0.05f, bone4Pitch, bone4DefaultPitch);
		}

// Apply pitch
		tail.pitch = tailPitch;
		bone.pitch = bonePitch;
		bone2.pitch = bone2Pitch;
		bone3.pitch = bone3Pitch;
		bone4.pitch = bone4Pitch;
	}
}