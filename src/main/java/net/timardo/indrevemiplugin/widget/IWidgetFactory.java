package net.timardo.indrevemiplugin.widget;

import java.util.List;

import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import me.steven.indrev.recipes.machines.IRRecipe;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import net.timardo.indrevemiplugin.IRMachineRecipe;

public interface IWidgetFactory<T extends IRRecipe> {
    void addWidgets(WidgetHolder holder, IRMachineRecipe<T> recipe);
    
    default EmiIngredient getOrEmpty(List<? extends EmiIngredient> ingredients, int index) {
        return ingredients.size() <= index ? EmiStack.EMPTY : ingredients.get(index);
    }
    
    default double getChance(OutputEntry[] ingredients, int index) {
        return ingredients.length <= index ? 1 : ingredients[index].getChance();
    }
    
    static int loc(double gridLocation) {
        return (int)(gridLocation * 18);
    }
}
