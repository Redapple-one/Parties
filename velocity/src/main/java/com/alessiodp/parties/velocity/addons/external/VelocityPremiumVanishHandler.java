package com.alessiodp.parties.velocity.addons.external;

import com.alessiodp.core.common.configuration.Constants;
import com.alessiodp.core.velocity.bootstrap.ADPVelocityBootstrap;
import com.alessiodp.parties.common.PartiesPlugin;
import com.alessiodp.parties.velocity.VelocityPartiesPlugin;
import com.velocitypowered.api.proxy.Player;
import de.myzelyam.api.vanish.VelocityVanishAPI;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@RequiredArgsConstructor
public class VelocityPremiumVanishHandler {
	@NotNull private final PartiesPlugin plugin;
	private static final String ADDON_NAME = "PremiumVanish";
	
	private static boolean active = false;
	
	public void init() {
		active = false;
			if (((ADPVelocityBootstrap) this.plugin.getBootstrap()).getServer().getPluginManager().getPlugin(ADDON_NAME).isPresent()) {
				active = true;
				
				plugin.getLoggerManager().log(String.format(Constants.DEBUG_ADDON_HOOKED, ADDON_NAME), true);
			}
	}
	
	public static boolean isPlayerVanished(UUID uuid) {
		if (active) {
			Player player = ((ADPVelocityBootstrap) VelocityPartiesPlugin.getInstance().getBootstrap()).getServer().getPlayer(uuid).orElse(null);
			if (player != null)
				return VelocityVanishAPI.isInvisible(player);
		}
		return false;
	}
}