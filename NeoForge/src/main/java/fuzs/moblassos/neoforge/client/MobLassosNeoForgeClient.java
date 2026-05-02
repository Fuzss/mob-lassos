package fuzs.moblassos.neoforge.client;

import fuzs.moblassos.common.MobLassos;
import fuzs.moblassos.common.client.MobLassosClient;
import fuzs.moblassos.common.data.client.ModLanguageProvider;
import fuzs.moblassos.common.data.client.ModModelProvider;
import fuzs.moblassos.neoforge.data.client.ModSoundDefinitionsProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = MobLassos.MOD_ID, dist = Dist.CLIENT)
public class MobLassosNeoForgeClient {

    public MobLassosNeoForgeClient() {
        ClientModConstructor.construct(MobLassos.MOD_ID, MobLassosClient::new);
        DataProviderHelper.registerDataProviders(MobLassos.MOD_ID, ModLanguageProvider::new, ModModelProvider::new,
                ModSoundDefinitionsProvider::new
        );
    }
}
