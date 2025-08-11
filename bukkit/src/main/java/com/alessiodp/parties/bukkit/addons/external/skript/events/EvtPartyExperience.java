package com.alessiodp.parties.bukkit.addons.external.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Trigger;
import ch.njol.skript.registrations.EventValues;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyGetExperienceEvent;
import com.alessiodp.parties.api.events.bukkit.party.BukkitPartiesPartyLevelUpEvent;
import com.alessiodp.parties.api.interfaces.Party;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class EvtPartyExperience extends SkriptEvent {
	static {
		Skript.registerEvent("Party Get Experience", EvtPartyExperience.class, BukkitPartiesPartyGetExperienceEvent.class,
				"party get[s] experience")
				.description("Called when a party gets experience.")
				.examples("on party get experience:",
						"\tmessage \"%event-party% got %event-number% experience\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPartyGetExperienceEvent.class, Party.class, BukkitPartiesPartyGetExperienceEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyGetExperienceEvent.class, Number.class, BukkitPartiesPartyGetExperienceEvent::getExperience, EventValues.TIME_NOW);
		
		Skript.registerEvent("Party Level Up", EvtPartyExperience.class, BukkitPartiesPartyLevelUpEvent.class,
				"party level[s] up")
				.description("Called when a party levels up.")
				.examples("on party level up:",
						"\tmessage \"%event-party% leveled up to %event-number%\"")
				.since("3.0.0");
		EventValues.registerEventValue(BukkitPartiesPartyLevelUpEvent.class, Party.class, BukkitPartiesPartyLevelUpEvent::getParty, EventValues.TIME_NOW);
		EventValues.registerEventValue(BukkitPartiesPartyLevelUpEvent.class, Number.class, BukkitPartiesPartyLevelUpEvent::getNewLevel, EventValues.TIME_NOW);
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
		return "party experience";
	}
}
