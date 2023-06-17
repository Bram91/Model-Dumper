/*
 * Copyright (c) 2021, Bram91
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.bram91.modeldumper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("modeldumper")
public interface ModelDumperPluginConfig extends Config
{
	@ConfigSection(
			name = "Exporter",
			description = "Technical settings for the exporter",
			position = 0
	)
	String exporterSection = "exporterSection";

	@ConfigSection(
			name = "Transmog",
			description = "Technical settings for the transmog",
			position = 1
	)
	String transmogSection = "transmogSection";

	@ConfigItem(
		keyName = "material",
		name = "Export Color",
		description = "Also export colors for the models.",
		position = 1,
		section = exporterSection
	)
	default boolean material()
	{
		return false;
	}

	@ConfigItem(
			keyName = "forceRestPose",
			name = "Force Rest Pose (Local Player)",
			description = "Forces local player to perform an animation on export. The resulting model is in a rest pose.",
			position = 2,
			section = exporterSection
	)
	default boolean forceRestPose() { return false; }

	@ConfigItem(
			keyName="exportFormat",
			name = "Export Format",
			description="Select which 3d model file format to export to. OBJ uses averaged face colors while PLY uses vertex colors.",
			position = 3,
			section = exporterSection
	)
	default ExportFormat exportFormat() { return ExportFormat.OBJ; }

	@ConfigItem(
			keyName = "transmogEnabled",
			name = "Enabled",
			description = "Possibility to transmog into a NPC to make export easier",
			position = 0,
			section = transmogSection
	)
	default boolean transmogEnabled() {
		return false;
	}

	@ConfigItem(
			keyName = "npcId",
			name = "NPC ID",
			description = "NPC ID to transmog into",
			position = 1,
			section = transmogSection
	)
	default int npcId() {
		return -1;
	}

	@ConfigItem(
			keyName = "animationId",
			name = "Animation ID",
			description = "Animation to perform. Use 0 to disable",
			position = 2,
			section = transmogSection
	)
	default int animationId() {
		return -1;
	}

	@ConfigItem(
			keyName = "frame",
			name = "Frame",
			description = "Specific frame for the animation. Note: it will flicker a bit, but still easier to export a certain frame. Use 0 to disable",
			position = 3,
			section = transmogSection
	)
	default int frame() {
		return -1;
	}
}
