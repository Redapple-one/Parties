package com.alessiodp.parties.bukkit.addons.external.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Trigger;
import ch.njol.skript.registrations.EventValues;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostLeaveEvent;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPreLeaveEvent;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class EvtPlayerLeave extends SkriptEvent {
	static {
		Skript.registerEvent("PartyPlayer Pre Leave Party", EvtPlayerLeave.class, BukkitPartiesPlayerPreLeaveEvent.class,
				"[player] pre leave[s] [a] party")
				.description("Called when a player is leaving a party.")
				.examples("on player pre leave party:",
						"\tmessage \"Player %name of event-partyplayer% is leaving the party %name of event-party%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPlayerPreLeaveEvent.class, Party.class, BukkitPartiesPlayerPreLeaveEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPlayerPreLeaveEvent.class, PartyPlayer.class, BukkitPartiesPlayerPreLeaveEvent::getPartyPlayer, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPlayerPreLeaveEvent.class, CommandSender.class, e ->  Bukkit.getPlayer(e.getPartyPlayer().getPlayerUUID()), EventValues.TIME_NOW);
		
		Skript.registerEvent("PartyPlayer Post Leave Party", EvtPlayerLeave.class, BukkitPartiesPlayerPostLeaveEvent.class,
				"[player] [post] leave[s] [a] party")
				.description("Called when a player left a party.")
				.examples("on player post leave party:",
						"\tmessage \"Player %name of event-partyplayer% left the party %name of event-party%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPlayerPostLeaveEvent.class, Party.class, BukkitPartiesPlayerPostLeaveEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPlayerPostLeaveEvent.class, PartyPlayer.class, BukkitPartiesPlayerPostLeaveEvent::getPartyPlayer, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPlayerPostLeaveEvent.class, CommandSender.class, e ->  Bukkit.getPlayer(e.getPartyPlayer().getPlayerUUID()), EventValues.TIME_NOW);
	}
	
	private static final List<Trigger> TRIGGERS = Collections.synchronizedList(new ArrayList<>());
	
	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
		return true;
	}
	
	@Override
	public boolean load() {
		TRIGGERS.add(trigger);
		return true;
	}
	
	@Override
	public void unload() {
		TRIGGERS.remove(trigger);
	}
	
	@Override
	public boolean check(Event event) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString(Event event, boolean debug) {
		return "party leave";
	}
}
