package org.dimdev.dimdoors.entity.masktypes;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import org.dimdev.dimdoors.entity.AbstractMaskEntity;

public class BiMaskEntity extends AbstractMaskEntity {
	public BiMaskEntity(EntityType<? extends AbstractMaskEntity> entityType, World world) {
		super(entityType, world);
	}
}
