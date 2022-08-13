package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;

public class AttackReachEnchant extends Enchantment
{
	public AttackReachEnchant(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super(rarityIn, EnchantmentType.WEAPON, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) 
	{
		return false;
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) 
	{
		return stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem ? true : EnchantmentType.WEAPON.canEnchant(stack.getItem());
	}
	
	@Override
	public int getMaxLevel() 
	{
		if(Config.attackReach.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.attackReach.maxLevel.get();
	}
	
	@Override
	public int getMinCost(int enchantmentLevel)
	{
		return 10 + (enchantmentLevel - 1) * 12;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel)
	{
		return this.getMinCost(enchantmentLevel) + 25;
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment ench) 
	{
		return super.checkCompatibility(ench) && ench != EnchantmentInit.BLOCK_REACH.get();
	}
	
	
	@Override
	public boolean isTradeable() 
	{
		return false;
	} 
}
