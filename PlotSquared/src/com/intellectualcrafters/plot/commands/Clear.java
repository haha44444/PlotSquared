/*
 * Copyright (c) IntellectualCrafters - 2014.
 * You are not allowed to distribute and/or monetize any of our intellectual property.
 * IntellectualCrafters is not affiliated with Mojang AB. Minecraft is a trademark of Mojang AB.
 *
 * >> File = Clear.java
 * >> Generated by: Citymonstret at 2014-08-09 01:41
 */

package com.intellectualcrafters.plot.commands;

import com.intellectualcrafters.plot.database.DBFunc;
import com.intellectualcrafters.plot.PlotMain;
import com.intellectualcrafters.plot.PlayerFunctions;
import com.intellectualcrafters.plot.Plot;
import org.bukkit.entity.Player;

/**
 * Created by Citymonstret on 2014-08-01.
 */
public class Clear extends SubCommand {

    public Clear() {
        super(Command.CLEAR, "Clear a plot", "clear", CommandCategory.ACTIONS);
    }
    @Override
    public boolean execute(Player plr, String ... args) {
        if(!PlayerFunctions.isInPlot(plr)) {
            PlayerFunctions.sendMessage(plr, "You're not in a plot.");
            return true;
        }
        Plot plot = PlayerFunctions.getCurrentPlot(plr);
        boolean result = PlotMain.removePlot(plr.getWorld().getName(), plot.id);
        if (result) {
            DBFunc.delete(plr.getWorld().getName(), plot);
            plot.clear(plr);
        }
        else {
            PlayerFunctions.sendMessage(plr, "Plot clearing has been denied.");
        }
        return true;
    }
}
