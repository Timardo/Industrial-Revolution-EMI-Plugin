package net.timardo.indrevemiplugin;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.IRFluidRecipe;
import me.steven.indrev.recipes.machines.IRRecipe;
import me.steven.indrev.recipes.machines.entries.InputEntry;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.minecraft.util.Identifier;

public class IRMachineRecipe<T extends IRRecipe> implements EmiRecipe {

    private T recipe;
    private IRMachineCategory<T> category;
    private List<EmiIngredient> inputs;
    private List<EmiStack> outputs;
    
    public IRMachineRecipe(T recipe, IRMachineCategory<T> category) {
        this.recipe = recipe;
        this.category = category;
        
        this.inputs = new ArrayList<EmiIngredient>();
        this.outputs = new ArrayList<EmiStack>();
        
        fillInputs();
        fillOutputs();
    }
    
    @Override
    public EmiRecipeCategory getCategory() {
        return this.category;
    }

    @Override
    public @Nullable Identifier getId() {
        return this.recipe.getId();
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return this.inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return this.outputs;
    }

    @Override
    public int getDisplayWidth() {
        return 170;
    }

    @Override
    public int getDisplayHeight() {
        return 75;
    }
    
    public T getIRRecipe() {
        return this.recipe;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        this.category.addWidgets(widgets, this);
    }

    private void fillInputs() {
        if (this.recipe instanceof IRFluidRecipe fluidRecipe) {
            ResourceAmount<FluidVariant>[] fluidInput = fluidRecipe.getFluidInput();
            
            if (fluidInput.length > 0) {
                this.inputs.add(EmiStack.of(((FluidVariant)fluidInput[0].resource()).getFluid(), fluidInput[0].amount()));
            }
        }
        
        for (InputEntry ingredient : this.recipe.getInput()) {
            this.inputs.add(EmiIngredient.of(ingredient.getIngredient(), ingredient.getCount()));
        }
    }
    
    private void fillOutputs() {
        if (recipe instanceof IRFluidRecipe fluidRecipe) {
            ResourceAmount<FluidVariant>[] fluidOutput = fluidRecipe.getFluidOutput();
            
            if (fluidOutput.length > 0) {
                this.outputs.add(EmiStack.of(((FluidVariant)fluidOutput[0].resource()).getFluid(), fluidOutput[0].amount()));
            }
        }
        
        for (OutputEntry entry : recipe.getOutputs()) {
            this.outputs.add(EmiStack.of(entry.getStack()));
        }
    }
}
