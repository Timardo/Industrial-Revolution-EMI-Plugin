package net.timardo.indrevemiplugin.widget.factory;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.PulverizerRecipe;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.recipes.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

public class PulverizerWidgetFactory implements IWidgetFactory<PulverizerRecipe> {

    // TODO: being able to switch between different workstations (MK1-MK4 + Factory) to see different speeds/energy consumption
    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<PulverizerRecipe> recipe) {
        // input slot
        holder.addSlot(getOrEmpty(recipe.getInputs(), 0), loc(3.3), loc(1.2));
        
        // filling arrow
        holder.addFillingArrow(loc(4.4), loc(1.2), recipe.getIRRecipe().getTicks() * 50);
        holder.addText(Text.of(recipe.getIRRecipe().getTicks() / 20.0 + "s"), loc(4.4), loc(1.2) - 15, -1, false);
        
        // primary output slot                          -4 because of large slot +4 because the default arrow is 4 pixels wider
        holder.addSlot(getOrEmpty(recipe.getOutputs(), 0), loc(5.84) - 4 + 4, loc(1.2) - 4).large(true).recipeContext(recipe);
        
        // secondary output slot
        EmiIngredient secondaryOutput = getOrEmpty(recipe.getOutputs(), 1);
        
        // chance is inverted in the original source which is most probably a bug
        if (recipe.getOutputs().size() > 1) {
            secondaryOutput.setChance(1.0f - (float)recipe.getIRRecipe().getOutputs()[1].getChance());
        }
        
        holder.addSlot(secondaryOutput, loc(5.84) + 4, loc(2.5)).recipeContext(recipe);
    }
}
