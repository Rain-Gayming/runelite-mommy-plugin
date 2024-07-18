package com.mommy;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("mommy")
public interface MommyConfig extends Config
{
	@ConfigItem(
		keyName = "petname",
		name = "Petname",
		description = "Mommy's petname for you~"
	)
	default String greeting()
	{
		return "Darling";
	}
	@ConfigItem(
		keyName = "gender",
		name = "Gender",
		description = "Your gender"
	)
	default String gender()
	{
		return "girl";
	}
	@ConfigItem(
		keyName = "name",
		name = "Name",
		description = "The name you want mommy to use"
	)
	default String name()
	{
		return "Rain";
	}
	@ConfigItem(
		keyName = "pronoun_a",
		name = "prounoun a",
		description = "Your first pronoun (she/him/them)"
	)
	default String pronoun_a()
	{
		return "she";
	}
	@ConfigItem(
		keyName = "pronoun_b",
		name = "prounoun b",
		description = "Your first pronoun her/him/them)"
	)
	default String pronoun_b()
	{
		return "her";
	}
	@ConfigItem(
		keyName = "pronoun_c",
		name = "prounoun c",
		description = "Your first pronoun (hers/his/their)"
	)
	default String pronoun_c()
	{
		return "hers";
	}
	
	@ConfigItem(
		keyName = "extra_dommy",
		name = "Extra Dommy",
		description = "Mommy will be extra dominant to you~)"
	)
	default boolean extra_dommy()
	{
		return false;
	}
}
