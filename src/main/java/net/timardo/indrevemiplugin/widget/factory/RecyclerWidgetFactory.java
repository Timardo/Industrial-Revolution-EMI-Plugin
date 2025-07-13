package net.timardo.indrevemiplugin.widget.factory;

import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.RecyclerRecipe;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

public class RecyclerWidgetFactory implements IWidgetFactory<RecyclerRecipe> {

    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<RecyclerRecipe> recipe) {
        // input slot
        holder.addSlot(getOrEmpty(recipe.getInputs(), 0), loc(2.8), loc(1.8));
        
        // filling arrow
        holder.addFillingArrow(loc(3.95), loc(1.8), recipe.getIRRecipe().getTicks() * 50);
        holder.addText(Text.of(recipe.getIRRecipe().getTicks() / 20.0 + "s"), loc(3.95), loc(1.8) - 15, -1, false);
        
        // output slot
        holder.addSlot(getOrEmpty(recipe.getOutputs(), 0), loc(5.44) - 4 + 4, loc(1.8) - 4).large(true).recipeContext(recipe);
    }
}
