package net.timardo.indrevemiplugin.widget.factory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.config.MachineConfig;
import me.steven.indrev.recipes.machines.LaserRecipe;
import me.steven.indrev.registry.MachineRegistry;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.recipes.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

public class LaserWidgetFactory implements IWidgetFactory<LaserRecipe> {

    private static final MachineRegistry LASER_REGISTRY = MachineRegistry.Companion.getLASER_EMITTER_REGISTRY();
    
    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<LaserRecipe> recipe) {
        // input slot
        holder.addSlot(getOrEmpty(recipe.getInputs(), 0), loc(3.0), loc(1.8));
        
        int actualTime = (int)(recipe.getIRRecipe().getTicks() / ((MachineConfig)LASER_REGISTRY.config(LASER_REGISTRY.getTiers()[0])).getEnergyCost());
        
        // filling arrow
        holder.addFillingArrow(loc(4.15), loc(1.8), actualTime * 50);
        holder.addText(Text.of(actualTime / 20.0 + "s"), loc(4.45), loc(1.8) - 15, -1, false);
        
        // output slot
        holder.addSlot(getOrEmpty(recipe.getOutputs(), 0), loc(5.6) + 4, loc(1.8)).recipeContext(recipe);
    }
}
