/* Copyright (c) 2020 by micro
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
 *
 * Portions of the code are based off of the "Implings" RuneLite plugin.
 * The "Implings" is:
 * Copyright (c) 2017, Robin <robin.weymans@gmail.com>
 * All rights reserved.
 */
package net.bram91.modeldumper;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.NpcID;
import java.util.Map;

/**
 * For each NPCid that corresponds to a pet, it is assigned a group based on how it is obtianed
 * and is given a text info on how it is obtained.
 * Once more info is known about the NPCids, we can also add information about variants
 */
@AllArgsConstructor
@Getter
public enum Pet
{
	ABYSSAL_ORPHAN(PetGroup.BOSS, NpcID.ABYSSAL_ORPHAN, Pet.ABYSSAL_ORPHAN_INFO),
	ABYSSAL_ORPHAN_5884(PetGroup.BOSS, NpcID.ABYSSAL_ORPHAN_5884, Pet.ABYSSAL_ORPHAN_INFO),

	BABY_CHINCHOMPA(PetGroup.SKILLING, NpcID.BABY_CHINCHOMPA, Pet.BABY_CHINCHOMPA_INFO),	// Red Variant
	BABY_CHINCHOMPA_6719(PetGroup.SKILLING, NpcID.BABY_CHINCHOMPA_6719, Pet.BABY_CHINCHOMPA_INFO),	// Grey Variant
	BABY_CHINCHOMPA_6720(PetGroup.SKILLING, NpcID.BABY_CHINCHOMPA_6720, Pet.BABY_CHINCHOMPA_INFO),	// Black Variant
	BABY_CHINCHOMPA_6721(PetGroup.SKILLING, NpcID.BABY_CHINCHOMPA_6721, Pet.BABY_CHINCHOMPA_INFO + " " + Pet.BABY_CHINCHOMPA_GOLD),	// Gold Variant
	BABY_CHINCHOMPA_6756(PetGroup.SKILLING, NpcID.BABY_CHINCHOMPA_6756, Pet.BABY_CHINCHOMPA_INFO),	// Red Variant
	BABY_CHINCHOMPA_6757(PetGroup.SKILLING, NpcID.BABY_CHINCHOMPA_6757, Pet.BABY_CHINCHOMPA_INFO),	// Grey Variant
	BABY_CHINCHOMPA_6758(PetGroup.SKILLING, NpcID.BABY_CHINCHOMPA_6758, Pet.BABY_CHINCHOMPA_INFO),	// Black Variant
	BABY_CHINCHOMPA_6759(PetGroup.SKILLING, NpcID.BABY_CHINCHOMPA_6759, Pet.BABY_CHINCHOMPA_INFO + " " + Pet.BABY_CHINCHOMPA_GOLD),	// Gold Variant

	BABY_MOLE(PetGroup.BOSS, NpcID.BABY_MOLE, Pet.BABY_MOLE_INFO),	// Pink/naked
	BABY_MOLE_5781(PetGroup.BOSS, NpcID.BABY_MOLE_5781, Pet.BABY_MOLE_INFO),	// Pink slightly smaller?
	BABY_MOLE_5782(PetGroup.BOSS, NpcID.BABY_MOLE_5782, Pet.BABY_MOLE_INFO),	// Pink even smaller
	BABY_MOLE_6635(PetGroup.BOSS, NpcID.BABY_MOLE_6635, Pet.BABY_MOLE_INFO),	// Small Brown	// Seen
	BABY_MOLE_6651(PetGroup.BOSS, NpcID.BABY_MOLE_6651, Pet.BABY_MOLE_INFO),	// Small Brown

	BEAVER(PetGroup.SKILLING, NpcID.BEAVER, Pet.BEAVER_INFO),
	BEAVER_6724(PetGroup.SKILLING, NpcID.BEAVER_6724, Pet.BEAVER_INFO),

	BLOODHOUND(PetGroup.OTHER, NpcID.BLOODHOUND, Pet.BLOODHOUND_INFO),
	BLOODHOUND_7232(PetGroup.OTHER, NpcID.BLOODHOUND_7232, Pet.BLOODHOUND_INFO),	// Seen in game, other players

	CALLISTO_CUB(PetGroup.BOSS, NpcID.CALLISTO_CUB, Pet.CALLISTO_CUB_INFO),
	CALLISTO_CUB_5558(PetGroup.BOSS, NpcID.CALLISTO_CUB_5558, Pet.CALLISTO_CUB_INFO),

	CAT(PetGroup.OTHER, NpcID.CAT, Pet.CAT_INFO),	// Black and grey checkered
	CAT_1619(PetGroup.OTHER, NpcID.CAT_1619, Pet.CAT_INFO),	// Black and grey checkered
	CAT_1620(PetGroup.OTHER, NpcID.CAT_1620, Pet.CAT_INFO),	// White
	CAT_1621(PetGroup.OTHER, NpcID.CAT_1621, Pet.CAT_INFO),	// Checkered orange
	CAT_1622(PetGroup.OTHER, NpcID.CAT_1622, Pet.CAT_INFO),	// Black
	CAT_1623(PetGroup.OTHER, NpcID.CAT_1623, Pet.CAT_INFO),	// Grey and Brown checkered
	CAT_1624(PetGroup.OTHER, NpcID.CAT_1624, Pet.CAT_INFO),	// Grey and blue checkered
	CAT_3831(PetGroup.OTHER, NpcID.CAT_3831, Pet.CAT_INFO),	// Brown checkered, looks unkempt
	CAT_3832(PetGroup.OTHER, NpcID.CAT_3832, Pet.CAT_INFO),	// Black checkered, looks unkempt
	CAT_6662(PetGroup.OTHER, NpcID.CAT_6662, Pet.CAT_INFO),	// Black and grey checkered
	CAT_6663(PetGroup.OTHER, NpcID.CAT_6663, Pet.CAT_INFO),	// White
	CAT_6664(PetGroup.OTHER, NpcID.CAT_6664, Pet.CAT_INFO),	// Checkered orange
	CAT_6665(PetGroup.OTHER, NpcID.CAT_6665, Pet.CAT_INFO),	// Black
	CAT_6666(PetGroup.OTHER, NpcID.CAT_6666, Pet.CAT_INFO),	// Grey and Brown checkered
	CAT_6667(PetGroup.OTHER, NpcID.CAT_6667, Pet.CAT_INFO),	// Grey and blue checkered
	CAT_7380(PetGroup.OTHER, NpcID.CAT_7380, Pet.CAT_INFO),	// Black with white feet
	//CAT_8594(PetGroup.OTHER, NpcID.CAT_8594, Pet.CAT_INFO),	// No model found

	CHAOS_ELEMENTAL_JR(PetGroup.BOSS, NpcID.CHAOS_ELEMENTAL_JR, Pet.CHAOS_ELEMENTAL_JR_INFO),	// Spotted in Game as other players
	CHAOS_ELEMENTAL_JR_5907(PetGroup.BOSS, NpcID.CHAOS_ELEMENTAL_JR_5907, Pet.CHAOS_ELEMENTAL_JR_INFO),

	CHOMPY_CHICK(PetGroup.OTHER, NpcID.CHOMPY_CHICK, Pet.CHOMPY_CHICK_INFO),
	CHOMPY_CHICK_4002(PetGroup.OTHER, NpcID.CHOMPY_CHICK_4002, Pet.CHOMPY_CHICK_INFO),	// Slightly larger?

	CLOCKWORK_CAT(PetGroup.TOY, NpcID.CLOCKWORK_CAT, Pet.CLOCKWORK_CAT_INFO),
	CLOCKWORK_CAT_541(PetGroup.TOY, NpcID.CLOCKWORK_CAT_541, Pet.CLOCKWORK_CAT_INFO),
	CLOCKWORK_CAT_2782(PetGroup.TOY, NpcID.CLOCKWORK_CAT_2782, Pet.CLOCKWORK_CAT_INFO),
	CLOCKWORK_CAT_6616(PetGroup.TOY, NpcID.CLOCKWORK_CAT_6661, Pet.CLOCKWORK_CAT_INFO),

	CORPOREAL_CRITTER(PetGroup.BOSS, NpcID.CORPOREAL_CRITTER, Pet.CORPOREAL_CRITTER_INFO),
	CORPOREAL_CRITTER_8010(PetGroup.BOSS, NpcID.CORPOREAL_CRITTER_8010, Pet.CORPOREAL_CRITTER_INFO),

	CORRUPTED_YOUNGLLEF(PetGroup.BOSS, NpcID.CORRUPTED_YOUNGLLEF, Pet.CORRUPTED_YOUNGLLEF_INFO),
	CORRUPTED_YOUNGLLEF_8738(PetGroup.BOSS, NpcID.CORRUPTED_YOUNGLLEF_8738, Pet.CORRUPTED_YOUNGLLEF_INFO),

	DAGANNOTH_PRIME_JR(PetGroup.BOSS, NpcID.DAGANNOTH_PRIME_JR, Pet.DAGANNOTH_PRIME_JR_INFO),
	DAGANNOTH_PRIME_JR_9929(PetGroup.BOSS, NpcID.DAGANNOTH_PRIME_JR_6629, Pet.DAGANNOTH_PRIME_JR_INFO),

	DAGANNOTH_REX_JR(PetGroup.BOSS, NpcID.DAGANNOTH_REX_JR, Pet.DAGANNOTH_REX_JR_INFO),
	DAGANNOTH_REX_JR_6641(PetGroup.BOSS, NpcID.DAGANNOTH_REX_JR_6641, Pet.DAGANNOTH_REX_JR_INFO),

	DAGANNOTH_SUPREME_JR(PetGroup.BOSS, NpcID.DAGANNOTH_SUPREME_JR, Pet.DAGANNOTH_SUPREME_JR_INFO),
	DAGANNOTH_SUPREME_JR_6628(PetGroup.BOSS, NpcID.DAGANNOTH_SUPREME_JR_6628, Pet.DAGANNOTH_SUPREME_JR_INFO),

	DARK_CORE(PetGroup.BOSS, NpcID.DARK_CORE, Pet.DARK_CORE_INFO),    // Not sure this is the pet dark core, but it looks right
	DARK_CORE_388(PetGroup.BOSS, NpcID.DARK_CORE_388, Pet.DARK_CORE_INFO),

	DARK_SQUIRREL(PetGroup.BOSS, NpcID.DARK_SQUIRREL, Pet.GIANT_SQUIRREL_INFO + Pet.DARK_Giant_SQUIRREL),	// Thank you to Gamma91/Bram91 on gitHub for hte info
	DARK_SQUIRREL_9638(PetGroup.BOSS, NpcID.DARK_SQUIRREL_9638, Pet.GIANT_SQUIRREL_INFO + Pet.DARK_Giant_SQUIRREL),

	EEK(PetGroup.OTHER, NpcID.EEK, Pet.EEK_INFO),

	ENRAGED_TEKTINY(PetGroup.OTHER, NpcID.ENRAGED_TEKTINY, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),
	ENRAGED_TEKTINY_9513(PetGroup.OTHER, NpcID.ENRAGED_TEKTINY_9513, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),

	FISHBOWL(PetGroup.OTHER, NpcID.FISHBOWL, Pet.FISHBOWL_INFO),	// Blue
	FISHBOWL_6659(PetGroup.OTHER, NpcID.FISHBOWL_6659, Pet.FISHBOWL_INFO),	// Green
	FISHBOWL_6660(PetGroup.OTHER, NpcID.FISHBOWL_6660, Pet.FISHBOWL_INFO),	// Gold

	FLYING_VESPINA(PetGroup.OTHER, NpcID.FLYING_VESPINA, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),
	FLYING_VESPINA_9514(PetGroup.OTHER, NpcID.FLYING_VESPINA_9514, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),

	GENERAL_GRAARDOR_JR(PetGroup.BOSS, NpcID.GENERAL_GRAARDOR_JR, Pet.GENERAL_GRAARDOR_JR_INFO),
	GENERAL_GRAARDOR_JR_6644(PetGroup.BOSS, NpcID.GENERAL_GRAARDOR_JR_6644, Pet.GENERAL_GRAARDOR_JR_INFO),

	GIANT_SQUIRREL(PetGroup.SKILLING, NpcID.GIANT_SQUIRREL, Pet.GIANT_SQUIRREL_INFO),
	GIANT_SQUIRREL_7351(PetGroup.SKILLING, NpcID.GIANT_SQUIRREL_7351, Pet.GIANT_SQUIRREL_INFO),	// Seen in game as other players
	GIANT_SQUIRREL_9666(PetGroup.SKILLING, NpcID.GIANT_SQUIRREL_9666, Pet.GIANT_SQUIRREL_INFO), // There's usually an even number, so I'm not sure what's up

	HELLCAT(PetGroup.OTHER, NpcID.HELLCAT, Pet.HELLCAT_INFO),	// Spotted in Game as other players
	HELLCAT_6668(PetGroup.OTHER, NpcID.HELLCAT_6668, Pet.HELLCAT_INFO),	// Spotted in Game as other players

	HELLKITTEN(PetGroup.OTHER, NpcID.HELLKITTEN, Pet.HELLKITTEN_INFO),

	HELLPUPPY(PetGroup.BOSS, NpcID.HELLPUPPY, Pet.HELLPUPPY_INFO),
	HELLPUPPY_3099(PetGroup.BOSS, NpcID.HELLPUPPY_3099, Pet.HELLPUPPY_INFO),	// Seen in game as other players

	HERBI(PetGroup.SKILLING, NpcID.HERBI, Pet.HERBI_INFO),
	HERBI_7760(PetGroup.SKILLING, NpcID.HERBI_7760, Pet.HERBI_INFO),	// Spotted in game as other players, slightly larger?

	HERON(PetGroup.SKILLING, NpcID.HERON, Pet.HERON_INFO),	// Spotted in Game as other players
	HERON_6722(PetGroup.SKILLING, NpcID.HERON_6722, Pet.HERON_INFO),

	IKKLE_HYDRA(PetGroup.BOSS, NpcID.IKKLE_HYDRA, Pet.IKKLE_HYDRA_INFO),	// Green Variant, other player
	IKKLE_HYDRA_8493(PetGroup.BOSS, NpcID.IKKLE_HYDRA_8493, Pet.IKKLE_HYDRA_INFO),	// Blue Variant, other player
	IKKLE_HYDRA_8494(PetGroup.BOSS, NpcID.IKKLE_HYDRA_8494, Pet.IKKLE_HYDRA_INFO),	// Red Variant, other player
	IKKLE_HYDRA_8495(PetGroup.BOSS, NpcID.IKKLE_HYDRA_8495, Pet.IKKLE_HYDRA_INFO),	// Grey Variant, other player
	IKKLE_HYDRA_8517(PetGroup.BOSS, NpcID.IKKLE_HYDRA_8517, Pet.IKKLE_HYDRA_INFO),	// Green
	IKKLE_HYDRA_8518(PetGroup.BOSS, NpcID.IKKLE_HYDRA_8518, Pet.IKKLE_HYDRA_INFO),	// Blue
	IKKLE_HYDRA_8519(PetGroup.BOSS, NpcID.IKKLE_HYDRA_8519, Pet.IKKLE_HYDRA_INFO),	// Red
	IKKLE_HYDRA_8520(PetGroup.BOSS, NpcID.IKKLE_HYDRA_8520, Pet.IKKLE_HYDRA_INFO),	// Grey

	JALNIBREK(PetGroup.BOSS, NpcID.JALNIBREK, Pet.JALNIBREK_INFO),
	JALNIBREK_7675(PetGroup.BOSS, NpcID.JALNIBREK_7675, Pet.JALNIBREK_INFO),

	KALPHITE_PRINCESS(PetGroup.BOSS, NpcID.KALPHITE_PRINCESS, Pet.KALPHITE_PRINCESS_INFO),	// Orange airborn
	KALPHITE_PRINCESS_6638(PetGroup.BOSS, NpcID.KALPHITE_PRINCESS_6638, Pet.KALPHITE_PRINCESS_INFO),	// Green grounded	// Seen in game as other players
	KALPHITE_PRINCESS_6653(PetGroup.BOSS, NpcID.KALPHITE_PRINCESS_6653, Pet.KALPHITE_PRINCESS_INFO),	// Green grounded
	KALPHITE_PRINCESS_6654(PetGroup.BOSS, NpcID.KALPHITE_PRINCESS_6654, Pet.KALPHITE_PRINCESS_INFO),	// Green grounded

	KITTEN(PetGroup.OTHER, NpcID.KITTEN, Pet.KITTEN_INFO),	// Black and grey checkered
	KITTEN_5591(PetGroup.OTHER, NpcID.KITTEN_5591, Pet.KITTEN_INFO),	// Black and grey checkered
	KITTEN_5592(PetGroup.OTHER, NpcID.KITTEN_5592, Pet.KITTEN_INFO),	// White	// Seen in game as other players, white kitten
	KITTEN_5593(PetGroup.OTHER, NpcID.KITTEN_5593, Pet.KITTEN_INFO),	// Checkered orange	// Seen in game as other players
	KITTEN_5594(PetGroup.OTHER, NpcID.KITTEN_5594, Pet.KITTEN_INFO),	// Black	// Seen in game as other players
	KITTEN_5595(PetGroup.OTHER, NpcID.KITTEN_5595, Pet.KITTEN_INFO),	// Grey and Brown checkered
	KITTEN_5596(PetGroup.OTHER, NpcID.KITTEN_5596, Pet.KITTEN_INFO),	// Grey and blue checkered	// Seen in game as other players

	KRAKEN_6640(PetGroup.BOSS, NpcID.KRAKEN_6640, Pet.KRAKEN_INFO),
	KRAKEN_6656(PetGroup.BOSS, NpcID.KRAKEN_6656, Pet.KRAKEN_INFO),

	KREEARRA_JR(PetGroup.BOSS, NpcID.KREEARRA_JR, Pet.KREEARRA_JR_INFO),
	KREEARRA_JR_6643(PetGroup.BOSS, NpcID.KREEARRA_JR_6643, Pet.KREEARRA_JR_INFO),

	KRIL_TSUTSAROTH_JR(PetGroup.BOSS, NpcID.KRIL_TSUTSAROTH_JR, Pet.KRIL_TSUTSAROTH_JR_INFO),
	KRIL_TSUTSAROTH_JR_6647(PetGroup.BOSS, NpcID.KRIL_TSUTSAROTH_JR_6647, Pet.KRIL_TSUTSAROTH_JR_INFO),

	LAZY_CAT(PetGroup.OTHER, NpcID.LAZY_CAT, Pet.LAZY_CAT_INFO),	// White
	LAZY_CAT_1627(PetGroup.OTHER, NpcID.LAZY_CAT_1627, Pet.LAZY_CAT_INFO),	// Black and grey checkered
	LAZY_CAT_1628(PetGroup.OTHER, NpcID.LAZY_CAT_1628, Pet.LAZY_CAT_INFO),	// Checkered orange
	LAZY_CAT_1629(PetGroup.OTHER, NpcID.LAZY_CAT_1629, Pet.LAZY_CAT_INFO),	// Black
	LAZY_CAT_1630(PetGroup.OTHER, NpcID.LAZY_CAT_1630, Pet.LAZY_CAT_INFO),	// Grey and Brown checkered
	LAZY_CAT_1631(PetGroup.OTHER, NpcID.LAZY_CAT_1631, Pet.LAZY_CAT_INFO),	// Grey and blue checkered
	LAZY_CAT_6683(PetGroup.OTHER, NpcID.LAZY_CAT_6683, Pet.LAZY_CAT_INFO),	// Black and grey checkered
	LAZY_CAT_6684(PetGroup.OTHER, NpcID.LAZY_CAT_6684, Pet.LAZY_CAT_INFO),	// White
	LAZY_CAT_6685(PetGroup.OTHER, NpcID.LAZY_CAT_6685, Pet.LAZY_CAT_INFO),	// Checkered orange
	LAZY_CAT_6686(PetGroup.OTHER, NpcID.LAZY_CAT_6686, Pet.LAZY_CAT_INFO),	// Black
	LAZY_CAT_6687(PetGroup.OTHER, NpcID.LAZY_CAT_6687, Pet.LAZY_CAT_INFO),	// Grey and Brown checkered
	LAZY_CAT_6688(PetGroup.OTHER, NpcID.LAZY_CAT_6688, Pet.LAZY_CAT_INFO),	// Grey and blue checkered

	LAZY_HELLCAT(PetGroup.OTHER, NpcID.LAZY_HELLCAT, Pet.LAZY_CAT_INFO),
	LAZY_HELLCAT_6689(PetGroup.OTHER, NpcID.LAZY_HELLCAT_6689, Pet.LAZY_CAT_INFO),

	LIL_ZIK(PetGroup.BOSS, NpcID.LIL_ZIK, Pet.LIL_ZIK_INFO),
	LIL_ZIK_8377(PetGroup.BOSS, NpcID.LIL_ZIK_8337, Pet.LIL_ZIK_INFO),	// Seen in game other player

	LITTLE_NIGHTMARE(PetGroup.BOSS, NpcID.LITTLE_NIGHTMARE, Pet.LITTLE_NIGHTMARE_INFO),
	LITTLE_NIGHTMARE_9399(PetGroup.BOSS, NpcID.LITTLE_NIGHTMARE_9399, Pet.LITTLE_NIGHTMARE_INFO),

	MIDNIGHT(PetGroup.BOSS, NpcID.MIDNIGHT, Pet.MIDNIGHT_INFO),
	MIDNIGHT_7893(PetGroup.BOSS, NpcID.MIDNIGHT_7893, Pet.MIDNIGHT_INFO),	// Seen in game other player, morfed to NOON_7892

	NOON(PetGroup.BOSS, NpcID.NOON, Pet.NOON_INFO),
	NOON_7892(PetGroup.BOSS, NpcID.NOON_7892, Pet.NOON_INFO),	// Seen in game as other players, morfed to MIDNIGHT_7893

	OLMLET(PetGroup.BOSS, NpcID.OLMLET, Pet.OLMLET_INFO),
	OLMLET_7520(PetGroup.BOSS, NpcID.OLMLET_7520, Pet.OLMLET_INFO),	// Seen in game other player

	OVERGROWN_CAT(PetGroup.OTHER, NpcID.OVERGROWN_CAT, Pet.OVERGROWN_CAT_INFO),	// Black and grey checkered
	OVERGROWN_CAT_5599(PetGroup.OTHER, NpcID.OVERGROWN_CAT_5599, Pet.OVERGROWN_CAT_INFO),	// White
	OVERGROWN_CAT_5600(PetGroup.OTHER, NpcID.OVERGROWN_CAT_5600, Pet.OVERGROWN_CAT_INFO),	// Checkered orange
	OVERGROWN_CAT_5601(PetGroup.OTHER, NpcID.OVERGROWN_CAT_5601, Pet.OVERGROWN_CAT_INFO),	// Black
	OVERGROWN_CAT_5602(PetGroup.OTHER, NpcID.OVERGROWN_CAT_5602, Pet.OVERGROWN_CAT_INFO),	// Grey and Brown checkered
	OVERGROWN_CAT_5603(PetGroup.OTHER, NpcID.OVERGROWN_CAT_5603, Pet.OVERGROWN_CAT_INFO),	// Grey and blue checkered	// Seen in game as other players
	OVERGROWN_CAT_6676(PetGroup.OTHER, NpcID.OVERGROWN_CAT_6676, Pet.OVERGROWN_CAT_INFO),	// Black and grey checkered
	OVERGROWN_CAT_6677(PetGroup.OTHER, NpcID.OVERGROWN_CAT_6677, Pet.OVERGROWN_CAT_INFO),	// White
	OVERGROWN_CAT_6678(PetGroup.OTHER, NpcID.OVERGROWN_CAT_6678, Pet.OVERGROWN_CAT_INFO),	// Checkered orange
	OVERGROWN_CAT_6679(PetGroup.OTHER, NpcID.OVERGROWN_CAT_6679, Pet.OVERGROWN_CAT_INFO),	// Black
	OVERGROWN_CAT_6680(PetGroup.OTHER, NpcID.OVERGROWN_CAT_6680, Pet.OVERGROWN_CAT_INFO),	// Grey and Brown checkered
	OVERGROWN_CAT_6681(PetGroup.OTHER, NpcID.OVERGROWN_CAT_6681, Pet.OVERGROWN_CAT_INFO),	// Grey and blue checkered

	OVERGROWN_HELLCAT(PetGroup.OTHER, NpcID.OVERGROWN_HELLCAT, Pet.OVERGROWN_HELLCAT_INFO),	// Seen in game as other players
	OVERGROWN_HELLCAT_6682(PetGroup.OTHER, NpcID.OVERGROWN_HELLCAT_6682, Pet.OVERGROWN_HELLCAT_INFO),

	PET_ROCK(PetGroup.OTHER, NpcID.PET_ROCK, Pet.PET_ROCK_INFO),
	PET_ROCK_6657(PetGroup.OTHER, NpcID.PET_ROCK_6657, Pet.PET_ROCK_INFO),

	PENANCE_PET(PetGroup.OTHER, NpcID.PENANCE_PET, Pet.PENANCE_PET_INFO),
	PENANCE_PET_6674(PetGroup.OTHER, NpcID.PENANCE_PET_6674, Pet.PENANCE_PET_INFO),

	PHOENIX(PetGroup.SKILLING, NpcID.PHOENIX, Pet.PHOENIX_INFO),	// Green
	PHOENIX_3078(PetGroup.SKILLING, NpcID.PHOENIX_3078, Pet.PHOENIX_INFO),	// Blue
	PHOENIX_3079(PetGroup.SKILLING, NpcID.PHOENIX_3079, Pet.PHOENIX_INFO),	// White
	PHOENIX_3080(PetGroup.SKILLING, NpcID.PHOENIX_3080, Pet.PHOENIX_INFO),	// Purple
	PHOENIX_3081(PetGroup.SKILLING, NpcID.PHOENIX_3081, Pet.PHOENIX_INFO),	// Green
	PHOENIX_3082(PetGroup.SKILLING, NpcID.PHOENIX_3082, Pet.PHOENIX_INFO),	// Blue
	PHOENIX_3083(PetGroup.SKILLING, NpcID.PHOENIX_3083, Pet.PHOENIX_INFO),	// White
	PHOENIX_3084(PetGroup.SKILLING, NpcID.PHOENIX_3084, Pet.PHOENIX_INFO),	// Purple
	PHOENIX_7368(PetGroup.SKILLING, NpcID.PHOENIX_7368, Pet.PHOENIX_INFO),	// Orange
	PHOENIX_7370(PetGroup.SKILLING, NpcID.PHOENIX_7370, Pet.PHOENIX_INFO),	// Orange

	PRINCE_BLACK_DRAGON(PetGroup.BOSS, NpcID.PRINCE_BLACK_DRAGON, Pet.PRINCE_BLACK_DRAGON_INFO),
	PRINCE_BLACK_DRAGON_6652(PetGroup.BOSS, NpcID.PRINCE_BLACK_DRAGON_6652, Pet.PRINCE_BLACK_DRAGON_INFO),

	PUPPADILE(PetGroup.BOSS, NpcID.PUPPADILE, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),
	PUPPADILE_8201(PetGroup.BOSS, NpcID.PUPPADILE_8201, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),

	RED(PetGroup.SKILLING, NpcID.RED, Pet.ROCKY_INFO + " " + Pet.ROCKY_RED_PANDA),
	RED_9852(PetGroup.SKILLING, NpcID.RED_9852, Pet.ROCKY_INFO + " " + Pet.ROCKY_RED_PANDA),

	RIFT_GUARDIAN(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN, Pet.RIFT_GUARDIAN_INFO + " This is the Fire variant."),	// Fire
	RIFT_GUARDIAN_7338(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7338, Pet.RIFT_GUARDIAN_INFO + " This is the Air variant."),	// Air
	RIFT_GUARDIAN_7339(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7339, Pet.RIFT_GUARDIAN_INFO + " This is the Mind variant."),	// Mind
	RIFT_GUARDIAN_7340(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7340, Pet.RIFT_GUARDIAN_INFO + " This is the Water variant."),	// Water
	RIFT_GUARDIAN_7341(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7341, Pet.RIFT_GUARDIAN_INFO + " This is the Earth variant."),	// Earth
	RIFT_GUARDIAN_7342(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7342, Pet.RIFT_GUARDIAN_INFO + " This is the Body variant."),	// Body
	RIFT_GUARDIAN_7343(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7343, Pet.RIFT_GUARDIAN_INFO + " This is the Cosmic variant."),	// Cosmic
	RIFT_GUARDIAN_7344(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7344, Pet.RIFT_GUARDIAN_INFO + " This is the Chaos variant."),	// Chaos
	RIFT_GUARDIAN_7345(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7345, Pet.RIFT_GUARDIAN_INFO + " This is the Nature variant."),	// Nature
	RIFT_GUARDIAN_7346(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7346, Pet.RIFT_GUARDIAN_INFO + " This is the Law variant."),	// Law
	RIFT_GUARDIAN_7347(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7347, Pet.RIFT_GUARDIAN_INFO + " This is the Death variant."),	// Death
	RIFT_GUARDIAN_7348(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7348, Pet.RIFT_GUARDIAN_INFO + " This is the Soul variant."),	// Soul
	RIFT_GUARDIAN_7349(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7349, Pet.RIFT_GUARDIAN_INFO + " This is the Astral variant."),	// Astral
	RIFT_GUARDIAN_7350(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7350, Pet.RIFT_GUARDIAN_INFO + " This is the Blood variant."),	// Blood
	RIFT_GUARDIAN_7354(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7354, Pet.RIFT_GUARDIAN_INFO + " This is the Wrath variant."),	// Wrath
	RIFT_GUARDIAN_7355(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7355, Pet.RIFT_GUARDIAN_INFO + " This is the Air variant."),	// Air
	RIFT_GUARDIAN_7356(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7356, Pet.RIFT_GUARDIAN_INFO + " This is the Mind variant."),	// Mind
	RIFT_GUARDIAN_7357(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7357, Pet.RIFT_GUARDIAN_INFO + " This is the Water variant."),	// Water
	RIFT_GUARDIAN_7358(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7358, Pet.RIFT_GUARDIAN_INFO + " This is the Earth variant."),	// Earth
	RIFT_GUARDIAN_7359(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7359, Pet.RIFT_GUARDIAN_INFO + " This is the Body variant."),	// Body
	RIFT_GUARDIAN_7360(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7360, Pet.RIFT_GUARDIAN_INFO + " This is the Cosmic variant."),	// Cosmic
	RIFT_GUARDIAN_7361(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7361, Pet.RIFT_GUARDIAN_INFO + " This is the Chaos variant."),	// Chaos
	RIFT_GUARDIAN_7362(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7362, Pet.RIFT_GUARDIAN_INFO + " This is the Nature variant."),	// Nature
	RIFT_GUARDIAN_7363(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7363, Pet.RIFT_GUARDIAN_INFO + " This is the Law variant."),	// Law
	RIFT_GUARDIAN_7364(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7364, Pet.RIFT_GUARDIAN_INFO + " This is the Death variant."),	// Death
	RIFT_GUARDIAN_7365(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7365, Pet.RIFT_GUARDIAN_INFO + " This is the Soul variant."),	// Soul
	RIFT_GUARDIAN_7366(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7366, Pet.RIFT_GUARDIAN_INFO + " This is the Astral variant."),	// Astral
	RIFT_GUARDIAN_7367(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_7367, Pet.RIFT_GUARDIAN_INFO + " This is the Blood variant."),	// Blood
	RIFT_GUARDIAN_8024(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_8024, Pet.RIFT_GUARDIAN_INFO + " This is the Wrath variant."),	// Wrath
	RIFT_GUARDIAN_8028(PetGroup.SKILLING, NpcID.RIFT_GUARDIAN_8028, Pet.RIFT_GUARDIAN_INFO + " This is the Wrath variant."),	// Wrath?

	ROCK_GOLEM(PetGroup.SKILLING, NpcID.ROCK_GOLEM, Pet.ROCK_GOLEM_INFO + " This is the Amethyst variant."),	// Amethyst
	// ROCK_GOLEM_6725(PetGroup.SKILLING, NpcID.ROCK_GOLEM_6725, Pet.ROCK_GOLEM_INFO),	// Not the pet
//	ROCK_GOLEM_6726(PetGroup.SKILLING, NpcID.ROCK_GOLEM_6726, Pet.ROCK_GOLEM_INFO),	// Not the pet
//	ROCK_GOLEM_6727(PetGroup.SKILLING, NpcID.ROCK_GOLEM_6727, Pet.ROCK_GOLEM_INFO),	// Not the pet
//	ROCK_GOLEM_6728(PetGroup.SKILLING, NpcID.ROCK_GOLEM_6728, Pet.ROCK_GOLEM_INFO),	// Not the pet
//	ROCK_GOLEM_6729(PetGroup.SKILLING, NpcID.ROCK_GOLEM_6729, Pet.ROCK_GOLEM_INFO),	// Not the pet
//	ROCK_GOLEM_6730(PetGroup.SKILLING, NpcID.ROCK_GOLEM_6730, Pet.ROCK_GOLEM_INFO),	// Not the pet
	ROCK_GOLEM_7439(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7439, Pet.ROCK_GOLEM_INFO + " This is the Rock variant."),	// Rock
	ROCK_GOLEM_7440(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7440, Pet.ROCK_GOLEM_INFO + " This is the Tin variant."),	// Tin
	ROCK_GOLEM_7441(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7441, Pet.ROCK_GOLEM_INFO + " This is the Copper variant."),	// Copper
	ROCK_GOLEM_7442(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7442, Pet.ROCK_GOLEM_INFO + " This is the Iron variant."),	// Iron
	ROCK_GOLEM_7443(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7443, Pet.ROCK_GOLEM_INFO + " This is the Blurite variant."),	// Blurite
	ROCK_GOLEM_7444(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7444, Pet.ROCK_GOLEM_INFO + " This is the Silver variant."),	// Silver
	ROCK_GOLEM_7445(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7445, Pet.ROCK_GOLEM_INFO + " This is the Coal variant."),	// Coal
	ROCK_GOLEM_7446(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7446, Pet.ROCK_GOLEM_INFO + " This is the Gold variant."),	// Gold
	ROCK_GOLEM_7447(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7447, Pet.ROCK_GOLEM_INFO + " This is the Mithril variant."),	// Mithril
	ROCK_GOLEM_7448(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7448, Pet.ROCK_GOLEM_INFO + " This is the Granite variant."),	// Granite
	ROCK_GOLEM_7449(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7449, Pet.ROCK_GOLEM_INFO + " This is the Adamantite variant."),	// Adamantite
	ROCK_GOLEM_7450(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7450, Pet.ROCK_GOLEM_INFO + " This is the Runite variant."),	// Runite
	ROCK_GOLEM_7451(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7451, Pet.ROCK_GOLEM_INFO + " This is the Rock variant."),	// Rock
	ROCK_GOLEM_7452(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7452, Pet.ROCK_GOLEM_INFO + " This is the Tin variant."),	// Tin
	ROCK_GOLEM_7453(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7453, Pet.ROCK_GOLEM_INFO + " This is the Copper variant."),	// Copper
	ROCK_GOLEM_7454(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7454, Pet.ROCK_GOLEM_INFO + " This is the Iron variant."),	// Iron
	ROCK_GOLEM_7455(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7455, Pet.ROCK_GOLEM_INFO + " This is the Blurite variant."),	// Blurite
	ROCK_GOLEM_7642(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7642, Pet.ROCK_GOLEM_INFO + " This is the Silver variant."),	// Silver
	ROCK_GOLEM_7643(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7643, Pet.ROCK_GOLEM_INFO + " This is the Coal variant."),	// Coal
	ROCK_GOLEM_7644(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7644, Pet.ROCK_GOLEM_INFO + " This is the Gold variant."),	// Gold
	ROCK_GOLEM_7645(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7645, Pet.ROCK_GOLEM_INFO + " This is the Mithril variant."),	// Mithril
	ROCK_GOLEM_7646(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7646, Pet.ROCK_GOLEM_INFO + " This is the Granite variant."),	// Granite
	ROCK_GOLEM_7647(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7647, Pet.ROCK_GOLEM_INFO + " This is Adamantite fire variant."),	// Adamantite
	ROCK_GOLEM_7648(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7648, Pet.ROCK_GOLEM_INFO + " This is the Runite variant."),	// Runite
	ROCK_GOLEM_7711(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7711, Pet.ROCK_GOLEM_INFO + " This is the Amethyst variant."),	// Amethyst
	ROCK_GOLEM_7736(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7736, Pet.ROCK_GOLEM_INFO + " This is the Lovakite variant."),	// Lovakite
	ROCK_GOLEM_7737(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7737, Pet.ROCK_GOLEM_INFO + " This is the Elemental variant."),	// Elemental
	ROCK_GOLEM_7738(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7738, Pet.ROCK_GOLEM_INFO + " This is the Daeyalt variant."),	// Daeyalt
	ROCK_GOLEM_7739(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7739, Pet.ROCK_GOLEM_INFO + " This is the Lovakite variant."),	// Lovakite variant, otherplayer
	ROCK_GOLEM_7740(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7740, Pet.ROCK_GOLEM_INFO + " This is the Elemental variant."),	// Elemental variant, other player
	ROCK_GOLEM_7741(PetGroup.SKILLING, NpcID.ROCK_GOLEM_7741, Pet.ROCK_GOLEM_INFO + " This is the Daeyalt variant."),	// Daeyalt

	ROCKY(PetGroup.SKILLING, NpcID.ROCKY, Pet.ROCKY_INFO + " " + Pet.ROCKY_RACCOON),
	ROCKY_7353(PetGroup.SKILLING, NpcID.ROCKY_7353, Pet.ROCKY_INFO + " " + Pet.ROCKY_RACCOON),

	SCORPIAS_OFFSPRING(PetGroup.BOSS, NpcID.SCORPIAS_OFFSPRING, Pet.SCORPIAS_OFFSPRING_INFO),		// Usually even, what's up with this?
	SCORPIAS_OFFSPRING_5561(PetGroup.BOSS, NpcID.SCORPIAS_OFFSPRING_5561, Pet.SCORPIAS_OFFSPRING_INFO),
	SCORPIAS_OFFSPRING_6616(PetGroup.BOSS, NpcID.SCORPIAS_OFFSPRING_6616, Pet.SCORPIAS_OFFSPRING_INFO),	// This one is much smaller

	SKOTOS(PetGroup.BOSS, NpcID.SKOTOS, Pet.SKOTOS_INFO),
	SKOTOS_7671(PetGroup.BOSS, NpcID.SKOTOS_7671, Pet.SKOTOS_INFO),

	SMOKE_DEVIL_6639(PetGroup.BOSS, NpcID.SMOKE_DEVIL_6639, Pet.SMOKE_DEVIL_INFO),	// Yellow
	SMOKE_DEVIL_6655(PetGroup.BOSS, NpcID.SMOKE_DEVIL_6655, Pet.SMOKE_DEVIL_INFO),	// Yellow
	SMOKE_DEVIL_8482(PetGroup.BOSS, NpcID.SMOKE_DEVIL_8482, Pet.SMOKE_DEVIL_INFO),	// Blue
	SMOKE_DEVIL_8483(PetGroup.BOSS, NpcID.SMOKE_DEVIL_8483, Pet.SMOKE_DEVIL_INFO),	// Blue

	SMOLCANO(PetGroup.BOSS, NpcID.SMOLCANO, Pet.SMOLCANO_INFO),
	SMOLCANO_8739(PetGroup.BOSS, NpcID.SMOLCANO_8739, Pet.SMOLCANO_INFO),

	// Not sure if there are more pet ids, or if the boss fight minions will appear
	SNAKELING_2127(PetGroup.BOSS, NpcID.SNAKELING_2127, Pet.SNAKELING_INFO),	// Green
	SNAKELING_2130(PetGroup.BOSS, NpcID.SNAKELING_2130, Pet.SNAKELING_INFO),	// Green variant, other player
	SNAKELING_2131(PetGroup.BOSS, NpcID.SNAKELING_2131, Pet.SNAKELING_INFO),	// Red variant, other player
	SNAKELING_2132(PetGroup.BOSS, NpcID.SNAKELING_2132, Pet.SNAKELING_INFO),	// Blue/Purple variant, other player

	SRARACHA(PetGroup.BOSS, NpcID.SRARACHA, Pet.SRARACHA_INFO),
	SRARACHA_2144(PetGroup.BOSS, NpcID.SRARACHA_2144, Pet.SRARACHA_INFO),

	TANGLEROOT(PetGroup.SKILLING, NpcID.TANGLEROOT, Pet.TANGLEROOT_INFO),	// Acorn
	TANGLEROOT_7352(PetGroup.SKILLING, NpcID.TANGLEROOT_7352, Pet.TANGLEROOT_INFO + " This is the Acorn variant."),	// Acorn
	TANGLEROOT_9492(PetGroup.SKILLING, NpcID.TANGLEROOT_9492, Pet.TANGLEROOT_INFO + " This is the Crystal variant."),	// Crystal
	TANGLEROOT_9493(PetGroup.SKILLING, NpcID.TANGLEROOT_9493, Pet.TANGLEROOT_INFO + " This is the Dragon Fruit variant."),	// Dragon
	TANGLEROOT_9494(PetGroup.SKILLING, NpcID.TANGLEROOT_9494, Pet.TANGLEROOT_INFO + " This is the Guam variant."),	// Guam
	TANGLEROOT_9495(PetGroup.SKILLING, NpcID.TANGLEROOT_9495, Pet.TANGLEROOT_INFO + " This is the White Lily variant."),	// White Lily
	TANGLEROOT_9496(PetGroup.SKILLING, NpcID.TANGLEROOT_9496, Pet.TANGLEROOT_INFO + " This is the Redwood variant."),	// Redwood
	TANGLEROOT_9497(PetGroup.SKILLING, NpcID.TANGLEROOT_9497, Pet.TANGLEROOT_INFO + " This is the Crystal variant."),	// Crystal
	TANGLEROOT_9498(PetGroup.SKILLING, NpcID.TANGLEROOT_9498, Pet.TANGLEROOT_INFO + " This is the Dragon Fruit variant."),	// Dragon Fruit Variant, other player
	TANGLEROOT_9499(PetGroup.SKILLING, NpcID.TANGLEROOT_9499, Pet.TANGLEROOT_INFO + " This is the Guam variant."),	// Guam Variant, other player
	TANGLEROOT_9500(PetGroup.SKILLING, NpcID.TANGLEROOT_9500, Pet.TANGLEROOT_INFO + " This is the White Lily variant."),	// White Lily Variant, other player
	TANGLEROOT_9501(PetGroup.SKILLING, NpcID.TANGLEROOT_9501, Pet.TANGLEROOT_INFO + " This is the Redwood variant."),	// Redwood Variant, other player

	TEKTINY(PetGroup.BOSS, NpcID.TEKTINY, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),
	TEKTINY_8202(PetGroup.BOSS, NpcID.TEKTINY_8202, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),

	TOY_DOLL(PetGroup.TOY, NpcID.TOY_DOLL, Pet.TOY_DOLL_INFO),
	TOY_DOLL_9253(PetGroup.TOY, NpcID.TOY_DOLL_9253, Pet.TOY_DOLL_INFO),

	TOY_MOUSE(PetGroup.TOY, NpcID.TOY_MOUSE, Pet.TOY_MOUSE_INFO),
	TOY_MOUSE_9255(PetGroup.TOY, NpcID.TOY_MOUSE_9255, Pet.TOY_MOUSE_INFO),

	TOY_SOLDIER(PetGroup.TOY, NpcID.TOY_SOLDIER, Pet.TOY_SOLDIER_INFO),
	TOY_SOLDIER_9251(PetGroup.TOY, NpcID.TOY_SOLDIER_9251, Pet.TOY_SOLDIER_INFO),

	TZREKJAD(PetGroup.BOSS, NpcID.TZREKJAD, Pet.TZREKJAD_INFO),
	TZREKJAD_5893(PetGroup.BOSS, NpcID.TZREKJAD_5893, Pet.TZREKJAD_INFO),

	TZREKZUK(PetGroup.BOSS, NpcID.TZREKZUK, Pet.TZREKZUK_IHFO),
	TZREKZUK_8011(PetGroup.BOSS, NpcID.TZREKZUK_8011, Pet.TZREKZUK_IHFO),

	VANGUARD_8198(PetGroup.BOSS, NpcID.VANGUARD_8198, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),
	VANGUARD_8203(PetGroup.BOSS, NpcID.VANGUARD_8203, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),

	VASA_MINIRIO(PetGroup.BOSS, NpcID.VASA_MINIRIO, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),
	VASA_MINIRIO_8204(PetGroup.BOSS, NpcID.VASA_MINIRIO_8204, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),

	VENENATIS_SPIDERLING(PetGroup.BOSS, NpcID.VENENATIS_SPIDERLING, Pet.VENENATIS_SPIDERLING_INFO),
	VENENATIS_SPIDERLING_5557(PetGroup.BOSS, NpcID.VENENATIS_SPIDERLING_5557, Pet.VENENATIS_SPIDERLING_INFO),

	VESPINA(PetGroup.BOSS, NpcID.VESPINA, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),
	VESPINA_8205(PetGroup.BOSS, NpcID.VESPINA_8205, Pet.OLMLET_INFO + Pet.OLMLET_CM_VARIANTS),

	VETION_JR(PetGroup.BOSS, NpcID.VETION_JR, Pet.VETION_JR_INFO),	// Purple
	VETION_JR_5537(PetGroup.BOSS, NpcID.VETION_JR_5537, Pet.VETION_JR_INFO),	// Orange
	VETION_JR_5559(PetGroup.BOSS, NpcID.VETION_JR_5559, Pet.VETION_JR_INFO),	// Purple
	VETION_JR_5560(PetGroup.BOSS, NpcID.VETION_JR_5560, Pet.VETION_JR_INFO),	// Orange

	VORKI(PetGroup.BOSS, NpcID.VORKI, Pet.VORKI_INFO),
	VORKI_8029(PetGroup.BOSS, NpcID.VORKI_8029, Pet.VORKI_INFO),	// Seen in game, other player

	WILY_CAT(PetGroup.OTHER, NpcID.WILY_CAT, Pet.WILY_CAT_INFO),	// White
	WILY_CAT_5585(PetGroup.OTHER, NpcID.WILY_CAT_5585, Pet.WILY_CAT_INFO),	// Grey and Brown checkered
	WILY_CAT_5586(PetGroup.OTHER, NpcID.WILY_CAT_5586, Pet.WILY_CAT_INFO),	// Checkered orange
	WILY_CAT_5587(PetGroup.OTHER, NpcID.WILY_CAT_5587, Pet.WILY_CAT_INFO),	// Black
	WILY_CAT_5588(PetGroup.OTHER, NpcID.WILY_CAT_5588, Pet.WILY_CAT_INFO),	// Grey and Brown checkered
	WILY_CAT_5589(PetGroup.OTHER, NpcID.WILY_CAT_5589, Pet.WILY_CAT_INFO),	// Grey and blue checkered
	WILY_CAT_6690(PetGroup.OTHER, NpcID.WILY_CAT_6690, Pet.WILY_CAT_INFO),	// Black and grey checkered
	WILY_CAT_6691(PetGroup.OTHER, NpcID.WILY_CAT_6691, Pet.WILY_CAT_INFO),	// White
	WILY_CAT_6692(PetGroup.OTHER, NpcID.WILY_CAT_6692, Pet.WILY_CAT_INFO),	// Checkered orange
	WILY_CAT_6693(PetGroup.OTHER, NpcID.WILY_CAT_6693, Pet.WILY_CAT_INFO),	// Black
	WILY_CAT_6694(PetGroup.OTHER, NpcID.WILY_CAT_6694, Pet.WILY_CAT_INFO),	// Grey and Brown checkered
	WILY_CAT_6695(PetGroup.OTHER, NpcID.WILY_CAT_6695, Pet.WILY_CAT_INFO),	// Black and grey checkered

	WILY_HELLCAT(PetGroup.OTHER, NpcID.WILY_HELLCAT, Pet.WILY_HELLCAT_INFO),
	WILY_HELLCAT_6696(PetGroup.OTHER, NpcID.WILY_HELLCAT_6696, Pet.WILY_HELLCAT_INFO),

	YOUNGLLEF(PetGroup.BOSS, NpcID.YOUNGLLEF, Pet.YOUNGLLEF_INFO),
	YOUNGLLEF_8737(PetGroup.BOSS, NpcID.YOUNGLLEF_8737, Pet.YOUNGLLEF_INFO),

	ZIGGY(PetGroup.SKILLING, NpcID.ZIGGY, Pet.ROCKY_INFO + " " + Pet.ROCKY_TANUKI),
	ZIGGY_9853(PetGroup.SKILLING, NpcID.ZIGGY_9853, Pet.ROCKY_INFO + " " + Pet.ROCKY_TANUKI),

	ZILYANA_JR(PetGroup.BOSS, NpcID.ZILYANA_JR, Pet.ZILYANA_JR_INFO),
	ZILYANA_JR_6646(PetGroup.BOSS, NpcID.ZILYANA_JR_6646, Pet.ZILYANA_JR_INFO);

	private PetGroup petGroup;
	private final int npcId;
	private String info;

	private static final Map<Integer, Pet> PETS;

	static
	{
		ImmutableMap.Builder<Integer, Pet> builder = new ImmutableMap.Builder<>();

		for (Pet pet : values())
		{
			builder.put(pet.npcId, pet);
		}

		PETS = builder.build();
	}

	/**
	 * Returns the Pet enum if the passed NPCid is a pet, null if not
	 */
	static Pet findPet(int npcId)
	{
		return PETS.get(npcId);
	}

	/**
	 * Returns the info string for the passed NPCid
	 */
	static String getInfo(int npcId)
	{
		return PETS.get(npcId).info;
	}

	/*
	 * Pet Info Strings
	 */
	private static final String ABYSSAL_ORPHAN_INFO = "is obtained by placing an unsired on the Font of Consumption, at a rate of 5/128.";
	private static final String BABY_CHINCHOMPA_INFO = "is obtained while catching chinchompas.";
	private static final String BABY_MOLE_INFO = "is obtained by killing the Giant Mole, at a rate of 1/3000.";
	private static final String BEAVER_INFO = "is obtained while training Woodcutting.";
	private static final String BLOODHOUND_INFO = "is obtained by completing Master Clue Scrolls, at a rate of 1/1000.";
	private static final String CALLISTO_CUB_INFO = "is dropped by Callisto, at a rate of 1/2000.";
	private static final String CAT_INFO = "is obtained by letting a kitten grow for about 2 hours.";
	private static final String CHAOS_ELEMENTAL_JR_INFO = "is dropped by the Chaos Elemental, at a rate of 1/300; or the Chaos Fanatic, at 1/1000.";
	private static final String CHOMPY_CHICK_INFO = "is dropped by Chompy birds after completing the elite Western Provinces Diary, at a rate of 1/500.";
	private static final String CLOCKWORK_CAT_INFO = "can be crafted in a POH with 84 Crafting and a Crafting table 4.";
	private static final String CORPOREAL_CRITTER_INFO = "is obtained by causing a pet Dark Core to metamorphosize.";
	private static final String CORRUPTED_YOUNGLLEF_INFO = "is obtained by completing the Corrupted Gauntlet then causing a Youngllef to metamorphosize.";
	private static final String DAGANNOTH_PRIME_JR_INFO = "is dropped by Dagannoth Prime, at a rate of 1/5000.";
	private static final String DAGANNOTH_REX_JR_INFO = "is dropped by Dagannoth Rex, at a rate of 1/5000.";
	private static final String DAGANNOTH_SUPREME_JR_INFO = "is dropped by Dagannoth Supreme, at a rate of 1/5000.";
	private static final String DARK_CORE_INFO = "is dropped by the Corporeal Beast, at a rate of 1/5000.";
	private static final String EEK_INFO = "was obtained during the 2018 Halloween event.";
	private static final String FISHBOWL_INFO = "can be caught is in Harry's Fishing Shop";
	private static final String GENERAL_GRAARDOR_JR_INFO = "is dropped by General Graardor, at a rate of 1/5000";
	private static final String GIANT_SQUIRREL_INFO = "is obtained by training Agility.";
	private static final String HELLCAT_INFO = "is obtained by having a pet Cat hunt Hell-Rats.";
	private static final String HELLKITTEN_INFO = "is obtained by having a pet Kitten hunt Hell-Rats.";
	private static final String HELLPUPPY_INFO = "is dropped by Cerberus, at a rate of 1/3000.";
	private static final String HERBI_INFO = "is obtained by harvesting Herbiboars, at a rate of 1/6500.";
	private static final String HERON_INFO = "is obtained while training Fishing.";
	private static final String IKKLE_HYDRA_INFO = "is dropped by Alchemical Hydra, at a rate of 1/3000.";
	private static final String JALNIBREK_INFO = "is obtained by completing the inferno, at a rate of 1/100 (or 1/75 while on a TzKal-Zuk tast).";
	private static final String KALPHITE_PRINCESS_INFO = "is dropped by Kalphite Queen, at a rate of 1/3000.";
	private static final String KITTEN_INFO = "can be bought from Gertrude for 100gp, after completing the Gertrude's Cat quest.";
	private static final String KRAKEN_INFO ="is dropped by Kraken, at a rate of 1/3000.";
	private static final String KREEARRA_JR_INFO = "is dropped by Kree'arra, at a rate of 1/5000.";
	private static final String KRIL_TSUTSAROTH_JR_INFO = "is dropped by K'ril Tsutsaroth.";
	private static final String LAZY_CAT_INFO = "is obtained by letting a Wily Cat grow for about an hour.";
	private static final String LAZY_HELLCAT_INFO = "is obtained by letting a Wily Hellcat at grow for about an hour.";
	private static final String LIL_ZIK_INFO = "is obtained by completing the theater of blood, at a rate of 1/650 (with optimal performance).";
	private static final String LITTLE_NIGHTMARE_INFO = "is dropped by The Nightmare, at a rate of 1/4000 (or 1/3800 as MVP).";
	private static final String MIDNIGHT_INFO = "is obtained by causing a pet Noon to metamorphosize.";
	private static final String NOON_INFO = "is dropped by the Grotesque Guardians, at a rate of 1/3000.";
	private static final String OLMLET_INFO = "is dropped by the Great Olm, at a rate of 1/53 per received broadcasted unique item.";
	private static final String OVERGROWN_CAT_INFO = "is obtained by letting a pet Cat grow for about 2-3 hours.";
	private static final String OVERGROWN_HELLCAT_INFO = "is obtained by letting a pet Hellcat grow for about 2-3 hours.";
	private static final String PET_ROCK_INFO = "can be received from Askeladden after The Fremennik Trials.";
	private static final String PENANCE_PET_INFO = "is received from High-level gambles in Barbarian Assault, at a rate of 1/1000.";
	private static final String PHOENIX_INFO = "is obtained by opening Supply crates from the Wintertodt, at a rate of 1/5000.";
	private static final String PRINCE_BLACK_DRAGON_INFO = "is dropped by the King Black Dragon, at a rate of 1/3000.";
	private static final String RIFT_GUARDIAN_INFO = "is obtained while training Runecraft.";
	private static final String ROCK_GOLEM_INFO = "is obtained while training Mining.";
	private static final String ROCKY_INFO = "is obtained while training Thieving.";
	private static final String SCORPIAS_OFFSPRING_INFO = "is dropped by Scorpia, at a rate of 1/2,015.75";
	private static final String SKOTOS_INFO = "is dropped by Skotizo, at a rate of 1/65.";
	private static final String SMOKE_DEVIL_INFO = "is dropped by the Thermonuclear smoke devil, at a rate of 1/3000.";
	private static final String SMOLCANO_INFO = "is dropped by Zalcano, at a rate of 1/2250.";
	private static final String SNAKELING_INFO = "is dropped by Zulrah, at a rate of 1/4000.";
	private static final String SRARACHA_INFO = "is dropped by Sarachnis, at a rate of 1/3000/";
	private static final String TANGLEROOT_INFO = "is obtained while training Farming.";
	private static final String TOY_DOLL_INFO = "can be crafted in a POH with 18 Crafting and a Crafting table 3.";
	private static final String TOY_MOUSE_INFO = "can be crafted in a POH with 33 Crafting and a Crafting table 4.";
	private static final String TOY_SOLDIER_INFO = "can be crafted in a POH with 13 Crafting and a Crafting table 3.";
	private static final String TZREKJAD_INFO = "is dropped by TzTok-Jad, at a rate of 1/200 (or 1/100 if on a slayer task).";
	private static final String TZREKZUK_IHFO = "is obtained by causing a pet Jal-nib-rek to metamorphosize.";
	private static final String VENENATIS_SPIDERLING_INFO = "is dropped by Venenatis at a rate of 1/2000.";
	private static final String VETION_JR_INFO = "is dropped by Vet'ion, at a rate of 1/2000.";
	private static final String VORKI_INFO = "is dropped by Vorkath, at a rate of 1/3000.";
	private static final String WILY_CAT_INFO = "is obtained by asking Felkrash to train an Overgrown Cat, after completing the Ratcatchers quest.";
	private static final String WILY_HELLCAT_INFO = "is obtained by asking Felkrash to train an Overgrown Hellcat, after completing the Ratcatchers quest.";
	private static final String YOUNGLLEF_INFO = "is obtained by completing The Gauntlet, at a rate of 1/2000 (or 1/800 for The Corrupted Gauntlet).";
	private static final String ZILYANA_JR_INFO = "is dropped by Commander Zilyana, at a rate of 1/5000.";

	/*
	 *	Variant text
	 */
	private static final String BABY_CHINCHOMPA_GOLD = "The gold variant is obtained by causing a Baby Chinchompa to metamorphosize at a rate of 1/10000.";

	private static final String DARK_Giant_SQUIRREL = " This is a variant which is is unlocked buying the dark acorn in the hallowed sepulchre for 5000 hallowed marks and using it on a Giant Squirrel.";

	private static final String ROCKY_RACCOON = " This is the Raccoon variant of Rocky, obtained by using Redberries on any variant of Rocky.";
	private static final String ROCKY_RED_PANDA = " This is the Red Panda variant of Rocky, obtained by using Redberries on any variant of Rocky.";
	private static final String ROCKY_TANUKI = " This is the Tanuki variant of Rocky, obtained by using Poison Ivy Berries on any variant of Rocky.";

	private static final String OLMLET_CM_VARIANTS = " This is a COX Challenge Mode variant of the Olmlet. Obtained by causing any variant of the Olmlet to metamorphosize, after having used metamorphic dust on the Olmlet.";
}