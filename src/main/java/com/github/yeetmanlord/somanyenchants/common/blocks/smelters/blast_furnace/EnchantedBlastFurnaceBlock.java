package com.github.yeetmanlord.somanyenchants.common.blocks.smelters.blast_furnace;

import java.util.Random;

import com.github.yeetmanlord.somanyenchants.common.blocks.smelters.AbstractEnchantedSmelterBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnchantedBlastFurnaceBlock extends AbstractEnchantedSmelterBlock
{

	public EnchantedBlastFurnaceBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader reader)
	{
		return new EnchantedBlastFurnaceTileEntity();
	}


	@Override
	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player)
	{
		TileEntity tileentity = worldIn.getBlockEntity(pos);

		if (tileentity instanceof EnchantedBlastFurnaceTileEntity)
		{
			player.openMenu((INamedContainerProvider) tileentity);
			player.awardStat(Stats.INTERACT_WITH_BLAST_FURNACE);
		}

	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{

		if (stateIn.getValue(LIT))
		{
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY();
			double d2 = (double) pos.getZ() + 0.5D;

			if (rand.nextDouble() < 0.1D)
			{
				worldIn.playLocalSound(d0, d1, d2, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F,
						1.0F, false);
			}

			Direction direction = stateIn.getValue(FACING);
			Direction.Axis direction$axis = direction.getAxis();
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;
			double d5 = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
			double d6 = rand.nextDouble() * 9.0D / 16.0D;
			double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : d4;
			worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
		}

	}
	
	@Override
	public Block getUnenchantedBlock()
	{ return Blocks.BLAST_FURNACE; }

}
