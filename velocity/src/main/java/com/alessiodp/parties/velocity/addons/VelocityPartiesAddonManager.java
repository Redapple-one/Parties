package com.alessiodp.parties.velocity.addons;

import com.alessiodp.core.velocity.addons.external.VelocityRedisBungeeHandler;
import com.alessiodp.parties.common.PartiesPlugin;
import com.alessiodp.parties.common.addons.PartiesAddonManager;
import com.alessiodp.parties.velocity.addons.external.PartiesVelocityRedisBungeeHandler;
import com.alessiodp.parties.velocity.addons.external.VelocityPremiumVanishHandler;
import com.alessiodp.parties.velocity.configuration.data.VelocityConfigMain;

public class VelocityPartiesAddonManager extends PartiesAddonManager {
	private final VelocityPremiumVanishHandler premiumVanish;
	private final VelocityRedisBungeeHandler redisBungee;
	
	public VelocityPartiesAddonManager(PartiesPlugin plugin) {
		super(plugin);
		
		premiumVanish =  new VelocityPremiumVanishHandler(plugin);
		redisBungee = new PartiesVelocityRedisBungeeHandler(plugin);
	}
	
	@Override
	public void loadAddons() {
		super.loadAddons();
		
		redisBungee.init(VelocityConfigMain.PARTIES_BUNGEECORD_REDIS);
	}
	
	@Override
	public void postLoadAddons() {
		super.postLoadAddons();
		
		premiumVanish.init();
	}
}
