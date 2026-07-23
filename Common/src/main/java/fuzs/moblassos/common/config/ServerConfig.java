package fuzs.moblassos.common.config;

import com.google.common.collect.Sets;
import fuzs.puzzleslib.common.api.config.v3.Config;
import fuzs.puzzleslib.common.api.config.v3.ConfigCore;
import net.minecraft.world.entity.MobCategory;

import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;

public class ServerConfig implements ConfigCore {
    @Config
    public final LassoConfig goldenLasso = new LassoConfig(false, true, false);
    @Config
    public final LassoConfig aquaticLasso = new LassoConfig(false, false, true);
    @Config
    public final LassoConfig diamondLasso = new LassoConfig(false, true, true);
    @Config
    public final EmeraldLassoConfig emeraldLasso = new EmeraldLassoConfig();
    @Config
    public final HostileLassoConfig hostileLasso = new HostileLassoConfig();
    @Config(description = "The percentage each level of the holding enchantment will increase a lasso's holding time by.")
    @Config.DoubleRange(min = 0.0, max = 100.0)
    public double holdingMultiplier = 0.2;

    public ServerConfig() {
        this.goldenLasso.holdingTime = 120;
        this.aquaticLasso.holdingTime = 240;
        this.diamondLasso.holdIndefinitely = true;
        this.emeraldLasso.holdingTime = 300;
        this.hostileLasso.holdingTime = 180;
    }

    public static class LassoConfig implements ConfigCore {
        @Config(description = {
                "Categories for mobs which can be captured by a lasso.",
                "This behavior can be further customized using entity type tags."
        })
        final MobsConfig mobs;
        @Config(description = "Time in seconds for which a lasso is able to hold a mob.")
        @Config.IntRange(min = 0, max = 3600)
        int holdingTime = 120;
        @Config(description = "Is the lasso able to hold mobs forever without any time limitation.")
        boolean holdIndefinitely = false;

        public LassoConfig(boolean monster, boolean creature, boolean aquatic) {
            this.mobs = new MobsConfig(monster, creature, aquatic);
        }

        public Collection<MobCategory> getAllMobs() {
            return this.mobs.toFlatSet();
        }

        public OptionalInt getMaxHoldingTime() {
            return this.holdIndefinitely ? OptionalInt.empty() : OptionalInt.of(this.holdingTime);
        }
    }

    public static class EmeraldLassoConfig extends LassoConfig {
        @Config(description = "Capturing a villager in an emerald lasso requires it to accept a contract.")
        public boolean villagersRequireContract = true;

        public EmeraldLassoConfig() {
            super(false, false, false);
        }
    }

    public static class HostileLassoConfig extends LassoConfig {
        @Config(description = "Percentage of its total health or less a hostile mob must have in order for the lasso to be able to pick it up.")
        @Config.DoubleRange(min = 0.0, max = 1.0)
        public double hostileMobHealth = 0.5;
        @Config(description = {
                "Time interval in seconds after which the player is hurt by half a heart for carrying any hostile lasso containing a monster.",
                "Set to -1 to disable."
        })
        @Config.IntRange(min = -1, max = 3600)
        public int hostileDamageRate = 5;

        public HostileLassoConfig() {
            super(true, false, false);
        }
    }

    public static class MobsConfig implements ConfigCore {
        @Config(description = "Night time monsters, illagers, nether creatures, etc.")
        boolean monster;
        @Config(description = "Animals, passive mobs, villagers, etc.")
        boolean creature;
        @Config(description = "Squids, fishes, axolotls, etc.")
        boolean aquatic;

        public Set<MobCategory> mobs;

        public MobsConfig() {
            this(false, false, false);
        }

        public MobsConfig(boolean monster, boolean creature, boolean aquatic) {
            this.monster = monster;
            this.creature = creature;
            this.aquatic = aquatic;
        }

        @Override
        public void afterConfigReload() {
            this.mobs = this.toFlatSet();
        }

        private Set<MobCategory> toFlatSet() {
            return this.toGroupSet().stream().mapMulti(MobCategoryGroup::addAll).collect(Sets.toImmutableEnumSet());
        }

        private Set<MobCategoryGroup> toGroupSet() {
            Set<MobCategoryGroup> set = new HashSet<>();
            if (this.monster) {
                set.add(MobCategoryGroup.MONSTER);
            }

            if (this.creature) {
                set.add(MobCategoryGroup.CREATURE);
            }

            if (this.aquatic) {
                set.add(MobCategoryGroup.AQUATIC);
            }

            return Sets.immutableEnumSet(set);
        }
    }
}
