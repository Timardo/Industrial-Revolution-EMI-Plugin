package net.timardo.indrevemiplugin.widget.factory;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.registry.IRItemRegistry;
import net.minecraft.recipe.Ingredient;
import net.timardo.indrevemiplugin.recipes.IRMachineRecipe;
import net.timardo.indrevemiplugin.recipes.MiningRigRecipeWrapper;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

public class MiningRigWidgetFactory implements IWidgetFactory<MiningRigRecipeWrapper> {
    private static final EmiIngredient DRILL_HEADS = EmiIngredient.of(Ingredient.ofItems(
            IRItemRegistry.INSTANCE.getSTONE_DRILL_HEAD(),
            IRItemRegistry.INSTANCE.getIRON_DRILL_HEAD(),
            IRItemRegistry.INSTANCE.getDIAMOND_DRILL_HEAD(),
            IRItemRegistry.INSTANCE.getNETHERITE_DRILL_HEAD()
            ));
    @Override
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<MiningRigRecipeWrapper> recipe) {
        // mining drillhead component
        holder.addSlot(DRILL_HEADS, 2, 2);
        
        // filling arrow
        holder.addFillingArrow(24, 2, 5000);
        
        // output ore
        holder.addSlot(getOrEmpty(recipe.getOutputs(), 0), 52, 2).recipeContext(recipe);
    }
}
