package StevenDimDoors.mod_pocketDim.blocks;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import StevenDimDoors.mod_pocketDim.mod_pocketDim;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDoorGold extends BlockDoor
{
	@SideOnly(Side.CLIENT)
    private Icon[] upperTextures;
    @SideOnly(Side.CLIENT)
    private Icon[] lowerTextures;

	public BlockDoorGold(int par1, Material par2Material)
	{
		super(par1, par2Material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		upperTextures = new Icon[2];
        lowerTextures = new Icon[2];
        upperTextures[0] = iconRegister.registerIcon(mod_pocketDim.modid + ":" + this.getUnlocalizedName() + "_upper");
        lowerTextures[0] = iconRegister.registerIcon(mod_pocketDim.modid + ":" + this.getUnlocalizedName() + "_lower");
        upperTextures[1] = new IconFlipped(upperTextures[0], true, false);
        lowerTextures[1] = new IconFlipped(lowerTextures[0], true, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int idPicked(World world, int x, int y, int z)
	{
		return mod_pocketDim.itemGoldenDoor.itemID;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return (par1 & 8) != 0 ? 0 : mod_pocketDim.itemGoldenDoor.itemID;
    }
	  
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
    {
        return this.upperTextures[0];
    }
	
	/**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
	@Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        if (side != 1 && side != 0)
        {
            int fullMetadata = this.getFullMetadata(blockAccess, x, y, z);
            int orientation = fullMetadata & 3;
            boolean reversed = false;

            if (BaseDimDoor.isDoorOpen(fullMetadata))
            {
                if (orientation == 0 && side == 2)
                {
                    reversed = !reversed;
                }
                else if (orientation == 1 && side == 5)
                {
                    reversed = !reversed;
                }
                else if (orientation == 2 && side == 3)
                {
                    reversed = !reversed;
                }
                else if (orientation == 3 && side == 4)
                {
                    reversed = !reversed;
                }
            }
            else
            {
                if (orientation == 0 && side == 5)
                {
                    reversed = !reversed;
                }
                else if (orientation == 1 && side == 3)
                {
                    reversed = !reversed;
                }
                else if (orientation == 2 && side == 4)
                {
                    reversed = !reversed;
                }
                else if (orientation == 3 && side == 2)
                {
                    reversed = !reversed;
                }

                if ((fullMetadata & 16) != 0)
                {
                    reversed = !reversed;
                }
            }
            if (BaseDimDoor.isUpperDoorBlock(fullMetadata))
            {
            	return this.upperTextures[reversed ? 1 : 0];
            }
            return this.lowerTextures[reversed ? 1 : 0];
        }
        return this.lowerTextures[0];
    }
}
