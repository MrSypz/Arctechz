package sypztep.arctechz.common.util;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ArctechzUtil {
    public static Text createCustomName(int count, String name) {
        return Text.literal(">")
                .formatted(Formatting.GOLD)
                .append(Text.literal(" x" + count + " ")
                        .formatted(Formatting.RED, Formatting.BOLD))
                .append(Text.literal(name)
                        .formatted(Formatting.GRAY));
    }
}
