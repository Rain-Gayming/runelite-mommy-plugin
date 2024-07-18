package com.mommy;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.ActorDeath;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Mommy"
)
public class MommyPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private MommyConfig config;

    private final Map<Skill, Integer> oldExperience = new EnumMap<>(Skill.class);

	@Override
	protected void startUp() throws Exception
	{
		log.info("Mommy started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Mommy stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Hello " + config.greeting() +"~", "Mommy");
		}	
	}


    @Subscribe
    public void onActorDeath(ActorDeath actorDeath) {
        if (actorDeath.getActor() != client.getLocalPlayer())
            return;
		
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "It will be ok " + config.greeting() + ". Mommy promises~", "Mommy");
		
    }


    @Subscribe
    public void onStatChanged(StatChanged statChanged) {
		//thanks cengineer for the math lol
        final Skill skill = statChanged.getSkill();

        // Modified from Nightfirecat's virtual level ups plugin as this info isn't (yet?) built in to statChanged event
        final int xpAfter = client.getSkillExperience(skill);
        final int levelAfter = Experience.getLevelForXp(xpAfter);
        final int xpBefore = oldExperience.getOrDefault(skill, -1);
        final int levelBefore = xpBefore == -1 ? -1 : Experience.getLevelForXp(xpBefore);

        oldExperience.put(skill, xpAfter);

        // Do not proceed if any of the following are true:
        //  * xpBefore == -1              (don't fire when first setting new known value)
        //  * xpAfter <= xpBefore         (do not allow 200m -> 200m exp drops)
        //  * levelBefore >= levelAfter   (stop if if we're not actually reaching a new level)
        //  * levelAfter > MAX_REAL_LEVEL && config says don't include virtual (level is virtual and config ignores virtual)
        if (xpBefore == -1 || xpAfter <= xpBefore || levelBefore >= levelAfter ||
                (levelAfter > Experience.MAX_REAL_LEVEL)) {
            return;
        }

        // If we get here, 'skill' was leveled up!
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Look at Mommy's big " + config.gender() + " getting all strong~", "Mommy");
		
    }

	@Provides
	MommyConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MommyConfig.class);
	}			
}
	