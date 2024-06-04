/*
 * Copyright 2024
 * BSDevelopment <https://bsdevelopment.org>
 */

package simplepets.brainsynder.nms.utils;

import net.minecraft.network.syncher.EntityDataAccessor;

public record DataWatcherValue(EntityDataAccessor accessor, Object value) {}
