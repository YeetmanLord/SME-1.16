package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;

public class ArmorHeavyEnchant extends Enchantment
{
	public ArmorHeavyEnchant(Rarity rarityIn, EquipmentSlotType[] slots) 
	{
		super(rarityIn, EnchantmentType.ARMOR, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentType.ARMOR.canEnchant(stack.getItem());
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 25;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return super.getMinCost(enchantmentLevel) + 100;
	}
	 
	@Override
	protected boolean checkCompatibility(Enchantment ench) 
	{
			return super.checkCompatibility(ench) && ench != EnchantmentInit.REINFORCED_ARMOR.get() && ench != EnchantmentInit.TEMPERED_ARMOR.get();
	}
	
	@Override
	public int getMaxLevel() {
		if (Config.heavyArmor.isEnabled.get() == false) {
			return 0;
		}
		return Config.heavyArmor.maxLevel.get();
	}

}
