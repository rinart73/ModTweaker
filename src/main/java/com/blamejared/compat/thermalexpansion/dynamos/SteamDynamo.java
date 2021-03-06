package com.blamejared.compat.thermalexpansion.dynamos;

import cofh.thermalexpansion.util.managers.dynamo.SteamManager;
import com.blamejared.ModTweaker;
import com.blamejared.mtlib.helpers.*;
import com.blamejared.mtlib.utils.BaseAction;
import crafttweaker.annotations.*;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.*;

@ZenClass("mods.thermalexpansion.SteamDynamo")
@ModOnly("thermalexpansion")
@ZenRegister
public class SteamDynamo {
    
    @ZenMethod
    public static void addFuel(IItemStack stack, int energy) {
        ModTweaker.LATE_ADDITIONS.add(new Add(InputHelper.toStack(stack), energy));
    }
    
    @ZenMethod
    public static void removeFuel(IItemStack stack) {
        ModTweaker.LATE_REMOVALS.add(new Remove(InputHelper.toStack(stack)));
    }
    
    
    private static class Add extends BaseAction {
        
        private ItemStack stack;
        private int energy;
        
        protected Add(ItemStack stack, int energy) {
            super("SteamDynamo");
            this.stack = stack;
            this.energy = energy;
        }
        
        @Override
        public void apply() {
            SteamManager.addFuel(stack, energy);
        }
        
        @Override
        protected String getRecipeInfo() {
            return LogHelper.getStackDescription(stack);
        }
    }
    
    private static class Remove extends BaseAction {
        
        private ItemStack stack;
        
        protected Remove(ItemStack stack) {
            super("SteamDynamo");
            this.stack = stack;
        }
        
        @Override
        public void apply() {
            SteamManager.removeFuel(stack);
        }
        
        @Override
        protected String getRecipeInfo() {
            return LogHelper.getStackDescription(stack);
        }
    }
    
    
}
