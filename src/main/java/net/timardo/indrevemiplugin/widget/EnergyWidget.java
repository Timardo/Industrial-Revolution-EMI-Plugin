package net.timardo.indrevemiplugin.widget;

import java.util.List;

import com.google.common.collect.Lists;

import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.Widget;
import dev.emi.emi.runtime.EmiDrawContext;
import me.steven.indrev.gui.widgets.machines.WCustomBarKt;
import me.steven.indrev.utils.UtilsKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.util.Formatting;

public class EnergyWidget extends Widget {
    
    private final Bounds bounds;
    private final int x;
    private final int y;
    private final long energy;
    private final long energyCapacity;
    
    public EnergyWidget(int x, int y, long energy, long energyCapacity) {
        this.bounds = new Bounds(x, y, 10, 64);
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.energyCapacity = energyCapacity;
    }

    @Override
    public Bounds getBounds() {
        return this.bounds;
    }
    
    @Override
    public List<TooltipComponent> getTooltip(int mouseX, int mouseY) {
        List<TooltipComponent> list = Lists.newArrayList();
        list.add(TooltipComponent.of(UtilsKt.translatable("gui.widget.energy").formatted(Formatting.BLUE).asOrderedText()));
        list.add(TooltipComponent.of(UtilsKt.literal(this.energy + " LF").asOrderedText()));
        return list;
    }

    @Override
    public void render(DrawContext draw, int mouseX, int mouseY, float delta) {
        EmiDrawContext context = EmiDrawContext.wrap(draw);
        context.resetColor();
        // draw empty bar
        context.drawTexture(WCustomBarKt.getENERGY_EMPTY().image(), this.x, this.y, 10, 64, 0, 0, 10, 64, 10, 64);
        
        // draw full bar
        context.drawTexture(
                WCustomBarKt.getENERGY_FULL().image(),
                this.x,
                this.y + (int)(64 * (1 - this.energyPercentage())),
                10,
                (int)(64 * this.energyPercentage()),
                0,
                (int)(64 * (1 - this.energyPercentage())),
                10,
                (int)(64 * this.energyPercentage()),
                10,
                64);
    }
    
    private float energyPercentage() {
        return Math.min((float)this.energy / this.energyCapacity, 1);
    }
}
