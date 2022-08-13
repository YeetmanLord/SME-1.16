package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;

public class HealthBoostEnchant extends Enchantment {

	public HealthBoostEnchant(Rarity rarityIn, EquipmentSlotType[] slots) {
		super(rarityIn, EnchantmentType.ARMOR, slots);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return EnchantmentType.ARMOR.canEnchant(stack.getItem());
	}

	@Override
	public boolean isTradeable() {
		return true;
	}

	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 15 + (enchantmentLevel - 1) * 9;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return super.getMinCost(enchantmentLevel) + 50;
	}

	@Override
	public boolean isDiscoverable() {
		return true;
	}
	
	@Override
	public int getMaxLevel() {
		if (Config.healthBoost.isEnabled.get() == false) {
			return 0;
		}
		return Config.healthBoost.maxLevel.get();
	}

}
