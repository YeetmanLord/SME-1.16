package com.github.yeetmanlord.somanyenchants.common.enchantments.blocks;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.inventory.EquipmentSlotType;

public class FuelEfficientSmelterEnchant extends ModEnchantment {

	public FuelEfficientSmelterEnchant(Rarity rarityIn, EquipmentSlotType... slots) {

		super(rarityIn, EnchantmentTypesInit.SMELTER, Config.fuelEfficient, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return (enchantmentLevel - 1) * 3 + 5;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 20;

	}

}
