package net.timardo.indrevemiplugin.widget.factory;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.stack.FluidEmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.FluidInfuserRecipe;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.text.Text;
import net.timardo.indrevemiplugin.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.CustomTankWidget;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

public class FluidInfuserWidgetFactory implements IWidgetFactory<FluidInfuserRecipe> {

    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<FluidInfuserRecipe> recipe) {
        int i = 0;
        EmiIngredient input1 = getOrEmpty(recipe.getInputs(), i++);
        
        // first ingredient should be always fluid because fluids are added first
        if (!(input1 instanceof FluidEmiStack)) {
            i--;
            input1 = EmiStack.EMPTY;
        }
        
        // input slot
        holder.addSlot(getOrEmpty(recipe.getInputs(), i), loc(3.7), loc(1.8));
        
        // input tank
        holder.add(new CustomTankWidget(input1, loc(2.5), loc(1.0), 16, 43, FluidConstants.BUCKET * 8));
        
        // filling arrow
        holder.addFillingArrow(loc(5.0), loc(1.8), recipe.getIRRecipe().getTicks() * 50);
        holder.addText(Text.of(recipe.getIRRecipe().getTicks() / 20.0 + "s"), loc(5.0), loc(1.8) - 15, -1, false);
        
        i = 0;
        EmiIngredient output1 = getOrEmpty(recipe.getOutputs(), i++);
        
        // first ingredient should be always fluid because fluids are added first
        if (!(output1 instanceof FluidEmiStack)) {
            i--;
            output1 = EmiStack.EMPTY;
        }
        
        // output slot
        holder.addSlot(getOrEmpty(recipe.getOutputs(), i), loc(6.4) + 4, loc(1.8)).recipeContext(recipe);
        
        // output tank
        CustomTankWidget fluidOutput = holder.add(new CustomTankWidget(output1, loc(7.7) + 4, loc(1.0), 16, 43, FluidConstants.BUCKET * 8));
        fluidOutput.recipeContext(recipe);
    }
}
