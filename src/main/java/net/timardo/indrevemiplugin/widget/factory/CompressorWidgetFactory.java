package net.timardo.indrevemiplugin.widget.factory;

import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.CompressorRecipe;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.recipes.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

public class CompressorWidgetFactory implements IWidgetFactory<CompressorRecipe> {

    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<CompressorRecipe> recipe) {
        // input slot
        holder.addSlot(getOrEmpty(recipe.getInputs(), 0), loc(3.3), loc(1.8));
        
        // filling arrow
        holder.addFillingArrow(loc(4.45), loc(1.8), recipe.getIRRecipe().getTicks() * 50);
        holder.addText(Text.of(recipe.getIRRecipe().getTicks() / 20.0 + "s"), loc(4.45), loc(1.8) - 15, -1, false);
        
        // output slot
        holder.addSlot(getOrEmpty(recipe.getOutputs(), 0), loc(5.94), loc(1.8) - 4).large(true).recipeContext(recipe);
    }
}
