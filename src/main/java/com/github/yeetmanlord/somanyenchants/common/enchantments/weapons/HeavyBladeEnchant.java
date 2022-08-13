package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.CreatureAttribute;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;

public class HeavyBladeEnchant extends Enchantment
{

	public HeavyBladeEnchant(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super(rarityIn, EnchantmentType.WEAPON, slots);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return this.category.canEnchant(stack.getItem());
	}

	@Override
	public boolean canEnchant(ItemStack stack)
	{
		return stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem ? true
				: super.canEnchant(stack);
	}

	@Override
	public boolean isTradeable()
	{
		return true;
	}

	@Override
	public boolean isAllowedOnBooks()
	{ return true; }

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

	@Override
	public int getMaxLevel()
	{

		if (Config.heavyBlade.isEnabled.get() == false)
		{
			return 0;
		}
		else
			return Config.heavyBlade.maxLevel.get();

	}

	@Override
	public boolean isDiscoverable()
	{
		return true;
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench)
	{
		return ench == EnchantmentInit.LIGHT_BLADE.get() || ench instanceof DamageEnchantment
				|| ench == EnchantmentInit.ATTACK_REACH.get() || ench == EnchantmentInit.BLOCK_REACH.get()
						? false : super.checkCompatibility(ench);
	}

	@Override
	public float getDamageBonus(int level, CreatureAttribute creatureType)
	{
		return (float) (Math.max(0, level - 1) * 2 + 4);
	}

}
