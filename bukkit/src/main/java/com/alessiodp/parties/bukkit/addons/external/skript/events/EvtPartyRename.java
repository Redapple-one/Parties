package com.alessiodp.parties.bukkit.addons.external.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Trigger;
import ch.njol.skript.registrations.EventValues;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyPostRenameEvent;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyPreRenameEvent;
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class EvtPartyRename extends SkriptEvent {
	static {
		Skript.registerEvent("Party Pre Rename", EvtPartyRename.class, BukkitPartiesPartyPreRenameEvent.class,
				"[player] pre rename[s] [a] party")
				.description("Called when a player is renaming a party. \"event-partyplayer\" can be null if executed by console. Cancellable.")
				.examples("on pre rename party:",
						"\tmessage \"Party %name of event-party% is getting renamed to %event-string%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPartyPreRenameEvent.class, Party.class, BukkitPartiesPartyPreRenameEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreRenameEvent.class, PartyPlayer.class, BukkitPartiesPartyPreRenameEvent::getPartyPlayer, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreRenameEvent.class, String.class, BukkitPartiesPartyPreRenameEvent::getNewPartyName, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreRenameEvent.class, CommandSender.class, e ->  e.getPartyPlayer() != null ? Bukkit.getPlayer(e.getPartyPlayer().getPlayerUUID()) : Bukkit.getConsoleSender(), EventValues.TIME_NOW);
		
		Skript.registerEvent("Party Post Rename", EvtPartyRename.class, BukkitPartiesPartyPostRenameEvent.class,
				"[player] [post] rename[s] [a] party")
				.description("Called when a player renamed a party. \"event-partyplayer\" can be null if executed by console.")
				.examples("on post rename party:",
						"\tmessage \"Party %event-string% has been renamed to %name of event-party%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPartyPreRenameEvent.class, Party.class, BukkitPartiesPartyPreRenameEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreRenameEvent.class, PartyPlayer.class, BukkitPartiesPartyPreRenameEvent::getPartyPlayer, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreRenameEvent.class, String.class, BukkitPartiesPartyPreRenameEvent::getNewPartyName, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyPreRenameEvent.class, CommandSender.class, e ->  e.getPartyPlayer() != null ? Bukkit.getPlayer(e.getPartyPlayer().getPlayerUUID()) : Bukkit.getConsoleSender(), EventValues.TIME_NOW);
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
		return "party rename";
	}
}
