package com.github.yeetmanlord.somanyenchants.client;

import com.github.yeetmanlord.somanyenchants.common.container.AbstractEnchantedSmelterContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractEnchantedSmelterScreen<T extends AbstractEnchantedSmelterContainer>
		extends ContainerScreen<T> implements IRecipeShownListener {
	private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation(
			"textures/gui/recipe_button.png");
	public final AbstractRecipeBookGui recipeBookComponent;
	private boolean widthTooNarrow;
	private final ResourceLocation texture;

	public AbstractEnchantedSmelterScreen(T screenContainer, AbstractRecipeBookGui recipeGuiIn, PlayerInventory inv,
			ITextComponent titleIn, ResourceLocation guiTextureIn) {
		super(screenContainer, inv, titleIn);
		this.recipeBookComponent = recipeGuiIn;
		this.texture = guiTextureIn;
	}

	@Override
	public void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.leftPos = this.recipeBookComponent.updateScreenPosition(widthTooNarrow, this.width, this.imageWidth);
		this.addButton(new ImageButton(this.leftPos + 20, this.height / 2 - 49, 20, 18, 0, 0, 19,
				RECIPE_BUTTON_LOCATION, (p_97863_) -> {
					this.recipeBookComponent.toggleVisibility();
					this.leftPos = this.recipeBookComponent.updateScreenPosition(widthTooNarrow, this.width,
							this.imageWidth);
					((ImageButton) p_97863_).setPosition(this.leftPos + 20, this.height / 2 - 49);
				}));
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void tick() {
		super.tick();
		this.recipeBookComponent.tick();
	}

	@Override
	public void render(MatrixStack p_97858_, int p_97859_, int p_97860_, float p_97861_) {
		this.renderBackground(p_97858_);
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBg(p_97858_, p_97861_, p_97859_, p_97860_);
			this.recipeBookComponent.render(p_97858_, p_97859_, p_97860_, p_97861_);
		} else {
			this.recipeBookComponent.render(p_97858_, p_97859_, p_97860_, p_97861_);
			super.render(p_97858_, p_97859_, p_97860_, p_97861_);
			this.recipeBookComponent.renderGhostRecipe(p_97858_, this.leftPos, this.topPos, true, p_97861_);
		}

		this.renderTooltip(p_97858_, p_97859_, p_97860_);
		this.recipeBookComponent.renderTooltip(p_97858_, this.leftPos, this.topPos, p_97859_, p_97860_);
	}

	@Override
	protected void renderBg(MatrixStack p_97853_, float p_97854_, int p_97855_, int p_97856_) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(texture);
		int i = this.leftPos;
		int j = this.topPos;
		this.blit(p_97853_, i, j, 0, 0, this.imageWidth, this.imageHeight);
		if (this.menu.isLit()) {
			int k = this.menu.getLitProgress();
			this.blit(p_97853_, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}

		int l = this.menu.getBurnProgress();
		this.blit(p_97853_, i + 79, j + 34, 176, 14, l + 1, 16);
	}

	@Override
	public boolean mouseClicked(double p_97834_, double p_97835_, int p_97836_) {
		if (this.recipeBookComponent.mouseClicked(p_97834_, p_97835_, p_97836_)) {
			return true;
		} else {
			return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? true
					: super.mouseClicked(p_97834_, p_97835_, p_97836_);
		}
	}

	@Override
	protected void slotClicked(Slot p_97848_, int p_97849_, int p_97850_, ClickType p_97851_) {
		super.slotClicked(p_97848_, p_97849_, p_97850_, p_97851_);
		this.recipeBookComponent.slotClicked(p_97848_);
	}

	@Override
	public boolean keyPressed(int p_97844_, int p_97845_, int p_97846_) {
		return this.recipeBookComponent.keyPressed(p_97844_, p_97845_, p_97846_) ? false
				: super.keyPressed(p_97844_, p_97845_, p_97846_);
	}

	@Override
	protected boolean hasClickedOutside(double p_97838_, double p_97839_, int p_97840_, int p_97841_, int p_97842_) {
		boolean flag = p_97838_ < (double) p_97840_ || p_97839_ < (double) p_97841_
				|| p_97838_ >= (double) (p_97840_ + this.imageWidth)
				|| p_97839_ >= (double) (p_97841_ + this.imageHeight);
		return this.recipeBookComponent.hasClickedOutside(p_97838_, p_97839_, this.leftPos, this.topPos,
				this.imageWidth, this.imageHeight, p_97842_) && flag;
	}

	@Override
	public boolean charTyped(char p_97831_, int p_97832_) {
		return this.recipeBookComponent.charTyped(p_97831_, p_97832_) ? true : super.charTyped(p_97831_, p_97832_);
	}

	@Override
	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

	@Override
	public RecipeBookGui getRecipeBookComponent() {
		return this.recipeBookComponent;
	}

	@Override
	public void removed() {
		this.recipeBookComponent.removed();
		super.removed();
	}
}