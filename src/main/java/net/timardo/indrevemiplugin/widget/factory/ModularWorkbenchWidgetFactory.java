package net.timardo.indrevemiplugin.widget.factory;

import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.ModuleRecipe;
import net.timardo.indrevemiplugin.recipes.IRMachineRecipe;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

public class ModularWorkbenchWidgetFactory implements IWidgetFactory<ModuleRecipe> {
    private static final float SHRINKING_FACTOR = 0.8f;
    private static final int X_OFFSET = 55;
    private static final int[][][] SLOT_LAYOUTS = new int[][][] {
            new int[][] {
                    { 2 * 18,0 }
            },
            new int[][] {
                    { 0,2 * 18 },
                    { 4 * 18, 2 * 18 }
            },
            new int[][] {
                    { 2 * 18, 0 },
                    { 0, 4 * 17 },
                    { 4 * 18, 4 * 17 }
            },
            new int[][] {
                    { 2 * 18, 0 },
                    { 0, 2 * 18 },
                    { 4 * 18, 2 * 18 },
                    { 2 * 18, 4 * 18 }
            },
            new int[][] {
                    { 2 * 18, 0 },
                    { 0, 2 * 18 },
                    { 4 * 18, 2 * 18 },
                    { 1 * 14, 4 * 18 },
                    { 3 * 20, 4 * 18 }
            },
            new int[][] {
                    { 2 * 18, 0 },
                    { 0 * 18, 1 * 18 },
                    { 4 * 18, 1 * 18 },
                    { 0 * 14, 3 * 18 },
                    { 4 * 18, 3 * 18 },
                    { 2 * 18, 4 * 18 }
            }
    };

    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<ModuleRecipe> recipe) {
        int layout = recipe.getInputs().size() - 1;
        
        // input slots
        for (int i = 0; i <= layout; i++) {
            holder.addSlot(getOrEmpty(recipe.getInputs(), i), (int)(SLOT_LAYOUTS[layout][i][0] * SHRINKING_FACTOR) + X_OFFSET, (int)(SLOT_LAYOUTS[layout][i][1] * SHRINKING_FACTOR));
        }
        
        // output slot
        holder.addSlot(getOrEmpty(recipe.getOutputs(), 0), (int)(2 * 18 * SHRINKING_FACTOR) + X_OFFSET - 4, (int)(2 * 18 * SHRINKING_FACTOR) - 4).large(true).recipeContext(recipe);
    }
}
