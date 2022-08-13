package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;

public class ReplantingEnchant extends Enchantment 
{
	public ReplantingEnchant(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, EnchantmentType.DIGGER, slots);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
	      return 20;
	}
	
	@Override
	public boolean canEnchant(ItemStack stack)
	{
		return stack.getItem() instanceof HoeItem || stack.getItem() instanceof AxeItem ? true : false;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return stack.getItem() instanceof HoeItem ? true : false;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
	      return this.getMinCost(enchantmentLevel) + 50;
	}
	   
	@Override
	public int getMaxLevel() 
	{
		if(Config.replanting.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.replanting.maxLevel.get();
	}
}
