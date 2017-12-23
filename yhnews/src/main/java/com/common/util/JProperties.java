package com.common.util;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.util.StringUtils;


public class JProperties {
	private static Configuration configuration;
	private static String configLocation;
	private static String reloadable;
	private static String refreshDelay;

	@SuppressWarnings("static-access")
	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	@SuppressWarnings("static-access")
	public void setReloadable(String reloadable) {
		this.reloadable = reloadable;
	}

	@SuppressWarnings("static-access")
	public void setRefreshDelay(String refreshDelay) {
		this.refreshDelay = refreshDelay;
	}

	public static void loadConfiguration(String configPath, String reloadable,
			String refreshDelay) {
		if (!StringUtils.hasText(configPath)) {
			configPath = "/config/config.xml";
		}
		if (!StringUtils.hasText(reloadable)) {
			reloadable = "true";
		}
		if (!StringUtils.hasText(refreshDelay)) {
			refreshDelay = "2";
		}

		ConfigurationFactory factory = new ConfigurationFactory();

		URL url = JProperties.class.getResource(configPath);

		if (url != null)
			factory.setConfigurationURL(url);
		else
			factory.setConfigurationFileName(configPath);
		try {
			configuration = factory.getConfiguration();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		if ("true".equalsIgnoreCase(reloadable))
			setConfigurationStrategy(configuration, reloadable, refreshDelay);
	}

	private static void setConfigurationStrategy(Configuration config,
			String reloadable, String refreshDelay) {
		if ((config instanceof CompositeConfiguration)) {
			CompositeConfiguration cc = (CompositeConfiguration) config;
			for (int i = 0; i < cc.getNumberOfConfigurations(); i++) {
				if (!(cc.getConfiguration(i) instanceof FileConfiguration))
					continue;
				FileChangedReloadingStrategy strategy = null;
				if ("true".equalsIgnoreCase(reloadable))
					strategy = new FileChangedReloadingStrategy();
				if ((refreshDelay != null) && (!"".equals(refreshDelay))) {
					strategy.setRefreshDelay(Long.parseLong(refreshDelay));
				}
				((FileConfiguration) cc.getConfiguration(i))
						.setReloadingStrategy(strategy);
			}
		}
	}

	private static void initialize() {
		if (configuration == null)
			loadConfiguration(configLocation, reloadable, refreshDelay);
	}

	public static String getString(String param) {
		initialize();
		return configuration.getString(param);
	}

	public static int getInt(String param) {
		initialize();
		return configuration.getInt(param);
	}

	public static String[] getStringArray(String param) {
		initialize();
		return configuration.getStringArray(param);
	}

	public static Properties getProperties(String param) {
		initialize();
		return configuration.getProperties(param);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getList(String param) {
		initialize();
		return configuration.getList(param);
	}
}
