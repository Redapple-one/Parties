package com.alessiodp.parties.bukkit.addons.external.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Trigger;
import ch.njol.skript.registrations.EventValues;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyPostCreateEvent;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyPreCreateEvent;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class EvtPartyCreate extends SkriptEvent {
	static {
		Skript.registerEvent("Party Pre Create", EvtPartyCreate.class, BukkitPartiesPartyPreCreateEvent.class,
				"[player] pre create[s] [a] party")
				.description("Called when a player is creating a party. Cancellable.")
				.examples("on pre create party:",
						"\tmessage \"%event-partyplayer% is creating the party %event-string%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPartyPreCreateEvent.class, String.class, BukkitPartiesPartyPreCreateEvent::getPartyName, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreCreateEvent.class, PartyPlayer.class, BukkitPartiesPartyPreCreateEvent::getPartyPlayer, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreCreateEvent.class, CommandSender.class, e ->  e.getPartyPlayer() != null ? Bukkit.getPlayer(e.getPartyPlayer().getPlayerUUID()) : Bukkit.getConsoleSender(), EventValues.TIME_NOW);
		
		Skript.registerEvent("Party Post Create", EvtPartyCreate.class, BukkitPartiesPartyPostCreateEvent.class,
				"[player] [post] create[s] [a] party")
				.description("Called when a player has created a party.")
				.examples("on post create party:",
						"\tmessage \"%event-partyplayer% created the party %event-party%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPartyPostCreateEvent.class, Party.class, BukkitPartiesPartyPostCreateEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPostCreateEvent.class, PartyPlayer.class, BukkitPartiesPartyPostCreateEvent::getCreator, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPostCreateEvent.class, CommandSender.class, e -> e.getCreator() != null ? Bukkit.getPlayer(e.getCreator().getPlayerUUID()) : Bukkit.getConsoleSender(), EventValues.TIME_NOW);
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
		return "party create";
	}
}
