package com.example;

import net.bram91.modeldumper.ModelDumperPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ModelDumperPlugin.class);
		RuneLite.main(args);
	}
}
