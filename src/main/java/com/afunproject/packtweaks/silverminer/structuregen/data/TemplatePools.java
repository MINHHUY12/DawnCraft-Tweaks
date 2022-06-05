/*
 * Copyright © 2022 AFunProject
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software
 * and associated documentation files (the “Software”),
 * to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.afunproject.packtweaks.silverminer.structuregen.data;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.afunproject.packtweaks.PackTweaks;
import com.mojang.datafixers.util.Pair;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class TemplatePools {
	public static final Holder<StructureTemplatePool> QUEST_DUNGEON = register(Keys.QUEST_DUNGEON, new StructureTemplatePool(PackTweaks.location("quest_dungeon"), new ResourceLocation("empty"), List.of(
			Pair.of(StructurePoolElement.single(PackTweaks.location("quest_dungeon").toString()).apply(StructureTemplatePool.Projection.RIGID), 1)
			)));

	private static @NotNull Holder<StructureTemplatePool> register(ResourceKey<StructureTemplatePool> resourceKey, StructureTemplatePool structureTemplatePool) {
		return BuiltinRegistries.register(BuiltinRegistries.TEMPLATE_POOL, resourceKey, structureTemplatePool);
	}

	public static class Keys {
		public static final ResourceKey<StructureTemplatePool> QUEST_DUNGEON = register("quest_dungeon");

		private static @NotNull ResourceKey<StructureTemplatePool> register(String path) {
			return ResourceKey.create(Registry.TEMPLATE_POOL_REGISTRY, PackTweaks.location(path));
		}
	}
}