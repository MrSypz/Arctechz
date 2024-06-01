package sypztep.arctechz.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class PatchNoteScreen extends Screen {
    public static final String LinkPatchNote = "http://mc-arctechz.trueddns.com:25711/";

    public PatchNoteScreen() {
        super(Text.literal("Patch Notes"));
    }

    @Override
    protected void init() {
        ButtonWidget button1 = ButtonWidget.builder(Text.literal("Continue"), button -> {
                    close();
                })
                .dimensions(width / 2 - 100, height / 3, 200, 20)
                .tooltip(Tooltip.of(Text.literal("I want to play a game!")))
                .build();
        addDrawableChild(button1);

        ButtonWidget linkButton = ButtonWidget.builder(Text.literal("Open Patch Notes"), button -> {
                    Util.getOperatingSystem().open(LinkPatchNote);
                })
                .dimensions(width / 2 - 100, height / 2 + 10, 200, 20).tooltip(Tooltip.of(Text.literal("Read a patch note")))
                .build();
        addDrawableChild(linkButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Patch Notes"), width / 2, 20, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Click here to read"), width / 2, height / 2 - 10, 0xffffff);
    }


    @Override
    public boolean shouldPause() {
        return false;
    }
}
