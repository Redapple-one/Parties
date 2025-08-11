package com.alessiodp.parties.bukkit.addons.external.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SelfRegisteringSkriptEvent;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Trigger;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPostJoinEvent;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPreJoinEvent;
import com.alessiodp.parties.api.events.bukkit.player.BukkitPartiesPlayerPreLeaveEvent;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class EvtPlayerJoin extends SkriptEvent {
	static {
		Skript.registerEvent("PartyPlayer Pre Join Party", EvtPlayerJoin.class, BukkitPartiesPlayerPreJoinEvent.class,
				"[player] pre join[s] [a] party")
				.description("Called when a player is joining a party.")
				.examples("on player pre join party:",
						"\tmessage \"Player %name of event-partyplayer% is joining the party %name of event-party%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPlayerPreJoinEvent.class, Party.class, BukkitPartiesPlayerPreJoinEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPlayerPreJoinEvent.class, PartyPlayer.class, BukkitPartiesPlayerPreJoinEvent::getPartyPlayer, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPlayerPreJoinEvent.class, CommandSender.class, e ->  Bukkit.getPlayer(e.getPartyPlayer().getPlayerUUID()), EventValues.TIME_NOW);
		
		Skript.registerEvent("PartyPlayer Post Join Party", EvtPlayerJoin.class, BukkitPartiesPlayerPostJoinEvent.class,
				"[player] [post] join[s] [a] party")
				.description("Called when a player joined a party.")
				.examples("on player post join party:",
						"\tmessage \"Player %name of event-partyplayer% joined the party %name of event-party%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPlayerPostJoinEvent.class, Party.class, BukkitPartiesPlayerPostJoinEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPlayerPostJoinEvent.class, PartyPlayer.class, BukkitPartiesPlayerPostJoinEvent::getPartyPlayer, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPlayerPostJoinEvent.class, CommandSender.class, e ->  Bukkit.getPlayer(e.getPartyPlayer().getPlayerUUID()), EventValues.TIME_NOW);
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
		return "party join";
	}
}
