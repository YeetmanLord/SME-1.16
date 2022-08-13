package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class BlockReachEnchant extends Enchantment
{
	public BlockReachEnchant(Rarity rarityIn, EquipmentSlotType... slots)
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
		if(Config.blockReach.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.blockReach.maxLevel.get();
	}
	
	@Override
	public int getMinCost(int enchantmentLevel)
	{
		return 10 + (enchantmentLevel - 1) * 10;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel)
	{
		return this.getMinCost(enchantmentLevel) + 15;
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment ench) 
	{
		return super.checkCompatibility(ench) && ench != EnchantmentInit.ATTACK_REACH.get();
	}
}
