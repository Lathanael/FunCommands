/*************************************************************************
 * Copyright (C) 2012 Philippe Leipold
 *
 * This file is part of FunCommands.
 *
 * FunCommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FunCommands is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FunCommands. If not, see <http://www.gnu.org/licenses/>.
 *
 **************************************************************************/

package de.Lathanael.FC.FunCommands;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import be.Balor.bukkit.AdminCmd.ACPluginManager;


/**
 * @author Lathanael (aka Philippe Leipold)
 *
 */
public class ChatCensorStrings {

	private static Set<String> strings = new HashSet<String>();

	public static void loadStrings(File file) {
		try {
			FileInputStream stream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(stream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in , "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				strings.add(line.toLowerCase());
			}
			in.close();
		} catch (FileNotFoundException e) {
			ACPluginManager.getPluginInstance("FunCommands").getLogger().info("File CensorStrings.txt was not found, make sure it is existing!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean containsString(String s) {
		return strings.contains(s.toLowerCase());
	}
}
