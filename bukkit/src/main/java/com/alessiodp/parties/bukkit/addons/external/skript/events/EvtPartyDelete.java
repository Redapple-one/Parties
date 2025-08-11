package com.alessiodp.parties.bukkit.addons.external.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Trigger;
import ch.njol.skript.registrations.EventValues;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyPostDeleteEvent;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyPreDeleteEvent;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class EvtPartyDelete extends SkriptEvent {
	static {
		Skript.registerEvent("Party Pre Delete", EvtPartyDelete.class, BukkitPartiesPartyPreDeleteEvent.class,
				"[player] pre delete[s] [a] party")
				.description("Called when a player is deleting a party. Cancellable.")
				.examples("on pre delete party:",
						"\tmessage \"%event-partyplayer% is deleting the party %event-party%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPartyPreDeleteEvent.class, Party.class, BukkitPartiesPartyPreDeleteEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreDeleteEvent.class, PartyPlayer.class, BukkitPartiesPartyPreDeleteEvent::getCommandSender, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreDeleteEvent.class, CommandSender.class, e ->  e.getKickedPlayer() != null ? Bukkit.getPlayer(e.getKickedPlayer().getPlayerUUID()) : Bukkit.getConsoleSender(), EventValues.TIME_NOW);
		
		Skript.registerEvent("Party Post Delete", EvtPartyDelete.class, BukkitPartiesPartyPostDeleteEvent.class,
				"[player] [post] delete[s] [a] party")
				.description("Called when a player has deleted a party.")
				.examples("on post delete party:",
						"\tmessage \"%event-partyplayer% deleted the party %event-party%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPartyPostDeleteEvent.class, Party.class, BukkitPartiesPartyPostDeleteEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPostDeleteEvent.class, PartyPlayer.class, BukkitPartiesPartyPostDeleteEvent::getCommandSender, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPostDeleteEvent.class, CommandSender.class, e ->  e.getKickedPlayer() != null ? Bukkit.getPlayer(e.getKickedPlayer().getPlayerUUID()) : Bukkit.getConsoleSender(), EventValues.TIME_NOW);
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
		return "party delete";
	}
}
