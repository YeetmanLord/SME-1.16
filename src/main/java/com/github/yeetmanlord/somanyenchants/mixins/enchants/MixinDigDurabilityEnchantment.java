package com.github.yeetmanlord.somanyenchants.mixins.enchants;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.enchantment.UnbreakingEnchantment;

@Mixin(UnbreakingEnchantment.class)
public class MixinDigDurabilityEnchantment {

	@Inject(at = @At("HEAD"), method = "getMaxLevel()I", cancellable = true)
	private void getMaxLevel(CallbackInfoReturnable<Integer> callback) {

		if (Config.unbreaking.isEnabled.get()) {
			callback.setReturnValue(Config.unbreaking.maxLevel.get());
		}

	}

}
