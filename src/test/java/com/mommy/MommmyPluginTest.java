package com.mommy;

import com.mommy.MommyPlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MommmyPluginTest
{						
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MommyPlugin.class);
		RuneLite.main(args);
	}
}			