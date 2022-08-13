package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class StepAssistEnchant extends Enchantment
{
	public StepAssistEnchant(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentType.ARMOR_FEET.canEnchant(stack.getItem());
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 25;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel)
	{
		return this.getMinCost(enchantmentLevel) + 40;
	}
	
	@Override
	public int getMaxLevel() 
	{
		if(Config.stepAssist.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.stepAssist.maxLevel.get();
	}
}
