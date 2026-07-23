package fuzs.moblassos.common.config;

import fuzs.puzzleslib.common.api.config.v3.Config;
import fuzs.puzzleslib.common.api.config.v3.ConfigCore;

import java.util.OptionalInt;

public class ServerConfig implements ConfigCore {
    @Config
    public final LassoConfig goldenLasso = new LassoConfig();
    @Config
    public final LassoConfig aquaticLasso = new LassoConfig();
    @Config
    public final LassoConfig diamondLasso = new LassoConfig();
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
        this.diamondLasso.holdInfinitely = true;
        this.emeraldLasso.holdingTime = 300;
        this.hostileLasso.holdingTime = 180;
    }

    public static class LassoConfig implements ConfigCore {
        @Config(description = "Time in seconds for which a lasso is able to hold a mob.")
        @Config.IntRange(min = 0, max = 3600)
        int holdingTime = 120;
        @Config(description = "Is the lasso able to hold mobs forever without any time limitation.")
        boolean holdInfinitely = false;

        public OptionalInt getMaxHoldingTime() {
            return this.holdInfinitely ? OptionalInt.empty() : OptionalInt.of(this.holdingTime);
        }
    }

    public static class EmeraldLassoConfig extends LassoConfig {
        @Config(description = "Capturing a villager in an emerald lasso requires it to accept a contract.")
        public boolean villagersRequireContract = true;
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
    }
}
