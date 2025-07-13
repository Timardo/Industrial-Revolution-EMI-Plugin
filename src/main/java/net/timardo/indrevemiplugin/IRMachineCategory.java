package net.timardo.indrevemiplugin;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.emi.emi.runtime.EmiDrawContext;
import me.steven.indrev.blocks.machine.MachineBlock;
import me.steven.indrev.config.BasicMachineConfig;
import me.steven.indrev.config.HeatMachineConfig;
import me.steven.indrev.recipes.machines.IRRecipe;
import me.steven.indrev.recipes.machines.IRRecipeType;
import me.steven.indrev.registry.MachineRegistry;
import me.steven.indrev.utils.GuiutilsKt;
import me.steven.indrev.utils.UtilsKt;
import net.minecraft.block.Block;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.timardo.indrevemiplugin.widget.EnergyWidget;
import net.timardo.indrevemiplugin.widget.IWidgetFactory;

import static net.timardo.indrevemiplugin.widget.IWidgetFactory.loc;

public class IRMachineCategory<T extends IRRecipe> extends EmiRecipeCategory {
    // HEAT_EMPTY does not have a getter, why? no idea
    private static final Identifier HEAT_EMPTY = UtilsKt.identifier("textures/gui/widget_temperature_empty.png");
    
    private IRRecipeType<T> type;
    private MachineRegistry registry;
    private MachineRegistry factoryRegistry;
    private IWidgetFactory<T> widgetFactory;
    private boolean isHeatMachine;
    private MachineBlock defaultBlock;

    public IRMachineCategory(IRRecipeType<T> type, MachineRegistry registry, IWidgetFactory<T> widgetFactory) {
        super(Identifier.of(IndustrialRevolutionEMIPlugin.MOD_ID, type.getId().getPath()), EmiStack.of(registry.block(registry.getTiers()[0])));
        this.type = type;
        this.registry = registry;
        this.widgetFactory = widgetFactory;
        
        Block block = registry.block(registry.getTiers()[0]);
        this.defaultBlock = (MachineBlock)block;
        // fluid infuser does not have heat machine config, yet it features a temperature bar
        // this is the only nonstupid way to find out if a machine has temperature bar without hardcoding everything as far as I know
        this.isHeatMachine = this.defaultBlock.getConfig() instanceof HeatMachineConfig || registry.equals(MachineRegistry.Companion.getFLUID_INFUSER_REGISTRY());
        
    }
    
    public IRMachineCategory(IRRecipeType<T> type, MachineRegistry registry, IWidgetFactory<T> widgetFactory, MachineRegistry factory) {
        this(type, registry, widgetFactory);
        this.factoryRegistry = factory;
    }
    
    public IRRecipeType<T> getType() {
        return this.type;
    }
    
    public MachineRegistry getRegistry() {
        return this.registry;
    }
    
    public boolean hasFactory() {
        return this.factoryRegistry != null;
    }
    
    public MachineRegistry getFactoryRegistry() {
        return this.factoryRegistry;
    }
    
    public void addWidgets(WidgetHolder holder, IRMachineRecipe<T> recipe) {
        this.widgetFactory.addWidgets(holder, recipe);
        
        addEnergyWidget(holder, recipe.getIRRecipe().getTicks(), recipe);
        
        if (this.isHeatMachine) {
            addTemperatureWidget(holder);
        }
    }

    private void addEnergyWidget(WidgetHolder holder, int baseTime, IRMachineRecipe<T> recipe) {
        // laser has special handling of the crafting ticks for some odd reason
        if (registry.equals(MachineRegistry.Companion.getLASER_EMITTER_REGISTRY())) {
            holder.add(new EnergyWidget(loc(0.1), loc(0.15), recipe.getIRRecipe().getTicks(), this.defaultBlock.getConfig().getMaxEnergyStored()));
        } else {
            holder.add(new EnergyWidget(loc(0.1), loc(0.15), baseTime * ((BasicMachineConfig)this.defaultBlock.getConfig()).getEnergyCost(), this.defaultBlock.getConfig().getMaxEnergyStored()));
        }
    }
    
    private void addTemperatureWidget(WidgetHolder holder) {
        // temperature bar
        holder.addTexture(HEAT_EMPTY, loc(0.95), loc(0.15), 10, 43, 0, 0, 10, 43, 10, 43);
        
        // cooler slot
        holder.add(new SlotWidget(EmiStack.EMPTY, loc(0.75), loc(2.75)) {
            @Override
            public void drawSlotHighlight(DrawContext draw, Bounds bounds) {
                // this is to prevent not drawing the vent icon when hovering over the slot
                EmiDrawContext.wrap(draw).drawTexture(GuiutilsKt.getVENT_ICON_ID(), loc(0.75), loc(2.75), 18, 18, 0, 0, 18, 18, 18, 18);
                super.drawSlotHighlight(draw, bounds);
            }
        });
        
        holder.addTexture(GuiutilsKt.getVENT_ICON_ID(), loc(0.75), loc(2.75), 18, 18, 0, 0, 18, 18, 18, 18);
    }
}
