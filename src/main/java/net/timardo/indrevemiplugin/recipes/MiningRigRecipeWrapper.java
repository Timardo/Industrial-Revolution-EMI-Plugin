package net.timardo.indrevemiplugin.recipes;

import java.util.List;

import me.steven.indrev.components.CraftingComponent;
import me.steven.indrev.recipes.machines.IRRecipe;
import me.steven.indrev.recipes.machines.IRRecipeType;
import me.steven.indrev.recipes.machines.entries.InputEntry;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import me.steven.indrev.utils.IRFluidTank;
import me.steven.indrev.utils.UtilsKt;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MiningRigRecipeWrapper implements IRRecipe {
    private static final IRRecipe.IRRecipeSerializer<MiningRigRecipeWrapper> SERIALIZER = new IRRecipe.IRRecipeSerializer<MiningRigRecipeWrapper>((id, in, out, ticks) -> new MiningRigRecipeWrapper(id, in, out, ticks));
    public static final Identifier IDENTIFIER_WRAPPER = UtilsKt.identifier("mining_rig");
    public static final IRRecipeType<MiningRigRecipeWrapper> TYPE_WRAPPER = new IRRecipeType<MiningRigRecipeWrapper>(IDENTIFIER_WRAPPER);
    
    private final Identifier identifier;
    private final InputEntry[] inputs;
    private final OutputEntry[] output;
    private final int ticks;

    public MiningRigRecipeWrapper(Identifier identifier, InputEntry[] inputDrillHead, OutputEntry[] outputStack, int ticks) {
        this.identifier = identifier;
        this.inputs = inputDrillHead;
        this.output = outputStack;
        this.ticks = ticks;
    }
    
    @Override
    public Identifier getIdentifier() {
        return this.identifier;
    }
    
    @Override
    public InputEntry[] getInput() {
        return this.inputs;
    }
    
    @Override
    public OutputEntry[] getOutputs() {
        return this.output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public boolean canStart(CraftingComponent<?> component) {
        return DefaultImpls.canStart(this, component);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemStack craft(Inventory inv, DynamicRegistryManager registryManager) {
        return DefaultImpls.craft(this, inv, registryManager);
    }

    @Override
    public List<ItemStack> craft(Random random) {
        return DefaultImpls.craft(this, random);
    }

    @Override
    public Identifier getId() {
        return DefaultImpls.getId(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return DefaultImpls.getIngredients(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return DefaultImpls.getOutput(this, registryManager);
    }

    @Override
    public int getTicks() {
        return this.ticks;
    }

    @Override
    public IRRecipeType<?> getType() {
        return TYPE_WRAPPER;
    }

    @Override
    public boolean isEmpty() {
        return DefaultImpls.isEmpty(this);
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return DefaultImpls.isIgnoredInRecipeBook(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean matches(Inventory inv, World world) {
        return DefaultImpls.matches(this, inv, world);
    }

    @Override
    public boolean matches(ItemStack inv, List<IRFluidTank> fluidVolume) {
        return DefaultImpls.matches(this, inv, fluidVolume);
    }

    @Override
    public boolean matches(List<ItemStack> stack, List<IRFluidTank> fluidVolume) {
        return DefaultImpls.matches(this, stack, fluidVolume);
    }
    
    
}
