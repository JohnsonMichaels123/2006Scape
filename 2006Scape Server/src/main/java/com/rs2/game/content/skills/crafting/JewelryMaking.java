package com.rs2.game.content.skills.crafting;

import com.rs2.GameConstants;
import com.rs2.game.items.DeprecatedItems;
import com.rs2.game.players.Player;

public class JewelryMaking {

	private final static int[][] RINGS = { // Ring, Gem, Level, XP
	{ 1635, -1, 5, 15 }, { 1637, 1607, 20, 40 }, { 1639, 1605, 27, 55 },
			{ 1641, 1603, 34, 70 }, { 1643, 1601, 43, 85 },
			{ 1645, 1615, 55, 100 }, { 6575, 6573, 67, 115 } };
	private final static int[][] NECKLACES = { { 1654, -1, 6, 20 },
			{ 1656, 1607, 22, 55 }, { 1658, 1605, 29, 60 },
			{ 1660, 1603, 40, 75 }, { 1662, 1601, 56, 90 },
			{ 1664, 1615, 72, 105 }, { 6577, 6573, 82, 120 } };
	private static int[][] AMULETS = { { 1673, -1, 8, 30 },
			{ 1675, 1607, 24, 65 }, { 1677, 1605, 31, 70 },
			{ 1679, 1603, 50, 85 }, { 1681, 1601, 70, 100 },
			{ 1683, 1615, 80, 150 }, { 6579, 6573, 90, 165 } };

	private final static int[][] MOULD_INTERFACE_IDS = {
	/* Rings */
	{ 1635, 1637, 1639, 1641, 1643, 1645, 6575 },
	/* Neclece */
	{ 1654, 1656, 1658, 1660, 1662, 1664, 6577 },
	/* amulet */
	{ 1673, 1675, 1677, 1679, 1681, 1683, 6579 }

	};

	public static void mouldInterface(Player c) {
		c.getPacketSender().showInterface(4161);
		/* Rings */
		if (c.getItemAssistant().playerHasItem(1592, 1)) {
			for (int i = 0; i < MOULD_INTERFACE_IDS[0].length; i++) {
				c.getPacketSender().sendFrame34(MOULD_INTERFACE_IDS[0][i],
						i, 4233, 1);
			}
			c.getPacketSender().sendFrame34(1643, 4, 4233, 1);
			c.getPacketSender().sendString("", 4230);
			c.getPacketSender().sendFrame246(4229, 0, -1);
		} else {
			c.getPacketSender().sendFrame246(4229, 120, 1592);
			for (int i = 0; i < MOULD_INTERFACE_IDS[0].length; i++) {
				c.getPacketSender().sendFrame34(-1, i, 4233, 1);
			}
			c.getPacketSender().sendString(
					"You need a ring mould to craft rings.", 4230);
		}
		/* Necklace */
		if (c.getItemAssistant().playerHasItem(1597, 1)) {
			for (int i = 0; i < MOULD_INTERFACE_IDS[1].length; i++) {
				c.getPacketSender().sendFrame34(MOULD_INTERFACE_IDS[1][i],
						i, 4239, 1);
			}
			c.getPacketSender().sendFrame34(1662, 4, 4239, 1);
			c.getPacketSender().sendFrame246(4235, 0, -1);
			c.getPacketSender().sendString("", 4236);
		} else {
			c.getPacketSender().sendFrame246(4235, 120, 1597);
			c.getPacketSender().sendString("You need a necklace mould to craft necklaces", 4236);
			for (int i = 0; i < MOULD_INTERFACE_IDS[1].length; i++) {
				c.getPacketSender().sendFrame34(-1, i, 4239, 1);
			}
		}
		/* Amulets */
		if (c.getItemAssistant().playerHasItem(1595, 1)) {
			for (int i = 0; i < MOULD_INTERFACE_IDS[2].length; i++) {
				c.getPacketSender().sendFrame34(MOULD_INTERFACE_IDS[2][i],
						i, 4245, 1);
			}
			c.getPacketSender().sendFrame34(1681, 4, 4245, 1);
			c.getPacketSender().sendFrame246(4241, 0, -1);
			c.getPacketSender().sendString("", 4242);
		} else {
			c.getPacketSender().sendFrame246(4241, 120, 1595);
			c.getPacketSender().sendString(
					"You need an amulet mould to craft necklaces", 4242);
			for (int i = 0; i < MOULD_INTERFACE_IDS[2].length; i++) {
				c.getPacketSender().sendFrame34(-1, i, 4245, 1);
			}
		}
	}

	public static void stringAmulet(final Player c, final int itemUsed, final int usedWith) {
		final int amuletId = itemUsed == 1759 ? usedWith : itemUsed;
		for (final CraftingData.amuletData a : CraftingData.amuletData.values()) {
			if (amuletId == a.getAmuletId()) {
				c.getItemAssistant().deleteItem(1759, 1);
				c.getItemAssistant().deleteItem(amuletId, 1);
				c.getItemAssistant().addItem(a.getProduct(), 1);
				c.getPlayerAssistant().addSkillXP(4, 12);
			}
		}
	}

	public static void mouldItem(Player player, int item, int amount) {
		int done = 0;

		final int GOLD_BAR = 2357;

		boolean isRing = false;
		boolean isNeck = false;
		boolean isAmulet = false;
		int gem = 0;
		int itemAdd = -1;
		int xp = 0;
		int lvl = 1;
		for (int i = 0; i < 7; i++) {
			if (item == RINGS[i][0]) {
				isRing = true;
				itemAdd = RINGS[i][0];
				gem = RINGS[i][1];
				lvl = RINGS[i][2];
				xp = RINGS[i][3];
				break;
			}
			if (item == NECKLACES[i][0]) {
				isNeck = true;
				itemAdd = NECKLACES[i][0];
				gem = NECKLACES[i][1];
				lvl = NECKLACES[i][2];
				xp = NECKLACES[i][3];
				break;
			}
			if (item == AMULETS[i][0]) {
				isAmulet = true;
				itemAdd = AMULETS[i][0];
				gem = AMULETS[i][1];
				lvl = AMULETS[i][2];
				xp = AMULETS[i][3];
				break;
			}
		}
		if (!isRing && !isNeck && !isAmulet) {
			return;
		}
		if (player.playerLevel[GameConstants.CRAFTING] >= lvl) {
			if (DeprecatedItems.getItemName(itemAdd).toLowerCase().contains("gold")
					&& !player.getItemAssistant().playerHasItem(GOLD_BAR, 1)
					|| !player.getItemAssistant().playerHasItem(GOLD_BAR, 1)) {
				player.getPacketSender().sendMessage("You need a Gold bar to make this.");
				return;
			} else if (!player.getItemAssistant().playerHasItem(gem, 1)
					&& player.getItemAssistant().playerHasItem(GOLD_BAR, 1)) {
				player.getPacketSender().sendMessage(getRequiredMessage(DeprecatedItems.getItemName(gem)));
				return;
			}
			player.getPacketSender().closeAllWindows();
			while (done < amount
					&& (DeprecatedItems.getItemName(gem).toLowerCase()
							.contains("unarmed")
							&& player.getItemAssistant().playerHasItem(GOLD_BAR, 1) || player
							.getItemAssistant().playerHasItem(gem, 1)
							&& player.getItemAssistant().playerHasItem(GOLD_BAR, 1))) {
				player.getItemAssistant().deleteItem(gem, 1);
				player.getItemAssistant().deleteItem(GOLD_BAR, 1);
				player.getItemAssistant().addItem(itemAdd, 1);
				player.getPlayerAssistant().addSkillXP(xp, GameConstants.CRAFTING);
				player.getPlayerAssistant().refreshSkill(GameConstants.CRAFTING);
				done++;
			}
			if (done == 1) {
				player.getPacketSender().sendMessage("You craft the gold to form a " + DeprecatedItems.getItemName(itemAdd) + ".");
			} else if (done > 1) {
				player.getPacketSender().sendMessage(
						"You craft the gold to form " + done
								+ " " + DeprecatedItems.getItemName(itemAdd)
								+ "'s.");
			}
		} else {
			player.getPacketSender().sendMessage("You need a Crafting level of " + lvl + " to craft this.");
			return;
		}
	}

	public static String getRequiredMessage(String item) {
		if (item.startsWith("A") || item.startsWith("E")
				|| item.startsWith("I") || item.startsWith("O")
				|| item.startsWith("U")) {
			return "You need a Gold bar and an " + item + " to make this.";
		} else {
			return "You need a Gold bar and a " + item + " to make this.";
		}
	}
}
