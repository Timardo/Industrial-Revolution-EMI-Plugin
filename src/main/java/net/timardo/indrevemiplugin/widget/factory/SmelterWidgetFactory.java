package net.timardo.indrevemiplugin.widget.factory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.SmelterRecipe;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.CustomTankWidget;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

public class SmelterWidgetFactory implements IWidgetFactory<SmelterRecipe> {

    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<SmelterRecipe> recipe) {
        // input slot
        holder.addSlot(getOrEmpty(recipe.getInputs(), 0), loc(3.5), loc(1.8));
        
        // filling arrow
        holder.addFillingArrow(loc(4.8), loc(1.8), recipe.getIRRecipe().getTicks() * 50);
        holder.addText(Text.of(recipe.getIRRecipe().getTicks() / 20.0 + "s"), loc(4.8), loc(1.8) - 15, -1, false);
        
        // output tank
        holder.add(new CustomTankWidget(getOrEmpty(recipe.getOutputs(), 0), loc(6.2) + 4, loc(1.0), 16, 43, FluidConstants.BUCKET * 8));
    }
}
