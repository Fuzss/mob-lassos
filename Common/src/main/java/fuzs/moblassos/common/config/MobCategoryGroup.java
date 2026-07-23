package fuzs.moblassos.common.config;

import com.google.common.collect.Sets;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.MobCategory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * @see MobCategory
 * @see net.minecraft.world.entity.EquipmentSlotGroup
 */
public enum MobCategoryGroup implements StringRepresentable {
    MONSTER(MobCategory.MONSTER),
    CREATURE(MobCategory.CREATURE, MobCategory.AMBIENT),
    AQUATIC(MobCategory.AXOLOTLS,
            MobCategory.UNDERGROUND_WATER_CREATURE,
            MobCategory.WATER_CREATURE,
            MobCategory.WATER_AMBIENT);

    public final Collection<MobCategory> mobs;

    MobCategoryGroup(MobCategory... mobs) {
        this.mobs = Sets.immutableEnumSet(Arrays.asList(mobs));
    }

    public void addAll(Consumer<MobCategory> consumer) {
        this.mobs.forEach(consumer);
    }

    @Override
    public String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
