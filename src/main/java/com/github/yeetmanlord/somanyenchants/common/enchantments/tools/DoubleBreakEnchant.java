package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class DoubleBreakEnchant extends Enchantment
{
	public DoubleBreakEnchant(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super(rarityIn, EnchantmentType.DIGGER, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) 
	{
		return EnchantmentType.DIGGER.canEnchant(stack.getItem());
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) 
	{
		return EnchantmentType.DIGGER.canEnchant(stack.getItem());
	}
	
	@Override
	public int getMaxLevel() 
	{
		if(Config.doubleBreak.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.doubleBreak.maxLevel.get();
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 15 + (enchantmentLevel - 1) * 9;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return super.getMinCost(enchantmentLevel) + 50;
	}
}
