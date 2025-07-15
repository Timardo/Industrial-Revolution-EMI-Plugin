package net.timardo.indrevemiplugin;

import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import net.timardo.indrevemiplugin.config.IREmiPluginConfig;
import net.timardo.indrevemiplugin.recipes.IRMachineRecipe;
import net.timardo.indrevemiplugin.recipes.MiningRigRecipeWrapper;
import net.timardo.indrevemiplugin.widget.factory.CompressorWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.CondenserWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.FluidInfuserWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.LaserWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.MiningRigWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.ModularWorkbenchWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.PulverizerWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.RecyclerWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.SawmillWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.SmelterWidgetFactory;
import net.timardo.indrevemiplugin.widget.factory.SolidInfuserWidgetFactory;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.registry.EmiStackList;
import kotlin.UninitializedPropertyAccessException;
import me.steven.indrev.api.machines.Tier;
import me.steven.indrev.config.IRConfig;
import me.steven.indrev.config.MiningRigConfig;
import me.steven.indrev.recipes.machines.CompressorRecipe;
import me.steven.indrev.recipes.machines.CondenserRecipe;
import me.steven.indrev.recipes.machines.FluidInfuserRecipe;
import me.steven.indrev.recipes.machines.IRRecipe;
import me.steven.indrev.recipes.machines.InfuserRecipe;
import me.steven.indrev.recipes.machines.LaserRecipe;
import me.steven.indrev.recipes.machines.ModuleRecipe;
import me.steven.indrev.recipes.machines.PulverizerRecipe;
import me.steven.indrev.recipes.machines.RecyclerRecipe;
import me.steven.indrev.recipes.machines.SawmillRecipe;
import me.steven.indrev.recipes.machines.SmelterRecipe;
import me.steven.indrev.recipes.machines.entries.InputEntry;
import me.steven.indrev.recipes.machines.entries.OutputEntry;
import me.steven.indrev.registry.IRItemRegistry;
import me.steven.indrev.registry.MachineRegistry;
import me.steven.indrev.utils.EnergyutilsKt;
import me.steven.indrev.utils.HiddenitemsKt;

public class IndustrialRevolutionEMIPlugin implements ModInitializer, EmiPlugin {
	public static final String MOD_ID = "indrev-emi-plugin";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	@SuppressWarnings("unchecked")
    private static final IRMachineCategory<IRRecipe>[] CATEGORIES = new IRMachineCategory[] {
            new IRMachineCategory<PulverizerRecipe>(PulverizerRecipe.Companion.getTYPE(), MachineRegistry.Companion.getPULVERIZER_REGISTRY(), new PulverizerWidgetFactory(), MachineRegistry.Companion.getPULVERIZER_FACTORY_REGISTRY()),
            new IRMachineCategory<InfuserRecipe>(InfuserRecipe.Companion.getTYPE(), MachineRegistry.Companion.getSOLID_INFUSER_REGISTRY(), new SolidInfuserWidgetFactory(), MachineRegistry.Companion.getSOLID_INFUSER_FACTORY_REGISTRY()),
            new IRMachineCategory<CompressorRecipe>(CompressorRecipe.Companion.getTYPE(),MachineRegistry.Companion.getCOMPRESSOR_REGISTRY(), new CompressorWidgetFactory(), MachineRegistry.Companion.getCOMPRESSOR_FACTORY_REGISTRY()),
            new IRMachineCategory<RecyclerRecipe>(RecyclerRecipe.Companion.getTYPE(), MachineRegistry.Companion.getRECYCLER_REGISTRY(), new RecyclerWidgetFactory()),
            new IRMachineCategory<FluidInfuserRecipe>(FluidInfuserRecipe.Companion.getTYPE(), MachineRegistry.Companion.getFLUID_INFUSER_REGISTRY(), new FluidInfuserWidgetFactory(), true),
            new IRMachineCategory<CondenserRecipe>(CondenserRecipe.Companion.getTYPE(), MachineRegistry.Companion.getCONDENSER_REGISTRY(), new CondenserWidgetFactory()),
            new IRMachineCategory<SmelterRecipe>(SmelterRecipe.Companion.getTYPE(), MachineRegistry.Companion.getSMELTER_REGISTRY(), new SmelterWidgetFactory()),
            new IRMachineCategory<SawmillRecipe>(SawmillRecipe.Companion.getTYPE(), MachineRegistry.Companion.getSAWMILL_REGISTRY(), new SawmillWidgetFactory()),
            new IRMachineCategory<ModuleRecipe>(ModuleRecipe.Companion.getTYPE(), MachineRegistry.Companion.getMODULAR_WORKBENCH_REGISTRY(), new ModularWorkbenchWidgetFactory()),
            new IRMachineCategory<LaserRecipe>(LaserRecipe.Companion.getTYPE(), MachineRegistry.Companion.getLASER_EMITTER_REGISTRY(), new LaserWidgetFactory())
    };
	
	private static final IRMachineCategory<MiningRigRecipeWrapper> MINING_RIG_CATEGORY = new IRMachineCategory<MiningRigRecipeWrapper>(MiningRigRecipeWrapper.TYPE_WRAPPER, MachineRegistry.Companion.getMINING_RIG_REGISTRY(), new MiningRigWidgetFactory(), 72, 22);
    
    private static final Item[] CHARGED_ITEMS = new Item[] {
            IRItemRegistry.INSTANCE.getMINING_DRILL_MK1(),
            IRItemRegistry.INSTANCE.getMINING_DRILL_MK2(),
            IRItemRegistry.INSTANCE.getMINING_DRILL_MK3(),
            IRItemRegistry.INSTANCE.getMINING_DRILL_MK4(),
            IRItemRegistry.INSTANCE.getMODULAR_ARMOR_HELMET(),
            IRItemRegistry.INSTANCE.getMODULAR_ARMOR_CHEST(),
            IRItemRegistry.INSTANCE.getMODULAR_ARMOR_LEGGINGS(),
            IRItemRegistry.INSTANCE.getMODULAR_ARMOR_BOOTS(),
            IRItemRegistry.INSTANCE.getPORTABLE_CHARGER_ITEM(),
            IRItemRegistry.INSTANCE.getBATTERY()
    };

	@Override
	public void onInitialize() {
		LOGGER.info("Let there be recipes! And items.");
	}
    
    @Override
    public void register(EmiRegistry registry) {
        IREmiPluginConfig.INSTANCE.reload();
        
        if (IREmiPluginConfig.INSTANCE.addChargedVersions) {
            for (Item item : CHARGED_ITEMS) {
                registerCharged(registry, item);
            }
            
            registerGamerAxe(registry);
        }

        if (IREmiPluginConfig.INSTANCE.hideNotImplementedItems) {
            registry.removeEmiStacks(IndustrialRevolutionEMIPlugin::removePredicate);
        }

        if (IREmiPluginConfig.INSTANCE.addMissingItems) {
            registerMissingItems(registry);
        }

        if (IREmiPluginConfig.INSTANCE.addMachineRecipes) {
            registerCategories(registry);
            registerRecipes(registry);
            addElectricFurnaceToSmeltingCategory(registry);
        }
        
        if (IREmiPluginConfig.INSTANCE.addMiningRigRecipes) {
            registerMiningRigRecipes(registry);
        }
    }
    
    private void registerCharged(EmiRegistry registry, Item item) {
        ItemStack fullEnergy = new ItemStack(item);
        NbtCompound tag = fullEnergy.getOrCreateNbt();
        tag.putLong("energy", EnergyutilsKt.energyOf(fullEnergy).getCapacity());
        registry.addEmiStackAfter(EmiStack.of(fullEnergy), EmiStack.of(item));
    }
    
    private void registerGamerAxe(EmiRegistry registry) {
        ItemStack activeGamerAxe = new ItemStack(IRItemRegistry.INSTANCE.getGAMER_AXE_ITEM());
        NbtCompound tag = activeGamerAxe.getOrCreateNbt();
        tag.putLong("energy", EnergyutilsKt.energyOf(activeGamerAxe).getCapacity());
        tag.putBoolean("Active", true);
        tag.putFloat("Progress", 1.0F);
        registry.addEmiStackAfter(EmiStack.of(activeGamerAxe), EmiStack.of(IRItemRegistry.INSTANCE.getGAMER_AXE_ITEM()));
    }
    
    private static final boolean removePredicate(EmiStack stack) {
        Identifier id = stack.getId();
        
        return id == null ? false : HiddenitemsKt.hide(id);
    }
    
    @SuppressWarnings("deprecation")
    private void registerMissingItems(EmiRegistry registry) {
        // a VERY hacky (but kinda futureproof™) way of adding all the missing items to EMI
        // this solution does not add the items to the creative tab, but who the hell would use that when they have EMI installed, right?
        var timer = System.currentTimeMillis();
        HashSet<EmiStack> registeredStacks = new HashSet<EmiStack>(EmiStackList.stacks);
        
        for (var entry : Registries.ITEM) {
            if (!entry.getRegistryEntry().getKey().get().getValue().getNamespace().equals("indrev")) continue; // filter out other namespaces
            if (registeredStacks.contains(EmiStack.of(entry))) continue; // ignore already present items (I actually admire EMI for using its own getHash method)
            
            registry.addEmiStack(EmiStack.of(entry));
        }
        
        timer = System.currentTimeMillis() - timer;
        String message = "Adding missing entries took " + timer + "ms";
        
        if (timer > 2000) {
            LOGGER.warn(message);
        } else {
            LOGGER.info(message);
        }
    }
    
    private void registerCategories(EmiRegistry registry) {
        for (IRMachineCategory<IRRecipe> category : CATEGORIES) {
            registry.addCategory(category);
            
            // add all tiers as workstations
            for (Tier tier : category.getRegistry().getTiers()) {
                registry.addWorkstation(category, EmiStack.of(category.getRegistry().block(tier)));
            }
            
            // add factory as workstation
            if (category.hasFactory()) {
                registry.addWorkstation(category, EmiStack.of(category.getFactoryRegistry().block(category.getFactoryRegistry().getTiers()[0])));
            }
        }
    }
    
    private void registerRecipes(EmiRegistry registry) {
        for (IRMachineCategory<IRRecipe> category : CATEGORIES) {
            // register recipes for each category
            for (IRRecipe recipe : registry.getRecipeManager().listAllOfType(category.getType())) {
                registry.addRecipe(new IRMachineRecipe<IRRecipe>(recipe, category));
            }
        }
    }
    
    private void registerMiningRigRecipes(EmiRegistry registry) {
        MiningRigConfig rigConfig = null;
        try {
            rigConfig = IRConfig.INSTANCE.getMiningRigConfig();
        }
        
        catch (UninitializedPropertyAccessException e) {
            LOGGER.error("Industrial Revolution config not initialized. Skipping mining rig recipes registration.");
            return;
        }
        
        registry.addCategory(MINING_RIG_CATEGORY);
        
        for (Tier tier : MINING_RIG_CATEGORY.getRegistry().getTiers()) {
            registry.addWorkstation(MINING_RIG_CATEGORY, EmiStack.of(MINING_RIG_CATEGORY.getRegistry().block(tier)));
        }
        
        for (var allowedTag : rigConfig.getAllowedTags().entrySet()) {
            TagKey<Item> outputTag = TagKey.of(RegistryKeys.ITEM, new Identifier(allowedTag.getKey()));
            
            for (var item : Registries.ITEM.getEntryList(outputTag).get()) {
                InputEntry[] inputEntry = new InputEntry[] { };
                OutputEntry[] outputEntry = new OutputEntry[] { new OutputEntry(new ItemStack(item), 1) };
                MiningRigRecipeWrapper wrappedRecipe = new MiningRigRecipeWrapper(new Identifier(MOD_ID, "/" + item.getKey().get().getValue().toString().replace(':', '_')), inputEntry, outputEntry, 1000);
                registry.addRecipe(new IRMachineRecipe<MiningRigRecipeWrapper>(wrappedRecipe, MINING_RIG_CATEGORY));
            }
        }
    }
    
    private void addElectricFurnaceToSmeltingCategory(EmiRegistry registry) {
        var electricFurnace = MachineRegistry.Companion.getELECTRIC_FURNACE_REGISTRY();
        
        for (Tier tier : electricFurnace.getTiers()) {
            registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(electricFurnace.block(tier)));
        }
        
        var factory = MachineRegistry.Companion.getELECTRIC_FURNACE_FACTORY_REGISTRY();
        registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(factory.block(factory.getTiers()[0])));
    }
}
