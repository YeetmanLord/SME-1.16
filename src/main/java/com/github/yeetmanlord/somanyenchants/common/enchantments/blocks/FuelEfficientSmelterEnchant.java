package com.github.yeetmanlord.somanyenchants.common.enchantments.blocks;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;

public class FuelEfficientSmelterEnchant extends Enchantment {

	public FuelEfficientSmelterEnchant(Rarity rarityIn, EquipmentSlotType... slots) {

		super(rarityIn, EnchantmentTypesInit.SMELTER, slots);

	}

	@Override
	public boolean canEnchant(ItemStack stack) {

		return canApplyAtEnchantingTable(stack);

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		return EnchantmentTypesInit.SMELTER.canEnchant(stack.getItem());

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 5 * enchantmentLevel;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 40;

	}

	@Override
	public int getMaxLevel() {

		if (Config.fuelEfficient.isEnabled.get() == false) {
			return 0;
		}
		else return Config.fuelEfficient.maxLevel.get();

	}

}
