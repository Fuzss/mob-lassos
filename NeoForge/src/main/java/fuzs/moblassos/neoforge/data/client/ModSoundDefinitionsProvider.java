package fuzs.moblassos.neoforge.data.client;

import fuzs.moblassos.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.neoforge.api.client.data.v3.sounds.AbstractSoundProvider;
import net.minecraft.sounds.SoundEvents;

public class ModSoundDefinitionsProvider extends AbstractSoundProvider {

    public ModSoundDefinitionsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void registerSounds() {
        this.add(ModRegistry.LASSO_PICK_UP_SOUND_EVENT.value(), sound(SoundEvents.BEEHIVE_ENTER));
        this.add(ModRegistry.LASSO_RELEASE_SOUND_EVENT.value(), sound(SoundEvents.BEEHIVE_EXIT));
    }
}
