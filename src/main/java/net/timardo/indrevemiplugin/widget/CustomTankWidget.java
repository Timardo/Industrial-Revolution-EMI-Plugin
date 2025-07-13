package net.timardo.indrevemiplugin.widget;

import dev.emi.emi.EmiRenderHelper;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.TankWidget;
import dev.emi.emi.runtime.EmiDrawContext;
import me.steven.indrev.gui.widgets.machines.WCustomBarKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class CustomTankWidget extends TankWidget {

    public CustomTankWidget(EmiIngredient stack, int x, int y, int width, int height, long capacity) {
        super(stack, x, y, width, height, capacity);
        customBackground(WCustomBarKt.getTANK_BOTTOM().image(), 0, 0, 16, 43);
    }
    
    @Override
    public SlotWidget customBackground(Identifier id, int u, int v, int width, int height) {
        super.customBackground(id, u, v, width, height);
        this.textureId = id;
        return this;
    }
    
    @Override
    public void drawStack(DrawContext draw, int mouseX, int mouseY, float delta) {
        super.drawStack(draw, mouseX, mouseY, delta);
        Bounds bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        draw.drawTexture(WCustomBarKt.getTANK_TOP().image(), bounds.x(), bounds.y(), width, height, u, v, width, height, width, height);
    }
    
    @Override
    public void drawBackground(DrawContext draw, int mouseX, int mouseY, float delta) {
        EmiDrawContext context = EmiDrawContext.wrap(draw);
        Bounds bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (drawBack) {
            if (textureId != null) {
                context.drawTexture(textureId, bounds.x(), bounds.y(), width, height, u, v, width, height, width, height);
            } else {
                int v = getStack().getChance() != 1 ? bounds.height() : 0;
                if (output) {
                    context.drawTexture(EmiRenderHelper.WIDGETS, bounds.x(), bounds.y(), 26, 26, 18, v, 26, 26, 256, 256);
                } else {
                    context.drawTexture(EmiRenderHelper.WIDGETS, bounds.x(), bounds.y(), 18, 18, 0, v, 18, 18, 256, 256);
                }
            }
        }
    }
}
