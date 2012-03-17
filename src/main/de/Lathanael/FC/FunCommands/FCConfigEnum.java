/************************************************************************
 * This file is part of FunCommands.
 *
 * FunCommands is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ExamplePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FunCommands.  If not, see <http://www.gnu.org/licenses/>.
 ************************************************************************/

package de.Lathanael.FC.FunCommands;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * @author Lathanael
 * @author Balor
 *
 */
public enum FCConfigEnum {

	SLAP_NP("Slap.normalPower", 1.1, "Slap power without flag"),
	SLAP_NH("Slap.normalHeight", 0, "Slap height without flag"),
	SLAP_HP("Slap.hPower", 2, "Slap power with flag h"),
	SLAP_HH("Slap.hHeight", 0.5, "Slap height with flag h"),
	SLAP_VP("Slap.vPower", 3, "Slap power with flag v"),
	SLAP_VH("Slap.vHeight", 1, "Slap height with flag v"),
	ROCKET_NP("Rocket.normalPower", 1.5, "Rocket height without flag"),
	ROCKET_FP("Rocket.flagPower", 5, "Rocket height with flag"),
	PERS_NAMES("PersistentNames", false, "If set to true, name changes done with"
			+ "/attaint are persistent."),
	CENSOR("ChatCensor", false, "If set to true, The chat will we searched for the"
			+ "strings defined in the ChatSenorStrings.txt. If one is found, the player"
			+ "will be killed.");

	private static ConfigurationSection pluginConfig;
	private static String pluginVersion;
	private static String pluginName;
	private final String path, desc;
	private final Object value;

	private FCConfigEnum(String path, Object value, String desc) {
		this.path = path;
		this.desc = desc;
		this.value = value;
	}

	public String getString() {
		return pluginConfig.getString(path);
	}


	public int getInt() {
		return pluginConfig.getInt(path);
	}

	public double getDouble() {
		return pluginConfig.getDouble(path);
	}

	public boolean getBoolean() {
		return pluginConfig.getBoolean(path);
	}

	public long getLong() {
		return pluginConfig.getLong(path);
	}

	public float getFloat() {
		return Float.parseFloat(pluginConfig.getString(path));
	}

	public List<String> getStringList() {
		return pluginConfig.getStringList(path);
	}

	public List<Integer> getIntList() {
		return pluginConfig.getIntegerList(path);
	}

	/**
	 * @return the defaultvalues
	 */
	public static Map<String, Object> getDefaultvalues() {
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		for (FCConfigEnum ce : values())
			values.put(ce.path, ce.value);
		return values;
	}

	public static String getHeader() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("pluginConfiguration file of ").append(pluginName).append('\n');
		buffer.append("Plugin Version: ").append(pluginVersion).append('\n').append('\n');
		for (FCConfigEnum ce : values())
			buffer.append(ce.path).append("\t:\t").append(ce.desc).append(" (Default : ")
					.append(ce.value).append(')').append('\n');
		return buffer.toString();
	}

	/**
	 * @param pluginConfig
	 *            the pluginConfig to set
	 */
	public static void setPluginConfig(ConfigurationSection config) {
		FCConfigEnum.pluginConfig = config;
	}

	public static void setPluginInfos(PluginDescriptionFile pdf) {
		FCConfigEnum.pluginVersion = pdf.getVersion();
		FCConfigEnum.pluginName = pdf.getName();
	}
}