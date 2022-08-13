package com.github.yeetmanlord.somanyenchants.common.enchantments;

import com.github.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;

public class EffectEnchantment extends Enchantment {
	private final Effect effect;
	private final EnchantmentConfig config;
	private final boolean canEnchant;

	public EffectEnchantment(Effect effect, EnchantmentConfig config, Rarity rarity, EquipmentSlotType... equipment) {
		this(effect, config, false, rarity, equipment);
	}
	
	public EffectEnchantment(Effect effect, EnchantmentConfig config, boolean canEnchant, Rarity rarity, EquipmentSlotType... equipment) {
		super(rarity, EnchantmentType.ARMOR, equipment);
		this.effect = effect;
		this.config = config;
		this.canEnchant = true;
	}
	
	
	public void applyEffect(PlayerEntity player, int level) {
		player.addEffect(new EffectInstance(effect, 600, level - 1, false, false, false));
	}
	
	@Override
	public boolean isCurse() {
		return !effect.isBeneficial();
	}
	
	@Override
	public int getMaxLevel() {
		if (config.isEnabled.get()) {
			return config.maxLevel.get();
		}
		return 0;
	}
	
	@Override
	public int getMaxCost(int level) {
		return getMinCost(level) + 20;
	}
	
	@Override
	public int getMinCost(int level) {
		return level * 8;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if (canEnchant) {
			return super.canApplyAtEnchantingTable(stack);
		} else {
			return false;
		}
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) 
	{
		return category.canEnchant(stack.getItem());
	}
}
