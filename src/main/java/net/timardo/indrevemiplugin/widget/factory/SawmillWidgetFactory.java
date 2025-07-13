package net.timardo.indrevemiplugin.widget.factory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.SawmillRecipe;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

public class SawmillWidgetFactory implements IWidgetFactory<SawmillRecipe> {

    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<SawmillRecipe> recipe) {
        // input slot
        holder.addSlot(getOrEmpty(recipe.getInputs(), 0), loc(3.0), loc(1.8));
        
        // filling arrow
        holder.addFillingArrow(loc(4.15), loc(1.8), recipe.getIRRecipe().getTicks() * 50);
        holder.addText(Text.of(recipe.getIRRecipe().getTicks() / 20.0 + "s"), loc(4.15), loc(1.8) - 15, -1, false);
        
        // output slots
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                EmiIngredient output = getOrEmpty(recipe.getOutputs(), j + 2 * i);
                output.setChance((float)getChance(recipe.getIRRecipe().getOutputs(), j + 2 * i));
                holder.addSlot(output, loc(6.6) + 4 + 18 * j, loc(1.8) - 4 + 18 * i).recipeContext(recipe);
            }
        }
    }
}
