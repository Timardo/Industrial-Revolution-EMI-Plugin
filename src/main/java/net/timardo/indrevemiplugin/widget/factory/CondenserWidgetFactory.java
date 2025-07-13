package net.timardo.indrevemiplugin.widget.factory;

import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.CondenserRecipe;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.CustomTankWidget;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

public class CondenserWidgetFactory implements IWidgetFactory<CondenserRecipe> {

    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<CondenserRecipe> recipe) {
        // input tank
        holder.add(new CustomTankWidget(getOrEmpty(recipe.getInputs(), 0), loc(2.8), loc(1.0), 16, 43, FluidConstants.BUCKET * 8));
        
        // filling arrow
        holder.addFillingArrow(loc(4.0), loc(1.8), recipe.getIRRecipe().getTicks() * 50);
        holder.addText(Text.of(recipe.getIRRecipe().getTicks() / 20.0 + "s"), loc(4.0), loc(1.8) - 15, -1, false);
        
        // output slot
        holder.addSlot(getOrEmpty(recipe.getOutputs(), 0), loc(5.7), loc(1.8) - 4).large(true).recipeContext(recipe);
    }
}
