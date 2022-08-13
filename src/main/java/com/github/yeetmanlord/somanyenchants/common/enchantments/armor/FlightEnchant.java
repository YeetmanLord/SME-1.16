package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class FlightEnchant extends Enchantment 
{
	public FlightEnchant(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
	}
	
	@Override
	public boolean isTradeable()
	{
		return false;
	}
	
	@Override
	public boolean isTreasureOnly() 
	{
		return true;
		
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return false;
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) 
	{
		return EnchantmentType.ARMOR_FEET.canEnchant(stack.getItem());
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench)
	{
	      return super.checkCompatibility(ench) && ench != Enchantments.SOUL_SPEED;
	} 
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return enchantmentLevel * 10;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return this.getMinCost(enchantmentLevel) + 15;
	}
	
	@Override
	public int getMaxLevel()
	{
		if(Config.flight.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.flight.maxLevel.get();
	}
	
	@Override
	public boolean isDiscoverable() 
	{
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks()
	{
		return true;
	}
}
