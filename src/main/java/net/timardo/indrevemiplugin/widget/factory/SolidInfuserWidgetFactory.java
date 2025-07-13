package net.timardo.indrevemiplugin.widget.factory;

import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.InfuserRecipe;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

public class SolidInfuserWidgetFactory implements IWidgetFactory<InfuserRecipe> {

    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<InfuserRecipe> recipe) {
        // input slot 1
        holder.addSlot(getOrEmpty(recipe.getInputs(), 0), loc(2.9), loc(1.8));
        
        // input slot 2
        holder.addSlot(getOrEmpty(recipe.getInputs(), 1), loc(4.0), loc(1.8));
        
        // filling arrow
        holder.addFillingArrow(loc(5.15), loc(1.8), recipe.getIRRecipe().getTicks() * 50);
        holder.addText(Text.of(recipe.getIRRecipe().getTicks() / 20.0 + "s"), loc(5.15), loc(1.8) - 15, -1, false);
        
        // output slot
        holder.addSlot(getOrEmpty(recipe.getOutputs(), 0), loc(6.6), loc(1.8) - 4).large(true).recipeContext(recipe);
    }
}
